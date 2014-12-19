package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Lokacija {
    private int idLokacija;
    private String naziv;
    private float longitude;
    private float latitude;
    private String adresa;
    private String web;
    private String email;
    private String telefon;
    private int  idMjesto;
    private int  idKorisnik;

    public Lokacija(int idLokacija, String naziv, float longitude, float latitude, String adresa, String web, String email, String telefon, int idMjesto, int idKorisnik) {
        this.idLokacija = idLokacija;
        this.naziv = naziv;
        this.longitude = longitude;
        this.latitude = latitude;
        this.adresa = adresa;
        this.web = web;
        this.email = email;
        this.telefon = telefon;
        this.idMjesto = idMjesto;
        this.idKorisnik = idKorisnik;
    }

    public int getIdLokacija() {
        return idLokacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getWeb() {
        return web;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public int getIdMjesto() {
        return idMjesto;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }
}
