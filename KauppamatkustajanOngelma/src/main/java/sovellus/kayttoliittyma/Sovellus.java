/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovellus.kayttoliittyma;

import java.util.Random;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

/**
 *
 * @author luxville
 */
public class Sovellus extends Application {

    BorderPane asettelu;
    Button ahnePainike;
    Button arvontaPainike;
    Button brutePainike;
    Button christofidesPainike;
    Button keskeytysPainike;
    Button lopetusPainike;
    Button nollausPainike;
    Button ohjePainike;
    Button palautaAlkutilannePainike;
    Button piirraReittiJarjestyksessaPainike;
    Pane kartta;
    TextField kaupunkienLkm;
    VBox valikko;

    int[] xKoordinaatit, yKoordinaatit;

    private final int LEVEYS = 1600;
    private final int KORKEUS = 900;
    private final int X = 8;

    @Override
    public void start(Stage ikkuna) throws Exception {
        asettelu = new BorderPane();

        valikko = new VBox();
        valikko.setSpacing(2);

        kartta = new Pane();
        kartta.setPrefSize(LEVEYS, KORKEUS);
        kartta.setStyle("-fx-border-color: black; -fx-background-color: lightgrey");

        alustaValikko();

        asettelu.setLeft(valikko);

        Circle a = new Circle(30, 150, X);
        Circle b = new Circle(530, 150, X);
        Circle c = new Circle(30, 350, X);
        Circle d = new Circle(530, 350, X);
        Circle e = new Circle(830, 550, X);

        kartta.getChildren().addAll(a, b, c, d, e);

        Path reitti = new Path();
        reitti.setStyle("-fx-stroke: red; -fx-stroke-width: 4");
        reitti.getElements().add(new MoveTo(a.getCenterX(), a.getCenterY()));
        reitti.getElements().add(new LineTo(b.getCenterX(), b.getCenterY()));
        reitti.getElements().add(new LineTo(d.getCenterX(), d.getCenterY()));
        reitti.getElements().add(new LineTo(e.getCenterX(), e.getCenterY()));
        reitti.getElements().add(new LineTo(c.getCenterX(), c.getCenterY()));
        reitti.getElements().add(new LineTo(a.getCenterX(), a.getCenterY()));

        kartta.getChildren().add(reitti);

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
        alustaAhnePainike();
        alustaChristofidesPainike();
        // Nopeussäätö visualisoinnin etenemiselle
        alustaKeskeytysPainike();
        alustaNollausPainike();
        alustaPalautaAlkutilannePainike();
        alustaOhjePainike();
        alustaLopetusPainike();
        valikko.getChildren().addAll(new Label("Kaupunkeja"), kaupunkienLkm,
                arvontaPainike, piirraReittiJarjestyksessaPainike, brutePainike, ahnePainike, christofidesPainike,
                keskeytysPainike, palautaAlkutilannePainike, nollausPainike,
                ohjePainike, lopetusPainike);
    }

    private Button alustaArvontaPainike() {
        arvontaPainike = new Button("Luo kaupungit");
        arvontaPainike.setOnAction((event) -> {
            String lkm = kaupunkienLkm.getText();
            if (!lkm.isBlank() && onNumero(lkm)) {
                int kaupunkeja = (int) Double.parseDouble(lkm);
                if (kaupunkeja > 1) {
                    xKoordinaatit = new int[kaupunkeja];
                    yKoordinaatit = new int[kaupunkeja];
                    for (int i = 0; i < kaupunkeja; i++) {
                        luoKaupunki(i);
                    }
                }
            }
        });
        return arvontaPainike;
    }

    private Button alustaBrutePainike() {
        brutePainike = new Button("Brute force");
        brutePainike.setStyle("-fx-background-color: blue");

        return brutePainike;
    }

    private Button alustaAhnePainike() {
        ahnePainike = new Button("Lähin naapuri");
        ahnePainike.setStyle("-fx-background-color: blue");

        return ahnePainike;
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
            for (int i = 0; i < xKoordinaatit.length; i++) {
                int loppu = i + 1;
                if (i + 1 == xKoordinaatit.length) {
                    loppu = 0;
                }
                piirraTie(i, loppu);
            }
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
}
