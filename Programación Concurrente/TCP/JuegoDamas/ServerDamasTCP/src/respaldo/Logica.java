package respaldo;

import respaldo.Movimiento;
import java.util.ArrayList;

public class Logica {
    
    //Inicializa matriz del tablero
    public static char[][] board;
    private static boolean jumpsPossible = false;

    public Logica() {
    } // constructor

    public Logica(char[][] b, int depth) {
       board = b;
    }



    public static void flip() { // used by evaluation
        for (int i = 0; i < 4; i++) {
            char[] tempr = board[i]; // flips the ranks
            board[i] = board[7 - i];
            board[7 - i] = tempr;
        }
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 4; j++) { // flips the files
//                if (Character.isUpperCase(board[i][j])) // switches the colour of pieces (used for AI)
//                {
//                    board[i][j] = Character.toLowerCase(board[i][j]);
//                } else {
//                    board[i][j] = Character.toUpperCase(board[i][j]);
//                }
//                if (Character.isUpperCase(board[i][7 - j])) {
//                    board[i][7 - j] = Character.toLowerCase(board[i][7 - j]);
//                } else {
//                    board[i][7 - j] = Character.toUpperCase(board[i][7 - j]);
//                }
//
//                char tempf = board[i][j];
//                board[i][j] = board[i][7 - j];
//                board[i][7 - j] = tempf;
//            }
//        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'x') {
                    board[i][j] = 'o';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
	// =====================================================================================

    public static ArrayList<Movimiento> findMoves() {
        ArrayList<Movimiento> allMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j]) {
                    case 'X': // pawns
                        allMoves.addAll(checkX(i, j));
                        break;
                    case 'K': // kings
                        allMoves.addAll(checkK(i, j));
                }
            }
        }
        if (jumpsPossible) { // SI SALTO ES POSIBLE ES OBLIGATORIO
            
            ArrayList<Movimiento> tempMoves = new ArrayList<>();
            int len = allMoves.size();
            for (int i = 0; i < len; i++) {
                if (allMoves.get(i).isJump()) {
                    tempMoves.add(allMoves.get(i));
                }
            }
            allMoves = tempMoves;
        }
        jumpsPossible = false;
        return allMoves;
    }
	// =====================================================================================

    public static ArrayList<Movimiento> checkK(int r, int c) { // check kings
        ArrayList<Movimiento> moves = new ArrayList<>();

        // king jumping moves
        if (!immediateJumps(r, c, 'K').isEmpty()) { // jump moves take priority
            jumpsPossible = true;
            moves.addAll(getJumpMoves(r, c, new Movimiento(r, c, 0), new ArrayList<>(), 'K'));
            return moves;
        }

        // normal king moves
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                try {
                    if (board[r + j][c + i] == ' ') {
                        Movimiento tempMove = new Movimiento(r, c, 0);
                        tempMove.addDestination((r + j), (c + i), 0);
                        moves.add(tempMove);
                    }
                } catch (Exception e) {
                }
            }
        }
        return moves;
    }

    public static ArrayList<Movimiento> checkX(int r, int c) { // check normal pieces 
        ArrayList<Movimiento> moves = new ArrayList<>();

        // jumping moves
        if (!immediateJumps(r, c, 'X').isEmpty()) { // jump moves take priority
            jumpsPossible = true;
            moves.addAll(getJumpMoves(r, c, new Movimiento(r, c, 0), new ArrayList<>(), 'X'));
            return moves;
        }

        // normal moves
        for (int i = -1; i <= 1; i += 2) {
            try {
                if (board[r - 1][c + i] == ' ') {
                    Movimiento tempMove = new Movimiento(r, c, 0);
                    tempMove.addDestination((r - 1), (c + i), 0);
                    moves.add(tempMove);
                }
            } catch (Exception e) {
            }
        }
        return moves;
    }
	// =====================================================================================

    /*
     * recursively find all jumping paths of a piece
     * this thing took me hours - 27/Sep/2015 never4get
     */
    public static ArrayList<Movimiento> getJumpMoves(int r, int c, Movimiento jump, ArrayList<Movimiento> paths, char piece) {
        ArrayList<String> immediateJumps = immediateJumps(r, c, piece);

        if (immediateJumps.isEmpty()) { // end of current jump, load jump to list
            Movimiento temp = new Movimiento(jump); // real copy of the object - not a reference
            paths.add(temp);
            jump.removeLastDestination(); // !! important !!
            return null;
        }

        for (int i = 0; i < immediateJumps.size(); i++) { // loops through paths at the crossing
            String target = immediateJumps.get(i);
            int destr = Character.getNumericValue(target.charAt(0));
            int destc = Character.getNumericValue(target.charAt(1));
            jump.addDestination(destr, destc, board[(r + destr) / 2][(c + destc) / 2]);

            // temporary move to avoid overflow error with king (can go forwards and back)
            // i refrained from using jump as its properties changes throughout the loop and so cannot be undone
            Movimiento tempMove = new Movimiento(r, c, 0);
            tempMove.addDestination(destr, destc, board[(r + destr) / 2][(c + destc) / 2]);
//            makeMove(tempMove);
            getJumpMoves(destr, destc, jump, paths, piece); // moves onto the next level, starting from the new square
//            undoMove(tempMove);
        }
        jump.removeLastDestination(); // !! important !!
        return paths;
    }

    // helper function for getJumpMoves(), finds all possible jumps
    public static ArrayList<String> immediateJumps(int r, int c, char piece) {
        ArrayList<String> temp = new ArrayList<>();
        if (piece == 'X') // pawn jumps
        {
            for (int i = -1; i <= 1; i += 2) {
                try {
                    if (Character.isLowerCase(board[r - 1][c + i]) && board[r - 2][c + i * 2] == ' ') {
                        temp.add("" + (r - 2) + (c + i * 2));
                    }
                } catch (Exception e) {
                }
            }
        } else if (piece == 'K') // king jumps
        {
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    try {
                        if (Character.isLowerCase(board[r + j][c + i]) && board[r + j * 2][c + i * 2] == ' ') {
                            temp.add("" + (r + j * 2) + (c + i * 2));
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return temp;
    }
    // =====================================================================================
}