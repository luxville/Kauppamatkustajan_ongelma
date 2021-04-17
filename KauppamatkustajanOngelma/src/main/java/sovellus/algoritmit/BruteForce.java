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
public class BruteForce {

    int kaupunkeja;
    double[][] etaisyysmatriisi;
    ArrayList<Integer> reitti, lyhinReitti;

    public double bruteForceKauppamatkustaja(double[][] etaisyysmatriisi, int aloitus) {
        ArrayList<Integer> kaupungit = new ArrayList<>();
        kaupungit.add(aloitus);
        kaupunkeja = etaisyysmatriisi.length;

        for (int i = 0; i < kaupunkeja; i++) {
            if (i != aloitus) {
                kaupungit.add(i);
            }
        }
        System.out.println("Kaupunkeja ovat " + kaupungit + ", ensimmäisenä on " + aloitus);
        double lyhinReitinPituus = Double.MAX_VALUE;
        lyhinReitti = new ArrayList<>();

        while (loytyyUusiReitti(kaupungit)) {
            boolean samaKahdesti = false;
            reitti = new ArrayList<>();
            double nykyisenReitinPituus = 0;
            boolean[] mukana = new boolean[kaupunkeja];
            mukana[aloitus] = true;
            int kaupunki = aloitus;
            for (int i = 0; i < kaupungit.size(); i++) {
                if (i == aloitus) continue;
                reitti.add(kaupunki);
                if (mukana[i]) {
                    samaKahdesti = true;
                    break;
                }
                mukana[kaupunki] = true;
                nykyisenReitinPituus += etaisyysmatriisi[kaupunki][kaupungit.get(i)];
                kaupunki = kaupungit.get(i);
            }
            if (samaKahdesti) {
                System.out.println("Löytyi virheellinen reitti " + reitti);
                continue;
            }
            nykyisenReitinPituus += etaisyysmatriisi[kaupunki][aloitus];
            reitti.add(aloitus);
            System.out.println("Löytyi reitti " + reitti);
            if (nykyisenReitinPituus < lyhinReitinPituus) {
                lyhinReitti = new ArrayList<>();
                for (Integer integer : reitti) {
                    lyhinReitti.add(integer);
                }
                System.out.println("Löytyi lyhyempi reitti " + reitti + ", jonka pituus on " + nykyisenReitinPituus);
            }
            lyhinReitinPituus = Math.min(lyhinReitinPituus, nykyisenReitinPituus);
        }
        System.out.println("Lyhin reitti on " + lyhinReitti + ", pituus " + lyhinReitinPituus);
        return lyhinReitinPituus;
    }

    private boolean loytyyUusiReitti(ArrayList<Integer> kaupungit) {
        if (kaupungit.size() <= 1) {
            return false;
        }
        int jaljella = kaupungit.size() - 2;

        while (jaljella >= 0) {
            if (kaupungit.get(jaljella) < kaupungit.get(jaljella + 1)) {
                break;
            }
            jaljella--;
        }

        if (jaljella < 0) {
            return false;
        }

        int seuraavaSuurempi = kaupungit.size() - 1;

        for (int i = kaupungit.size() - 1; i > jaljella; i--) {
            if (kaupungit.get(i) > kaupungit.get(jaljella)) {
                seuraavaSuurempi = i;
                break;
            }
        }
        kaupungit = vaihdaJarjestysta(kaupungit, seuraavaSuurempi, jaljella);
        kaupungit = palaaAiempaan(kaupungit, jaljella + 1, kaupungit.size() - 1);
        return true;
    }

    private ArrayList<Integer> vaihdaJarjestysta(ArrayList<Integer> kaupungit, int vasen, int oikea) {
        int apu = kaupungit.get(vasen);
        kaupungit.set(vasen, kaupungit.get(oikea));
        kaupungit.set(oikea, apu);
        return kaupungit;
    }

    private ArrayList<Integer> palaaAiempaan(ArrayList<Integer> kaupungit, int vasen, int oikea) {
        while (vasen < oikea) {
            int apu = kaupungit.get(vasen);
            kaupungit.set(vasen++, kaupungit.get(oikea));
            kaupungit.set(oikea--, apu);
        }
        return kaupungit;
    }
}
