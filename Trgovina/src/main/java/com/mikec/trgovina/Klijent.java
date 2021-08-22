package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Klijent extends Osoba{
    private Osoba osoba;
    private String ulicaIKbr;
    private String mjesto;
    private String pbr;

    public Klijent() {
    }
    
    public Klijent(
            Osoba osoba, 
            String ulicaIKbr, 
            String mjesto, 
            String pbr) {
        this.osoba = osoba;
        this.ulicaIKbr = ulicaIKbr;
        this.mjesto = mjesto;
        this.pbr = pbr;
    }

    public Klijent(
            String ime, 
            String prezime, 
            String telefon, 
            String email, 
            String ulicaIKbr, 
            String mjesto, 
            String pbr) {
        super(ime, prezime, telefon, email);
        this.ulicaIKbr = ulicaIKbr;
        this.mjesto = mjesto;
        this.pbr = pbr;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getUlicaIKbr() {
        return ulicaIKbr;
    }

    public void setUlicaIKbr(String ulicaiKbr) {
        this.ulicaIKbr = ulicaiKbr;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getPbr() {
        return pbr;
    }

    public void setPbr(String pbr) {
        this.pbr = pbr;
    }

    @Override
    public String toString() {
        return getOsoba().toString()
                + "\nUlica i kućni broj: " + ulicaIKbr + "\n"
                + "Mjesto: " + mjesto + "\n"
                + "Poštanski broj: " + pbr;
    }

    public String adresa(){
        return ulicaIKbr + ", " + pbr + " " + mjesto;
    }
    
    public String klijentZaPrikaz(){
        return this.getOsoba().imeIPrezime() + ", " + this.adresa();
    }
    
}
