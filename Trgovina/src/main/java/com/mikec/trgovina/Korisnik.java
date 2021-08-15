package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Korisnik extends Osoba {

    private Osoba osoba;
    private String korisnickoIme;
    private String lozinka;
    private int razina;
    private boolean aktivan;

    public Korisnik() {
    }

    public Korisnik(
            Osoba osoba,
            String korisnickoIme,
            String lozinka,
            int razina,
            boolean aktivan) {
        this.osoba = osoba;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.razina = razina;
        this.aktivan = aktivan;
    }

    public Korisnik(
            String korisnickoIme,
            String lozinka,
            int razina,
            boolean aktivan,
            String ime,
            String prezime,
            String telefon,
            String email) {
        super(ime, prezime, telefon, email);
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.razina = razina;
        this.aktivan = aktivan;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getRazina() {
        return razina;
    }

    public void setRazina(int razina) {
        this.razina = razina;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public void detalji() {
        System.out.println(
            this.getOsoba().toString()
            + "\nKorisničko ime: " + korisnickoIme + "\n"
            + "Lozinka: " + lozinka + "\n"
            + "Razina: " + razina + "\n"
            + "Aktivan: " + Alati.parseBool(aktivan)
        );
    }

    @Override
    public String toString() {
        return "Korisničko ime: " + korisnickoIme + "\n"
                + "Lozinka: " + lozinka + "\n"
                + "Razina: " + razina + "\n"
                + "Aktivan: " + Alati.parseBool(aktivan);
    }

}
