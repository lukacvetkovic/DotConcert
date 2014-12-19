package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.Constants;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.GetRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.JavaJsonHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.Models.Koncert;
import dotconcert.ppp.fer.hr.com.dotconcert.R;


/**
 * Created by Cveki on 27.11.2014..
 */
public class PrikaziSveKoncerteNaMapi extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener {


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Bundle extras;
    String s;
    JSONObject jsonData;
    String dataFromServer;
    GetRestService dohvatiSaServera;
    double longitude = 0, latitude = 0;
    Koncert[] poljeKoncerata;
    HashMap<Marker,Koncert> markerKey = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dohvatiSaServera = new GetRestService();
        dohvatiSaServera.setAdresaSKojeUziamam(Constants.dohvatiSveKoncerteURL);
        try {
            s=dohvatiSaServera.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_maps);

        try {
            setUpMapIfNeeded();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            setUpMapIfNeeded();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() throws ExecutionException, InterruptedException {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            mMap.setOnInfoWindowClickListener(this);

        }



        List<Koncert> listaKoncerata = new ArrayList<Koncert>();

        try {
            Koncert[] sviKoncerti = JavaJsonHelper.izStringaNapraviKoncerte(s);
            for (Koncert instanca : sviKoncerti) {
                Log.d("KONCERT", "--------" + instanca.getNaziv());
                listaKoncerata.add(instanca);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (Koncert koncert : listaKoncerata) {
            dohvatiSaServera = new GetRestService();
            dohvatiSaServera.setAdresaSKojeUziamam(Constants.dohvatiLokacijuOdredenogKoncertaURL + String.valueOf(koncert.getIdLokacija()));

            String dataFromServer=dohvatiSaServera.execute().get();
            Log.d("LOKACIJA KONCERTA", dataFromServer);
            try {
                jsonData = JavaJsonHelper.izStringaNapraviJson(dataFromServer);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Json", jsonData.toString());


            String izvadiIzJsona = null;
            try {
                izvadiIzJsona = jsonData.getString("longitude");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            String[] pomPolje = izvadiIzJsona.split(",");
            try{
                izvadiIzJsona = pomPolje[0] + "." + pomPolje[1];
            }
            catch (Exception e){
                continue;
            }

            longitude = Double.valueOf(izvadiIzJsona);

            try {
                izvadiIzJsona = jsonData.getString("latitude");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            pomPolje = izvadiIzJsona.split(",");
            izvadiIzJsona = pomPolje[0] + "." + pomPolje[1];
            latitude = Double.valueOf(izvadiIzJsona);

            Log.d("latitudeDOUBLE", String.valueOf(latitude));
            Log.d("longitudeDOUBLE", String.valueOf(longitude));

            if (mMap != null && latitude != 0 && longitude != 0) {
                Marker newMarker=mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(koncert.getNaziv()));
                markerKey.put(newMarker,koncert);
            }

        }


    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {


    }

    public void updateStanje(String data) {

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle koncertBundle= new Bundle();
        Class cls = DetaljKoncerti.class;
        Intent intent = new Intent(PrikaziSveKoncerteNaMapi.this, cls);


        Koncert koncert=markerKey.get(marker);
        /*
         *TODO moras dodat bundle za cijeli koncert
         */

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
        intent.putExtras(koncertBundle);
        startActivity(intent);
    }
}

