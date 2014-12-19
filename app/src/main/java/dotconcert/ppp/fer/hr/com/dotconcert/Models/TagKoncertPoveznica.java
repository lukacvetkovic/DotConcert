package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 11.11.2014..
 */
public class TagKoncertPoveznica {
    private int idTagKoncertPoveznica;
    private int itTag;
    private int idKoncert;

    public TagKoncertPoveznica(int idTagKoncertPoveznica, int itTag, int idKoncert) {
        this.idTagKoncertPoveznica = idTagKoncertPoveznica;
        this.itTag = itTag;
        this.idKoncert = idKoncert;
    }

    public int getIdTagKoncertPoveznica() {
        return idTagKoncertPoveznica;
    }

    public int getItTag() {
        return itTag;
    }

    public int getIdKoncert() {
        return idKoncert;
    }
}
