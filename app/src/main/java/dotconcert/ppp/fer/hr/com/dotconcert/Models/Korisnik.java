package dotconcert.ppp.fer.hr.com.dotconcert.Models;

/**
 * Created by Cveki on 9.11.2014..
 */
public class Korisnik {
    private String email;
    private String lozinka;
    private String status;
    private PravaKorisnik pravaKorisnik;

    public Korisnik(String email, String lozinka, String status, PravaKorisnik pravaKorisnik) {
        this.email = email;
        this.lozinka = lozinka;
        this.status = status;
        this.pravaKorisnik = pravaKorisnik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PravaKorisnik getPravaKorisnik() {
        return pravaKorisnik;
    }

    public void setPravaKorisnik(PravaKorisnik pravaKorisnik) {
        this.pravaKorisnik = pravaKorisnik;
    }
}
