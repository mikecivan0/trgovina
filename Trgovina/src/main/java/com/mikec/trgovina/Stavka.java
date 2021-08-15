package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Stavka extends Artikal {
    private Racun racun;
    private Artikal artikal;
    private double kolicina;
    private double nettoIznos;
    private double iznosPoreza;
    private double ukupanIznos;

    public Stavka() {
    }

    public Stavka(
            Racun racun, 
            Artikal artikal, 
            double kolicina) {
        this.racun = racun;
        this.artikal = artikal;
        this.kolicina = kolicina;
    }

    public Stavka(
            Racun racun, 
            Kategorija kategorija, 
            String naziv, 
            double cijena, 
            boolean akcija, 
            double akcijskaCijena, 
            String opis,
            double kolicina) {
        super(kategorija, naziv, cijena, akcija, akcijskaCijena, opis);
        this.racun = racun;
        this.kolicina = kolicina;
    }
    
    public Stavka(
            Racun racun, 
            String kategorija, 
            String naziv, 
            double cijena, 
            boolean akcija, 
            double akcijskaCijena, 
            String opis,
            double kolicina) {
        super(kategorija, naziv, cijena, akcija, akcijskaCijena, opis);
        this.racun = racun;
        this.kolicina = kolicina;
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String ispisReda() {
        String ispis;         
        ispis = this.getArtikal().getNaziv() + "\t\t"; 
        ispis += kolicina + "\t\t";
        ispis += nettoIznos + "\t\t";
        ispis += iznosPoreza + "\t\t";
        ispis += ukupanIznos;        
        return ispis;
    }
    
    public void izracunOstalihVrijednosti(double stopaPoreza){
        double nettoCijenaPoKomadu = (this.getArtikal().isAkcija()) ?  
                this.getArtikal().getAkcijskaCijena() : 
                this.getArtikal().getCijena();
        nettoIznos = nettoCijenaPoKomadu*this.kolicina;       
        iznosPoreza = nettoIznos*(stopaPoreza/100);
        ukupanIznos = nettoIznos+iznosPoreza;
    }
    
}
