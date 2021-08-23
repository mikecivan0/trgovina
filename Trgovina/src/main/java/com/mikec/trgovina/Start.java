package com.mikec.trgovina;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

	private static final double STOPA_POREZA = 25.0;
	private static final String LINK_GITHUB = "https://github.com/mikecivan0/trgovina";
	private static final String LINK_ER_DIJAGRAM = "https://github.com/mikecivan0/trgovina/blob/master/Trgovina/trgovina-database.png";
	private List<Osoba> osobe;
	private List<Korisnik> korisnici;
	private List<Kategorija> kategorije;
	private List<Artikal> artikli;
	private List<Stanje> stanja;
	private List<Klijent> klijenti;
	private List<Racun> racuni;
	private List<Stavka> stavke;
	private Korisnik trenutniKorisnik;
	private boolean valjanost;
	private final String porukaIzboraAkcije;
	private final String porukaGreskeIzboraAkcije;
	private final String porukaGreskePraznogUnosa;
	private final String porukaGreskeDaNe;
	private final String porukaGreskeNemaOsoba;
	private final String porukaGreskeNemaKorisnika;
	private final String porukaGreskeNemaKategorija;
	private final String porukaGreskeNemaArtikala;
	private final String porukaGreskeNemaStanja;
	private final String porukaGreskeNemaKlijenata;
	private final String porukaGreskeNemaRacuna;
