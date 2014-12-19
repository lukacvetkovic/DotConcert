package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Mjesto {
    private int idMjesto;
    private String nazivMjesta;
    private int pbr;
    private int idDrzava;

    public Mjesto(int idMjesto, String nazivMjesta, int pbr, int idDrzava) {
        this.idMjesto = idMjesto;
        this.nazivMjesta = nazivMjesta;
        this.pbr = pbr;
        this.idDrzava = idDrzava;
    }

    public int getIdMjesto() {
        return idMjesto;
    }

    public String getNazivMjesta() {
        return nazivMjesta;
    }

    public int getPbr() {
        return pbr;
    }

    public int getIdDrzava() {
        return idDrzava;
    }
}
