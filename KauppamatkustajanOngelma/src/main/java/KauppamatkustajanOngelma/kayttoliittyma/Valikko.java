/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KauppamatkustajanOngelma.kayttoliittyma;

/**
 *
 * @author luxville
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Valikko extends Application {

    @Override
    public void start(Stage ikkuna) throws Exception {
        BorderPane asettelu = new BorderPane();
        
        VBox valikko = new VBox();
        valikko.setSpacing(2);
        
        TextField pisteidenLkm = new TextField();
        Button arvontaPainike = new Button("Luo pisteet");
        Button brutePainike = new Button("Brute force");
        Button ahnePainike = new Button("Lähin naapuri");
        Button christofidesPainike = new Button("Christofides");
        Button keskeytysPainike = new Button("Keskeytä etsintä");
        Button nollausPainike = new Button("Aloita alusta");
        Button ohjePainike = new Button("Ohjeita");
        // Nopeussäätö visualisoinnin etenemiselle
        valikko.getChildren().addAll(pisteidenLkm, arvontaPainike,
                brutePainike, ahnePainike, christofidesPainike,
                keskeytysPainike, nollausPainike, ohjePainike);
        
        asettelu.setLeft(valikko);
        Scene nakyma = new Scene(asettelu);
        
        ikkuna.setScene(nakyma);
        ikkuna.show();
    }
    
    public static void main(String[] args) {
        launch(Valikko.class);
    }
}
