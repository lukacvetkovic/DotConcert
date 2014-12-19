package dotconcert.ppp.fer.hr.com.dotconcert.Helpers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by Cveki on 13.12.2014..
 */
public class GeoPointHelper{


    public static LatLng getLocationFromAddress(String strAddress,Context context ) {
        LatLng pos=null;

        Geocoder geoCoder = new Geocoder(context);

        try {
            List<Address> addresses =
                    geoCoder.getFromLocationName(strAddress, 1);
            if (addresses.size() >  0) {
                pos = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
            }

        } catch (IOException e) { // TODO Auto-generated catch block
            e.printStackTrace(); }

        return pos;

    }
}
