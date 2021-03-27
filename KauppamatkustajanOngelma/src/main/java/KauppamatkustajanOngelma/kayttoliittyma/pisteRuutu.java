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
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class pisteRuutu extends Application {
    
    @Override
    public void start(Stage ikkuna) throws Exception {
        Pane ruutu = new Pane();
        ruutu.setPrefSize(1000, 600);
        
        ruutu.getChildren().add(new Circle(30, 150, 5));
        
        Scene visualisoisoija = new Scene(ruutu);
        ikkuna.setTitle("Kauppamatkustajan ongelman visualisoija");
        ikkuna.setScene(visualisoisoija);
        ikkuna.show();
    }
}
