package objects;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Tablero {
    private int dimension;
    private float porcentajeBloqueo;
    private Casilla principio;
    private Casilla fin;

    public Tablero(int dimension, float porcentajeBloqueo) {
        this.dimension = dimension;
        this.porcentajeBloqueo = porcentajeBloqueo;
        Generar();
    }

    private void Generar() {
        LinkedList<Casilla> tmp = new LinkedList<>();
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                tmp.add(new Casilla(new Point(j, i)));
            }
        }

        this.principio = tmp.getFirst();
        this.fin = tmp.get(new Random().nextInt(tmp.size() - 2) + 1);

        Random rd = new Random();
        for (int i = 0; i < (int)(this.porcentajeBloqueo * tmp.size()); i++) {
            while (true) {
                int c = rd.nextInt(tmp.size());
                if (!(this.principio.equals(tmp.get(c)) || this.fin.equals(tmp.get(c)))){
                    tmp.get(c).setBloqueada();
                    break;
                }
            }
        }

        for (Casilla c : tmp) {
            for (Casilla v : tmp) {
                if (c.equals(v)) {

                } else if (c.getPosicion().x + 1 == v.getPosicion().x && c.getPosicion().y + 1 == v.getPosicion().y
                        || c.getPosicion().x - 1 == v.getPosicion().x && c.getPosicion().y - 1 == v.getPosicion().y) {
                    c.getVecinos().add(v);
                } else if (c.getPosicion().x == v.getPosicion().x && c.getPosicion().y + 1 == v.getPosicion().y
                        || c.getPosicion().x == v.getPosicion().x && c.getPosicion().y - 1 == v.getPosicion().y) {
                    c.getVecinos().add(v);
                } else if (c.getPosicion().x + 1 == v.getPosicion().x && c.getPosicion().y == v.getPosicion().y
                        || c.getPosicion().x - 1 == v.getPosicion().x && c.getPosicion().y == v.getPosicion().y) {
                    c.getVecinos().add(v);
                } else if (c.getPosicion().x + 1 == v.getPosicion().x && c.getPosicion().y - 1 == v.getPosicion().y
                        || c.getPosicion().x - 1 == v.getPosicion().x && c.getPosicion().y + 1 == v.getPosicion().y) {
                    c.getVecinos().add(v);
                }
            }
        }
    }

    public Casilla getPrincipio() {
        return principio;
    }

    public Casilla getFin() {
        return fin;
    }
}
