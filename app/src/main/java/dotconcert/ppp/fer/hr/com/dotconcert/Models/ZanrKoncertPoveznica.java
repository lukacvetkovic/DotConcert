package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 11.11.2014..
 */
public class ZanrKoncertPoveznica {

    private int idZanrKoncertPoveznica;
    private int idZanr;
    private int idKoncert;

    public ZanrKoncertPoveznica(int idZanrKoncertPoveznica, int idZanr, int idKoncert) {
        this.idZanrKoncertPoveznica = idZanrKoncertPoveznica;
        this.idZanr = idZanr;
        this.idKoncert = idKoncert;
    }

    public int getIdZanrKoncertPoveznica() {
        return idZanrKoncertPoveznica;
    }

    public int getIdZanr() {
        return idZanr;
    }

    public int getIdKoncert() {
        return idKoncert;
    }
}
