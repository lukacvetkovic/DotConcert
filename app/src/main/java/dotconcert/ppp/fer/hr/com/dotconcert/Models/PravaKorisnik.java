package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class PravaKorisnik {

    private String razinaPrava;
    private String nazivPrava;

    public PravaKorisnik(String razinaPrava, String nazivPrava) {
        this.razinaPrava = razinaPrava;
        this.nazivPrava = nazivPrava;
    }

    public String getRazinaPrava() {
        return razinaPrava;
    }

    public void setRazinaPrava(String razinaPrava) {
        this.razinaPrava = razinaPrava;
    }

    public String getNazivPrava() {
        return nazivPrava;
    }

    public void setNazivPrava(String nazivPrava) {
        this.nazivPrava = nazivPrava;
    }
}
