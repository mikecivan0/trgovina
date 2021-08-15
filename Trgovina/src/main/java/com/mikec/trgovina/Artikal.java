package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Artikal extends Kategorija{
    private Kategorija kategorija;
    private String naziv;
    private double cijena;
    private boolean akcija;
    private double akcijskaCijena;
    private String opis;

    public Artikal() {
    }

    public Artikal(
            Kategorija kategorija, 
            String naziv, 
            double cijena, 
            boolean akcija, 
            double akcijskaCijena, 
            String opis) {
        this.kategorija = kategorija;
        this.naziv = naziv;
        this.cijena = cijena;
        this.akcija = akcija;
        this.akcijskaCijena = akcijskaCijena;
        this.opis = opis;
    }

    public Artikal(
            String nazivKategorije, 
            String naziv,
            double cijena, 
            boolean akcija, 
            double akcijskaCijena, 
            String opis) {
        super(nazivKategorije);
        this.naziv = naziv;
        this.cijena = cijena;
        this.akcija = akcija;
        this.akcijskaCijena = akcijskaCijena;
        this.opis = opis;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public boolean isAkcija() {
        return akcija;
    }

    public void setAkcija(boolean akcija) {
        this.akcija = akcija;
    }

    public double getAkcijskaCijena() {
        return akcijskaCijena;
    }

    public void setAkcijskaCijena(double akcijskaCijena) {
        this.akcijskaCijena = akcijskaCijena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Naziv artikla: " + naziv +"\n"
                + "Cijena: " + cijena + "\n"
                + "Akcijska cijena: " + akcijskaCijena + "\n"
                + "Trenutno na akciji: " + Alati.parseBool(akcija) + "\n"
                + "Opis: " + opis + "\n"
                + "Kategorija: " + getKategorija().getNaziv();
    }
    
    
    
}
