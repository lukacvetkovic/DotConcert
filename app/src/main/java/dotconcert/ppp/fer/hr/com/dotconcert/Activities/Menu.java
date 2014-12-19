package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Cveki on 30.10.2014..
 */
public class Menu extends ListActivity{
    String classes[]= {"MapsActivity","AddMarker","PopisKoncerata","TestDrugi","PrikaziSveKoncerteNaMapi","DodajNoviKoncert"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese= classes[position];
        Class ourClass = null;
        try {
            ourClass = Class.forName("dotconcert.ppp.fer.hr.com.dotconcert.Activities."+cheese);
            Intent ourIntent= new Intent(Menu.this,ourClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
