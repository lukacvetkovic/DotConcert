package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.Constants;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.GetRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.JavaJsonHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.R;

/**
 * Created by Cveki on 16.11.2014..
 */
public class PokaziKoncertNaKarti extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    Bundle extras;
    JSONObject jsonData;
    String dataFromServer;
    GetRestService dohvatiLokaciju;
    double longitude=0, latitude=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        dohvatiLokaciju= new GetRestService();
        dohvatiLokaciju.setAdresaSKojeUziamam(Constants.dohvatiLokacijuOdredenogKoncertaURL+String.valueOf(extras.getInt("idLokacija")));
        try {
            dataFromServer=dohvatiLokaciju.execute().get();
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

        }
        Log.d("LOKACIJA KONCERTA",dataFromServer);
        try {
            jsonData= JavaJsonHelper.izStringaNapraviJson(dataFromServer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Json",jsonData.toString());


        String izvadiIzJsona = null;
        try {
            izvadiIzJsona=jsonData.getString("longitude");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[]pomPolje=izvadiIzJsona.split(",");
        izvadiIzJsona=pomPolje[0]+"."+pomPolje[1];
        longitude=Double.valueOf(izvadiIzJsona);

        try {
            izvadiIzJsona=jsonData.getString("latitude");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        pomPolje=izvadiIzJsona.split(",");
        izvadiIzJsona=pomPolje[0]+"."+pomPolje[1];
        latitude=Double.valueOf(izvadiIzJsona);

        Log.d("latitudeDOUBLE",String.valueOf(latitude));
        Log.d("longitudeDOUBLE",String.valueOf(longitude));

        if (mMap != null && latitude!=0 && longitude != 0) {
            setUpMap();
        }





    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng marker = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(extras.getString("naziv")));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));

    }
}