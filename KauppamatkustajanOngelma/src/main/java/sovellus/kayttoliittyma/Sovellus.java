/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sovellus.kayttoliittyma;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author luxville
 */
public class Sovellus extends Application {

    Button ahnePainike;
    Button arvontaPainike;
    Button brutePainike;
    Button christofidesPainike;
    Button keskeytysPainike;
    Button lopetaPainike;
    Button nollausPainike;
    Button ohjePainike;
    VBox valikko;

    @Override
    public void start(Stage ikkuna) throws Exception {
        BorderPane asettelu = new BorderPane();

        valikko = new VBox();
        valikko.setSpacing(2);

        TextField pisteidenLkm = new TextField();
        arvontaPainike = new Button("Luo pisteet");
        brutePainike = new Button("Brute force");
        ahnePainike = new Button("Lähin naapuri");
        christofidesPainike = new Button("Christofides");
        keskeytysPainike = new Button("Keskeytä etsintä");
        nollausPainike = new Button("Aloita alusta");
        ohjePainike = new Button("Ohjeita");
        lopetaPainike = new Button("Sulje sovellus");
        // Nopeussäätö visualisoinnin etenemiselle
        valikko.getChildren().addAll(pisteidenLkm, arvontaPainike,
                brutePainike, ahnePainike, christofidesPainike,
                keskeytysPainike, nollausPainike, ohjePainike, lopetaPainike);

        asettelu.setLeft(valikko);
        
        Pane kartta = new Pane();
        kartta.setPrefSize(1000, 600);
        kartta.setStyle("-fx-border-color: black");
        
        kartta.getChildren().add(new Circle(30, 150, 5));
        
        asettelu.setRight(kartta);
        
        //Scene visualisoisoija = new Scene(ruutu);
        ikkuna.setTitle("Kauppamatkustajan ongelman visualisoija");
        Scene nakyma = new Scene(asettelu);

        ikkuna.setScene(nakyma);
        ikkuna.show();
    }

}
