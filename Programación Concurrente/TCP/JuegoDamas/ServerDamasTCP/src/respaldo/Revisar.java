package respaldo;

import respaldo.Logica;

public class Revisar extends Logica {

    private final char[][] board;
    private int pawns, kings;

    public Revisar(char[][] b) {
        board = b;
        pawns = 0;
        kings = 0;
    }

//    public int rate() {
//        int score = 0;
//        score += (material() + position());
//        flip();
//        score -= (material() + position());
//        flip();
//        return -score;
//    }

    public int material() {
        int score;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j]) {
                    case 'X':
                        pawns++;
                        break;
                    case 'K':
                        kings++;
                        break;
                }
            }
        }
        score = pawns + kings * 3;
        return score;
    }

    public int position() {
        int score = 0;
        int[][] table = {
            {0, 4, 0, 4, 0, 4, 0, 4},
            {4, 0, 3, 0, 3, 0, 3, 0},
            {0, 3, 0, 2, 0, 2, 0, 4},
            {4, 0, 2, 0, 1, 0, 3, 0},
            {0, 3, 0, 1, 0, 2, 0, 4},
            {4, 0, 2, 0, 2, 0, 3, 0},
            {0, 3, 0, 3, 0, 3, 0, 4},
            {4, 0, 4, 0, 4, 0, 4, 0},};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Character.isUpperCase(board[i][j])) {
                    score += table[i][j];
                }
            }
        }
        return score;
    }
}