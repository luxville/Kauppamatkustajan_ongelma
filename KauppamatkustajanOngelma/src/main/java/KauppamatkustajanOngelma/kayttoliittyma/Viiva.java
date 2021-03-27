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
public class Viiva {
    private int alkuX, alkuY, loppuX, loppuY;
    private double pituus;

    public Viiva(int alkuX, int alkuY, int loppuX, int loppuY, double pituus) {
        this.alkuX = alkuX;
        this.alkuY = alkuY;
        this.loppuX = loppuX;
        this.loppuY = loppuY;
        setPituus(alkuX, alkuY, loppuX, loppuY);
        this.pituus = getPituus();
    }

    public double getPituus() {
        return pituus;
    }

    public void setPituus(int alkuX, int alkuY, int loppuX, int loppuY) {
        int xMuutos = Math.abs(loppuX - alkuX);
        int yMuutos = Math.abs(loppuY - alkuY);
        this.pituus = Math.sqrt(xMuutos * xMuutos + yMuutos * yMuutos);
    }
    
    
}
