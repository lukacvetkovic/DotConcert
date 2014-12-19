package dotconcert.ppp.fer.hr.com.dotconcert.Helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dotconcert.ppp.fer.hr.com.dotconcert.Models.Koncert;


/**
 * Created by Cveki on 9.11.2014..
 */
public class JavaJsonHelper {

    /*{
        "brojKarata":"String content",
            "dodatneInformacije":"String content",
            "idKoncert":2147483647,
            "idKorisnik":2147483647,
            "idLokacija":2147483647,
            "izvodjac":"String content",
            "naziv":"String content",
            "status":2147483647,
            "vrijemeOdrzavanja":"String content",
            "webIzvodjac":"String content"
    }*/

    //{"phonetype":"N95","cat":"WP"}
    //JSONObject jsonObj = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");

    public static Koncert[] izStringaNapraviKoncerte(String koncerti) throws JSONException {

        koncerti = "{ \"koncerti\": " + koncerti + " }";

        JSONArray jsonKoncerti = new JSONObject(koncerti).getJSONArray("koncerti");
        for (int i = 0; i < jsonKoncerti.length(); i++) {
            JSONObject koncert = jsonKoncerti.getJSONObject(i);
            //Log.d("MOJE", i + "----" + koncert);
        }
        Koncert[] sviKoncerti = new Koncert[jsonKoncerti.length()];

        for (int i = 0; i < jsonKoncerti.length(); i++) {
            sviKoncerti[i] = izJsonaNapraviKoncert(jsonKoncerti.getJSONObject(i));
        }

        return sviKoncerti;
    }

    public static JSONObject izStringaNapraviJson(String string) throws JSONException {
        JSONObject jsonObject= new JSONObject(string);
        return  jsonObject;
    }


    public static JSONObject izKoncertaNapraviJson(Koncert koncert) throws IOException, JSONException {

        JSONObject novi = new JSONObject();
        novi.put("brojKarata", koncert.getBrojKarata());
        novi.put("dodatneInformacije", koncert.getDodatneInformacije());
        novi.put("idKoncert", koncert.getIdKoncert());
        novi.put("idKorisnik", koncert.getIdKorisnik());
        novi.put("idLokacija", koncert.getIdLokacija());
        novi.put("izvodjac", koncert.getIzvodjac());
        novi.put("status", koncert.getStatus());
        novi.put("vrijemeOdrzavanja", koncert.getVrijemeOdrzavanja());
        novi.put("webIzvodjac", koncert.getWebIzvodjac());

        return novi;

    }

    public static Koncert izJsonaNapraviKoncert(JSONObject json) throws JSONException {


        String brojKarata = json.getString("brojKarata");
        String dodatneInformacije = json.getString("dodatneInformacije");
        String naziv = json.getString("naziv");
        int idKoncert = json.getInt("idKoncert");
        int idKorisnik = json.getInt("idKorisnik");
        int idLokacija = json.getInt("idLokacija");
        String izvodjac = json.getString("izvodjac");
        int status = json.getInt("status");
        String vrijemeOdrzavanja = json.getString("vrijemeOdrzavanja");
        String webIzvodjac = json.getString("webIzvodjac");

        Koncert novi = new Koncert(idKoncert, naziv, izvodjac, webIzvodjac, vrijemeOdrzavanja, dodatneInformacije, brojKarata, status, idLokacija, idKoncert);
        //Log.d("MOJE", "----" + novi.getNaziv());
        return novi;

    }
}
