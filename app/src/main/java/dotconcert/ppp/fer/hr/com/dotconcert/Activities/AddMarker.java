package dotconcert.ppp.fer.hr.com.dotconcert.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dotconcert.ppp.fer.hr.com.dotconcert.R;

/**
 * Created by Cveki on 30.10.2014..
 */
public class AddMarker extends Activity implements View.OnClickListener {
    Button primijeni;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmarker);
        primijeni=(Button) findViewById(R.id.bPrimijeni);
        primijeni.setOnClickListener(this);
    }

    /**
     * On click listener
     * @param v
     */
    @Override
    public void onClick(View v) {
            System.out.print("Nekaj");
    }
}
