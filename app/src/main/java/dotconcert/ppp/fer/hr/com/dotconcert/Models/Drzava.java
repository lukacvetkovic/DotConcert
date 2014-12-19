package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Drzava {
    private int idDrzava;
    private String imeDrzava;

    public Drzava(int idDrzava, String imeDrzava) {
        this.idDrzava = idDrzava;
        this.imeDrzava = imeDrzava;
    }

    public int getIdDrzava() {
        return idDrzava;
    }

    public void setIdDrzava(int idDrzava) {
        this.idDrzava = idDrzava;
    }

    public String getImeDrzava() {
        return imeDrzava;
    }

    public void setImeDrzava(String imeDrzava) {
        this.imeDrzava = imeDrzava;
    }
}
