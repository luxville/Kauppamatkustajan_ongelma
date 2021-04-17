/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovellus.kayttoliittyma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import sovellus.algoritmit.*;
import sovellus.logiikka.*;

/**
 *
 * @author luxville
 */
public class Sovellus extends Application {

    ArrayList<Integer> reitti = new ArrayList<>();
    BorderPane asettelu;
    Button lahinNaapuriPainike;
    Button arvontaPainike;
    Button brutePainike;
    Button christofidesPainike;
    Button keskeytysPainike;
    Button lopetusPainike;
    Button nollausPainike;
    Button ohjePainike;
    Button palautaAlkutilannePainike;
    Button piirraReittiJarjestyksessaPainike;
    double pituus;
    Pane kartta;
    TextArea siirtymat;
    TextField kaupunkienLkm;
    TextField lyhinReitti;
    TextField reitinPituus;
    VBox tulokset;
    VBox valikko;

    int[] xKoordinaatit, yKoordinaatit;
    double[] valimatkat;
    double[][] etaisyysmatriisi;

    private final int LEVEYS = 1500;
    private final int KORKEUS = 800;
    private final int PALJON = Integer.MAX_VALUE;
    private final int X = 8;

    @Override
    public void start(Stage ikkuna) throws Exception {
        asettelu = new BorderPane();

        valikko = new VBox();
        valikko.setSpacing(2);

        tulokset = new VBox();
        tulokset.setSpacing(2);

        kartta = new Pane();
        kartta.setPrefSize(LEVEYS, KORKEUS);
        kartta.setStyle("-fx-border-color: black; -fx-background-color: lightgrey");

        alustaValikko();
        alustaTulokset();

        asettelu.setLeft(valikko);
        asettelu.setBottom(tulokset);
        
        asettelu.setRight(kartta);

        ikkuna.setTitle("Kauppamatkustajan ongelman visualisoija");
        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();
    }

    private void alustaValikko() {
        kaupunkienLkm = new TextField();
        alustaArvontaPainike();
        alustaPiirraReittiJarjestyksessaPainike();
        alustaBrutePainike();
        alustaLahinNaapuriPainike();
        alustaChristofidesPainike();
        // Nopeussäätö visualisoinnin etenemiselle
        alustaKeskeytysPainike();
        alustaNollausPainike();
        alustaPalautaAlkutilannePainike();
        alustaOhjePainike();
        alustaLopetusPainike();
        valikko.getChildren().addAll(new Label("Kaupunkeja"), kaupunkienLkm,
                arvontaPainike, piirraReittiJarjestyksessaPainike, brutePainike,
                lahinNaapuriPainike, christofidesPainike, //tulokset,
                keskeytysPainike, palautaAlkutilannePainike, nollausPainike,
                ohjePainike, lopetusPainike);
    }

    private Button alustaArvontaPainike() {
        arvontaPainike = new Button("Luo kaupungit");
        arvontaPainike.setOnAction((event) -> {
            String lkm = kaupunkienLkm.getText();
            if (!lkm.strip().isBlank() && onNumero(lkm)) {
                int kaupunkeja = (int) Double.parseDouble(lkm);
                if (kaupunkeja > 1) {
                    xKoordinaatit = new int[kaupunkeja];
                    yKoordinaatit = new int[kaupunkeja];
                    valimatkat = new double[kaupunkeja];
                    etaisyysmatriisi = new double[kaupunkeja][kaupunkeja];
                    reitti = new ArrayList();
                    pituus = PALJON;
                    for (int i = 0; i < kaupunkeja; i++) {
                        luoKaupunki(i);
                    }
                    luoEtaisyysMatriisi();
                }
            }
        });
        return arvontaPainike;
    }

    private Button alustaBrutePainike() {
        brutePainike = new Button("Brute force");
        brutePainike.setStyle("-fx-background-color: blue");
        BruteForce brute = new BruteForce();
        double lyhinReitinPituus = Double.MAX_VALUE;
        
        brutePainike.setOnAction((event) -> {
            double nykyinenLyhinPituus = brute.bruteForceKauppamatkustaja(etaisyysmatriisi, 0);
            nykyinenLyhinPituus = Math.min(lyhinReitinPituus, nykyinenLyhinPituus);
        });

        return brutePainike;
    }

    private Button alustaLahinNaapuriPainike() {
        lahinNaapuriPainike = new Button("Lähin naapuri");
        lahinNaapuriPainike.setStyle("-fx-background-color: blue");
        LahinNaapuri lahinNaapuri = new LahinNaapuri();

        lahinNaapuriPainike.setOnAction((event) -> {
            double nykyinenLyhinPituus = lahinNaapuri.lahinNaapuriKauppamatkustaja(etaisyysmatriisi);
        });
        
        return lahinNaapuriPainike;
    }

    private Button alustaChristofidesPainike() {
        christofidesPainike = new Button("Christofides");
        christofidesPainike.setStyle("-fx-background-color: blue");

        return christofidesPainike;
    }

    private Button alustaKeskeytysPainike() {
        keskeytysPainike = new Button("Keskeytä");

        return keskeytysPainike;
    }

