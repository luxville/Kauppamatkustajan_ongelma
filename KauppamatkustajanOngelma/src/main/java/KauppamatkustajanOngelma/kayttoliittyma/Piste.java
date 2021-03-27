/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KauppamatkustajanOngelma.kayttoliittyma;

import javafx.scene.shape.Circle;

/**
 *
 * @author luxville
 */
public class Piste {
    private Circle piste;
    private int x, y;

    public Piste(int x, int y) {
        this.piste = new Circle(x, y, 5);
        this.x = x;
        this.y = y;
    }

    public Circle getPiste() {
        return piste;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
