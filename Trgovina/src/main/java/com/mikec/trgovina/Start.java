package com.mikec.trgovina;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

    private static final double STOPA_POREZA = 25.0;
	public static final String LINK_GITHUB = "https://github.com/mikecivan0/trgovina";
	public static final String LINK_ER_DIJAGRAM = "https://github.com/mikecivan0/RasporedRada/blob/main/src/raspored_rada.png";
	private List<Osoba> osobe;
	private List<Korisnik> korisnici;
	private List<Korisnik> aktivniKorisnici;
	private Korisnik trenutniKorisnik;
	private boolean valjanost;
	private String porukaIzboraAkcije = "Unesite neku od gore ponuđenih stavki: ";
	private String porukaGreskeIzboraAkcije = "Nepostojeći izbor";
	private String porukaGreskePraznogUnosa = "Unos ne smije biti prazan";
	private String porukaGreskeUnosaCijelogBroja = "Molimo unesite cijeli broj";
	private String porukaGreskeDaNe = "Molimo unesite da ili ne";
	private String porukaGreskeNemaOsoba = "\nU bazi nema niti jedne osobe";
	private String porukaGreskeNemaKorisnika = "\nU bazi nema niti jednog korisnika";
	SimpleDateFormat formatDatuma = new SimpleDateFormat("dd.MM.yyyy.");
        
    public static void main(String[] args) {
        new Start();
    }

    public Start() {
        Alati.scanner = new Scanner(System.in);
        glavniIzbornik();
    }

    private void glavniIzbornik() {
        System.out.println();
        System.out.println("---------APLIKACIJA ZA VOĐENJE TRGOVINE---------");
        Alati.ispisZaglavlja("GLAVNI IZBORNIK", true);
        System.out.println("1 za prijavu u aplikaciju");
        System.out.println("2 za pregled koda aplikacije na GitHub-u");
        System.out.println("3 za pregled ER dijagrama baze podataka aplikacije");
        glavniIzbornikOdabirAkcije();
    }

    private void glavniIzbornikOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 3)) {
            case 1 ->
                login();
            case 2 -> {
                otvoriGithub();
                glavniIzbornik();
            }
            case 3 -> {
                otvoriErDijagram();
                glavniIzbornik();
            }
        }
    }

    private void otvoriGithub() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(new URI(LINK_GITHUB));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Dogodila se greška. Pokušajte ponovno kasnije.");
        }
    }

    private void otvoriErDijagram() {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            desktop.browse(new URI(LINK_ER_DIJAGRAM));
        } catch (IOException | URISyntaxException e) {
            System.out.println("Dogodila se greška. Pokušajte ponovno kasnije.");
        }
    }

    private void logiranjePonovniPokusaj() {
        if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
            login();
        } else {
            glavniIzbornik();
        }
    }

    private void login() {
        Korisnik korisnik = new Korisnik();
        Alati.ispisZaglavlja("Podaci za prijavu u aplikaciju", true);
        korisnik.setKorisnickoIme(Alati.ucitajString("korisničko ime: ", porukaGreskePraznogUnosa, 1, 15));
        korisnik.setLozinka(Alati.ucitajString("lozinka: ", porukaGreskePraznogUnosa, 1, 30));
        provjeraVjerodajnica(korisnik);
    }

    private void logout() {
        valjanost = false;
        glavniIzbornik();
    }

    private void provjeraVjerodajnica(Korisnik k) {
        valjanost = false;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getKorisnickoIme().equals(k.getKorisnickoIme())
                    && korisnik.getLozinka().equals(k.getLozinka())
                    && korisnik.isAktivan()) {
                valjanost = true;
                trenutniKorisnik = korisnik;
                break;
            }
        }

        if (valjanost) {
            if (trenutniKorisnik.getRazina()==1) {
                trgovacGlavniIzbornik();
            } else {
                adminGlavniIzbornik();
            }
        } else {
            System.out.println("Nevaljana kombinacija korisničkog imena i lozinke");
            logiranjePonovniPokusaj();
        }
    }

    private void trgovacGlavniIzbornik() {
 
    }

    private void adminGlavniIzbornik() {

    }

    /**
     *
     * GLAVNI IZBORNIK KRAJ
     *
     * ADMIN IZBORNIK
     *
     */
}
