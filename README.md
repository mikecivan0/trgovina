# Trgovina
Dodatni rad za Edunovu

Konzolna aplikacija za rad trgovine


Rad sa aplikacijom je podijeljen na javni i korisnički dio. Korisnički dio ima dvije razine ovlasti rada.

1. Javni dio kojem svatko može pristupiti - nije potrebna prijava u aplikaciju
	U ovom dijelu je moguće:
	- otvaranje prijave u aplikaciju
	- otvaranje linka na GitHub repozitorij aplikacije
	- otvaranje linka na ER dijagram baze podataka aplikacije

2. Dio aplikacije kojoj pristupaju korisnici sa razinom ovlasti 1 - dio za djelatnike
	
	U ovom dijelu je moguće:	
	
	- rad sa kategorijama artikala
		- podaci koje je moguće unositi: 
			- naziv kategorije
		
		Ovdje je moguće:
		- unos nove kategorije 
			- vrši se provjera je li ime kategorije već unešeno
		- izmjena podataka postojeće kategorije 
 			- dobija se poruka ako nema niti jedne kategorije u bazi
			- izlistavaju se sve kategorije od kojih se bira koju se želi izmijeniti
			- vrši se provjera je li ime kategorije već unešeno
		- brisanje postojeće kategorije 
 			- dobija se poruka ako nema niti jedne kategorije u bazi
 			- moguće samo ako kategorija nije dodijeljena niti jednom artiklu
			- izlistavaju se sve kategorije od kojih se bira koju se želi obrisati
		- pregled svih kategorija 
			- dobija se poruka ako nema niti jedne kategorije u bazi
			- izlistanje kategorija po nazivu
		- pregled detalja određene kategorije 
			- dobija se poruka ako nema niti jedne kategorije u bazi
			- izlistavaju se sve kategorije od kojih se bira koju se želi detaljno pogledati
			
			
			
	- rad sa artiklima
		- podaci koje je moguće unositi: 
			- kategorija
			- naziv
			- cijena
			- je li artikal na akciji
			- akcijska cijena
			- opis
		
		Ovdje je moguće:
		- unos novog artikla
			- dobija se poruka ako u bazi ne postoji niti jedna kategorija artikala
			- vrši se provjera je li ime artikla već unešeno
			- ukoliko se unese da je artikal na akciji dobija se izbor želi li se unijeti akcijska cijena
		- izmjena podataka postojećeg artikla
 			- dobija se poruka ako u bazi ne postoji niti jedan artikal
			- vrši se provjera je li ime artikla već unešeno
			- ukoliko se unese da je artikal na akciji dobija se izbor želi li se unijeti akcijska cijena
		- brisanje postojećeg artikla
 			- dobija se poruka ako u bazi ne postoji niti jedan artikal
 			- moguće samo ako se artikal ne nalazi niti na jednom računu niti na stanju
 			- pretraga artikla kojeg se želi obrisati se može ozvršiti po indeksu ili nazivu
		- pregled svih artikala 
			- dobija se poruka ako u bazi ne postoji niti jedan artikal
			- izlistanje artikala po nazivu + kategrija artikla
		- pregled detalja određene kategorije 
			- dobija se poruka ako u bazi ne postoji niti jedan artikal
 			- pretraga artikla kojeg se želi obrisati se može ozvršiti po indeksu ili nazivu
 			- ukoliko se artikal nalazi na stanju prikazuje se podatak sa stanja
				
			
			
	- rad sa klijentima
		- podaci koje je moguće unositi: 
			- podaci osobe (ime, prezime, telefon, email)
			- ulica i kućni broj
			- mjesto
			- poštanski broj
		
		Ovdje je moguće:								 
 		- unos novoga klijenta 
 			- unos potojeće osobe kao novoga klijenta 
 				- dobija se poruka ako nema niti jedne osobe u bazi
 				- izlistavaju se sve osobe od kojih se bira ona koju se želi dodati na popis klijenata
 				- odabirom osobe koja je već klijent dobija se obavijest da je osoba već klijent
 			- unos nove osobe kao novoga klijenta
 				- unose se podaci nove osobe 
 				- unose se podaci novoga klijenta
 		- brisanje postojećeg klijenta 
 			- dobija se poruka ako nema niti jednog klijenta u bazi
 			- moguće samo ako klijent nije unešen u račune
			- pronalazak klijenta kojeg će se obrisati 
				- pronalazak po indeksu
					- izlistati će se svi klijenti od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- izmjena postojećeg klijenta 
 			- dobija se poruka ako nema niti jednog klijenta u bazi
			- pronalazak klijenta kojeg će se izmijeniti 
				- pronalazak po indeksu
					- izlistati će se svi klijenti od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- pregled svih klijenata 
			- dobija se poruka ako nema niti jednog klijenta u bazi
			- izlistanje imena, prezimena i adrese klijenta
		- pregled detalja određenog klijenta 
			- dobija se poruka ako nema niti jednog klijenta u bazi
			- pronalazak klijenta čiji detalji se žele pogledati
				- pronalazak po indeksu
					- izlistati će se svi klijenti od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime nazivu
 			- ukoliko postoje računi vezani za klijenta oni se također izlistavaju
 			
 			
 			
 	- rad sa računima
		- podaci koje je moguće unositi: 
			- klijent
			- datum
			- broj računa
			- artikli (stavke)
		
		Ovdje je moguće:								 
 		- unos novoga računa 
 			- po imenu i/ili prezimenu se pronalazi klijent za kojeg se kreira račun
 			- unosi se datum
 			- nakon unosa broja računa vrši se provjera je li broj već unešen
 			- po nazivu se bira artikal i unosi se količina
 			- ako je artikal na akciji računa se akcijska cijena
 			- netto cijena, iznos poreza i ukupna cijena se automatski izračunavaju
 			- stanje na lageru (prodano) se osvježava nakon dodavanja stavki na račun
 		- promjena storno stanja računa 
 			- dobija se poruka ako nema niti jednog računa u bazi
 			- pronalazak računa po imenu klijenta, datumu ili broju računa
		- pregled detalja određenog računa 
			- dobija se poruka ako nema niti jednog računa u bazi
			- pronalazak računa po imenu klijenta, datumu ili broju računa
 			- ispis podataka klijenta
 			- ispis stavki (naziv artikla, količina, pojedinačna cijena, ukupni netto, iznos poreza i ukupna cijena po stavki
 			- ispisuje se suma svih netto iznosa, svih poreza i svih ukupnih cijena stavki
 			- ispisuje se poruka ukoliko je račun storniran

3. Dio aplikacije kojoj pristupaju korisnici sa razinom ovlasti 2 - dio za administratore

	Administrator može sve kao i djelatnik plus još:
	
	- rad sa osobama
		- podaci koje je moguće unositi: 
			- ime
			- prezime
			- telefon
			- email (moguć izbor želi li se unijeti ili ne)
			- adresa (moguć izbor želi li se unijeti ili ne)
		
		Ovdje je moguće:
		- unos nove osobe 
		- izmjena podataka postojeće osobe 
 			- dobija se poruka ako nema niti jedne osobe u bazi
			- pronalazak osobe koju se želi izmijeniti
				- pronalazak po indeksu
					- izlistati će se sve osobe od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- brisanje postojeće osobe 
 			- dobija se poruka ako nema niti jedne osobe u bazi
 			- moguće samo ako osoba nije korisnik
			- pronalazak osobe kojeu će se obrisati 
				- pronalazak po indeksu
					- izlistati će se sve osobe od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- pregled svih osoba 
			- dobija se poruka ako nema niti jedne osobe u bazi
			- izlistanje osoba po imenu i prezimenu
		- pregled detalja određene osobe 
			- dobija se poruka ako nema niti jedne osobe u bazi
			- pronalazak osobe čiji detalji se žele pogledati je moguć po indeksu ili po imenu i/ili prezimenu
				- po indeksu znači da će se izlistati sve osobe od kojih će se izvršiti izbor
				- po imenu i/ili prezimenu je moguće neovisno upisuje li se prvo ime ili prezime
		
				
				
 	- rad sa korisnicima 
 		- podaci koje je moguće unositi: 
 			- korisničko ime
 			- lozinka
 			- osobni broj
 			- razina(1-običan korisnik, 2-admin)
 			- aktivan (da/ne)
 			- podaci osobe navedeni u gornjem odlomku
 		
 		Ovdje je moguće:								 
 		- unos novoga korisnika 
 			- vrši se provjera je li korisničko ime zauzeto
 			- unos potojeće osobe kao novoga korisnika 
 				- dobija se poruka ako nema niti jedne osobe u bazi
 				- izlistavaju se sve osobe od kojih se bira ona kojoj se žele dati korisnička prava
 				- odabirom osobe koja je već korisnik dobija se obavijest da je osoba već korisnik
 			- unos nove osobe kao novoga korisnika
 				- unose se podaci nove osobe 
 				- unose se podaci novoga korisnika
 		- brisanje postojećeg korisnika 
 			- dobija se poruka ako nema niti jednog korisnika u bazi
 			- moguće samo ako korisnik nije unešen u raspored
			- pronalazak korisnika kojeg će se obrisati 
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
		- izmjena postojećeg korisnika 
 			- dobija se poruka ako nema niti jednog korisnika u bazi
			- pronalazak korisnika kojeg će se izmijeniti 
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
		- pregled svih korisnika 
			- dobija se poruka ako nema niti jednog korisnika u bazi
			- izlistanje korisničkog imena i osobnog broja svih korisnika
		- pregled detalja određenog korisnika 
			- dobija se poruka ako nema niti jednog korisnika u bazi
			- pronalazak korisnika čiji detalji se žele pogledati
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
	
	
	
	- rad sa lagerom (stanjem)	
		- podaci koje je moguće unositi: 
			- artikal
			- raspoloživo
			- prodano
			
		Ovdje je moguće:
		
			- dodavanje artikla na lager
				- izbor atikla se može izvršti preko indeksa ili naziva
				- vrši se provjera ukoliko je arikal već uveden na stanje
			- izmjena stanja artikla
				- izbor atikla se može izvršti preko indeksa ili naziva
			- brisanje artikla sa stanja
				- izbor arikla se vrši preko naziva
			- pregled svih artikala na stanju
				- izlistavaju se svi artikli sa podacima sa stanja
			- pregled detalja stanja određenog artikla
				- izbor arikla se vrši preko naziva 
			