package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.JavaJsonHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.Models.Koncert;

/**
 * Created by Cveki on 14.11.2014..
 */
public class PopisKoncerata extends ListActivity {

    String koncerti[] = {"DetaljKoncerti"};
    List<Koncert> listaKoncerata = new ArrayList<Koncert>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LongRunningGetIO().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Class ourClass = null;
        try {
            ourClass = Class.forName("dotconcert.ppp.fer.hr.com.dotconcert.Activities." + "DetaljKoncerti");
            Intent ourIntent = new Intent(PopisKoncerata.this, ourClass);
            Bundle koncertBundle = new Bundle();
            addKoncertToKoncertBundle(listaKoncerata.get(position), koncertBundle);
            ourIntent.putExtras(koncertBundle);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void addKoncertToKoncertBundle(Koncert koncert, Bundle koncertBundle) {

        koncertBundle.putString("brojKarata", koncert.getBrojKarata());
        koncertBundle.putString("webIzvodjac", koncert.getWebIzvodjac());
        koncertBundle.putString("vrijemeOdrzavanja", koncert.getVrijemeOdrzavanja());
        koncertBundle.putString("naziv", koncert.getNaziv());
        koncertBundle.putString("izvodjac", koncert.getIzvodjac());
        koncertBundle.putString("dodatneInformacije", koncert.getDodatneInformacije());
        koncertBundle.putInt("status", koncert.getStatus());
        koncertBundle.putInt("idLokacija", koncert.getIdLokacija());
        koncertBundle.putInt("idKorisnik", koncert.getIdKorisnik());
        koncertBundle.putInt("idKoncert",koncert.getIdKoncert());


    }


    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();
            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://rppp.fer.hr/2014/Apps/WCFServisApi/DotConcertApi.svc/rest/dohvatiSveKoncerte");
            httpGet.addHeader("accept", "application/json");
            String text = null;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text;
        }

        protected void onPostExecute(String results) {
            try {
                Koncert[] sviKoncerti = JavaJsonHelper.izStringaNapraviKoncerte(results);
                for (Koncert instanca : sviKoncerti) {
                   // Log.d("KONCERT", "--------" + instanca.getNaziv());
                    listaKoncerata.add(instanca);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            koncerti = new String[listaKoncerata.size()];

            for (int i = 0; i < listaKoncerata.size(); i++) {
                koncerti[i] = listaKoncerata.get(i).getNaziv();
            }
            setListAdapter(new ArrayAdapter<String>(PopisKoncerata.this, android.R.layout.simple_list_item_1, koncerti));
        }
    }
}
