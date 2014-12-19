package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.Constants;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.DeleteRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.R;

/**
 * Created by Cveki on 14.11.2014..
 */
public class DetaljKoncerti extends Activity{
    TextView naziv;
    TextView izvodjac;
    TextView webIzvodjac;
    TextView vrijemeOdrzavanja;
    TextView dodatneInformacije;
    TextView brojKarata;
    Button prikaziNaMaps;
    Button obrisiKoncert;
    Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalji);
        extras = getIntent().getExtras();
        referenceAllInstances();
        putDataToEt();

    }


    private void referenceAllInstances() {
        naziv = (TextView) findViewById(R.id.tvNaziv);
        izvodjac = (TextView) findViewById(R.id.tvIzvodac);
        webIzvodjac = (TextView) findViewById(R.id.tvWebIzvodjac);
        vrijemeOdrzavanja = (TextView) findViewById(R.id.tvVrijemeOdrzavanja);
        dodatneInformacije = (TextView) findViewById(R.id.tvDodatneInformacije);
        brojKarata = (TextView) findViewById(R.id.tvBrojKarata);
        obrisiKoncert=(Button)findViewById(R.id.bObrisi);
        obrisiKoncert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRestService obrisi= new DeleteRestService();
                obrisi.obrisi(Constants.obrisiKoncertPomocuID+String.valueOf(extras.getInt("idKoncert")));

                Class ourClass = null;
                try {
                    ourClass = Class.forName("dotconcert.ppp.fer.hr.com.dotconcert.Activities." + "PopisKoncerata");
                    Intent ourIntent = new Intent(DetaljKoncerti.this, ourClass);
                    startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        prikaziNaMaps = (Button) findViewById(R.id.bPrikaziNaMapi);
        prikaziNaMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class ourClass = null;
                try {
                    ourClass = Class.forName("dotconcert.ppp.fer.hr.com.dotconcert.Activities." + "PokaziKoncertNaKarti");
                    Intent ourIntent = new Intent(DetaljKoncerti.this, ourClass);
                    ourIntent.putExtras(extras);
                    startActivity(ourIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void putDataToEt() {
        naziv.setText(extras.getString("naziv"));
        brojKarata.setText(extras.getString("brojKarata"));
        webIzvodjac.setText(extras.getString("webIzvodjac"));
        vrijemeOdrzavanja.setText(extras.getString("vrijemeOdrzavanja"));
        izvodjac.setText(extras.getString("izvodjac"));
        dodatneInformacije.setText(extras.getString("dodatneInformacije"));
    }


}
