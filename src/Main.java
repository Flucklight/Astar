import objects.Casilla;
import objects.Tablero;

import java.awt.*;
import java.util.LinkedList;

public class Main {
    private static Tablero t;

    public static void main(String[] args) {
        t = new Tablero(5,0.0f);
        Pathfinder();
    }

    private static void Pathfinder() {
        Casilla puntero;
        LinkedList<Casilla> LA = new LinkedList<>();
        LinkedList<Casilla> LC = new LinkedList<>();
        LinkedList<Casilla> path = new LinkedList<>();
        LA.add(t.getPrincipio());
        puntero = LA.getFirst();
        while (!puntero.equals(t.getFin())) {
            LA.remove(puntero);
            LC.add(puntero);

            for (Casilla c : puntero.getVecinos()) {
                if (!c.isBloqueada() && !LA.contains(c) && !LC.contains(c)) {
                    LA.add(c);
                }
            }

            if (LA.contains(t.getFin())) {
                t.getFin().setPadre(puntero);
                break;
            }

            for (Casilla c : LA) {
                if (c.getG() != 0 && puntero.getVecinos().contains(c)) {
                    if (c.getPosicion().x == puntero.getPosicion().x || c.getPosicion().y == puntero.getPosicion().y) {
                        if (c.getG() > (puntero.getG() + 10)) {
                            c.setPadre(puntero);
                            c.setG(puntero.getG() + 10);
                            c.setF();
                        }
                    } else {
                        if (c.getG() > (puntero.getG() + 15)) {
                            c.setPadre(puntero);
                            c.setG(puntero.getG() + 15);
                            c.setF();
                        }
                    }
                } else if (puntero.getVecinos().contains(c)) {
                    if (c.getPosicion().x == puntero.getPosicion().x || c.getPosicion().y == puntero.getPosicion().y) {
                        c.setG(10 + puntero.getG());
                    } else {
                        c.setG(15 + puntero.getG());
                    }
                    c.setPadre(puntero);
                    c.setH(CalcularH(c));
                    c.setF();
                }
            }

            puntero = LA.getFirst();
            for (Casilla c : LA) {
                if (c.getF() < puntero.getF()) {
                    puntero = c;
                }
            }
        }

        puntero = t.getFin();
        path.addFirst(puntero);
        while (true) {
            if (puntero.getPadre() != null) {
                path.addFirst(puntero.getPadre());
                puntero = puntero.getPadre();
            } else {
                break;
            }
        }

        for (Casilla c : path) {
            System.out.println(c.getPosicion());
        }

    }

    private static int CalcularH(Casilla c) {
        int  m = 0;
        Point p = new Point(c.getPosicion());
        while (!p.equals(t.getFin().getPosicion())) {
            if (p.x != t.getFin().getPosicion().x) {
                if (p.x < t.getFin().getPosicion().x) {
                    p.x++;
                    m++;
                } else if (p.x > t.getFin().getPosicion().x) {
                    p.x--;
                    m++;
                } if (p.y < t.getFin().getPosicion().y) {
                    p.y++;
                    m++;
                } else if (p.y > t.getFin().getPosicion().y) {
                    p.y--;
                    m++;
                }
            }
        }
        return m * 10;
    }
}
