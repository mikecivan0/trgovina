package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Osoba {
    private String ime;
    private String prezime;
    private String telefon;
    private String email;

    public Osoba() {
    }

    public Osoba(String ime, String prezime, String telefon, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Ime: " + ime + "\n"
                + "Prezime: " + prezime + "\n"
                + "Telefon: " + telefon + "\n"
                + "Email: " + email;
    }
    
    public String imeIPrezime(){
        return ime + " " + prezime;
    }
    
}
