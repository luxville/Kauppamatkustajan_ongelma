/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovellus.algoritmit;

import java.util.ArrayList;

/**
 *
 * @author luxville
 */
public class LahinNaapuri {

    int aloitus, kaupunkeja, seuraava;
    boolean[] mukana;
    double[] etaisyydet;
    //double[][] etaisyysmatriisi;
    ArrayList<Integer> reitti, lyhinReitti;

    public double lahinNaapuriKauppamatkustaja(double[][] etaisyysmatriisi) {
        kaupunkeja = etaisyysmatriisi[0].length;
        etaisyydet = new double[kaupunkeja];
        double lyhinReitinPituus = Double.MAX_VALUE;
        lyhinReitti = new ArrayList<>();
        // Lähimmän naapurin algoritmilla aloituskaupunki voi vaikuttaa reitin pituuteen, joten kokeillaan
        // kaikkia eri lähtökaupunkeja lyhimmän reitin löytämiseksi.
        for (int i = 0; i < kaupunkeja; i++) {
            mukana = new boolean[kaupunkeja];
            double nykyisenReitinPituus = 0;
            aloitus = i;
            //mukana[aloitus] = true;
            reitti = new ArrayList<>();
            //reitti.add(aloitus);
            seuraava = etsiLahinKaupunki(etaisyysmatriisi[aloitus]);
            while (reitti.size() <= kaupunkeja) {
                System.out.println("Reitillä " + reitti + " kaupunkeja: " + reitti.size());
                nykyisenReitinPituus += etaisyysLahmipaanKaupunkiin(seuraava, etaisyysmatriisi);
            }
            if (nykyisenReitinPituus <= lyhinReitinPituus) {
                lyhinReitinPituus = nykyisenReitinPituus;
                lyhinReitti = new ArrayList<>();
                for (Integer kaupunki : reitti) {
                    lyhinReitti.add(kaupunki);
                }
                System.out.println("Löytyi lyhin reitti " + lyhinReitti + ", jonka pituus on " + lyhinReitinPituus);
            }
            System.out.println("Kaupungista " + aloitus + " alkaen aina lähimpään kaupunkiin siirtyen lyhin reitti on " + reitti + ", jonka pituus on " + nykyisenReitinPituus);

        }
        System.out.println("Lyhin lähimmän naapurin algoritmilla löytynyt reitti on " + lyhinReitti + ", jonka pituus on " + lyhinReitinPituus);
        return lyhinReitinPituus;
    }

    private int etsiLahinKaupunki(double[] etaisyydet) {
        int lahinKaupunki = -1;
        double lyhinEtaisyys = Double.MAX_VALUE;
        for (int i = 0; i < etaisyydet.length; i++) {
            if (etaisyydet[i] <= 0 || mukana[i]) {
                System.out.println(i + ": etäisyys " + etaisyydet[i] + mukana[i]);
                continue;
            }
            if (etaisyydet[i] < lyhinEtaisyys) {
                lahinKaupunki = i;
                lyhinEtaisyys = etaisyydet[i];
                //mukana[i] = true;
            }
        }
        return lahinKaupunki;
    }

    private double etaisyysLahmipaanKaupunkiin(int kaupunki, double[][] etaisyysmatriisi) {
        if (reitti.size() == kaupunkeja) {
            seuraava = aloitus;
            reitti.add(aloitus);
            System.out.println("Löytyi reitti " + reitti);
            return etaisyysmatriisi[kaupunki][aloitus];
        }
        etaisyydet = new double[kaupunkeja];
        for (int i = 0; i < kaupunkeja; i++) {
            etaisyydet[i] = etaisyysmatriisi[kaupunki][i];
        }
        double lyhinEtaisyys = Double.MAX_VALUE;
        int lahinKaupunki = -1;
        for (int i = 0; i < kaupunkeja; i++) {
            if (i == kaupunki || mukana[i]) {
                System.out.println("i on " + i + " ja mukana on " + mukana[i]);
                continue;
            }
            if (etaisyydet[i] < lyhinEtaisyys) {
                lyhinEtaisyys = etaisyydet[i];
                lahinKaupunki = i;
            }
        }
        if (lahinKaupunki >= 0) {
            reitti.add(lahinKaupunki);
            mukana[lahinKaupunki] = true;
            seuraava = lahinKaupunki;
        }
        return lyhinEtaisyys;
    }

}
