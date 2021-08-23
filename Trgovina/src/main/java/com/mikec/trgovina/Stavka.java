package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Stavka extends Artikal {
    private Racun racun;
    private Artikal artikal;
    private double kolicina;
    private double cijenaPoKomadu;
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
    
    public double getNetto() {
    	return nettoIznos;
    }
    
    public double getPorez() {
    	return iznosPoreza;
    }
    
    public double getUkupno() {
    	return ukupanIznos;
    }

    public String ispisReda() {
        String ispis;     
        double cijena;
        cijena = (this.getArtikal().isAkcija()) ? this.getArtikal().getAkcijskaCijena() : this.getArtikal().getCijena();       
        ispis = this.getArtikal().getNaziv() + razmak(this.getArtikal().getNaziv()); 
        ispis += kolicina + "\t\t";
        ispis += cijena + "\t\t\t";
        ispis += nettoIznos + "\t\t";
        ispis += iznosPoreza + "\t\t";
        ispis += ukupanIznos;        
        return ispis;
    }
    
    private String razmak(String naziv) {
    	String razmak = "";
		if(naziv.length()<8) {
			razmak += "\t\t\t\t";
		}		
		if(naziv.length()>=8 && naziv.length()<16) {
			razmak += "\t\t\t";
		}		
		if(naziv.length()>=16 && naziv.length()<24) {
			razmak += "\t\t";
		}		
		if(naziv.length()>=24) {
			razmak += "\t";
		}
		return razmak;
	}

	public void izracunOstalihVrijednosti(double stopaPoreza){
        double nettoCijenaPoKomadu = (this.getArtikal().isAkcija()) ?  
                this.getArtikal().getAkcijskaCijena() : 
                this.getArtikal().getCijena();
        cijenaPoKomadu = nettoCijenaPoKomadu;
        nettoIznos = cijenaPoKomadu*this.kolicina;       
        iznosPoreza = nettoIznos*(stopaPoreza/100);
        ukupanIznos = nettoIznos+iznosPoreza;
    }
    
}
