package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Stanje extends Artikal{
    private Artikal artikal;
    private double raspolozivo;
    private double prodano;

    public Stanje() {
    }

    public Stanje(
            Artikal artikal, 
            double raspolozivo, 
            double prodano) {
        this.artikal = artikal;
        this.raspolozivo = raspolozivo;
        this.prodano = prodano;
    }

    public Stanje(
            Kategorija kategorija,
            String naziv, 
            double cijena, 
            boolean akcija, 
            double akcijskaCijena,
            String opis,
            double raspolozivo, 
            double prodano) {
        super(kategorija, naziv, cijena, akcija, akcijskaCijena, opis);
        this.raspolozivo = raspolozivo;
        this.prodano = prodano;
    }

    public Stanje(
            String nazivKategorije,             
            String naziv,
            double cijena, 
            boolean akcija, 
            double akcijskaCijena, 
            String opis,
            double raspolozivo, 
            double prodano) {
        super(nazivKategorije, naziv, cijena, akcija, akcijskaCijena, opis);
        this.raspolozivo = raspolozivo;
        this.prodano = prodano;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public double getRaspolozivo() {
        return raspolozivo;
    }

    public void setRaspolozivo(double raspolozivo) {
        this.raspolozivo = raspolozivo;
    }

    public double getProdano() {
        return prodano;
    }

    public void setProdano(double prodano) {
        this.prodano = prodano;
    }

    @Override
    public String toString() {
        return getArtikal().getNaziv() + " (" + this.getArtikal().getKategorija().getNaziv() + "): "
                + "raspoloživo: " + raspolozivo 
                + ", prodano: " + prodano;
    }
    
    public String detalji() {
        return "Artikal: " + getArtikal().getNaziv() 
        		+ "\nKategorija: " + this.getArtikal().getKategorija().getNaziv()
                + "\nRaspoloživo: " + raspolozivo 
                + "\nProdano: " + prodano;
    }
    
    public String ispisStanjaKodArtikala() {
        return "Raspoloživo: " + raspolozivo 
        		+ "\nProdano: " + prodano;
    }
    
    public String ispisStanjaKodDetalja() {
        return "raspoloživo: " + raspolozivo + ", prodano: " + prodano;
    }  
    
}
