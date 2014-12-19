package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Koncert {

    /**
     * Json tip koncerta
     * {
     * //"brojKarata":"String content",
     * //"dodatneInformacije":"String content",
     * "idKoncert":2147483647,
     * "idKorisnik":2147483647,
     * "idLokacija":2147483647,
     * //"izvodjac":"String content",
     * //"naziv":"String content",
     * "status":2147483647,
     * "vrijemeOdrzavanja":"String content",
     * "webIzvodjac":"String content"
     * <p/>
     * kada radim novi koncert idKoncert=0,
     * }
     */

    private int idKoncert;
    private String naziv;
    private String izvodjac;
    private String webIzvodjac;
    private String vrijemeOdrzavanja;
    private String dodatneInformacije;
    private String brojKarata;
    private int status;
    private int idLokacija;
    private int idKorisnik;


    public Koncert(int idKoncert, String naziv, String izvodjac, String webIzvodjac, String vrijemeOdrzavanja, String dodatneInformacije, String brojKarata, int status, int idLokacija, int idKorisnik) {
        this.idKoncert = idKoncert;
        this.naziv = naziv;
        this.izvodjac = izvodjac;
        this.webIzvodjac = webIzvodjac;
        this.vrijemeOdrzavanja = vrijemeOdrzavanja;
        this.dodatneInformacije = dodatneInformacije;
        this.brojKarata = brojKarata;
        this.status = status;
        this.idLokacija = idLokacija;
        this.idKorisnik = idKorisnik;
    }

    public int getIdKoncert() {
        return idKoncert;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getIzvodjac() {
        return izvodjac;
    }

    public String getWebIzvodjac() {
        return webIzvodjac;
    }

    public String getVrijemeOdrzavanja() {
        return vrijemeOdrzavanja;
    }

    public String getDodatneInformacije() {
        return dodatneInformacije;
    }

    public String getBrojKarata() {
        return brojKarata;
    }

    public int getStatus() {
        return status;
    }

    public int getIdLokacija() {
        return idLokacija;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }
}


