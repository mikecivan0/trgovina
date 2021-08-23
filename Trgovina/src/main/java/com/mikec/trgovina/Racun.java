package com.mikec.trgovina;

import java.util.Date;

/**
 *
 * @author Ivan
 */
public class Racun extends Klijent{
    private Klijent klijent;
    private String broj;
    private Date datum;
    private double netto;
    private double porez;
    private double ukupno;
    private boolean storno;

    public Racun() {
    }

    public Racun(
            Klijent klijent, 
            String broj,
            Date datum, 
            double netto, 
            double porez, 
            double ukupno,
            boolean storno) {
        this.klijent = klijent;
        this.broj = broj;
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
        this.storno = storno;
    }

    public Racun(
            Osoba osoba, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr,
            String broj,         
            Date datum,             
            double netto, 
            double porez, 
            double ukupno,
            boolean storno) {
        super(osoba, ulicaiKbr, mjesto, pbr);
        this.broj = broj;
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
        this.storno = storno;
    }

    public Racun(
            String ime, 
            String prezime, 
            String telefon, 
            String email, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr,
            String broj,
            Date datum, 
            double netto, 
            double porez, 
            double ukupno,
            boolean storno) {
        super(ime, prezime, telefon, email, ulicaiKbr, mjesto, pbr);
        this.broj = broj;
        this.datum = datum;
        this.netto = netto;
        this.porez = porez;
        this.ukupno = ukupno;
        this.storno = storno;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }
    
    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
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
    
    public boolean IsStorno() {
        return storno;
    }

    public void setStorno(boolean storno) {
        this.storno = storno;
    }

    public String zaglavlje() {    	
        return "Datum računa: " + Alati.hrDatum(datum) + "\n"
                + "Broj računa: " + broj + "\n"
                + "Klijent: " + this.getKlijent().getOsoba().imeIPrezime() + "\n"
                + "Adresa: " + this.getKlijent().adresa() + "\n";
    }  
    
    public String sumiranje() {
    	String ispis;
        ispis = "Netto iznos: " + netto + "\n"
                + "Iznos poreza: " + porez + "\n"
                + "Ukupno za naplatu: " + ukupno;
        if(IsStorno()) {
        	ispis += "\n\nRačun je storniran!";
        }
        return ispis;
    }  
    
    public String ispis() {
    	return "Datum: " + Alati.hrDatum(datum) + ", " + this.getKlijent().getOsoba().imeIPrezime() + ", " + ukupno;
    }
    
    public String ispisKodDetaljaKlijenta() {
    	String ispis;
    	ispis = "Datum: " + Alati.hrDatum(datum) + ", broj računa: " + broj + ", iznos: "  + ukupno;
    	if(IsStorno()) {
        	ispis += ", storno";
        }
    	return ispis;
    }
}