//	private SimpleDateFormat formatDatuma;

	public static void main(String[] args) {
		new Start();
	}

	public Start() {
		porukaIzboraAkcije = "\nUnesite neku od gore ponuđenih stavki: ";
		porukaGreskeIzboraAkcije = "Nepostojeći izbor";
		porukaGreskePraznogUnosa = "Unos ne smije biti prazan";
		porukaGreskeDaNe = "Molimo unesite da ili ne";
		porukaGreskeNemaOsoba = "\nU bazi nema niti jedne osobe";
		porukaGreskeNemaKorisnika = "\nU bazi nema niti jednog korisnika";
		porukaGreskeNemaKategorija = "\nU bazi nema niti jedne kategorije";
		porukaGreskeNemaArtikala = "\nU bazi nema niti jednog artikla";
		porukaGreskeNemaStanja = "\nU bazi nema niti jednog artikla na lageru";
		porukaGreskeNemaKlijenata = "\nU bazi nema niti jednog klijenta";
		porukaGreskeNemaRacuna = "\nU bazi ne postoji niti jedan račun";
		korisnici = new ArrayList<Korisnik>();
		osobe = new ArrayList<Osoba>();
		kategorije = new ArrayList<Kategorija>();
		trenutniKorisnik = new Korisnik();
		artikli = new ArrayList<Artikal>();
		stanja = new ArrayList<Stanje>();
		klijenti = new ArrayList<Klijent>();
		racuni = new ArrayList<Racun>();
		stavke = new ArrayList<Stavka>();
//		formatDatuma = new SimpleDateFormat("dd.MM.yyyy.");
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

		// probni podaci kategorija
		kategorije.add(new Kategorija("Mali kućanski uređaji"));
		kategorije.add(new Kategorija("Voće"));

		// probni podaci artikala
		artikli.add(new Artikal(kategorije.get(0), "Mikser", 120, false, 0, "Opis miksera"));
		artikli.add(new Artikal(kategorije.get(0), "Fen", 250, true, 120, "Opis fena"));
		artikli.add(new Artikal(kategorije.get(1), "Jabuka", 12, false, 0, "Opis jabuke"));
		artikli.add(new Artikal(kategorije.get(1), "Kruška", 25, true, 19, "Opis kruške"));

		// probni podaci stanja
		stanja.add(new Stanje(artikli.get(0), 1, 5));
		
		// probni podaci klijenata
		klijenti.add(new Klijent(osobe.get(0), "ulica 5", "mjesto", "32658"));

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
		case 1 -> login();
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
			if (korisnik.getKorisnickoIme().equals(k.getKorisnickoIme()) && korisnik.getLozinka().equals(k.getLozinka())
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
		System.out.println("3 za rad sa klijentima");
		System.out.println("4 za rad sa računima");
		System.out.println("5 za odjavu iz aplikacije");
		djelatnikGlavniIzbornikOdabirAkcije();
	}

	private void djelatnikGlavniIzbornikOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 5)) {
			case 1 -> kategorijeIzbornik();
			case 2 -> artikliIzbornik();
			case 3 -> klijentiIzbornik();
	        case 4 -> racuniIzbornik();
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
		Alati.ispisZaglavlja("Glavni izbornik za administratore", true);
		System.out.println("1 za rad sa osobama");
		System.out.println("2 za rad sa korisnicima");
		System.out.println("3 za rad sa kategorijama artikala");
		System.out.println("4 za rad sa artiklima");
		System.out.println("5 za rad sa lagerom");
		System.out.println("6 za rad sa klijentima");
		System.out.println("7 za rad sa računima");
		System.out.println("8 za odjavu iz aplikacije");
		adminGlavniIzbornikOdabirAkcije();
	}

	private void adminGlavniIzbornikOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 8)) {
			case 1 -> osobeIzbornik();
			case 2 -> korisniciIzbornik();
			case 3 -> kategorijeIzbornik();
			case 4 -> artikliIzbornik();
			case 5 -> stanjaIzbornik();
			case 6 -> klijentiIzbornik();
	            case 7 -> racuniIzbornik();
			case 8 -> {
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
			case 2 -> osobeIzmjena();
			case 3 -> osobeBrisanje();
			case 4 -> {
				osobeIzlistanje();
				osobeIzbornik();
			}
			case 5 -> {
				osobeDetalji();
				osobeIzbornik();
			}
			case 6 -> adminGlavniIzbornik();
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
			case 1 -> osobeIzmjenaPoIndeksu();
			case 2 -> osobeIzmjenaPoImenu();
		}
	}

	private void osobeIzmjenaPoIndeksu() {
		int i;
		Osoba osoba = new Osoba();
		osoba = osobeOdabirPoIndeksu("Unesite broj osobe koju želite izmijeniti: ");
		i = osobe.indexOf(osoba);
		osobeIzmjenaPodataka(osoba, i);
	}

	private void osobeIzmjenaPoImenu() {
		String uvjet;
		int izbor, indeksOdabraneOsobe;
		List<Osoba> nadjeneOsobe = new ArrayList<Osoba>();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			izbor = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
					nadjeneOsobe.size()) - 1;
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
			case 1 -> osobeBrisanjePoIndeksu();
			case 2 -> osobeBrisanjePoImenu();
		}
	}

	private void osobeBrisanjePoIndeksu() {
		Osoba osoba = new Osoba();
		osoba = osobeOdabirPoIndeksu("Unesite broj osobe koju želite obrisati: ");
		if (Alati.daNe("Želite li zaista obrisati osobu " + osoba.imeIPrezime() + " (da/ne): ", porukaGreskeDaNe)) {
			if (!korisniciJeLiOsobaKorisnik(osoba)) {
				osobe.remove(osoba);
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
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			izbor = Alati.ucitajBroj("Unesite broj osobe koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					nadjeneOsobe.size()) - 1;
			indeksOdabraneOsobe = osobeIndeksOsobeIzIzvorneListe(nadjeneOsobe.get(izbor));
			if (Alati.daNe(
					"Želite li zaista obrisati osobu " + osobe.get(indeksOdabraneOsobe).imeIPrezime() + " (da/ne): ",
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
			case 1 -> osobeDetaljiPoIndeksu();
			case 2 -> osobeDetaljiPoImenu();
		}
	}

	private void osobeDetaljiPoIndeksu() {
		Osoba osoba = new Osoba();
		osoba = osobeOdabirPoIndeksu("Unesite broj osobe čije detalje želite pogledati: ");
		Alati.ispisZaglavlja("Detalji osobe", false);
		System.out.println(osoba.toString());
		osobeIzbornik();
	}

	private void osobeDetaljiPoImenu() {
		String uvjet;
		int izbor;
		List<Osoba> nadjeneOsobe = new ArrayList<Osoba>();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			izbor = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
					nadjeneOsobe.size()) - 1;
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
				System.out.println(counter++ + " " + osoba.imeIPrezime());
			}
		} else {
			System.out.println(porukaGreskeNemaOsoba);
			osobeIzbornik();
		}
	}

	// izlistanje nađenih osoba
	private void osobeIzlistanje(String poruka, List<Osoba> osobe) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Osoba osoba : osobe) {
			System.out.println(counter++ + " " + osoba.imeIPrezime());
		}
	}

	private List<Osoba> osobePronadjiPoUvjetu(String uvjet) {
		List<Osoba> listaOsoba = new ArrayList<Osoba>();
		osobe.stream().filter(osoba -> (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet))).forEachOrdered(osoba -> {
			listaOsoba.add(osoba);
		});
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

	private Osoba osobeOdabirPoIndeksu(String poruka) {
		int i;
		osobeIzlistanje();
		i = Alati.ucitajBroj(poruka, porukaGreskeIzboraAkcije, 1, osobe.size()) - 1;
		return osobe.get(i);
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
			case 1 -> korisniciUnosNovog();
			case 2 -> korisniciIzmjena();
			case 3 -> korisniciBrisanje();
			case 4 -> {
				korisniciIzlistanje();
				korisniciIzbornik();
			}
			case 5 -> {
				korisniciDetalji();
				korisniciIzbornik();
			}
			case 6 -> adminGlavniIzbornik();
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
			case 1 -> korisniciUnosPostojeceOsobeKaoNovogKorisnika();
			case 2 -> korisniciUnosNoveOsobeKaoNovogKorisnika();
			case 3 -> korisniciIzbornik();
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
					String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ",
							porukaGreskePraznogUnosa, 1, 50);
					if (!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
						korisnik.setKorisnickoIme(korisnickoIme);
						korisnik = korisniciUnosOstalihPodataka(korisnik);
						korisnici.add(korisnik);
						System.out.println("\nOsoba je unešena i postavljena kao novi korisnik");
					} else {
						if (Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ",
								porukaGreskeDaNe)) {
							continue;
						}
					}
					break;
				}
			} else {
				if (Alati.daNe("Osoba je već unešena kao korisnik. Želite li odabrati drugu osobu? (da/ne): ",
						porukaGreskeDaNe)) {
					korisniciUnosPostojeceOsobeKaoNovogKorisnika();
				}
			}
		} else {
			System.out.println(
					"\nU bazi ne postoji niti jedna osoba. \nDa bi Ste mogli dodati osobu kao novog korisnika prvo unesite najmanje jednu osobu.");
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
			korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1,
					50);
			if (!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik = korisniciUnosOstalihPodataka(korisnik);
				korisnici.add(korisnik);
				System.out.println("\nNova osoba je unešena i postavljena kao novi korisnik");
			} else {
				if (Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ",
						porukaGreskeDaNe)) {
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
			case 1 -> korisniciIzmjenaPoIndeksu();
			case 2 -> korisniciIzmjenaPoImenu();
		}

	}

	private void korisniciIzmjenaPoIndeksu() {
		int i;
		Korisnik korisnik = new Korisnik();
		korisnik = korisniciOdabirPoIndeksu("Unesite broj korisnika kojeg želite izmijeniti: ");
		i = korisnici.indexOf(korisnik);
		korisniciIzmjenaPodataka(korisnik, i);
	}

	private void korisniciIzmjenaPoImenu() {
		String uvjet;
		int izbor, indeksIzabranogKorisnika;
		List<Korisnik> nadjeniKorisnici = new ArrayList<Korisnik>();
		Korisnik izabraniKorisnik = new Korisnik();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ",
				porukaGreskePraznogUnosa, 0, 30);
		nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (!nadjeniKorisnici.isEmpty()) {
			korisniciIzlistanje("Pronađeni korsnici", nadjeniKorisnici);
			izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
					nadjeniKorisnici.size()) - 1;
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
			case 1 -> korisniciBrisanjePoIndeksu();
			case 2 -> korisniciBrisanjePoNazivu();
		}
	}

	private void korisniciBrisanjePoIndeksu() {
		Korisnik korisnik = new Korisnik();
		korisnik = korisniciOdabirPoIndeksu("Unesite broj korisnika kojeg želite obrisati: ");
		if (Alati.daNe("Želite li zaista obrisati korisnika " + korisnik.korisnikZaPrikaz() + ": ", porukaGreskeDaNe)) {
			korisnici.remove(korisnik);
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
			izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					nadjeniKorisnici.size()) - 1;
			odabraniKorisnik = nadjeniKorisnici.get(izbor);
			indeksKorisnikaIzIzvorneListe = korisniciIndeksKorisnikaIzIzvorneListe(odabraniKorisnik);
			if (Alati.daNe(
					"Želite li zaista obrisati odabranog korisnika ("
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
			case 1 -> korisniciDetaljiPoIndeksu();
			case 2 -> korisniciDetaljiPoImenu();
		}
	}

	private void korisniciDetaljiPoIndeksu() {
		Korisnik korisnik = new Korisnik();
		korisnik = korisniciOdabirPoIndeksu("Unesite broj korisnika čije detalje želite pogledati: ");
		Alati.ispisZaglavlja("Detalji korisnika", false);
		System.out.println(korisnik.toString());
		korisniciIzbornik();
	}

	private void korisniciDetaljiPoImenu() {
		String uvjet;
		int izbor;
		List<Korisnik> nadjeniKorisnici = new ArrayList<Korisnik>();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime osobe/korisnika kojeg tražite: ",
				porukaGreskePraznogUnosa, 0, 30);
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
	// provjera kod unosa novog
	private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme) {
		return korisnici.stream().filter(o -> o.getKorisnickoIme().equals(korisnickoIme)).findFirst().isPresent();
	}

	// provjera kod izmjene
	private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme, final Korisnik korisnik) {
		boolean postojanje = false;
		for (Korisnik k : korisnici) {
			if (!k.equals(korisnik) && k.getKorisnickoIme().equals(korisnickoIme)) {
				postojanje = true;
				break;
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
		korisnik.setRazina(Alati.ucitajBroj("Unesite razinu korisnika (1 djelatnik ili 2 admin): ",
				porukaGreskeIzboraAkcije, 1, 2));
		korisnik.setAktivan(Alati.daNe("Hoće li korisnik biti aktivan (da/ne): ", porukaGreskeIzboraAkcije));
		return korisnik;
	}

	private boolean korisniciJeLiToKorisnikPoNazivu(Korisnik korisnik, String uvjet) {
		String imePrezime = korisnik.getOsoba().getIme().toLowerCase() + " "
				+ korisnik.getOsoba().getPrezime().toLowerCase();
		String prezimeIme = korisnik.getOsoba().getPrezime().toLowerCase() + " "
				+ korisnik.getOsoba().getIme().toLowerCase();
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
		korisnici.stream().filter(korisnik -> (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)))
				.forEachOrdered(korisnik -> {
					listaKorisnika.add(korisnik);
				});
		return listaKorisnika;
	}

	// izlistanje svih korisnika
	private void korisniciIzlistanje() {
		if (!korisnici.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("Korisici koji se nalaze u bazi", false);
			for (Korisnik korisnik : korisnici) {
				System.out.println(counter++ + " " + korisnik.korisnikZaPrikaz());
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
				System.out.println(counter++ + " " + korisnik.korisnikZaPrikaz());
			}
		} else {
			System.out.println(porukaGreskeNemaKorisnika);
		}
	}

	private Korisnik korisniciOdabirPoIndeksu(String poruka) {
		int i;
		korisniciIzlistanje();
		i = Alati.ucitajBroj(poruka, porukaGreskeIzboraAkcije, 1, korisnici.size()) - 1;
		return korisnici.get(i);
	}

	/**
	 *
	 * KORISNICI KRAJ
	 *
	 * KATEGORIJE ARTIKALA
	 *
	 */
	private void kategorijeIzbornik() {
		Alati.ispisZaglavlja("Rad sa kategorijama artikala", true);
		System.out.println("1 za unos nove kategorije");
		System.out.println("2 za izmjenu postojeće kategorije");
		System.out.println("3 za brisanje postojeće kategorije");
		System.out.println("4 za pregled svih kategorija");
		System.out.println("5 za pregled detalja postojeće kategorije");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		kategorijeOdabirAkcije();
	}

	private void kategorijeOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> kategorijeUnosNove();
			case 2 -> kategorijeIzmjena();
			case 3 -> kategorijeBrisanje();
			case 4 -> {
				kategorijeIzlistanje();
				kategorijeIzbornik();
			}
			case 5 -> kategorijeDetalji();
			case 6 -> djelatnikGlavniIzbornik();
		}
	}

	private void kategorijeUnosNove() {
		while (true) {
			String naziv;
			Kategorija kategorija = new Kategorija();
			naziv = Alati.ucitajString("\nUnesite naziv nove kategorije: ", porukaGreskePraznogUnosa, 1, 30);
			if (!kategorijePostojiLi(naziv)) {
				kategorija.setNaziv(naziv);
				kategorije.add(kategorija);
				System.out.println("\nNova kategorija je dodana");
				if (Alati.daNe("\nŽelite li dodati još koju kategoriju? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			} else {
				System.out.println("\nKategorija sa tim nazivom već postoji");
				if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			break;
		}
		kategorijeIzbornik();
	}

	private void kategorijeIzmjena() {
		int i;
		Kategorija kategorija;
		if (!kategorije.isEmpty()) {
			kategorija = new Kategorija();
			kategorijeIzlistanje();
			i = Alati.ucitajBroj("Unesite broj ispred kategorije koju " + "želite izmijeniti: ",
					porukaGreskeIzboraAkcije, 1, kategorije.size()) - 1;
			kategorija = kategorije.get(i);
			kategorijeIzmjenaPodataka(kategorija, i);
		} else {
			System.out.println(porukaGreskeNemaKategorija);
			kategorijeIzbornik();
		}
	}

	private void kategorijeIzmjenaPodataka(Kategorija kategorija, int i) {
		while (true) {
			String naziv;
			naziv = Alati.ucitajString("Unesite naziv kategorije", porukaGreskePraznogUnosa, 1, 30);
			if (!kategorijePostojiLi(naziv, kategorija)) {
				kategorija.setNaziv(naziv);
				kategorije.set(i, kategorija);
				System.out.println("\nNaziv kategorije je izmijenjen");
				break;
			} else {
				System.out.println("\nKategorija sa tim nazivom već postoji");
				if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			break;
		}
		kategorijeIzbornik();
	}

	private void kategorijeBrisanje() {
		int i;
		if (!kategorije.isEmpty()) {
			Kategorija kategorija = new Kategorija();
			kategorijeIzlistanje();
			i = Alati.ucitajBroj("Unesite broj ispred kategorije koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					kategorije.size()) - 1;
			kategorija = kategorije.get(i);
			if (Alati.daNe("Želite li zaista obrisati kategoriju " + kategorija.getNaziv() + ": ", porukaGreskeDaNe)) {
				if (!kategorijeJeLiDodijeljenaArtiklu(kategorija)) {
					kategorije.remove(i);
					System.out.println("\nKategorija je obrisana.");
				} else {
					System.out.println("\nKategorija ne može biti obrisana jer je dodijeljena artiklu");
				}
			}
		} else {
			System.out.println(porukaGreskeNemaKategorija);
		}
		kategorijeIzbornik();
	}

	private void kategorijeDetalji() {
		int i;
		if (!kategorije.isEmpty()) {
			kategorijeIzlistanje();
			i = Alati.ucitajBroj("Unesite redni broj ispred kategorije koju želite detaljnije pogledati: ",
					porukaGreskeIzboraAkcije, 1, kategorije.size()) - 1;
			Alati.ispisZaglavlja("Detalji kategorije", false);
			System.out.println(kategorije.get(i).toString());
		} else {
			System.out.println(porukaGreskeNemaKategorija);
		}
		kategorijeIzbornik();
	}

	// POMOĆNE METODE KATEGORIJA
	private void kategorijeIzlistanje() {
		if (!kategorije.isEmpty()) {
			System.out.println("\nU bazi postoje sljedeće kategorije:");
			int counter = 1;
			for (Kategorija k : kategorije) {
				System.out.println(counter++ + " " + k.getNaziv() + "");
			}
		} else {
			System.out.println(porukaGreskeNemaKategorija);
		}
	}

	private boolean kategorijeJeLiDodijeljenaArtiklu(Kategorija kategorija) {
		boolean postojanje = false;
		for (Artikal a : artikli) {
			if (a.getKategorija().equals(kategorija)) {
				postojanje = true;
				break;
			}
		}
		return postojanje;
	}

	private boolean kategorijePostojiLi(String naziv) {
		return kategorije.stream().filter(kategorija -> kategorija.getNaziv().equals(naziv)).findFirst().isPresent();

	}

	private boolean kategorijePostojiLi(String naziv, Kategorija kategorija) {
		boolean postojanje = false;
		for (Kategorija k : kategorije) {
			if (!k.equals(kategorija) && k.getNaziv().equals(naziv)) {
				postojanje = true;
			}
		}
		return postojanje;
	}

	/**
	 * 
	 * KATEGORIJE KRAJ
	 * 
	 * ARTIKLI
	 * 
	 */

	private void artikliIzbornik() {
		Alati.ispisZaglavlja("Rad sa artiklima", true);
		System.out.println("1 za unos novog atikla");
		System.out.println("2 za izmjenu postojećeg artikla");
		System.out.println("3 za brisanje postojećeg artikla");
		System.out.println("4 za pregled svih artikala");
		System.out.println("5 za pregled detalja postojećeg artikla");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		artikliOdabirAkcije();
	}

	private void artikliOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> artikliUnosNovog();
			case 2 -> artikliIzmjena();
			case 3 -> artikliBrisanje();
			case 4 -> {
				artikliIzlistanje();
				artikliIzbornik();
			}
			case 5 -> artikliDetalji();
			case 6 -> {
				if (trenutniKorisnik.getRazina() == 1) {
					djelatnikGlavniIzbornik();
				} else {
					adminGlavniIzbornik();
				}
			}
		}
	}

	private void artikliUnosNovog() {
		if (!kategorije.isEmpty()) {
			while (true) {
				String naziv;
				Artikal artikal = new Artikal();
				naziv = Alati.ucitajString("\nUnesite naziv novog artikla: ", porukaGreskePraznogUnosa, 1, 50);
				if (!artikliPostojiLi(naziv)) {
					artikal = artikliUcitajOstaleVrijednosti(naziv);
					artikli.add(artikal);
					System.out.println("\nNovi artikal je dodan");
					if (Alati.daNe("\nŽelite li dodati još koji artikal? (da/ne): ", porukaGreskeDaNe)) {
						continue;
					}
				} else {
					System.out.println("\nArtikal sa tim nazivom već postoji");
					if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
						continue;
					}
				}
				break;
			}
		} else {
			System.out.println(porukaGreskeNemaKategorija);
			System.out.println("Prvo unesite barem jednu kategoriju kako bi Ste mogli raditi sa podacima artikala.");
		}
		artikliIzbornik();
	}

	private void artikliIzmjena() {
		if (!artikli.isEmpty()) {
			artikliIspisIzboraPretrage("Izmjena podataka artikla");
			artikliUcitajOdabirPretrageZaIzmjenu();
		} else {
			System.out.println(porukaGreskeNemaArtikala);
			artikliIzbornik();
		}
	}

	private void artikliUcitajOdabirPretrageZaIzmjenu() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> artikliIzmjenaPoIndeksu();
			case 2 -> artikliIzmjenaPoNazivu();
		}
	}

	private void artikliIzmjenaPoIndeksu() {
		int i;
		Artikal artikal = new Artikal();
		artikal = artikliOdabirPoIndeksu("\nUnesite broj artikla koji želite izmijeniti: ");
		i = artikli.indexOf(artikal);
		artikliIzmjenaPodataka(artikal, i);
		artikliIzbornik();
	}

	private void artikliIzmjenaPoNazivu() {
		String uvjet;
		int izbor, indeksOdabranogArtikla;
		List<Artikal> nadjeniArtikli = new ArrayList<Artikal>();
		uvjet = Alati.ucitajString("Upišite naziv artikla kojeg tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeniArtikli = artikliPronadjiPoUvjetu(uvjet);
		if (!nadjeniArtikli.isEmpty()) {
			artikliIzlistanje("Pronađeni artikli", nadjeniArtikli);
			izbor = Alati.ucitajBroj("\nUnesite broj artikla koji želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
					nadjeniArtikli.size()) - 1;
			indeksOdabranogArtikla = artikliIndeksArtiklaIzIzvorneListe(nadjeniArtikli.get(izbor));
			artikliIzmjenaPodataka(artikli.get(indeksOdabranogArtikla), indeksOdabranogArtikla);
			artikliIzbornik();
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				artikliIzmjenaPoNazivu();
			} else {
				osobeIzbornik();
			}
		}
	}

	private void artikliIzmjenaPodataka(Artikal artikal, int i) {
		while (true) {
			String naziv;
			Artikal a = new Artikal();
			Alati.ispisZaglavlja("Trenutni podaci odabranog artikla", false);
			System.out.println(artikal.toString());
			naziv = Alati.ucitajString("\nUnesite naziv artikla: ", porukaGreskePraznogUnosa, 1, 50);
			if (!artikliPostojiLi(artikal, naziv)) {
				artikal.setNaziv(naziv);
				a = artikliUcitajOstaleVrijednosti(artikal);
				artikli.set(i, a);
				System.out.println("\nArtikal je izmijenjen");
			} else {
				System.out.println("\nArtikal sa tim nazivom već postoji");
				if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			break;
		}
	}

	private void artikliBrisanje() {
		if (!artikli.isEmpty()) {
			artikliIspisIzboraPretrage("Brisanje artikla");
			artikliUcitajOdabirPretrageZaBrisanje();
		} else {
			System.out.println(porukaGreskeNemaOsoba);
			artikliIzbornik();
		}
	}

	private void artikliUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> artikliBrisanjePoIndeksu();
			case 2 -> artikliBrisanjePoImenu();
		}
	}

	private void artikliBrisanjePoIndeksu() {
		Artikal artikal = new Artikal();
		artikal = artikliOdabirPoIndeksu("\nUnesite broj artikla koji želite obrisati: ");
		if (Alati.daNe("Želite li zaista obrisati artikal " + artikal.getNaziv() + " (da/ne): ", porukaGreskeDaNe)) {
			if (!stanjaPostojanjeArtiklaNaStanju(artikal) && !racuniPostojanjeArtiklaUStavkama(artikal)) {
				artikli.remove(artikal);
				System.out.println("\nArtikal je obrisan.");
			} else {
				System.out.println("\nOdabrani artikal se ne može obrisati jer se nalazi na lageru ili je uveden u račune.");
			}
		}
		artikliIzbornik();
	}

	private void artikliBrisanjePoImenu() {
		String uvjet;
		int izbor, indeksOdabranogArtikla;
		List<Artikal> nadjeniArtikli = new ArrayList<Artikal>();
		uvjet = Alati.ucitajString("Upišite naziv artikla koji tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeniArtikli = artikliPronadjiPoUvjetu(uvjet);
		if (!nadjeniArtikli.isEmpty()) {
			artikliIzlistanje("Pronađeni artikli", nadjeniArtikli);
			izbor = Alati.ucitajBroj("\nUnesite broj artikla koji želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					nadjeniArtikli.size()) - 1;
			indeksOdabranogArtikla = artikliIndeksArtiklaIzIzvorneListe(nadjeniArtikli.get(izbor));
			if (Alati.daNe("Želite li zaista obrisati artikal " + artikli.get(indeksOdabranogArtikla).getNaziv()
					+ " (da/ne): ", porukaGreskeDaNe)) {
				if (!stanjaPostojanjeArtiklaNaStanju(artikli.get(indeksOdabranogArtikla)) 
						&& !racuniPostojanjeArtiklaUStavkama(artikli.get(indeksOdabranogArtikla))) {
					artikli.remove(indeksOdabranogArtikla);
					System.out.println("\nArtikal je obrisan.");
				} else {
					System.out.println("\nOdabrani artikal se nalazi na lageru i stoga ga ne možete obrisati.");
				}
			}
		} else {
			System.out.println("\nNema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				artikliBrisanjePoImenu();
			}
		}
		artikliIzbornik();
	}

	private void artikliDetalji() {
		if (!artikli.isEmpty()) {
			artikliIspisIzboraPretrage("Detalji artikla");
			artikliUcitajOdabirPretrageZaDetalje();
		} else {
			System.out.println(porukaGreskeNemaArtikala);
			artikliIzbornik();
		}
	}

	private void artikliUcitajOdabirPretrageZaDetalje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> artikliDetaljiPoIndeksu();
			case 2 -> artikliDetaljiPoNazivu();
		}
	}

	private void artikliDetaljiPoIndeksu() {
		Artikal artikal = new Artikal();
		Stanje stanje;
		artikliIzlistanje();
		artikal = artikliOdabirPoIndeksu("\nUnesite broj artikla čije detalje želite pogledati: ");
		System.out.println(artikal.toString());
		if (stanjaPostojanjeArtiklaNaStanju(artikal)) {
			stanje = new Stanje();
			stanje = stanjaNadjiPoArtiklu(artikal);
			System.out.println(stanje.ispisStanjaKodArtikala());
		}
		artikliIzbornik();
	}

	private void artikliDetaljiPoNazivu() {
		String uvjet;
		int izbor;
		Artikal artikal = new Artikal();
		Stanje stanje = new Stanje();
		List<Artikal> nadjeniArtikli = new ArrayList<Artikal>();
		uvjet = Alati.ucitajString("Upišite naziv artikla koji tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeniArtikli = artikliPronadjiPoUvjetu(uvjet);
		if (!nadjeniArtikli.isEmpty()) {
			artikliIzlistanje("Pronađeni artikli", nadjeniArtikli);
			izbor = Alati.ucitajBroj("Unesite broj ispred artikla čije detalje želite pogledati: ",
					porukaGreskeIzboraAkcije, 1, nadjeniArtikli.size()) - 1;
			Alati.ispisZaglavlja("Detalji artikla", false);
			artikal = nadjeniArtikli.get(izbor);
			System.out.println(artikal.toString());
			if (stanjaPostojanjeArtiklaNaStanju(artikal)) {
				stanje = new Stanje();
				stanje = stanjaNadjiPoArtiklu(artikal);
				System.out.println(stanje.ispisStanjaKodArtikala());
			}
			artikliIzbornik();
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				artikliDetaljiPoNazivu();
			} else {
				artikliIzbornik();
			}
		}
	}

	// POMOĆNE METODE ARTIKALA
	// unos novog
	private boolean artikliPostojiLi(String naziv) {
		return artikli.stream().filter(Artikal -> Artikal.getNaziv().equals(naziv)).findFirst().isPresent();
	}

	// izmjena
	private boolean artikliPostojiLi(Artikal artikal, String naziv) {
		boolean postojanje = false;
		for (Artikal a : artikli) {
			if (!a.equals(artikal) && a.getNaziv().equals(naziv)) {
				postojanje = true;
				break;
			}
		}
		return postojanje;
	}

	private Artikal artikliUcitajOstaleVrijednosti(String naziv) {
		int i;
		double cijena, akcijskaCijena = 0;
		boolean akcija;
		String opis = "";
		Artikal artikal;
		kategorijeIzlistanje();
		i = Alati.ucitajBroj("\nUnesite broj ispred kategorije kojoj artikal pripada: ", porukaGreskeIzboraAkcije, 1,
				kategorije.size()) - 1;
		cijena = Alati.ucitajDouble("Unesite cijenu artikla: ", porukaGreskePraznogUnosa, 0.01, 0);
		akcija = Alati.daNe("Je li artikal trenutno na akciji? (da/ne): ", porukaGreskeDaNe);
		if (akcija) {
			if (Alati.daNe("Želite li unijeti akcijsku cijenu artikla? (da/ne): ", porukaGreskeDaNe)) {
				akcijskaCijena = Alati.ucitajDouble("Unesite akcijsku cijenu artikla: ", porukaGreskePraznogUnosa, 0.01,
						0);
			}
		}
		if (Alati.daNe("Želite li unijeti opis artikla? (da/ne): ", porukaGreskeDaNe)) {
			opis = Alati.ucitajString("Unesite opis artikla: ", porukaGreskePraznogUnosa, 1, 0);
		}
		artikal = new Artikal(kategorije.get(i), naziv, cijena, akcija, akcijskaCijena, opis);
		return artikal;
	}

	private Artikal artikliUcitajOstaleVrijednosti(Artikal a) {
		int i;
		double cijena, akcijskaCijena = a.getAkcijskaCijena();
		boolean akcija;
		String opis = a.getOpis();
		kategorijeIzlistanje();
		i = Alati.ucitajBroj("\nUnesite broj ispred kategorije kojoj artikal pripada: ", porukaGreskeIzboraAkcije, 1,
				kategorije.size()) - 1;
		cijena = Alati.ucitajDouble("Unesite cijenu artikla: ", porukaGreskePraznogUnosa, 0.01, 0);
		akcija = Alati.daNe("Je li artikal trenutno na akciji? (da/ne): ", porukaGreskeDaNe);
		if (akcija) {
			if (Alati.daNe("Želite li unijeti akcijsku cijenu artikla? (da/ne): ", porukaGreskeDaNe)) {
				akcijskaCijena = Alati.ucitajDouble("Unesite akcijsku cijenu artikla: ", porukaGreskePraznogUnosa, 0.01,
						0);
			}
		}
		if (Alati.daNe("Želite li unijeti opis artikla? (da/ne): ", porukaGreskeDaNe)) {
			opis = Alati.ucitajString("Unesite opis artikla: ", porukaGreskePraznogUnosa, 1, 0);
		}
		a.setKategorija(kategorije.get(i));
		a.setCijena(cijena);
		a.setAkcija(akcija);
		a.setAkcijskaCijena(akcijskaCijena);
		a.setOpis(opis);

		return a;
	}

	private void artikliIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih artikala od kojih će te izabrati željeni");
		System.out.println("2 za pretragu artikala po nazivu");
	}

	private void artikliIzlistanje() {
		if (!artikli.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("Artikli koji se nalaze u bazi", false);
			for (Artikal a : artikli) {
				System.out.println(counter++ + " " + a.ispis());
			}
		} else {
			System.out.println(porukaGreskeNemaArtikala);
		}
	}

	private void artikliIzlistanje(String poruka, List<Artikal> artikli) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Artikal a : artikli) {
			System.out.println(counter++ + " " + a.ispis());
		}
	}

	private List<Artikal> artikliPronadjiPoUvjetu(String uvjet) {
		List<Artikal> listaArtikala = new ArrayList<Artikal>();
		artikli.stream().filter(artikal -> (artikliJeLiToArtikalPoNazivu(artikal, uvjet))).forEachOrdered(artikal -> {
			listaArtikala.add(artikal);
		});
		return listaArtikala;
	}

	private boolean artikliJeLiToArtikalPoNazivu(Artikal artikal, String uvjet) {
		String naziv = artikal.getNaziv().toLowerCase();
		uvjet = uvjet.trim().toLowerCase();
		return naziv.contains(uvjet);
	}

	private int artikliIndeksArtiklaIzIzvorneListe(Artikal artikal) {
		return artikli.indexOf(artikal);
	}

	private Artikal artikliOdabirPoIndeksu(String poruka) {
		int i;
		artikliIzlistanje();
		i = Alati.ucitajBroj(poruka, porukaGreskeIzboraAkcije, 1, artikli.size()) - 1;
		return artikli.get(i);
	}

	/**
	 * 
	 * ARTIKLI KRAJ
	 * 
	 * STANJA
	 * 
	 */

	private void stanjaIzbornik() {
		Alati.ispisZaglavlja("Rad sa lagerom", true);
		System.out.println("1 za unos novog stanja određenog artikla");
		System.out.println("2 za izmjenu postojećeg stanja određenog artikla");
		System.out.println("3 za brisanje postojećeg stanja određenog artikla");
		System.out.println("4 za pregled svih stanja svih artikala");
		System.out.println("5 za pregled detalja stanja određenog artikla");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		stanjaOdabirAkcije();
	}

	private void stanjaOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> stanjaUnosNovog();
			case 2 -> stanjaIzmjena();
			case 3 -> stanjaBrisanje();
			case 4 -> {
				stanjaIzlistanje();
				stanjaIzbornik();
			}
			case 5 -> stanjaDetalji();
			case 6 -> adminGlavniIzbornik();
		}
	}

	private void stanjaUnosNovog() {
		Artikal artikal = new Artikal();
		double naStanju, prodano;
		if (!artikli.isEmpty()) {
			Alati.ispisZaglavlja("Dodavanje novog artikla na lager", false);
			artikliIspisIzboraPretrage("Odabir artikla");
			artikal = stanjaUcitajOdabirPretrageArtikla();
			if (!stanjaPostojanjeArtiklaNaStanju(artikal)) {
				naStanju = Alati.ucitajDouble("Koliko je trenutno raspoloživo artikala (" + artikal.getNaziv() + "): ",
						porukaGreskePraznogUnosa, 1, 0);
				prodano = Alati.ucitajBroj("Koliko je prodano artikala (" + artikal.getNaziv() + "): ",
						porukaGreskePraznogUnosa, 1, 0);
				stanja.add(new Stanje(artikal, naStanju, prodano));
				System.out.println("\nArtikal je dodan na lager");
			} else {
				System.out.println("\nArtikal se već nalazi na lageru");
			}
		} else {
			System.out.println(porukaGreskeNemaArtikala);
		}
		stanjaIzbornik();
	}

	private Artikal stanjaUcitajOdabirPretrageArtikla() {
		Artikal a = new Artikal();
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> a = artikliOdabirPoIndeksu("\nUnesite broj artikla kojeg želite dodati na lager: ");
			case 2 -> a = stanjaOdabirArtiklaPoNazivu();
		}
		return a;
	}

	private Artikal stanjaOdabirArtiklaPoNazivu() {
		String uvjet;
		int izbor;
		Artikal artikal = new Artikal();
		List<Artikal> nadjeniArtikli = new ArrayList<Artikal>();
		uvjet = Alati.ucitajString("Upišite naziv artikla koji tražite: ", porukaGreskePraznogUnosa, 0, 30);
		nadjeniArtikli = artikliPronadjiPoUvjetu(uvjet);
		if (!nadjeniArtikli.isEmpty()) {
			artikliIzlistanje("Pronađeni artikli", nadjeniArtikli);
			izbor = Alati.ucitajBroj("Unesite broj ispred artikla koji želite dodati na lager: ",
					porukaGreskeIzboraAkcije, 1, nadjeniArtikli.size()) - 1;
			artikal = nadjeniArtikli.get(izbor);

		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				stanjaOdabirArtiklaPoNazivu();
			} else {
				stanjaIzbornik();
			}
		}
		return artikal;
	}

	private void stanjaIzmjena() {
		if (!stanja.isEmpty()) {
			String uvjet;
			int izbor, indeksStanjaSaIzvorneListe;
			Stanje odabranoStanje = new Stanje();
			List<Stanje> pronadjenaStanja = new ArrayList<Stanje>();
			uvjet = Alati.ucitajString("\nUpišite ime artikla čije stanje želite izmijeniti: ",
					porukaGreskePraznogUnosa, 1, 30);
			pronadjenaStanja = stanjaPronadjiPoUvjetu(uvjet);
			if (!pronadjenaStanja.isEmpty()) {
				Alati.ispisZaglavlja("Pronađeni artikli", false);
				stanjaIzlistanje(pronadjenaStanja);
				izbor = Alati.ucitajBroj("\nUpišite broj ispred zapisa koji želite izmijeniti: ",
						porukaGreskeIzboraAkcije, 1, pronadjenaStanja.size()) - 1;
				odabranoStanje = pronadjenaStanja.get(izbor);
				indeksStanjaSaIzvorneListe = stanjaIndeksStanjaIzIzvorneListe(odabranoStanje);
				odabranoStanje = stanjaUnosOstalihPodataka(odabranoStanje);
				stanja.set(indeksStanjaSaIzvorneListe, odabranoStanje);
				System.out.println("\nStanje artikla na lageru je izmijenjeno");
			} else {
				System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
				if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
					stanjaIzmjena();
				}
			}
		} else {
			System.out.println(porukaGreskeNemaStanja);
		}
		stanjaIzbornik();
	}

	private void stanjaBrisanje() {
		if (!stanja.isEmpty()) {
			String uvjet;
			int izbor;
			Stanje odabranoStanje = new Stanje();
			List<Stanje> pronadjenaStanja = new ArrayList<Stanje>();
			uvjet = Alati.ucitajString("\nUpišite ime artikla čije stanje želite izmijeniti: ",
					porukaGreskePraznogUnosa, 1, 30);
			pronadjenaStanja = stanjaPronadjiPoUvjetu(uvjet);
			if (!pronadjenaStanja.isEmpty()) {
				Alati.ispisZaglavlja("Pronađeni artikli", false);
				stanjaIzlistanje(pronadjenaStanja);
				izbor = Alati.ucitajBroj("\nUpišite broj ispred zapisa koji želite obrisati: ",
						porukaGreskeIzboraAkcije, 1, pronadjenaStanja.size()) - 1;
				odabranoStanje = pronadjenaStanja.get(izbor);
				if (Alati.daNe("Želite li doista obrisati artikal " + odabranoStanje.getArtikal().getNaziv()
						+ " sa lagera? (da/ne)", porukaGreskeDaNe)) {
					stanja.remove(odabranoStanje);
					System.out.println("\nArtikal je obrisan sa lagera");
				}
			} else {
				System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
				if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
					stanjaIzmjena();
				}
			}
		} else {
			System.out.println(porukaGreskeNemaStanja);
		}
		stanjaIzbornik();
	}

	private void stanjaDetalji() {
		if (!stanja.isEmpty()) {
			String uvjet;
			int izbor;
			Stanje odabranoStanje = new Stanje();
			List<Stanje> pronadjenaStanja = new ArrayList<Stanje>();
			uvjet = Alati.ucitajString("\nUpišite ime artikla čije stanje želite detaljnije pogledati: ",
					porukaGreskePraznogUnosa, 1, 30);
			pronadjenaStanja = stanjaPronadjiPoUvjetu(uvjet);
			if (!pronadjenaStanja.isEmpty()) {
				Alati.ispisZaglavlja("Pronađeni artikli", false);
				stanjaIzlistanje(pronadjenaStanja);
				izbor = Alati.ucitajBroj("\nUpišite broj ispred zapisa koji želite izmijeniti: ",
						porukaGreskeIzboraAkcije, 1, pronadjenaStanja.size()) - 1;
				odabranoStanje = pronadjenaStanja.get(izbor);
				Alati.ispisZaglavlja("Detalji stanja artikla", false);
				System.out.println(odabranoStanje.detalji());
			} else {
				System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
				if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
					stanjaIzmjena();
				}
			}
		} else {
			System.out.println(porukaGreskeNemaStanja);
		}
		stanjaIzbornik();
	}

	// POMOĆNE METODE STANJA
	private Stanje stanjaUnosOstalihPodataka(Stanje odabranoStanje) {
		double raspolozivo, prodano;
		raspolozivo = Alati.ucitajDouble(
				"Koliko je trenutno raspoloživo artikala (" + odabranoStanje.getArtikal().getNaziv() + "): ",
				porukaGreskePraznogUnosa, 1, 0);
		prodano = Alati.ucitajBroj("Koliko je prodano artikala (" + odabranoStanje.getArtikal().getNaziv() + "): ",
				porukaGreskePraznogUnosa, 1, 0);
		odabranoStanje.setRaspolozivo(raspolozivo);
		odabranoStanje.setProdano(prodano);
		return odabranoStanje;
	}

	private void stanjaIzlistanje() {
		if (!stanja.isEmpty()) {
			int counter = 1;
			for (Stanje s : stanja) {
				System.out.println(counter++ + " " + s.toString());
			}
		} else {
			System.out.println(porukaGreskeNemaStanja);
		}
	}

	private void stanjaIzlistanje(List<Stanje> stanja) {
		int counter = 1;
		for (Stanje s : stanja) {
			System.out.println(counter++ + " " + s.toString());
		}
	}

	private boolean stanjaPostojanjeArtiklaNaStanju(Artikal a) {
		return stanja.stream().filter(stanje -> stanje.getArtikal().equals(a)).findFirst().isPresent();
	}

	private Stanje stanjaNadjiPoArtiklu(Artikal artikal) {
		Stanje s = new Stanje();
		for (Stanje stanje : stanja) {
			if (stanje.getArtikal().equals(artikal)) {
				s = stanje;
			}
		}
		return s;
	}

	private List<Stanje> stanjaPronadjiPoUvjetu(String uvjet) {
		List<Stanje> listaStanja = new ArrayList<Stanje>();
		for (Stanje s : stanja) {
			if (s.getArtikal().getNaziv().toLowerCase().contains(uvjet.toLowerCase())) {
				listaStanja.add(s);
			}
		}
		return listaStanja;
	}

	private int stanjaIndeksStanjaIzIzvorneListe(Stanje stanje) {
		return stanja.indexOf(stanje);
	}
	
	private void stanjaUpdateStanjaArtikla(Stavka stavka) {		
		for(Stanje stanje : stanja) {
			if(stanje.getArtikal().equals(stavka.getArtikal())) {
				stanje.setProdano(stanje.getProdano()+stavka.getKolicina());
				break;
			}
		}
	}

	/**
	 * 
	 * STANJA KRAJ
	 * 
	 * KLIJENTI
	 * 
	 */

	private void klijentiIzbornik() {
		Alati.ispisZaglavlja("Rad sa podacima klijenata", true);
		System.out.println("1 za unos novog klijenta");
		System.out.println("2 za izmjenu podataka postojećeg klijenta");
		System.out.println("3 za brisanje postojećeg klijenta");
		System.out.println("4 za pregled svih klijenata");
		System.out.println("5 za pregled detalja postojećeg klijenta");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		klijentiOdabirAkcije();
	}

	private void klijentiOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> klijentiUnosNovog();
			case 2 -> klijentiIzmjena();
			case 3 -> klijentiBrisanje();
			case 4 -> {
				klijentiIzlistanje();
				klijentiIzbornik();
			}
			case 5 -> {
				klijentiDetalji();
				klijentiIzbornik();
			}
			case 6 -> {
				if (trenutniKorisnik.getRazina() == 1) {
					djelatnikGlavniIzbornik();
				} else {
					adminGlavniIzbornik();
				}
			}
		}
	}

	private void klijentiUnosNovog() {
		Alati.ispisZaglavlja("Odabir načina unosa novog klijenta", true);
		System.out.println("1 za unos postojeće osobe kao novog klijenta");
		System.out.println("2 za unos nove osobe kao novog klijenta");
		System.out.println("3 za povratak u glavni izbornika za rad sa klijentima");
		klijentiUnosNovogOdabirAkcije();
	}

	private void klijentiUnosNovogOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 3)) {
			case 1 -> klijentiUnosPostojeceOsobeKaoNovogKlijenta();
			case 2 -> klijentiUnosNoveOsobeKaoNovogKlijenta();
			case 3 -> klijentiIzbornik();
		}
	}

	private void klijentiUnosPostojeceOsobeKaoNovogKlijenta() {
		Osoba osoba = new Osoba();
		Klijent klijent = new Klijent();
		if (!osobe.isEmpty()) {
			osobeIzlistanje();
			osoba = osobe.get(Alati.ucitajBroj("Unesite broj osobe koju želite dodati kao klijenta: ",
					"Unos ne smije biti prazan", 1, osobe.size()) - 1);
			if (!klijentiJeLiOsobaKlijent(osoba)) {
				klijent.setOsoba(osoba);
				klijent = klijentiUnosOstalihVrijednosti(klijent);
				klijenti.add(klijent);
				System.out.println("\nOsoba je dodan na popis klijenata");
			} else {
				if (Alati.daNe("Osoba je već unešena kao klijent. Želite li odabrati drugu osobu? (da/ne): ",
						porukaGreskeDaNe)) {
					klijentiUnosPostojeceOsobeKaoNovogKlijenta();
				}
			}
		} else {
			System.out.println("\nU bazi ne postoji niti jedna osoba. \n"
					+ "Da bi Ste mogli dodati osobu na popis klijenata prvo unesite najmanje jednu osobu.");
		}
		klijentiIzbornik();
	}

	private void klijentiUnosNoveOsobeKaoNovogKlijenta() {
		Klijent klijent = new Klijent();
		osobeUnosNove();
		klijent.setOsoba(osobe.get(osobe.size() - 1));
		klijent = klijentiUnosOstalihVrijednosti(klijent);
		klijenti.add(klijent);
		System.out.println("\nNova osoba je unešena i stavljena na listu klijenata");
		klijentiIzbornik();
	}

	private void klijentiIzmjena() {
		if (!klijenti.isEmpty()) {
			klijentiIspisIzboraPretrage("Izmjena podataka klijenta");
			klijentiIzmjenaUcitajOdabirPretrage();
		} else {
			System.out.println(porukaGreskeNemaKlijenata);
			klijentiIzbornik();
		}
	}

	private void klijentiIzmjenaUcitajOdabirPretrage() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> klijentiIzmjenaPoIndeksu();
			case 2 -> klijentiIzmjenaPoImenu();
		}

	}

	private void klijentiIzmjenaPoIndeksu() {
		int i;
		Klijent klijent = new Klijent();
		klijent = klijentiOdabirPoIndeksu("Unesite broj klijenta kojeg želite izmijeniti: ");
		i = klijenti.indexOf(klijent);
		klijentiIzmjenaPodataka(klijent, i);
	}

	private void klijentiIzmjenaPoImenu() {
		String uvjet;
		int izbor, indeksIzabranogKlijenta;
		List<Klijent> nadjeniKlijenti = new ArrayList<Klijent>();
		Klijent izabraniKlijent = new Klijent();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime, mjesto ili poštanski broj klijenta kojeg tražite: ",
				porukaGreskePraznogUnosa, 0, 30);
		nadjeniKlijenti = klijentiPronadjiPoUvjetu(uvjet);
		if (!nadjeniKlijenti.isEmpty()) {
			klijentiIzlistanje("Pronađeni klijenti", nadjeniKlijenti);
			izbor = Alati.ucitajBroj("Unesite broj klijenta kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
					nadjeniKlijenti.size()) - 1;
			indeksIzabranogKlijenta = klijentiIndeksKlijentaIzIzvorneListe(nadjeniKlijenti.get(izbor));
			izabraniKlijent = klijenti.get(indeksIzabranogKlijenta);
			klijentiIzmjenaPodataka(izabraniKlijent, indeksIzabranogKlijenta);
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				klijentiIzmjenaPoImenu();
			}
		}
		klijentiIzbornik();
	}	

	private void klijentiBrisanje() {
		if (!klijenti.isEmpty()) {
			klijentiIspisIzboraPretrage("Brisanje klijenata");
			klijentiUcitajOdabirPretrageZaBrisanje();
		} else {
			System.out.println(porukaGreskeNemaKlijenata);
			klijentiIzbornik();
		}
	}

	private void klijentiUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> klijentiBrisanjePoIndeksu();
			case 2 -> klijentiBrisanjePoNazivu();
		}
	}

	private void klijentiBrisanjePoIndeksu() {
		Klijent klijent = new Klijent();
		klijent = klijentiOdabirPoIndeksu("\nUnesite broj klijenta kojeg želite obrisati: ");
		if (Alati.daNe("Želite li zaista obrisati odabranog klijenta (da/ne): ", porukaGreskeDaNe)) {
			if(!racuniJeLiKlijentURacunima(klijent)) {
				klijenti.remove(klijent);
				System.out.println("\nOsoba je obrisana sa popisa klijenata");
			}else {
				System.out.println("\nKlijent se ne može obrisati jer postoje računi vezani za njega");
			}			
		}
		klijentiIzbornik();
	}

	private void klijentiBrisanjePoNazivu() {
		String uvjet;
		int izbor, indeksKlijentaIzIzvorneListe;
		List<Klijent> nadjeniKlijenti = new ArrayList<Klijent>();
		Klijent odabraniKlijent = new Klijent();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime, mjesto ili poštanski broj klijenta kojeg tražite: ",
				porukaGreskePraznogUnosa, 0, 30);
		nadjeniKlijenti = klijentiPronadjiPoUvjetu(uvjet);
		if (!nadjeniKlijenti.isEmpty()) {
			klijentiIzlistanje("Pronađeni klijenti", nadjeniKlijenti);
			izbor = Alati.ucitajBroj("\nUnesite broj klijenta kojeg želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					nadjeniKlijenti.size()) - 1;
			odabraniKlijent = nadjeniKlijenti.get(izbor);
			indeksKlijentaIzIzvorneListe = klijentiIndeksKlijentaIzIzvorneListe(odabraniKlijent);
			if (Alati.daNe("Želite li zaista obrisati odabranog klijenta (da/ne): ", porukaGreskeDaNe)) {
				if(!racuniJeLiKlijentURacunima(klijenti.get(indeksKlijentaIzIzvorneListe))) {
					klijenti.remove(indeksKlijentaIzIzvorneListe);
					System.out.println("\nOsoba je obrisana sa popisa klijenata");
				}else {
					System.out.println("\nKlijent se ne može obrisati jer postoje računi vezani za njega");
				}					
			}
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				klijentiBrisanjePoNazivu();
			}
		}
		klijentiIzbornik();
	}
	
	private void klijentiDetalji() {
		if (!klijenti.isEmpty()) {
			klijentiIspisIzboraPretrage("Detalji klijenta");
			klijentiUcitajOdabirPretrageZaIspisDetalja();
		} else {
			System.out.println(porukaGreskeNemaKlijenata);
			klijentiIzbornik();
		}
	}

	private void klijentiUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> klijentiDetaljiPoIndeksu();
			case 2 -> klijentiDetaljiPoImenu();
		}
	}

	private void klijentiDetaljiPoIndeksu() {
		Klijent klijent = new Klijent();
		klijent = klijentiOdabirPoIndeksu("Unesite broj klijenta čije detalje želite pogledati: ");
		Alati.ispisZaglavlja("Detalji klijenta", false);
		System.out.println(klijent.toString());
		if(racuniJeLiKlijentURacunima(klijent)) {
			racuniIspisRacunaPoKlijentu(klijent);
		}
		klijentiIzbornik();
	}

	private void klijentiDetaljiPoImenu() {
		String uvjet;
		int izbor;
		List<Klijent> nadjeniKlijenti = new ArrayList<Klijent>();
		uvjet = Alati.ucitajString("Upišite ime i/ili prezime, mjesto ili poštanski broj klijenta kojeg tražite: ",
				porukaGreskePraznogUnosa, 0, 30);
		nadjeniKlijenti = klijentiPronadjiPoUvjetu(uvjet);
		if (!nadjeniKlijenti.isEmpty()) {
			klijentiIzlistanje("Pronađeni klijenti", nadjeniKlijenti);
			izbor = Alati.ucitajBroj("Unesite broj klijenta čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
					1, nadjeniKlijenti.size()) - 1;
			Alati.ispisZaglavlja("Detalji klijenta", false);
			System.out.println(nadjeniKlijenti.get(izbor).toString());
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				klijentiDetaljiPoImenu();
			}
		}
		klijentiIzbornik();
	}

	// POMOĆNE METODE KLIJENATA
	private void klijentiIzlistanje() {
		if (!klijenti.isEmpty()) {
			Alati.ispisZaglavlja("Popis klijenata", false);
			int counter = 1;
			for (Klijent k : klijenti) {
				System.out.println(counter++ + " " + k.klijentZaPrikaz());
			}
		} else {
			System.out.println(porukaGreskeNemaKlijenata);
		}
	}
	
	private void klijentiIzlistanje(String poruka, List<Klijent> klijenti) {
		int counter;
		if (!klijenti.isEmpty()) {
			counter = 1;
			Alati.ispisZaglavlja(poruka, false);
			for (Klijent klijent : klijenti) {
				System.out.println(counter++ + " " + klijent.klijentZaPrikaz());
			}
		} else {
			System.out.println(porukaGreskeNemaKorisnika);
		}
	}

	private boolean klijentiJeLiOsobaKlijent(Osoba osoba) {
		boolean valjanost = false;
		for (Klijent klijent : klijenti) {
			if (klijent.getOsoba().equals(osoba)) {
				valjanost = true;
				break;
			}
		}
		return valjanost;
	}

	private Klijent klijentiUnosOstalihVrijednosti(Klijent klijent) {
		String ulicaIKbr, mjesto, pbr;
		ulicaIKbr = Alati.ucitajString("\nUnesite ulicu i kućni broj klijenta: ", porukaGreskePraznogUnosa, 1, 50);
		mjesto = Alati.ucitajString("\nUnesite naziv mjesta klijenta: ", porukaGreskePraznogUnosa, 1, 50);
		pbr = Alati.ucitajString("\nUnesite poštanski broj mjesta klijenta: ", porukaGreskePraznogUnosa, 1, 5);
		klijent.setUlicaIKbr(ulicaIKbr);
		klijent.setMjesto(mjesto);
		klijent.setPbr(pbr);
		return klijent;
	}

	private void klijentiIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih klijenata od kojih će te izabrati željenog");
		System.out.println("2 za pretragu klijenata po imenu i/ili prezimenu, mjestu ili poštanskom broju");
	}

	private Klijent klijentiOdabirPoIndeksu(String poruka) {
		int i;
		klijentiIzlistanje();
		i = Alati.ucitajBroj(poruka, porukaGreskeIzboraAkcije, 1, klijenti.size()) - 1;
		return klijenti.get(i);
	}

	private void klijentiIzmjenaPodataka(Klijent klijent, int i) {
		klijent = klijentiUnosOstalihVrijednosti(klijent);
		klijenti.set(i, klijent);
		System.out.println("\nNovi podaci klijenta su spremljeni");
		klijentiIzbornik();
	}

	private List<Klijent> klijentiPronadjiPoUvjetu(String uvjet) {
		List<Klijent> listaKlijenata = new ArrayList<Klijent>();
		klijenti.stream().filter(k -> (klijentiJeLiToKlijentPoUvjetu(k, uvjet))).forEachOrdered(k -> {
			listaKlijenata.add(k);
		});
		return listaKlijenata;
	}

	private boolean klijentiJeLiToKlijentPoUvjetu(Klijent klijent, String uvjet) {
		String imePrezime, prezimeIme, mjesto, pbr;
		imePrezime = klijent.getOsoba().getIme().toLowerCase() + " " + klijent.getOsoba().getPrezime().toLowerCase();
		prezimeIme = klijent.getOsoba().getPrezime().toLowerCase() + " " + klijent.getOsoba().getIme().toLowerCase();
		mjesto = klijent.getMjesto().toLowerCase();
		pbr = klijent.getPbr();
		uvjet = uvjet.trim().toLowerCase();
		if (imePrezime.contains(uvjet) || prezimeIme.contains(uvjet) || mjesto.contains(uvjet) || pbr.contains(uvjet)) {
			return true;
		} else {
			return false;
		}
	}
	
	private int klijentiIndeksKlijentaIzIzvorneListe(Klijent klijent) {
		return klijenti.indexOf(klijent);
	}
	
	/**
	 * 
	 * KLIJENTI KRAJ
	 * 
	 * RAČUNI
	 * 
	 */
	
	private void racuniIzbornik() {
		Alati.ispisZaglavlja("Rad sa računima", true);
		System.out.println("1 za kreiranje novog računa");
		System.out.println("2 za promjenu storno stanja postojećeg računa");
		System.out.println("3 za pregled detalja postojećeg računa");
		System.out.println("4 za povratak u glavni korisnički izbornik");
		racuniOdabirAkcije();
	}

	private void racuniOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 4)) {
			case 1 -> {
				racuniUnosNovog();
			}
			case 2 -> racuniStorno();
			case 3 -> racuniDetalji();
			case 4 -> {
				if (trenutniKorisnik.getRazina() == 1) {
					djelatnikGlavniIzbornik();
				} else {
					adminGlavniIzbornik();
				}
			}
		}
	}

	private void racuniUnosNovog() {
		Racun racun = new Racun();
		Alati.ispisZaglavlja("Podaci novog računa", false);
		racun.setStorno(false);
		racuniUnosPodataka(racun);
	}

	private void racuniUnosPodataka(Racun racun) {
		if (!klijenti.isEmpty()) {
			if(!artikli.isEmpty()) {				
			String uvjetKlijenta = Alati.ucitajString("Upišite ime i/ili prezime, mjesto ili poštanski broj klijenta za kojeg kreirate račun: ",
								porukaGreskePraznogUnosa, 0, 30);
			List<Klijent> nadjeniKlijenti = klijentiPronadjiPoUvjetu(uvjetKlijenta);
			if (!nadjeniKlijenti.isEmpty()) {
				klijentiIzlistanje("Pronađeni klijenti", nadjeniKlijenti);
				int izborKlijenta = Alati.ucitajBroj("Unesite broj klijenta za kojeg krairate račun: ", porukaGreskeIzboraAkcije,
						1, nadjeniKlijenti.size()) - 1;
				Klijent klijent = nadjeniKlijenti.get(izborKlijenta);
				racun.setKlijent(klijenti.get(klijentiIndeksKlijentaIzIzvorneListe(klijent)));
				racun.setDatum(Alati.ucitajDatum("Upišite datum računa: "));
				while(true) {
					String brojRacuna = Alati.ucitajString("Unesite broj novog računa: ", porukaGreskePraznogUnosa, 1, 20);					
					if(!racuniPostojanjeBroja(brojRacuna)) {
						racun.setBroj(brojRacuna);
						racun = racuniUnesiStavke(racun);
						racuni.add(racun);
						System.out.println("\nRačun je kreiran");
					}else {
						if(Alati.daNe("\nRačun sa tim brojem već postoji. Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) { continue; }
					}
					break;
				}				
			} else { System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
					if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) { racuniUnosPodataka(racun); }
				}
			}else { System.out.println(porukaGreskeNemaArtikala + "\nU bazu unesite prvo barem jedan artikal kako bi Ste mogli kreirati račun"); }			
		} else { System.out.println(porukaGreskeNemaKlijenata + "\nU bazu unesite prvo barem jednog klijenta kako bi Ste mogli kreirati račun"); }	
		racuniIzbornik();
	}
	
	private Racun racuniSumirajRacun(Racun racun) {
		double netto = 0, porez = 0, ukupno = 0;
		for(Stavka s : stavke) {
			if(s.getRacun().equals(racun)) {
				netto += s.getNetto();
				porez += s.getPorez();
				ukupno += s.getUkupno();
			}
		}
		racun.setNetto(netto);
		racun.setPorez(porez);
		racun.setUkupno(ukupno);
		return racun;
	}

	private Racun racuniUnesiStavke(Racun racun) {
		Artikal artikal;
		Stavka stavka;
		List<Artikal> pronadjeniArtikli = new ArrayList<Artikal>();
		String uvjetArtikla;		
		while(true) {
			int odabraniArtikal;
			double kolicina;
			artikal = new Artikal();
			stavka = new Stavka();
			uvjetArtikla = Alati.ucitajString("\nUnesite naziv artikla koji želite dodati na račun: ", porukaGreskePraznogUnosa, 1, 30);
			pronadjeniArtikli = artikliPronadjiPoUvjetu(uvjetArtikla);
			if(!pronadjeniArtikli.isEmpty()) {
				artikliIzlistanje("Pronađeni artkli", pronadjeniArtikli);
				odabraniArtikal = Alati.ucitajBroj("\nUnesite broj ispred aktikla kojeg želite dodati na listu: ", porukaGreskeIzboraAkcije, 1, pronadjeniArtikli.size())-1;
				artikal = artikli.get(artikliIndeksArtiklaIzIzvorneListe(pronadjeniArtikli.get(odabraniArtikal)));
				kolicina = Alati.ucitajDouble("Unesite količinu artikala koja se dodaje na račun: ", porukaGreskePraznogUnosa, 0.001, 0);
				stavka = racuniUcitajVrijednostiStavke(racun,artikal,kolicina);
				stavke.add(stavka);
				stanjaUpdateStanjaArtikla(stavka);
				if(Alati.daNe("Želite li dodati još koju stavku na račun? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
				break;
			}else {
				System.out.println("\nNema artikala koji zadovoljavaju upisani kriterij");
				if(Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}else {
					break;
				}
			}		
		}	
		racun = racuniSumirajRacun(racun);
		return racun;
	}

	private void racuniStorno() {
		if(!racuni.isEmpty()) {
			String uvjet;
			int i;
			Racun racun = new Racun();
			List<Racun> nadjeniRacuni = new ArrayList<Racun>();
			Alati.ispisZaglavlja("Pregled detalja računa", false);
			uvjet = Alati.ucitajString("Unesite ime i/ili prezime klijenta ili broj računa ili datum računa koji tražite: ", porukaGreskePraznogUnosa, 1, 30);
			nadjeniRacuni = racuniNadjiPoUvjetu(uvjet);
			if(!nadjeniRacuni.isEmpty()) {
				racuniIzlistanje(nadjeniRacuni);
				i = Alati.ucitajBroj("\nUnesite broj ispred računa koji želite izmijeniti: ", porukaGreskeIzboraAkcije, 1, nadjeniRacuni.size())-1;
				racun = racuni.get(racuniIndeksRacunaIzIzvorneListe(nadjeniRacuni.get(i)));
				racun = racuniIzmjenaStorno(racun);
				racuni.set(i, racun);
				System.out.println("\nRačun je izmijenjen");
			}else {
				System.out.println("\nNema niti jednog računa koji zadovoljava kriterij pretrage");
				if(Alati.daNe("\nŽelite li pokušati opet? (da/ne): ", porukaGreskeDaNe)){
					racuniStorno();
				}
			}
		}else {
			System.out.println(porukaGreskeNemaRacuna);
		}		
		racuniIzbornik();
	}
	
	private Racun racuniIzmjenaStorno(Racun racun) {
		boolean storno = false;
		if(Alati.daNe("Storno računa? (da/ne): ", porukaGreskeDaNe)) {
			storno = true;
		}
		racun.setStorno(storno);
		return racun;
	}

	private void racuniDetalji() {
		if(!racuni.isEmpty()) {
			String uvjet;
			int i;
			Racun racun = new Racun();
			List<Racun> nadjeniRacuni = new ArrayList<Racun>();
			Alati.ispisZaglavlja("Pregled detalja računa", false);
			uvjet = Alati.ucitajString("Unesite ime i/ili prezime klijenta ili broj računa ili datum računa koji tražite: ", porukaGreskePraznogUnosa, 1, 30);
			nadjeniRacuni = racuniNadjiPoUvjetu(uvjet);
			if(!nadjeniRacuni.isEmpty()) {
				racuniIzlistanje(nadjeniRacuni);
				i = Alati.ucitajBroj("\nUnesite broj ispred računa koji želite detaljno pogledati: ", porukaGreskeIzboraAkcije, 1, nadjeniRacuni.size())-1;
				racun = racuni.get(racuniIndeksRacunaIzIzvorneListe(nadjeniRacuni.get(i)));
				Alati.ispisZaglavlja("Detalji računa", false);
				racuniIspisDetalja(racun);
			}else {
				System.out.println("\nNema niti jednog računa koji zadovoljava kriterij pretrage");
				if(Alati.daNe("\nŽelite li pokušati opet? (da/ne): ", porukaGreskeDaNe)){
					racuniDetalji();
				}
			}
		}else {
			System.out.println(porukaGreskeNemaRacuna);
		}		
		racuniIzbornik();
	}

	private void racuniIspisDetalja(Racun racun) {
		System.out.println(racun.zaglavlje());
		System.out.println("Artikal\t\t\t\t\tKoličina\tCijena kom/kg\t\tNetto\t\tPorez\t\tUkupno");		
		System.out.println(racuniIspisStavki(racun));
		System.out.println(racun.sumiranje());		
	}

	private String racuniIspisStavki(Racun racun) {
		int counter = 1;
		String ispis = "";
		for(Stavka s : stavke) {
			if(s.getRacun().equals(racun)) {
				ispis += counter++ + " " + s.ispisReda() + "\n";
			}
		}
		return ispis;
	}

	// POMOĆNE METODE RAČUNA	
	private Stavka racuniUcitajVrijednostiStavke(Racun racun, Artikal artikal, double kolicina) {
		Stavka stavka = new Stavka(racun,artikal,kolicina);
		stavka.izracunOstalihVrijednosti(STOPA_POREZA);
		return stavka;
	}
	
	private List<Racun> racuniNadjiPoUvjetu(String uvjet) {
		List<Racun> nadjeniRacuni = new ArrayList<Racun>();
		for(Racun r : racuni) {
			if(Alati.hrDatum(r.getDatum()).contains(uvjet) 
					|| osobeJeLiToOsobaPoImenuIPrezimenu(r.getKlijent().getOsoba(), uvjet) 
					|| r.getBroj().contains(uvjet)) {
				nadjeniRacuni.add(r);
			}
		}
		return nadjeniRacuni;
	}
	
	private void racuniIzlistanje(List<Racun> racuni) {
		int counter = 1;
		for(Racun r : racuni) {
			System.out.println(counter++ + " " + r.ispis());
		}		
	}
	
	private int racuniIndeksRacunaIzIzvorneListe(Racun racun) {
		return racuni.indexOf(racun);
	}
	
	private boolean racuniPostojanjeArtiklaUStavkama(Artikal artikal) {
		return stavke.stream().filter(stavka -> stavka.getArtikal().equals(artikal)).findFirst().isPresent();		
	}
	
	private boolean racuniJeLiKlijentURacunima(Klijent klijent) {
		return racuni.stream().filter(racun -> racun.getKlijent().equals(klijent)).findFirst().isPresent();
	}
	
	private void racuniIspisRacunaPoKlijentu(Klijent klijent) {
		int counter = 1;
		System.out.println("\nRačuni klijenta");
		for(Racun r : racuni) {
			if(r.getKlijent().equals(klijent)) {
				System.out.println(counter++ + ". " + r.ispisKodDetaljaKlijenta());
			}
		}		
	}
	
	private boolean racuniPostojanjeBroja(String brojRacuna) {
		return racuni.stream().filter(racun -> racun.getBroj().equals(brojRacuna)).findFirst().isPresent();
	}

	
	/**
	 * 
	 * RAČUNI KRAJ
	 * 
	 */
}