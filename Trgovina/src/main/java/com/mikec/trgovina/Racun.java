package com.mikec.trgovina;

import java.util.Date;

/**
 *
 * @author Ivan
 */
public class Racun extends Klijent{
    private Klijent klijent;
    private Date datum;
    private double netto;
    private double porez;
    private double ukupno;

    public Racun() {
    }

    public Racun(
            Klijent klijent, 
            Date datum, 
            double netto, 
            double porez, 
            double ukupno) {
        this.klijent = klijent;
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
    }

    public Racun(
            Osoba osoba, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr,         
            Date datum, 
            double netto, 
            double porez, 
            double ukupno) {
        super(osoba, ulicaiKbr, mjesto, pbr);
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
    }

    public Racun(
            String ime, 
            String prezime, 
            String telefon, 
            String email, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr,
            Date datum, 
            double netto, 
            double porez, 
            double ukupno) {
        super(ime, prezime, telefon, email, ulicaiKbr, mjesto, pbr);
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public double getPorez() {
        return porez;
    }

    public void setPorez(double porez) {
        this.porez = porez;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    @Override
    public String toString() {
        return "Datum računa: " + Alati.hrDatum(datum) + "\n"
                + "Klijent: " + this.getOsoba().imeIPrezime() + this.getKlijent().adresa() + "\n"
                + "Netto iznos: " + netto + "\n"
                + "Iznos poreza: " + porez + "\n"
                + "Ukupno za naplatu: " + ukupno;
    }
    
    
    
    
}
