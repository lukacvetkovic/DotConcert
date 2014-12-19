package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.Constants;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.GeoPointHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.GetRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.JavaJsonHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.PostRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.R;

/**
 * Created by Cveki on 27.11.2014..
 */
public class DodajNoviKoncert extends FragmentActivity implements View.OnClickListener {

    private GoogleMap mMap;
    EditText naziv;
    EditText izvodac;
    EditText webIzvodac;
    EditText vrijemeOdzavanja;
    EditText dodatneInformacije;
    EditText brojKarata;
    EditText ulicaBroj;
    EditText grad;
    EditText drzava;
    Button dodaj;
    Button lokacija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dodavanje_koncerta);
        setUpMapIfNeeded();
        referenciraj();

    }



        private void setUpMapIfNeeded(){
            // Do a null check to confirm that we have not already instantiated the map.
            if (mMap == null) {
                // Try to obtain the map from the SupportMapFragment.


                }
            }


    private void referenciraj() {
        naziv=(EditText) findViewById(R.id.etNaziv);
        izvodac=(EditText)findViewById(R.id.etNazivIzvodac);
        webIzvodac=(EditText)findViewById(R.id.etNazivWebIzvodaca);
        vrijemeOdzavanja=(EditText)findViewById(R.id.etVrijeme);
        dodatneInformacije=(EditText)findViewById(R.id.etDodatneInf);
        brojKarata=(EditText)findViewById(R.id.etBrKarata);
        ulicaBroj=(EditText)findViewById(R.id.etUlicaBroj);
        grad=(EditText)findViewById(R.id.etGrad);
        drzava=(EditText)findViewById(R.id.etDrzava);
        dodaj=(Button)findViewById(R.id.bDodaj);
        dodaj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("DOSAO SAM NA POCETAK","WEEEEEEE");
        LatLng pozicija= GeoPointHelper.getLocationFromAddress(ulicaBroj.getText()+","+grad.getText()+","+drzava.getText(),this);

        if(pozicija!=null){
            PostRestService postRestService = new PostRestService();

            Log.d("DOBIO SAM KOORDINATE",pozicija.toString());

            postRestService.setAdresaNaKojuPostam(Constants.dodajLokacijuUrl);
            JSONObject koncert = new JSONObject();
            String rezultat = null;

            GetRestService getRestService = new GetRestService();
            getRestService.setAdresaSKojeUziamam(Constants.dohvatiSveDrzave);
            String drzave = null;
            try {
                drzave=getRestService.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            JSONObject drzaveJSON = null;
            try {
                drzaveJSON= JavaJsonHelper.izStringaNapraviJson(drzave);
            } catch (JSONException e) {
                e.printStackTrace();
            }



            Log.d("Drzave:",drzaveJSON.toString());




            /**
             * OVO nije impl
             */

            try {

               koncert.put("adresa", ulicaBroj.getText());
               koncert.put("email", "email@email.com");
               koncert.put("idKorisnik", 0);
               koncert.put("idLokacija", 0);
               koncert.put("idMjesto", 0);
               koncert.put("latitude", String.valueOf(pozicija.latitude));
               koncert.put("longitude", String.valueOf(pozicija.longitude));
               koncert.put("naziv", naziv.getText());
               koncert.put("telefon", "Nekibroj");
               koncert.put("web", webIzvodac.getText());
           }
           catch (Exception e){
               Log.d("NEKAJ NE VALJA","prilikom radnje JSONA");
           }

            postRestService.setNoviObjekt(koncert);

            try {
               rezultat =postRestService.execute().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            /*try {
                JSONObject noviKoncert=JavaJsonHelper.izStringaNapraviJson(rezultat);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/


            Log.d("NA IZLAZ SAM DOBIO:",rezultat);

        }

        Log.d("NE VALJA","UOPCE");
    }
}
