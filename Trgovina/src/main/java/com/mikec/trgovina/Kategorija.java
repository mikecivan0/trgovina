package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Kategorija {
    private String nazivKategorije;

    public Kategorija() {
    }

    public Kategorija(String naziv) {
        this.nazivKategorije = naziv;
    }

    public String getNaziv() {
        return nazivKategorije;
    }

    public void setNaziv(String naziv) {
        this.nazivKategorije = naziv;
    }

    @Override
    public String toString() {
        return "Naziv kategorije: " + nazivKategorije;
    }
    
    
}
