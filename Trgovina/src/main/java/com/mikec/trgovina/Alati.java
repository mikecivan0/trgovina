package com.mikec.trgovina;

/**
 *
 * @author Ivan
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Alati {

    public static Scanner scanner;
    public static final String FORMAT_DATUMA = "d.M.yyyy.";
    public static final String FORMAT_GODINE = "yyyy";
    public static final String FORMAT_MJESECA = "M";
    public static final String FORMAT_DANA = "d";
    private static SimpleDateFormat formatDatuma;
    private static SimpleDateFormat formatGodine;
    private static SimpleDateFormat formatMjeseca;
    private static SimpleDateFormat formatDana;

    public static Date ucitajDatum(String poruka) {
        formatDatuma = new SimpleDateFormat(FORMAT_DATUMA);
        while (true) {
            System.out.print(poruka);
            try {
                return formatDatuma.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Molim unesite datum u formatu " + formatDatuma.format(new Date()));
            }
        }
    }

    public static int ucitajBroj(String poruka, String porukaGreske, int min, int max) {
        int broj = 0;
        while (true) {           
            System.out.println(poruka);
            try {
                broj = Integer.parseInt(scanner.nextLine());
                if (broj < min || broj > max) {
                    System.out.println("Unesite broj između " + min + " i " + max);
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println(porukaGreske);
            }
        }
        return broj;
    }

    public static String ucitajString(String poruka, String porukaGreske, int minLength, int maxLength) {
        String unos = "";
        while (true) {
            System.out.println(poruka);
            unos = scanner.nextLine();
            if (unos.trim().equals("")) {
                System.out.println(porukaGreske);
            }
            if (unos.length() < minLength || unos.length() > maxLength) {
                System.out.println("Najmanji dopušteni broj znakova je " + minLength + ", a najveći " + maxLength);
                continue;
            }
            break;
        }
        return unos;
    }

    public static boolean daNe(String poruka, String porukaGreske) {
        String unos;
        while (true) {
            System.out.println(poruka);
            unos = scanner.nextLine().trim().toLowerCase();
            if (unos.equals("da")) {
                return true;
            }
            if (unos.equals("ne")) {
                return false;
            }
            System.out.println(porukaGreske);
        }
    }

    public static void ispisZaglavlja(String naslov, boolean unesite) {
        naslov = "-" + naslov + "-";
        String crtice = "";
        for (int i = 1; i <= naslov.length(); i++) {
            crtice += "-";
        }
        System.out.println();
        System.out.println(crtice);
        System.out.println(naslov);
        System.out.println(crtice);
        if (unesite) {
            System.out.println("Unesite");
        }

    }

    public static String hrGodina(Date datum) {
        formatGodine = new SimpleDateFormat(FORMAT_GODINE);
        return formatGodine.format(datum);
    }

    public static String hrMjesec(Date datum) {
        formatMjeseca = new SimpleDateFormat(FORMAT_MJESECA);
        return formatMjeseca.format(datum);
    }

    public static String hrDan(Date datum) {
        formatDana = new SimpleDateFormat(FORMAT_DANA);
        return formatDana.format(datum);
    }

    public static String hrDatum(Date datum) {
        formatDatuma = new SimpleDateFormat(FORMAT_DATUMA);
        return formatDatuma.format(datum);
    }

    public static String parseBool(boolean bool) {
        return (bool) ? "da" : "ne";
    }

}
