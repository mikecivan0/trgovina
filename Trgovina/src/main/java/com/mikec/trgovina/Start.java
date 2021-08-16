package com.mikec.trgovina;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

    private static final double STOPA_POREZA = 25.0;
    public static final String LINK_GITHUB = "https://github.com/mikecivan0/trgovina";
    public static final String LINK_ER_DIJAGRAM = "https://github.com/mikecivan0/trgovina/blob/master/Trgovina/trgovina-database.png";
    private List<Osoba> osobe;
    private List<Korisnik> korisnici;
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
        korisnici = new ArrayList<Korisnik>();
        osobe = new ArrayList<Osoba>();
        trenutniKorisnik = new Korisnik();
        valjanost = false;

        /**
         * početak probnih podataka
         */
        // probni podaci nove osobe
        osobe.add(new Osoba("Ivan", "Mikec", "091", "email"));
        osobe.add(new Osoba("Netko", "Drugi", "091", "email"));
        osobe.add(new Osoba("Netko", "Treći", "091", "email"));

        // probni podaci novog korisnika
        korisnici.add(new Korisnik(osobe.get(0), "ja", "ja", 2, true));
        korisnici.add(new Korisnik(osobe.get(1), "on", "on", 1, true));

        /**
         * KRAJ PROBNIH PODATAKA
         */
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

    private void login() {
        Korisnik korisnik = new Korisnik();
        Alati.ispisZaglavlja("Podaci za prijavu u aplikaciju", true);
        korisnik.setKorisnickoIme(Alati.ucitajString("Korisničko ime: ", porukaGreskePraznogUnosa, 1, 15));
        korisnik.setLozinka(Alati.ucitajString("Lozinka: ", porukaGreskePraznogUnosa, 1, 30));
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
            if (trenutniKorisnik.getRazina() == 1) {
                djelatnikGlavniIzbornik();
            } else {
                adminGlavniIzbornik();
            }
        } else {
            System.out.println("Nevaljana kombinacija korisničkog imena i lozinke");
            logiranjePonovniPokusaj();
        }
    }

    private void logiranjePonovniPokusaj() {
        if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
            login();
        } else {
            glavniIzbornik();
        }
    }

    /**
     *
     * GLAVNI IZBORNIK KRAJ
     *
     * DJELATNIK GLAVNI IZBORNIK
     *
     */
    private void djelatnikGlavniIzbornik() {
        Alati.ispisZaglavlja("Glavni izbornik za djelatnike", true);        
        System.out.println("1 za rad sa kategorijama artikala");
        System.out.println("2 za rad sa artiklima");
        System.out.println("3 za rad sa podacima klijenata");
        System.out.println("4 za rad sa računima");
        System.out.println("5 za odjavu iz aplikacije");
        djelatnikGlavniIzbornikOdabirAkcije();
    }

    private void djelatnikGlavniIzbornikOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 5)) {
//            case 1 -> kategorijeIzbornik();
//            case 2 -> artikliIzbornik();
//            case 3 -> klijentiIzbornik();
//            case 4 -> racuniIzbornik();
            case 5 -> {
                logout();
                glavniIzbornik();
            }
        }
    }

    /**
     *
     * DJELATNIK GLAVNI IZBORNIK KRAJ
     *
     * ADMIN GLAVNI IZBORNIK
     *
     */
    private void adminGlavniIzbornik() {
        Alati.ispisZaglavlja("Glavni izbornik za djelatnike", true);
        System.out.println("1 za rad sa osobama");
        System.out.println("2 za rad sa korisnicima");
        System.out.println("3 za rad sa kategorijama artikala");
        System.out.println("4 za rad sa artiklima");
        System.out.println("5 za rad sa podacima klijenata");
        System.out.println("6 za rad sa računima");
        System.out.println("7 za odjavu iz aplikacije");
        adminGlavniIzbornikOdabirAkcije();
    }

    private void adminGlavniIzbornikOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 7)) {
            case 1 -> osobeIzbornik();
            case 2 -> korisniciIzbornik();
//            case 3 -> kategorijeIzbornik();
//            case 4 -> artikliIzbornik();
//            case 5 -> klijentiIzbornik();
//            case 6 -> racuniIzbornik();
            case 7 -> {
                logout();
                glavniIzbornik();
            }
        }
    }

    /**
     *
     * ADMIN GLAVNI IZBORNIK KRAJ
     *
     * OSOBE
     *
     */
    private void osobeIzbornik() {
        Alati.ispisZaglavlja("Rad sa osobama", true);
        System.out.println("1 za unos nove osobe");
        System.out.println("2 za izmjenu postojeće osobe");
        System.out.println("3 za brisanje postojeće osobe");
        System.out.println("4 za pregled svih osoba");
        System.out.println("5 za pregled detalja postojeće osobe");
        System.out.println("6 za povratak u glavni korisnički izbornik");
        osobeOdabirAkcije();
    }

    private void osobeOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
            case 1 -> {
                osobeUnosNove();
                System.out.println("\nOsoba je spremljena. Što želite dalje?");
                osobeIzbornik();
            }
            case 2 ->
                osobeIzmjena();
            case 3 ->
                osobeBrisanje();
            case 4 -> {
                osobeIzlistanje();
                osobeIzbornik();
            }
            case 5 -> {
                osobeDetalji();
                osobeIzbornik();
            }
            case 6 ->
                adminGlavniIzbornik();
        }
    }

    // UNOS NOVE OSOBE
    private void osobeUnosNove() {
        Osoba osoba = new Osoba();
        Alati.ispisZaglavlja("Podaci nove osobe", true);
        osoba = osobeUnosPodataka(osoba);
        osobe.add(osoba);
    }

    // IZMJENA OSOBE
    private void osobeIzmjena() {
        if (!osobe.isEmpty()) {
            osobeIspisIzboraPretrage("Izmjena podataka osobe");
            osobeUcitajOdabirPretrageZaIzmjenu();
        } else {
            System.out.println(porukaGreskeNemaOsoba);
            osobeIzbornik();
        }
    }

    private void osobeUcitajOdabirPretrageZaIzmjenu() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                osobeIzmjenaPoIndeksu();
            case 2 ->
                osobeIzmjenaPoImenu();
        }
    }

    private void osobeIzmjenaPoIndeksu() {
        int i;
        osobeIzlistanje();
        i = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
                osobe.size()) - 1;
        Osoba osoba = osobe.get(i);
        osobeIzmjenaPodataka(osoba, i);
    }

    private void osobeIzmjenaPoImenu() {
        String uvjet;
        int izbor, indeksOdabraneOsobe;
        List<Osoba> nadjeneOsobe = new ArrayList<Osoba>();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
                30);
        nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
        if (!nadjeneOsobe.isEmpty()) {
            osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
            izbor = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije,
                    1, nadjeneOsobe.size()) - 1;
            indeksOdabraneOsobe = osobeIndeksOsobeIzIzvorneListe(nadjeneOsobe.get(izbor));
            osobeIzmjenaPodataka(osobe.get(indeksOdabraneOsobe), indeksOdabraneOsobe);
        } else {
            System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                osobeIzmjenaPoImenu();
            } else {
                osobeIzbornik();
            }
        }
    }

    private void osobeIzmjenaPodataka(Osoba osoba, int i) {
        osoba = osobeUnosPodataka(osoba);
        osobe.set(i, osoba);
        System.out.println("\nPodaci osobe su uspješno izmjenjeni. Što želite dalje?");
        osobeIzbornik();
    }

    // BRISANJE OSOBE	
    private void osobeBrisanje() {
        if (!osobe.isEmpty()) {
            osobeIspisIzboraPretrage("Brisanje osobe");
            osobeUcitajOdabirPretrageZaBrisanje();
        } else {
            System.out.println(porukaGreskeNemaOsoba);
            osobeIzbornik();
        }
    }

    private void osobeUcitajOdabirPretrageZaBrisanje() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                osobeBrisanjePoIndeksu();
            case 2 ->
                osobeBrisanjePoImenu();
        }
    }

    private void osobeBrisanjePoIndeksu() {
        int i;
        osobeIzlistanje();
        i = Alati.ucitajBroj("Unesite broj osobe koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
                osobe.size()) - 1;
        if (Alati.daNe("Želite li zaista obrisati osobu "
                + osobe.get(i).imeIPrezime() + " (da/ne): ", porukaGreskeDaNe)) {
            if (!korisniciJeLiOsobaKorisnik(osobe.get(i))) {
                osobe.remove(i);
                System.out.println("\nOsoba je obrisana.");
            } else {
                System.out.println("\nOdabrana osoba je korisnik i stoga ju ne možete obrisati.");
            }
        }
        osobeIzbornik();
    }

    private void osobeBrisanjePoImenu() {
        String uvjet;
        int izbor, indeksOdabraneOsobe;
        List<Osoba> nadjeneOsobe = new ArrayList<Osoba>();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
                30);
        nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
        if (!nadjeneOsobe.isEmpty()) {
            osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
            izbor = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije,
                    1, nadjeneOsobe.size()) - 1;
            indeksOdabraneOsobe = osobeIndeksOsobeIzIzvorneListe(nadjeneOsobe.get(izbor));
            if (Alati.daNe("Želite li zaista obrisati osobu "
                    + osobe.get(indeksOdabraneOsobe).imeIPrezime() + " (da/ne): ",
                    porukaGreskeDaNe)) {
                if (!korisniciJeLiOsobaKorisnik(osobe.get(indeksOdabraneOsobe))) {
                    osobe.remove(indeksOdabraneOsobe);
                    System.out.println("\nOsoba je obrisana.");
                } else {
                    System.out.println("\nOdabrana osoba je korisnik i stoga ju ne možete obrisati.");
                }
            }
        } else {
            System.out.println("\nNema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                osobeBrisanjePoImenu();
            }
        }
        osobeIzbornik();
    }

    // DETALJI OSOBE
    private void osobeDetalji() {
        if (!osobe.isEmpty()) {
            osobeIspisIzboraPretrage("Detalji osobe");
            osobeUcitajOdabirPretrageZaIspisDetalja();
        } else {
            System.out.println(porukaGreskeNemaOsoba);
            osobeIzbornik();
        }
    }

    private void osobeUcitajOdabirPretrageZaIspisDetalja() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                osobeDetaljiPoIndeksu();
            case 2 ->
                osobeDetaljiPoImenu();
        }
    }

    private void osobeDetaljiPoIndeksu() {
        int i;
        osobeIzlistanje();
        i = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
                osobe.size()) - 1;
        Alati.ispisZaglavlja("Detalji osobe", false);
        System.out.println(osobe.get(i).toString());
        osobeIzbornik();
    }

    private void osobeDetaljiPoImenu() {
        String uvjet;
        int izbor;
        List<Osoba> nadjeneOsobe = new ArrayList<Osoba>();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
                30);
        nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
        if (!nadjeneOsobe.isEmpty()) {
            osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
            izbor = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
                    1, nadjeneOsobe.size()) - 1;
            Alati.ispisZaglavlja("Detalji osobe", false);
            System.out.println(nadjeneOsobe.get(izbor).toString());
            osobeIzbornik();
        } else {
            System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                osobeDetaljiPoImenu();
            } else {
                osobeIzbornik();
            }
        }
    }

    // POMOĆNE METODE OSOBA
    // izlistanje svih osoba
    private void osobeIzlistanje() {
        int counter;
        if (!osobe.isEmpty()) {
            counter = 1;
            Alati.ispisZaglavlja("Osobe koje se nalaze u bazi", false);
            for (Osoba osoba : osobe) {
                System.out.println(counter + " " + osoba.imeIPrezime());
                counter++;
            }
        } else {
            System.out.println(porukaGreskeNemaOsoba);
            osobeIzbornik();
        }
    }

    //izlistanje nađenih osoba
    private void osobeIzlistanje(String poruka, List<Osoba> osobe) {
        int counter = 1;
        Alati.ispisZaglavlja(poruka, false);
        for (Osoba osoba : osobe) {
            System.out.println(counter + " " + osoba.imeIPrezime());
            counter++;
        }
    }

    private List<Osoba> osobePronadjiPoUvjetu(String uvjet) {
        List<Osoba> listaOsoba = new ArrayList<Osoba>();
        osobe.stream().filter(
                osoba -> (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet))).forEachOrdered(
                        osoba -> {
                            listaOsoba.add(osoba);
                         }
                );
        return listaOsoba;
    }

    private boolean osobeJeLiToOsobaPoImenuIPrezimenu(Osoba osoba, String uvjet) {
        String imePrezime = osoba.getIme().toLowerCase() + " " + osoba.getPrezime().toLowerCase();
        String prezimeIme = osoba.getPrezime().toLowerCase() + " " + osoba.getIme().toLowerCase();
        uvjet = uvjet.trim().toLowerCase();
        return imePrezime.contains(uvjet) || prezimeIme.contains(uvjet);
    }

    private int osobeIndeksOsobeIzIzvorneListe(Osoba osoba) {
        return osobe.indexOf(osoba);
    }

    private void osobeIspisIzboraPretrage(String naslov) {
        Alati.ispisZaglavlja(naslov, true);
        System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
        System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
    }

    private Osoba osobeUnosPodataka(Osoba osoba) {
        osoba.setIme(Alati.ucitajString("ime osobe: ", porukaGreskePraznogUnosa, 1, 25));
        osoba.setPrezime(Alati.ucitajString("prezime osobe: ", porukaGreskePraznogUnosa, 1, 25));
        if (Alati.daNe("Želite li unijeti telefon osobe? (da/ne): ", porukaGreskeDaNe)) {
            osoba.setTelefon(Alati.ucitajString("telefon osobe: ", porukaGreskePraznogUnosa, 1, 20));
        } else {
            osoba.setTelefon("");
        }
        if (Alati.daNe("Želite li unijeti email adresu osobe? (da/ne): ", porukaGreskeDaNe)) {
            osoba.setEmail(Alati.ucitajString("email osobe: ", porukaGreskePraznogUnosa, 1, 50));
        } else {
            osoba.setEmail("");
        }
        return osoba;
    }

    /**
     *
     * OSOBE KRAJ
     *
     * KORISNICI
     *
     */
    private void korisniciIzbornik() {
        Alati.ispisZaglavlja("Rad sa korisnicima", true);
        System.out.println("1 za unos novog korisnika");
        System.out.println("2 za izmjenu postojećeg korisnika");
        System.out.println("3 za brisanje postojećeg korisnika");
        System.out.println("4 za pregled svih korisnika");
        System.out.println("5 za pregled detalja postojećeg korisnika");
        System.out.println("6 za povratak u glavni korisnički izbornik");
        korisniciOdabirAkcije();
    }

    private void korisniciOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
            case 1 ->
                korisniciUnosNovog();
            case 2 ->
                korisniciIzmjena();
            case 3 ->
                korisniciBrisanje();
            case 4 -> {
                korisniciIzlistanje();
                korisniciIzbornik();
            }
            case 5 -> {
                korisniciDetalji();
                korisniciIzbornik();
            }
            case 6 ->
                adminGlavniIzbornik();
        }
    }

    private void korisniciUnosNovog() {
        Alati.ispisZaglavlja("Odabir načina unosa novog korisnika", true);
        System.out.println("1 za unos postojeće osobe kao novog korisnika");
        System.out.println("2 za unos nove osobe kao novog korisnika");
        System.out.println("3 za povratak u glavni izbornika za rad sa korisnicima");
        korisniciUnosNovogOdabirAkcije();
    }

    private void korisniciUnosNovogOdabirAkcije() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 3)) {
            case 1 ->
                korisniciUnosPostojeceOsobeKaoNovogKorisnika();
            case 2 ->
                korisniciUnosNoveOsobeKaoNovogKorisnika();
            case 3 ->
                korisniciIzbornik();
        }
    }

    private void korisniciUnosPostojeceOsobeKaoNovogKorisnika() {
        Osoba osoba = new Osoba();
        Korisnik korisnik = new Korisnik();
        if (!osobe.isEmpty()) {
            osobeIzlistanje();
            osoba = osobe.get(Alati.ucitajBroj("Unesite broj osobe koju želite dodati kao korisnika: ",
                    "Unos ne smije biti prazan", 1, osobe.size()) - 1);
            if (!korisniciJeLiOsobaKorisnik(osoba)) {
                korisnik.setOsoba(osoba);
                while (true) {
                    String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
                    if (!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
                        korisnik.setKorisnickoIme(korisnickoIme);
                        korisnik = korisniciUnosOstalihPodataka(korisnik);
                        korisnici.add(korisnik);
                        System.out.println("\nNova osoba je unešena i postavljena kao novi korisnik");
                    } else {
                        if (Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
                            continue;
                        }
                    }
                    break;
                }
            } else {
                if (Alati.daNe("Osoba je već unešena kao korisnik. Želite li odabrati drugu osobu? (da/ne): ", porukaGreskeDaNe)) {
                    korisniciUnosPostojeceOsobeKaoNovogKorisnika();
                }
            }
        } else {
            System.out.println("\nU bazi ne postoji niti jedna osoba. \n"
                    + "Da bi Ste mogli dodati osobu kao novog korisnika prvo unesite najmanje jednu osobu.");
        }
        korisniciIzbornik();
    }

    // UNOS NOVOG KORISNIKA
    private void korisniciUnosNoveOsobeKaoNovogKorisnika() {
        Korisnik korisnik = new Korisnik();
        String korisnickoIme;
        osobeUnosNove();
        korisnik.setOsoba(osobe.get(osobe.size() - 1));
        while (true) {
            korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
            if (!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
                korisnik.setKorisnickoIme(korisnickoIme);
                korisnik = korisniciUnosOstalihPodataka(korisnik);
                korisnici.add(korisnik);
                System.out.println("\nNova osoba je unešena i postavljena kao novi korisnik");
            } else {
                if (Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
                    continue;
                } else {
                    osobe.remove(osobe.size() - 1);
                }
            }
            korisniciIzbornik();
            break;
        }
    }

    // IZMJENA KORISNIKA
    private void korisniciIzmjena() {
        if (!korisnici.isEmpty()) {
            korisniciIspisIzboraPretrage("Izmjena podataka korisnika");
            korisniciIzmjenaUcitajOdabirPretrage();
        } else {
            System.out.println(porukaGreskeNemaKorisnika);
            korisniciIzbornik();
        }
    }

    private void korisniciIzmjenaUcitajOdabirPretrage() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                korisniciIzmjenaPoIndeksu();
            case 2 ->
                korisniciIzmjenaPoImenu();
        }

    }

    private void korisniciIzmjenaPoIndeksu() {
        int i;
        Korisnik korisnik = new Korisnik();
        korisniciIzlistanje();
        i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
                korisnici.size()) - 1;
        korisnik = korisnici.get(i);
        korisniciIzmjenaPodataka(korisnik, i);
    }

    private void korisniciIzmjenaPoImenu() {
        String uvjet;
        int izbor, indeksIzabranogKorisnika;
        List<Korisnik> nadjeniKorisnici = new ArrayList<Korisnik>();
        Korisnik izabraniKorisnik = new Korisnik();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
                30);
        nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
        if (!nadjeniKorisnici.isEmpty()) {
            korisniciIzlistanje("Pronađeni korsnici", nadjeniKorisnici);
            izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije,
                    1, nadjeniKorisnici.size()) - 1;
            indeksIzabranogKorisnika = korisniciIndeksKorisnikaIzIzvorneListe(nadjeniKorisnici.get(izbor));
            izabraniKorisnik = korisnici.get(indeksIzabranogKorisnika);
            korisniciIzmjenaPodataka(izabraniKorisnik, indeksIzabranogKorisnika);
        } else {
            System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                korisniciIzmjenaPoImenu();
            }
        }
        korisniciIzbornik();
    }

    private void korisniciIzmjenaPodataka(Korisnik korisnik, int i) {
        String korisnickoIme;
        while (true) {
            korisnickoIme = Alati.ucitajString("Unesite korisničko ime korisnika: ", porukaGreskePraznogUnosa, 1, 50);
            if (!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme, korisnik)) {
                korisnik.setKorisnickoIme(korisnickoIme);
                korisnik = korisniciUnosOstalihPodataka(korisnik);
                korisnici.set(i, korisnik);
                System.out.println("");
                System.out.println("Novi podaci korisnika su spremljeni");
            } else {
                if (Alati.daNe("Korisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
                    continue;
                }
            }
            korisniciIzbornik();
            break;
        }
    }

    // BRISANJE KORISNIKA
    private void korisniciBrisanje() {
        if (!korisnici.isEmpty()) {
            korisniciIspisIzboraPretrage("Brisanje korisnika");
            korisniciUcitajOdabirPretrageZaBrisanje();
        } else {
            System.out.println(porukaGreskeNemaKorisnika);
            korisniciIzbornik();
        }
    }

    private void korisniciUcitajOdabirPretrageZaBrisanje() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                korisniciBrisanjePoIndeksu();
            case 2 ->
                korisniciBrisanjePoNazivu();
        }
    }

    private void korisniciBrisanjePoIndeksu() {
        Korisnik korisnik = new Korisnik();
        int i;
        korisniciIzlistanje();
        i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije, 1,
                korisnici.size()) - 1;
        korisnik = korisnici.get(i);
        if (Alati.daNe("Želite li zaista obrisati korisnika "
                + korisnik.korisnikZaPrikaz() + ": ",
                porukaGreskeDaNe)) {
            korisnici.remove(i);
            System.out.println("\nKorisnik je obrisan.");
        }
        korisniciIzbornik();
    }

    private void korisniciBrisanjePoNazivu() {
        String uvjet;
        int izbor, indeksKorisnikaIzIzvorneListe;
        List<Korisnik> nadjeniKorisnici = new ArrayList<Korisnik>();
        Korisnik odabraniKorisnik = new Korisnik();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ",
                porukaGreskePraznogUnosa, 0, 30);
        nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
        if (!nadjeniKorisnici.isEmpty()) {
            korisniciIzlistanje("Pronađeni korisnici", nadjeniKorisnici);
            izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije,
                    1, nadjeniKorisnici.size()) - 1;
            odabraniKorisnik = nadjeniKorisnici.get(izbor);
            indeksKorisnikaIzIzvorneListe = korisniciIndeksKorisnikaIzIzvorneListe(odabraniKorisnik);
            if (Alati.daNe("Želite li zaista obrisati odabranog korisnika ("
                    + korisnici.get(indeksKorisnikaIzIzvorneListe).korisnikZaPrikaz() + "): ",
                    porukaGreskeDaNe)) {
                korisnici.remove(indeksKorisnikaIzIzvorneListe);
                System.out.println("\nKorisnik je obrisan.");
            }
        } else {
            System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                korisniciBrisanjePoNazivu();
            }
        }
        korisniciIzbornik();
    }

    // DETALJI KORISNIKA
    private void korisniciDetalji() {
        if (!korisnici.isEmpty()) {
            korisniciIspisIzboraPretrage("Detalji korisnika");
            korisniciUcitajOdabirPretrageZaIspisDetalja();
        } else {
            System.out.println(porukaGreskeNemaKorisnika);
            korisniciIzbornik();
        }
    }

    private void korisniciUcitajOdabirPretrageZaIspisDetalja() {
        switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
            case 1 ->
                korisniciDetaljiPoIndeksu();
            case 2 ->
                korisniciDetaljiPoImenu();
        }
    }

    private void korisniciDetaljiPoIndeksu() {
        int i;
        korisniciIzlistanje();
        i = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
                korisnici.size()) - 1;
        Alati.ispisZaglavlja("Detalji korisnika", false);
        System.out.println(korisnici.get(i).toString());
        korisniciIzbornik();
    }

    private void korisniciDetaljiPoImenu() {
        String uvjet;
        int izbor;
        List<Korisnik> nadjeniKorisnici = new ArrayList<Korisnik>();
        uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime osobe/korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
                30);
        nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
        if (!nadjeniKorisnici.isEmpty()) {
            korisniciIzlistanje("Pronađeni korisnici", nadjeniKorisnici);
            izbor = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
                    1, nadjeniKorisnici.size()) - 1;
            Alati.ispisZaglavlja("Detalji korisnika", false);
            System.out.println(nadjeniKorisnici.get(izbor).toString());
        } else {
            System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
            if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
                korisniciDetaljiPoImenu();
            }
        }
        korisniciIzbornik();
    }

    // POMOĆNE METODE KORISNIKA
    //  provjera kod unosa novog
    private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme) {
        return korisnici.stream().filter(o -> o.getKorisnickoIme().equals(korisnickoIme)).findFirst().isPresent();
    }

    // provjera kod izmjene
    private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme, final Korisnik korisnik) {
        boolean postojanje = false;
        for (Korisnik k : korisnici) {
            if (!k.equals(korisnik)) {
                if (k.getKorisnickoIme().equals(korisnickoIme)) {
                    postojanje = true;
                    break;
                }
            }
        }
        return postojanje;
    }

    private void korisniciIspisIzboraPretrage(String naslov) {
        Alati.ispisZaglavlja(naslov, true);
        System.out.println("1 za izlistanje svih korisnika od kojih će te izabrati željenog");
        System.out.println("2 za pretragu korisnika po imenu i/ili prezimenu ili korisničkom imenu");
    }

    private boolean korisniciJeLiOsobaKorisnik(Osoba osoba) {
        boolean valjanost = false;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getOsoba().equals(osoba)) {
                valjanost = true;
                break;
            }
        }
        return valjanost;
    }

    private Korisnik korisniciUnosOstalihPodataka(Korisnik korisnik) {
        korisnik.setLozinka(Alati.ucitajString("Unesite lozinku novoga korisnika: ", porukaGreskePraznogUnosa, 1, 100));
        korisnik.setRazina(Alati.ucitajBroj("Unesite razinu korisnika (1 djelatnik ili 2 admin): ", porukaGreskeIzboraAkcije, 1, 2));
        korisnik.setAktivan(Alati.daNe("Hoće li korisnik biti aktivan (da/ne): ", porukaGreskeIzboraAkcije));
        return korisnik;
    }

    private boolean korisniciJeLiToKorisnikPoNazivu(Korisnik korisnik, String uvjet) {
        String imePrezime = korisnik.getOsoba().getIme().toLowerCase() + " " + korisnik.getOsoba().getPrezime().toLowerCase();
        String prezimeIme = korisnik.getOsoba().getPrezime().toLowerCase() + " " + korisnik.getOsoba().getIme().toLowerCase();
        String korisnickoIme = korisnik.getKorisnickoIme().toLowerCase();
        uvjet = uvjet.trim().toLowerCase();
        if (imePrezime.contains(uvjet) || prezimeIme.contains(uvjet) || korisnickoIme.equals(uvjet)) {
            return true;
        } else {
            return false;
        }
    }

    private int korisniciIndeksKorisnikaIzIzvorneListe(Korisnik korisnik) {
        return korisnici.indexOf(korisnik);
    }

    private List<Korisnik> korisniciPronadjiPoUvjetu(String uvjet) {
        List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
        korisnici.stream().filter(
                korisnik -> (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet))).forEachOrdered(
                        korisnik -> {
                                    listaKorisnika.add(korisnik);
                        }
                );
        return listaKorisnika;
    }

    // izlistanje svih korisnika
    private void korisniciIzlistanje() {
        if (!korisnici.isEmpty()) {
            int counter = 1;
            Alati.ispisZaglavlja("Korisici koji se nalaze u bazi", false);
            for (Korisnik korisnik : korisnici) {
                System.out.println(counter + " " + korisnik.korisnikZaPrikaz());
                counter++;
            }
        } else {
            System.out.println(porukaGreskeNemaKorisnika);
        }
    }

    // izlistanje nađenih korisnika
    private void korisniciIzlistanje(String poruka, List<Korisnik> korisnici) {
        int counter;
        if (!korisnici.isEmpty()) {
            counter = 1;
            Alati.ispisZaglavlja(poruka, false);
            for (Korisnik korisnik : korisnici) {
                System.out.println(counter + " " + korisnik.korisnikZaPrikaz());
                counter++;
            }
        } else {
            System.out.println(porukaGreskeNemaKorisnika);
        }
    }

    /**
     *
     * KORISNICI KRAJ
     *
     * KATEGORIJE ARTIKALA
     *
     */
}
