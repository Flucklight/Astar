package objects;

import java.awt.*;
import java.util.ArrayList;

public class Casilla {
    private int G;
    private int H;
    private int F;
    private Point posicion;
    private boolean bloqueada;
    private Casilla padre;
    private ArrayList<Casilla> vecinos;

    public Casilla(Point p) {
        this.G = 0;
        this.H = 0;
        this.F = 0;
        this.bloqueada = false;
        this.padre = null;
        this.posicion = p;
        this.vecinos = new ArrayList<>();
    }

    public Point getPosicion() {
        return posicion;
    }

    public Casilla getPadre() {
        return padre;
    }

    public void setPadre(Casilla padre) {
        this.padre = padre;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public int getF() {
        return F;
    }

    public void setF() {
        F = G + H;
    }

    public ArrayList<Casilla> getVecinos() {
        return vecinos;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada() {
        this.bloqueada = true;
    }
}