package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
public class Klijent extends Osoba{
    private Osoba osoba;
    private String ulicaiKbr;
    private String mjesto;
    private String pbr;

    public Klijent() {
    }
    
    public Klijent(
            Osoba osoba, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr) {
        this.osoba = osoba;
        this.ulicaiKbr = ulicaiKbr;
        this.mjesto = mjesto;
        this.pbr = pbr;
    }

    public Klijent(
            String ime, 
            String prezime, 
            String telefon, 
            String email, 
            String ulicaiKbr, 
            String mjesto, 
            String pbr) {
        super(ime, prezime, telefon, email);
        this.ulicaiKbr = ulicaiKbr;
        this.mjesto = mjesto;
        this.pbr = pbr;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public String getUlicaiKbr() {
        return ulicaiKbr;
    }

    public void setUlicaiKbr(String ulicaiKbr) {
        this.ulicaiKbr = ulicaiKbr;
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
                + "\nUlica i kućni broj: " + ulicaiKbr + "\n"
                + "Mjesto: " + mjesto + "\n"
                + "Poštanski broj: " + pbr;
    }

    public String adresa(){
        return ulicaiKbr + ", " + pbr + " " + mjesto;
    }
    
}