    private Button alustaLopetusPainike() {
        lopetusPainike = new Button("Sulje sovellus");
        //lopetusPainike.setStyle("-fx-background-color: red");
        lopetusPainike.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });
        return lopetusPainike;
    }

    private Button alustaNollausPainike() {
        nollausPainike = new Button("Tyhjennä");

        nollausPainike.setOnAction((event) -> {
            kartta.getChildren().clear();
            xKoordinaatit = null;
            yKoordinaatit = null;
            valimatkat = null;
            etaisyysmatriisi = null;
            reitti = null;
            lyhinReitti.setText("");
            siirtymat.setText("");
            reitinPituus.setText("");
        });
        return nollausPainike;
    }

    private Button alustaOhjePainike() {
        ohjePainike = new Button("Ohjeita");

        return ohjePainike;
    }

    private Button alustaPalautaAlkutilannePainike() {
        palautaAlkutilannePainike = new Button("Palauta alkutila");
        palautaAlkutilannePainike.setOnAction((event) -> {
            kartta.getChildren().clear();
            palautaKaupungit();
        });
        return palautaAlkutilannePainike;
    }

    private Button alustaPiirraReittiJarjestyksessaPainike() {
        piirraReittiJarjestyksessaPainike = new Button("Piirrä reitti");
        piirraReittiJarjestyksessaPainike.setOnAction((event) -> {
            reitti.add(0);
            for (int i = 0; i < xKoordinaatit.length; i++) {
                int loppu = i + 1;
                if (loppu == xKoordinaatit.length) {
                    loppu = 0;
                }
                valimatkat[i] = etaisyysmatriisi[i][loppu];
                reitti.add(loppu);
                piirraTie(i, loppu);
            }
            laskeReitinPituus();
            System.out.println(Arrays.toString(valimatkat));
            System.out.println(reitti.toString());
            for (int i = 0; i < xKoordinaatit.length; i++) {
                System.out.println(Arrays.toString(etaisyysmatriisi[i]));
            }
            lyhinReitti.setText(reitti.toString());
            siirtymat.setText(Arrays.toString(valimatkat));
        });
        return piirraReittiJarjestyksessaPainike;
    }

    private boolean onNumero(String lkm) {
        try {
            Double.parseDouble(lkm);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void luoKaupunki(int i) {
        Random random = new Random();
        int x = random.nextInt(LEVEYS);
        int y = random.nextInt(KORKEUS);
        System.out.println(x + " ja " + y);
        xKoordinaatit[i] = x;
        yKoordinaatit[i] = y;
        Circle kaupunki = new Circle(x, y, X);
        kartta.getChildren().add(kaupunki);
    }

    private void palautaKaupungit() {
        for (int i = 0; i < xKoordinaatit.length; i++) {
            Circle kaupunki = new Circle(xKoordinaatit[i], yKoordinaatit[i], X);
            System.out.println(xKoordinaatit[i] + " ja " + yKoordinaatit[i]);
            kartta.getChildren().add(kaupunki);
        }
    }

    private void piirraTie(int alku, int loppu) {
        Circle lahto = new Circle(xKoordinaatit[alku], yKoordinaatit[alku], X);
        Circle kohde = new Circle(xKoordinaatit[loppu], yKoordinaatit[loppu], X);
        Line tie = new Line();
        tie.setStartX(lahto.getCenterX());
        tie.setStartY(lahto.getCenterY());
        tie.setEndX(kohde.getCenterX());
        tie.setEndY(kohde.getCenterY());

        kartta.getChildren().add(tie);
    }

    private void alustaTulokset() {
        tulokset.getChildren().add(new Label("Reitin pituus:"));
        pituus = laskeReitinPituus();
        reitinPituus = new TextField(Double.toString(pituus));
        tulokset.getChildren().add(reitinPituus);
        tulokset.getChildren().add(new Label("Lyhin reitti:"));
        lyhinReitti = new TextField("");
        tulokset.getChildren().add(lyhinReitti);
        tulokset.getChildren().add(new Label("Välimatkat:"));
        siirtymat = new TextArea("");
        siirtymat.setPrefHeight(12);
        tulokset.getChildren().add(siirtymat);
    }

    private double laskeEtaisyys(int alku, int loppu) {
        int xMuutos = xKoordinaatit[loppu] - xKoordinaatit[alku];
        int yMuutos = yKoordinaatit[loppu] - yKoordinaatit[alku];
        double etaisyys = Math.sqrt(xMuutos * xMuutos + yMuutos * yMuutos);
        return etaisyys;
    }

    private double laskeReitinPituus() {
        if (valimatkat == null || valimatkat.length == 0) {
            return pituus;
        }
        double uusiPituus = 0;
        for (int i = 0; i < valimatkat.length; i++) {
            uusiPituus += valimatkat[i];
        }
        pituus = Math.min(pituus, uusiPituus);
        reitinPituus.setText(Double.toString(pituus));
        return pituus;
    }

    void luoEtaisyysMatriisi() {
        for (int i = 0; i < xKoordinaatit.length; i++) {
            for (int j = 0; j < xKoordinaatit.length; j++) {
                if (i == j) {
                    etaisyysmatriisi[i][j] = 0;
                    continue;
                }
                etaisyysmatriisi[i][j] = laskeEtaisyys(i, j);
                System.out.println(laskeEtaisyys(i, j));
            }
        }
    }
}
