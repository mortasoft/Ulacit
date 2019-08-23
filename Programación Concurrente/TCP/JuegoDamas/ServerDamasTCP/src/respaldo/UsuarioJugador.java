package respaldo;

import respaldo.Movimiento;
import respaldo.Logica;
import respaldo.*;
import java.util.ArrayList;

public class UsuarioJugador {

    private static char[][] board;
    private static String name;

    public UsuarioJugador() {
    }

    public UsuarioJugador(int c, int d, String n) { //variables de jugador
        board = Tablero.board;
        name = n;
    }

    public ArrayList<Movimiento> legalMoves() { //Lista que contiene movimientos permitidos
        Logica ref = new Logica(board, 0);
        return Logica.findMoves();
    }

    public char[][] doMove(Movimiento m) { // metodo que hace el movimiento
        ArrayList<int[]> targets = m.getMove();
        for (int i = 0; i < targets.size() - 1; i++) {
            int or = targets.get(i)[0];
            int oc = targets.get(i)[1];
            int tr = targets.get(i + 1)[0];
            int tc = targets.get(i + 1)[1];

            if (board[or][oc] == 'X' && tr == 0) { // Cunado una pieza llega al final se corona
                board[or][oc] = 'K';
                m.setPromotion(true);
            }

            board[tr][tc] = board[or][oc];
            if (m.isJump()) {
                board[(or + tr) / 2][(oc + tc) / 2] = ' ';
            }
            board[or][oc] = ' ';
        }
        return board;
    }

    public static String getName() { //Consigue nombre de jugador
        return name;
    }
}