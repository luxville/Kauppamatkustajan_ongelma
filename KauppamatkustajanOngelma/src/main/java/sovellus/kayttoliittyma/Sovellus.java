/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovellus.kayttoliittyma;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    TextField pisteidenLkm;
    VBox valikko;
    
    private final int LEVEYS = 1600;
    private final int KORKEUS = 900;

    @Override
    public void start(Stage ikkuna) throws Exception {
        asettelu = new BorderPane();

        valikko = new VBox();
        valikko.setSpacing(2);

        alustaValikko();
        System.out.println("3");
        valikko.getChildren().add(pisteidenLkm);
        System.out.println("3,5");
        valikko.getChildren().addAll(arvontaPainike,
                brutePainike, ahnePainike, christofidesPainike,
                keskeytysPainike, nollausPainike, ohjePainike, lopetusPainike);
        System.out.println("4");
        asettelu.setLeft(valikko);

        Pane kartta = new Pane();
        kartta.setPrefSize(LEVEYS, KORKEUS);
        kartta.setStyle("-fx-border-color: black; -fx-background-color: lightgrey");

        Circle a = new Circle(30, 150, 9);
        Circle b = new Circle(530, 150, 7);
        Circle c = new Circle(30, 350, 8);
        Circle d = new Circle(530, 350, 6);
        Circle e = new Circle(830, 550, 5);

        kartta.getChildren().addAll(a, b, c, d, e);

        Line ad = new Line();
        ad.setStartX(a.getCenterX());
        ad.setStartY(a.getCenterY());
        ad.setEndX(d.getCenterX());
        ad.setEndY(d.getCenterY());

        kartta.getChildren().add(ad);

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

        //Scene visualisoisoija = new Scene(ruutu);
        ikkuna.setTitle("Kauppamatkustajan ongelman visualisoija");
        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();
    }

    private void alustaValikko() {
        pisteidenLkm = new TextField("0");
        String lkm = pisteidenLkm.getText();
        alustaArvontaPainike(lkm);
        alustaBrutePainike();
        alustaAhnePainike();
        alustaChristofidesPainike();
        alustaKeskeytysPainike();
        alustaNollausPainike();
        // Nopeussäätö visualisoinnin etenemiselle
        alustaOhjePainike();
        alustaLopetusPainike();
    }

    private Button alustaArvontaPainike(String lkm) {
        arvontaPainike = new Button("Luo kaupungit");
        if (!lkm.isBlank() && onNumero(lkm)) {
            arvontaPainike.setOnAction((event) -> {
                int kaupunkeja = (int) Double.parseDouble(lkm);
                if (kaupunkeja > 1) {
                    for (int i = 0; i < kaupunkeja; i++) {
                        luoKaupunki();
                    }
                }
            });
        }
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

    private Button alustaNollausPainike() {
        nollausPainike = new Button("Aloita alusta");

        return nollausPainike;
    }

    private Button alustaOhjePainike() {
        ohjePainike = new Button("Ohjeita");

        return ohjePainike;
    }

    private Button alustaLopetusPainike() {
        lopetusPainike = new Button("Sulje sovellus");
        lopetusPainike.setStyle("-fx-background-color: red");
        lopetusPainike.setOnAction((event) -> {
            Platform.exit();
            System.exit(0);
        });
        return lopetusPainike;
    }

    private boolean onNumero(String lkm) {
        try {
            Double.parseDouble(lkm);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void luoKaupunki() {
        
    }
}
