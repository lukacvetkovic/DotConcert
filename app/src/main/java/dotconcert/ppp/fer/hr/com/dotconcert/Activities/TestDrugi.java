package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.Constants;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.GetRestService;
import dotconcert.ppp.fer.hr.com.dotconcert.Helpers.JavaJsonHelper;
import dotconcert.ppp.fer.hr.com.dotconcert.Models.Koncert;
import dotconcert.ppp.fer.hr.com.dotconcert.R;


/**
 * Created by Cveki on 9.11.2014..
 */
public class TestDrugi extends Activity implements View.OnClickListener {

    GetRestService dohvati = new GetRestService();
    String s;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findViewById(R.id.my_button).setOnClickListener(this);
        dohvati.dohvati(Constants.dohvatiSveKoncerteURL);

    }

    @Override
    public void onClick(View v) {
        String s ;
        String imena;
        EditText et = (EditText) findViewById(R.id.my_edit);
        s=dohvati.getData();
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
        if(!listaKoncerata.isEmpty()){
            imena=listaKoncerata.get(0).getNaziv();
            for (int i=1;i<listaKoncerata.size();i++) {
                imena += listaKoncerata.get(i).getNaziv();
                imena += '\n';
            }
            et.setText(imena);
        }
        else{
            et.setText("NE RADI");
        }



    }
}







