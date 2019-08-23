package respaldo;

import java.util.*;

public class Movimiento {

    private ArrayList<int[]> destinations;
    private int score;
    private boolean promotion;

    public Movimiento() {
    }

    public Movimiento(Movimiento temp) { // constructor for copying an object
        this.destinations = new ArrayList<>();
        for (int i = 0; i < temp.destinations.size(); i++) {
            this.destinations.add(temp.destinations.get(i));
        }
//        this.score = temp.score;
        this.promotion = temp.promotion;
    }

    public Movimiento(int r, int c, int capturedPiece) {
        destinations = new ArrayList<>();
        int[] initSquare = {r, c, capturedPiece};
        destinations.add(initSquare);
        promotion = false;
    }
	// =====================================================================================
    // end of constructors

    public void addDestination(int r, int c, int capturedPiece) {
        int[] destinationSquare = {r, c, capturedPiece};
        destinations.add(destinationSquare);
    }

    public void removeLastDestination() {
        destinations.remove(destinations.size() - 1);
    }
    
    public int[] getStart() {
        return destinations.get(0);
    }
    
    public int[] getEnd() {
        int last = destinations.size()-1;
        return destinations.get(last);
    }

    public ArrayList<int[]> getMove() {
        return destinations;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int s) {
        score = s;
    }

    public void setPromotion(boolean p) {
        promotion = p;
    }

    public boolean isPromotion() {
        return promotion;
    }

    public boolean isJump() {
        int r0 = destinations.get(0)[0];
        int r1 = destinations.get(1)[0];
        return Math.abs(r0 - r1) == 2;
    }

    public boolean isIdentical(Movimiento m) { // check if the move made is correct
        ArrayList<int[]> list = m.getMove();
        int len = list.size();

        if (!(len == destinations.size())) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (!(destinations.get(i)[0] == list.get(i)[0] && destinations.get(i)[1] == list.get(i)[1])) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String returnStr = "";
        for (int i = 0; i < destinations.size(); i++) {
            returnStr += Arrays.toString(destinations.get(i));
        }
        return returnStr;
    }
}