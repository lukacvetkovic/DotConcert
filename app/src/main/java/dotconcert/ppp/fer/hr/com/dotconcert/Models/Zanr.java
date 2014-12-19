package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Zanr {

    private String imeZanra;
    private int idZanr;

    public Zanr(String imeZanra, int idZanr) {
        this.imeZanra = imeZanra;
        this.idZanr = idZanr;
    }

    public String getImeZanra() {
        return imeZanra;
    }

    public int getIdZanr() {
        return idZanr;
    }
}
