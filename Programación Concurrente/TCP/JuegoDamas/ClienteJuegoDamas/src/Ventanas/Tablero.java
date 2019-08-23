package Ventanas;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import Logica.*;

public final class Tablero extends javax.swing.JFrame {

    private static String whiteName;
    private static String blackName;
    private static int whiteType; // 1 == comp, 0 == human
    private static int blackType;
    private static int depth;
    private boolean pressed;
    private volatile boolean moveCompleted; // volatile as threads needs to wait for its value to change
    private boolean gameOngoing;
    private boolean compTurn;
    private char winner;
    private char turn;
    private JButton[][] squares;
    private ArrayList<Movimiento> allMoves;
    private ArrayList<int[]> moveSquares;
    private ImageIcon wPawn;
    private ImageIcon bPawn;
    private ImageIcon wKing;
    private ImageIcon bKing;
    private int fromR;
    private int fromC;
    private int toR;
    private int toC;

    protected char[][] initBoard = {
        {' ', 'o', ' ', 'o', ' ', 'o', ' ', 'o'}, // small letters: black pieces
        {'o', ' ', 'o', ' ', 'o', ' ', 'o', ' '},
        {' ', 'o', ' ', 'o', ' ', 'o', ' ', 'o'},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'X', ' ', 'X', ' ', 'X', ' ', 'X', ' '},
        {' ', 'X', ' ', 'X', ' ', 'X', ' ', 'X'},
        {'X', ' ', 'X', ' ', 'X', ' ', 'X', ' '}, // capital: white pieces
    };
    public static char[][] board;

    public Tablero(String wN, String bN, int wT, int bT, int d) {
//        whiteName = wN;
//        blackName = bN;
//        whiteType = wT;
//        blackType = bT;
//        depth = d;
//        pressed = false;
//        gameOngoing = true; // assuming the game is starting when the game starts
//        moveSquares = new ArrayList<>();
//        board = initBoard;
//        turn = 'w'; // default turn = white
//
//        initComponents();
//        initBoard();
//        updateBoard();
//
//        gameLogic = new Thread(() -> {
//            // startGame();
//        });
//        gameLogic.start();
    }

    public Tablero() {
        pressed = false;
        gameOngoing = true; // assuming the game is starting when the game starts
        moveSquares = new ArrayList<>();
        board = initBoard;
        turn = 'w'; // default turn = white

        initComponents();
        initBoard();
        updateBoard();

    }

    public void initBoard() { // initialise board with all squares & actionlisteners as well as images
        squares = new JButton[8][8];
        squares[0][0] = b00;
        squares[1][0] = b10;
        squares[2][0] = b20;
        squares[3][0] = b30;
        squares[0][1] = b01;
        squares[1][1] = b11;
        squares[2][1] = b21;
        squares[3][1] = b31;
        squares[0][2] = b02;
        squares[1][2] = b12;
        squares[2][2] = b22;
        squares[3][2] = b32;
        squares[0][3] = b03;
        squares[1][3] = b13;
        squares[2][3] = b23;
        squares[3][3] = b33;
        squares[0][4] = b04;
        squares[1][4] = b14;
        squares[2][4] = b24;
        squares[3][4] = b34;
        squares[0][5] = b05;
        squares[1][5] = b15;
        squares[2][5] = b25;
        squares[3][5] = b35;
        squares[0][6] = b06;
        squares[1][6] = b16;
        squares[2][6] = b26;
        squares[3][6] = b36;
        squares[0][7] = b07;
        squares[1][7] = b17;
        squares[2][7] = b27;
        squares[3][7] = b37;

        squares[4][0] = b40;
        squares[5][0] = b50;
        squares[6][0] = b60;
        squares[7][0] = b70;
        squares[4][1] = b41;
        squares[5][1] = b51;
        squares[6][1] = b61;
        squares[7][1] = b71;
        squares[4][2] = b42;
        squares[5][2] = b52;
        squares[6][2] = b62;
        squares[7][2] = b72;
        squares[4][3] = b43;
        squares[5][3] = b53;
        squares[6][3] = b63;
        squares[7][3] = b73;
        squares[4][4] = b44;
        squares[5][4] = b54;
        squares[6][4] = b64;
        squares[7][4] = b74;
        squares[4][5] = b45;
        squares[5][5] = b55;
        squares[6][5] = b65;
        squares[7][5] = b75;
        squares[4][6] = b46;
        squares[5][6] = b56;
        squares[6][6] = b66;
        squares[7][6] = b76;
        squares[4][7] = b47;
        squares[5][7] = b57;
        squares[6][7] = b67;
        squares[7][7] = b77;

        String[] paths = new String[4];
        try {
            BufferedReader br;
            try (FileReader fr = new FileReader("save/piecePaths.txt")) {
                br = new BufferedReader(fr);
                for (int i = 0; i < 4; i++) {
                    String str = br.readLine();
                    paths[i] = str;
                }
            }
            br.close();
        } catch (IOException e) {
        }

        wPawn = new ImageIcon(paths[0]);
        wKing = new ImageIcon(paths[1]);
        bPawn = new ImageIcon(paths[2]);
        bKing = new ImageIcon(paths[3]);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                addActionListenerTo(i, j);
            }
        }
    }

    public void addIcon(ImageIcon image, JButton bt) { // resize and set Icon method used instead of setIcon()
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(bt.getHeight(), bt.getWidth(), java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        bt.setIcon(icon);
    }

    public void addActionListenerTo(int r, int c) {
        squares[r][c].addActionListener((ActionEvent e) -> {
            if (!compTurn) {
                if (!pressed) {
                    pressed = true;
                    fromR = 8;
                    fromC = 4;
                    squares[fromR][fromC].setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                    paintMoveSquares(r, c);
                    moveCompleted = false;
                    System.out.println("from row" + r + ", column" + c);
                } else {
                    pressed = false;
                    toR = r;
                    toC = c;
                    squares[fromR][fromC].setBorder(BorderFactory.createEmptyBorder());
                    clearMoveSquares();
                    if (!(fromR == toR && fromC == toC)) {
                        moveCompleted = true;
                    }
                    System.out.println("to row" + r + ", column" + c + "\nselection completed: " + moveCompleted);
                }
            }
        });
    }

    public void paintMoveSquares(int r, int c) {
        allMoves.stream().forEach((m) -> { // painting all target squares of the selected square 
            int startR = m.getStart()[0];
            int startC = m.getStart()[1];
            if (turn == 'b') {
                startR = 7 - startR; // black's turn - selected square is flipped to match up with the flipped board
                startC = 7 - startC;
            }
            if ((startR == r) && (startC == c)) {
                int endR = m.getEnd()[0];
                int endC = m.getEnd()[1];
                if (turn == 'b') {
                    endR = 7 - endR; // end squares needs to be flipped to be in correct board position
                    endC = 7 - endC;
                }
                int[] endCoord = {endR, endC};
                moveSquares.add(endCoord);
            }
        });

        moveSquares.stream().forEach((moveSquare) -> { // paint all target squares with green boarders
            int paintR = moveSquare[0];
            int paintC = moveSquare[1];
            squares[paintR][paintC].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        });
    }

    public void clearMoveSquares() { // undoing what was done in paintMoveSquares()
        moveSquares.stream().forEach((moveSquare) -> {
            int paintR = moveSquare[0];
            int paintC = moveSquare[1];
            squares[paintR][paintC].setBorder(BorderFactory.createEmptyBorder());
        });
        moveSquares.clear();
    }

//    public Movimiento getMove() {
//        while (true) {
//            if (moveCompleted) {
//                if (turn == 'b') {
//                    // if it's black's turn, then store the coordinates as if viewed from white's side since that will be
//                    // how the move is proccessed
//                    fromR = 7 - fromR;
//                    fromC = 7 - fromC;
//                    toR = 7 - toR;
//                    toC = 7 - toC;
//                }
//                for (Movimiento m : allMoves) { // loop through allMoves to check if the squares selected match a legal move
//                    int[] start = m.getStart();
//                    int[] end = m.getEnd();
//                    if (start[0] == fromR && start[1] == fromC && end[0] == toR && end[1] == toC) {
//                        moveCompleted = false; // completed move, make room for next move
//                        System.out.println("valid move");
//                        return m; // if the move is valid, then return the move
//                    }
//                }
//                // if we reached this point - the move selected was not valid
//                moveCompleted = false; // setting moveCompleted to false - the move wasn't valid!
//                System.out.println("move invalid.");
//            }
//        }
//    }
    public void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                switch (board[i][j]) {
                    case 'X':
                        addIcon(wPawn, squares[i][j]);
                        break;
                    case 'o':
                        addIcon(bPawn, squares[i][j]);
                        break;
                    case 'K':
                        addIcon(wKing, squares[i][j]);
                        break;
                    case 'k':
                        addIcon(bKing, squares[i][j]);
                        break;
                    default:
                        squares[i][j].setIcon(null);
                }
            }
        }
    }

    public void startGame() {
        updateBoard(); // update the board in case it's not the default position
//        while (gameOngoing) {
//            if (turn == 'w') {
//                turnLabel.setText("It's white's turn!");
//                UsuarioJugador p1 = new UsuarioJugador(0, depth, "White");
//                allMoves = p1.legalMoves();
//                if (allMoves.isEmpty()) { // if one side has no moves - game is over
//                    winner = 'b';
//                    gameOngoing = false;
//                    break;
//                }
//
//                if (whiteType == 0) { // if player is a human, then launch getMove function
//                    compTurn = false;
//                    p1.doMove(getMove());
//                } else {
//                    compTurn = true;
////                    board = p1.think(); // if player is computer, then let the computer make its move
//                    try { Thread.sleep(500); } catch (InterruptedException e) {} // minor pause for realism
//                }
//                updateBoard(); // update board with the move just made
//                flip(); // flip the board for black to make move
//                turn = 'b'; // hand turn to black
//            }
//            if (turn == 'b') {
//                turnLabel.setText("It's black's turn!");
//                UsuarioJugador p2 = new UsuarioJugador(1, depth, "Black");
//                allMoves = p2.legalMoves();
//                if (allMoves.isEmpty()) { // if one side has no moves - game is over
//                    winner = 'w';
//                    gameOngoing = false;
//                    break;
//                }
//
//                if (blackType == 0) {
//                    compTurn = false;
//                    p2.doMove(getMove());
//                } else {
//                    compTurn = true;
////                    board = p2.think();
//                    try { Thread.sleep(500); } catch (InterruptedException e) {}
//                }
//                flip(); // flips the board back for white
//                updateBoard();
//                turn = 'w'; // hands turn back to white
//            }
//        }
//        turnLabel.setText("Game over!!!");
//        String winnerName = "";
//        if (winner == 'w') winnerName = whiteName;
//        else winnerName = blackName;
//        JOptionPane.showMessageDialog(this, winnerName + " won the game!", "We have a winner!", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void flip() { // flip board
        for (int i = 0; i < 4; i++) {
            char[] tempr = board[i];
            board[i] = board[7 - i];
            board[7 - i] = tempr;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) { // flips the files
                if (Character.isUpperCase(board[i][j])) // switches the colour of pieces (used for AI)
                {
                    board[i][j] = Character.toLowerCase(board[i][j]);
                } else {
                    board[i][j] = Character.toUpperCase(board[i][j]);
                }
                if (Character.isUpperCase(board[i][7 - j])) {
                    board[i][7 - j] = Character.toLowerCase(board[i][7 - j]);
                } else {
                    board[i][7 - j] = Character.toUpperCase(board[i][7 - j]);
                }

                char tempf = board[i][j];
                board[i][j] = board[i][7 - j];
                board[i][7 - j] = tempf;
            }
        }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        blackLabel = new javax.swing.JLabel();
        whiteLabel = new javax.swing.JLabel();
        turnLabel = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        boardPane = new javax.swing.JPanel();
        CheckersBoard = new javax.swing.JPanel();
        b00 = new javax.swing.JButton();
        b01 = new javax.swing.JButton();
        b02 = new javax.swing.JButton();
        b03 = new javax.swing.JButton();
        b04 = new javax.swing.JButton();
        b05 = new javax.swing.JButton();
        b06 = new javax.swing.JButton();
        b07 = new javax.swing.JButton();
        b10 = new javax.swing.JButton();
        b11 = new javax.swing.JButton();
        b12 = new javax.swing.JButton();
        b13 = new javax.swing.JButton();
        b14 = new javax.swing.JButton();
        b15 = new javax.swing.JButton();
        b16 = new javax.swing.JButton();
        b17 = new javax.swing.JButton();
        b20 = new javax.swing.JButton();
        b21 = new javax.swing.JButton();
        b22 = new javax.swing.JButton();
        b23 = new javax.swing.JButton();
        b24 = new javax.swing.JButton();
        b25 = new javax.swing.JButton();
        b26 = new javax.swing.JButton();
        b27 = new javax.swing.JButton();
        b30 = new javax.swing.JButton();
        b31 = new javax.swing.JButton();
        b32 = new javax.swing.JButton();
        b33 = new javax.swing.JButton();
        b34 = new javax.swing.JButton();
        b35 = new javax.swing.JButton();
        b36 = new javax.swing.JButton();
        b37 = new javax.swing.JButton();
        b40 = new javax.swing.JButton();
        b41 = new javax.swing.JButton();
        b42 = new javax.swing.JButton();
        b43 = new javax.swing.JButton();
        b44 = new javax.swing.JButton();
        b45 = new javax.swing.JButton();
        b46 = new javax.swing.JButton();
        b47 = new javax.swing.JButton();
        b50 = new javax.swing.JButton();
        b51 = new javax.swing.JButton();
        b52 = new javax.swing.JButton();
        b53 = new javax.swing.JButton();
        b54 = new javax.swing.JButton();
        b55 = new javax.swing.JButton();
        b56 = new javax.swing.JButton();
        b57 = new javax.swing.JButton();
        b60 = new javax.swing.JButton();
        b61 = new javax.swing.JButton();
        b62 = new javax.swing.JButton();
        b63 = new javax.swing.JButton();
        b64 = new javax.swing.JButton();
        b65 = new javax.swing.JButton();
        b66 = new javax.swing.JButton();
        b67 = new javax.swing.JButton();
        b70 = new javax.swing.JButton();
        b71 = new javax.swing.JButton();
        b72 = new javax.swing.JButton();
        b73 = new javax.swing.JButton();
        b74 = new javax.swing.JButton();
        b75 = new javax.swing.JButton();
        b76 = new javax.swing.JButton();
        b77 = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        blackLabel.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        blackLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        blackLabel.setText("J2");

        whiteLabel.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        whiteLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        whiteLabel.setText("J1");

        turnLabel.setFont(new java.awt.Font("Unispace", 1, 18)); // NOI18N
        turnLabel.setText("It's White's turn!");

        boardPane.setLayout(new java.awt.GridBagLayout());

        CheckersBoard.setPreferredSize(new java.awt.Dimension(400, 400));
        CheckersBoard.setLayout(new java.awt.GridLayout(8, 8));

        b00.setBackground(new java.awt.Color(255, 255, 255));
        b00.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b00.setMaximumSize(new java.awt.Dimension(50, 50));
        b00.setMinimumSize(new java.awt.Dimension(50, 50));
        b00.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b00);

        b01.setBackground(new java.awt.Color(0, 0, 0));
        b01.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b01.setMaximumSize(new java.awt.Dimension(50, 50));
        b01.setMinimumSize(new java.awt.Dimension(50, 50));
        b01.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b01);

        b02.setBackground(new java.awt.Color(255, 255, 255));
        b02.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b02.setMaximumSize(new java.awt.Dimension(50, 50));
        b02.setMinimumSize(new java.awt.Dimension(50, 50));
        b02.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b02);

        b03.setBackground(new java.awt.Color(0, 0, 0));
        b03.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b03.setMaximumSize(new java.awt.Dimension(50, 50));
        b03.setMinimumSize(new java.awt.Dimension(50, 50));
        b03.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b03);

        b04.setBackground(new java.awt.Color(255, 255, 255));
        b04.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b04.setMaximumSize(new java.awt.Dimension(50, 50));
        b04.setMinimumSize(new java.awt.Dimension(50, 50));
        b04.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b04);

        b05.setBackground(new java.awt.Color(0, 0, 0));
        b05.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b05.setMaximumSize(new java.awt.Dimension(50, 50));
        b05.setMinimumSize(new java.awt.Dimension(50, 50));
        b05.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b05);

        b06.setBackground(new java.awt.Color(255, 255, 255));
        b06.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b06.setMaximumSize(new java.awt.Dimension(50, 50));
        b06.setMinimumSize(new java.awt.Dimension(50, 50));
        b06.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b06);

        b07.setBackground(new java.awt.Color(0, 0, 0));
        b07.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b07.setMaximumSize(new java.awt.Dimension(50, 50));
        b07.setMinimumSize(new java.awt.Dimension(50, 50));
        b07.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b07);

        b10.setBackground(new java.awt.Color(0, 0, 0));
        b10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b10.setMaximumSize(new java.awt.Dimension(50, 50));
        b10.setMinimumSize(new java.awt.Dimension(50, 50));
        b10.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b10);

        b11.setBackground(new java.awt.Color(255, 255, 255));
        b11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b11.setMaximumSize(new java.awt.Dimension(50, 50));
        b11.setMinimumSize(new java.awt.Dimension(50, 50));
        b11.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b11);

        b12.setBackground(new java.awt.Color(0, 0, 0));
        b12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b12.setMaximumSize(new java.awt.Dimension(50, 50));
        b12.setMinimumSize(new java.awt.Dimension(50, 50));
        b12.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b12);

        b13.setBackground(new java.awt.Color(255, 255, 255));
        b13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b13.setMaximumSize(new java.awt.Dimension(50, 50));
        b13.setMinimumSize(new java.awt.Dimension(50, 50));
        b13.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b13);

        b14.setBackground(new java.awt.Color(0, 0, 0));
        b14.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b14.setMaximumSize(new java.awt.Dimension(50, 50));
        b14.setMinimumSize(new java.awt.Dimension(50, 50));
        b14.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b14);

        b15.setBackground(new java.awt.Color(255, 255, 255));
        b15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b15.setMaximumSize(new java.awt.Dimension(50, 50));
        b15.setMinimumSize(new java.awt.Dimension(50, 50));
        b15.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b15);

        b16.setBackground(new java.awt.Color(0, 0, 0));
        b16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b16.setMaximumSize(new java.awt.Dimension(50, 50));
        b16.setMinimumSize(new java.awt.Dimension(50, 50));
        b16.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b16);

        b17.setBackground(new java.awt.Color(255, 255, 255));
        b17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b17.setMaximumSize(new java.awt.Dimension(50, 50));
        b17.setMinimumSize(new java.awt.Dimension(50, 50));
        b17.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b17);

        b20.setBackground(new java.awt.Color(255, 255, 255));
        b20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b20.setMaximumSize(new java.awt.Dimension(50, 50));
        b20.setMinimumSize(new java.awt.Dimension(50, 50));
        b20.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b20);

        b21.setBackground(new java.awt.Color(0, 0, 0));
        b21.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b21.setMaximumSize(new java.awt.Dimension(50, 50));
        b21.setMinimumSize(new java.awt.Dimension(50, 50));
        b21.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b21);

        b22.setBackground(new java.awt.Color(102, 102, 102));
        b22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b22.setMaximumSize(new java.awt.Dimension(50, 50));
        b22.setMinimumSize(new java.awt.Dimension(50, 50));
        b22.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b22);

        b23.setBackground(new java.awt.Color(0, 0, 0));
        b23.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b23.setMaximumSize(new java.awt.Dimension(50, 50));
        b23.setMinimumSize(new java.awt.Dimension(50, 50));
        b23.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b23);

        b24.setBackground(new java.awt.Color(102, 102, 102));
        b24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b24.setMaximumSize(new java.awt.Dimension(50, 50));
        b24.setMinimumSize(new java.awt.Dimension(50, 50));
        b24.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b24);

        b25.setBackground(new java.awt.Color(0, 0, 0));
        b25.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b25.setMaximumSize(new java.awt.Dimension(50, 50));
        b25.setMinimumSize(new java.awt.Dimension(50, 50));
        b25.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b25);

        b26.setBackground(new java.awt.Color(102, 102, 102));
        b26.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b26.setMaximumSize(new java.awt.Dimension(50, 50));
        b26.setMinimumSize(new java.awt.Dimension(50, 50));
        b26.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b26);

        b27.setBackground(new java.awt.Color(0, 0, 0));
        b27.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b27.setMaximumSize(new java.awt.Dimension(50, 50));
        b27.setMinimumSize(new java.awt.Dimension(50, 50));
        b27.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b27);

        b30.setBackground(new java.awt.Color(0, 0, 0));
        b30.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b30.setMaximumSize(new java.awt.Dimension(50, 50));
        b30.setMinimumSize(new java.awt.Dimension(50, 50));
        b30.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b30);

        b31.setBackground(new java.awt.Color(102, 102, 102));
        b31.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b31.setMaximumSize(new java.awt.Dimension(50, 50));
        b31.setMinimumSize(new java.awt.Dimension(50, 50));
        b31.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b31);

        b32.setBackground(new java.awt.Color(0, 0, 0));
        b32.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b32.setMaximumSize(new java.awt.Dimension(50, 50));
        b32.setMinimumSize(new java.awt.Dimension(50, 50));
        b32.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b32);

        b33.setBackground(new java.awt.Color(102, 102, 102));
        b33.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b33.setMaximumSize(new java.awt.Dimension(50, 50));
        b33.setMinimumSize(new java.awt.Dimension(50, 50));
        b33.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b33);

        b34.setBackground(new java.awt.Color(0, 0, 0));
        b34.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b34.setMaximumSize(new java.awt.Dimension(50, 50));
        b34.setMinimumSize(new java.awt.Dimension(50, 50));
        b34.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b34);

        b35.setBackground(new java.awt.Color(102, 102, 102));
        b35.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b35.setMaximumSize(new java.awt.Dimension(50, 50));
        b35.setMinimumSize(new java.awt.Dimension(50, 50));
        b35.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b35);

        b36.setBackground(new java.awt.Color(0, 0, 0));
        b36.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b36.setMaximumSize(new java.awt.Dimension(50, 50));
        b36.setMinimumSize(new java.awt.Dimension(50, 50));
        b36.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b36);

        b37.setBackground(new java.awt.Color(102, 102, 102));
        b37.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b37.setMaximumSize(new java.awt.Dimension(50, 50));
        b37.setMinimumSize(new java.awt.Dimension(50, 50));
        b37.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b37);

        b40.setBackground(new java.awt.Color(102, 102, 102));
        b40.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b40.setMaximumSize(new java.awt.Dimension(50, 50));
        b40.setMinimumSize(new java.awt.Dimension(50, 50));
        b40.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b40);

        b41.setBackground(new java.awt.Color(0, 0, 0));
        b41.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b41.setMaximumSize(new java.awt.Dimension(50, 50));
        b41.setMinimumSize(new java.awt.Dimension(50, 50));
        b41.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b41);

        b42.setBackground(new java.awt.Color(102, 102, 102));
        b42.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b42.setMaximumSize(new java.awt.Dimension(50, 50));
        b42.setMinimumSize(new java.awt.Dimension(50, 50));
        b42.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b42);

        b43.setBackground(new java.awt.Color(0, 0, 0));
        b43.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b43.setMaximumSize(new java.awt.Dimension(50, 50));
        b43.setMinimumSize(new java.awt.Dimension(50, 50));
        b43.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b43);

        b44.setBackground(new java.awt.Color(102, 102, 102));
        b44.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b44.setMaximumSize(new java.awt.Dimension(50, 50));
        b44.setMinimumSize(new java.awt.Dimension(50, 50));
        b44.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b44);

        b45.setBackground(new java.awt.Color(0, 0, 0));
        b45.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b45.setMaximumSize(new java.awt.Dimension(50, 50));
        b45.setMinimumSize(new java.awt.Dimension(50, 50));
        b45.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b45);

        b46.setBackground(new java.awt.Color(102, 102, 102));
        b46.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b46.setMaximumSize(new java.awt.Dimension(50, 50));
        b46.setMinimumSize(new java.awt.Dimension(50, 50));
        b46.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b46);

        b47.setBackground(new java.awt.Color(0, 0, 0));
        b47.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b47.setMaximumSize(new java.awt.Dimension(50, 50));
        b47.setMinimumSize(new java.awt.Dimension(50, 50));
        b47.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b47);

        b50.setBackground(new java.awt.Color(0, 0, 0));
        b50.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b50.setMaximumSize(new java.awt.Dimension(50, 50));
        b50.setMinimumSize(new java.awt.Dimension(50, 50));
        b50.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b50);

        b51.setBackground(new java.awt.Color(102, 102, 102));
        b51.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b51.setMaximumSize(new java.awt.Dimension(50, 50));
        b51.setMinimumSize(new java.awt.Dimension(50, 50));
        b51.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b51);

        b52.setBackground(new java.awt.Color(0, 0, 0));
        b52.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b52.setMaximumSize(new java.awt.Dimension(50, 50));
        b52.setMinimumSize(new java.awt.Dimension(50, 50));
        b52.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b52);

        b53.setBackground(new java.awt.Color(102, 102, 102));
        b53.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b53.setMaximumSize(new java.awt.Dimension(50, 50));
        b53.setMinimumSize(new java.awt.Dimension(50, 50));
        b53.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b53);

        b54.setBackground(new java.awt.Color(0, 0, 0));
        b54.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b54.setMaximumSize(new java.awt.Dimension(50, 50));
        b54.setMinimumSize(new java.awt.Dimension(50, 50));
        b54.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b54);

        b55.setBackground(new java.awt.Color(102, 102, 102));
        b55.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b55.setMaximumSize(new java.awt.Dimension(50, 50));
        b55.setMinimumSize(new java.awt.Dimension(50, 50));
        b55.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b55);

        b56.setBackground(new java.awt.Color(0, 0, 0));
        b56.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b56.setMaximumSize(new java.awt.Dimension(50, 50));
        b56.setMinimumSize(new java.awt.Dimension(50, 50));
        b56.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b56);

        b57.setBackground(new java.awt.Color(102, 102, 102));
        b57.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b57.setMaximumSize(new java.awt.Dimension(50, 50));
        b57.setMinimumSize(new java.awt.Dimension(50, 50));
        b57.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b57);

        b60.setBackground(new java.awt.Color(102, 102, 102));
        b60.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b60.setMaximumSize(new java.awt.Dimension(50, 50));
        b60.setMinimumSize(new java.awt.Dimension(50, 50));
        b60.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b60);

        b61.setBackground(new java.awt.Color(0, 0, 0));
        b61.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b61.setMaximumSize(new java.awt.Dimension(50, 50));
        b61.setMinimumSize(new java.awt.Dimension(50, 50));
        b61.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b61);

        b62.setBackground(new java.awt.Color(102, 102, 102));
        b62.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b62.setMaximumSize(new java.awt.Dimension(50, 50));
        b62.setMinimumSize(new java.awt.Dimension(50, 50));
        b62.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b62);

        b63.setBackground(new java.awt.Color(0, 0, 0));
        b63.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b63.setMaximumSize(new java.awt.Dimension(50, 50));
        b63.setMinimumSize(new java.awt.Dimension(50, 50));
        b63.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b63);

        b64.setBackground(new java.awt.Color(102, 102, 102));
        b64.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b64.setMaximumSize(new java.awt.Dimension(50, 50));
        b64.setMinimumSize(new java.awt.Dimension(50, 50));
        b64.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b64);

        b65.setBackground(new java.awt.Color(0, 0, 0));
        b65.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b65.setMaximumSize(new java.awt.Dimension(50, 50));
        b65.setMinimumSize(new java.awt.Dimension(50, 50));
        b65.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b65);

        b66.setBackground(new java.awt.Color(102, 102, 102));
        b66.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b66.setMaximumSize(new java.awt.Dimension(50, 50));
        b66.setMinimumSize(new java.awt.Dimension(50, 50));
        b66.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b66);

        b67.setBackground(new java.awt.Color(0, 0, 0));
        b67.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b67.setMaximumSize(new java.awt.Dimension(50, 50));
        b67.setMinimumSize(new java.awt.Dimension(50, 50));
        b67.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b67);

        b70.setBackground(new java.awt.Color(0, 0, 0));
        b70.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b70.setMaximumSize(new java.awt.Dimension(50, 50));
        b70.setMinimumSize(new java.awt.Dimension(50, 50));
        b70.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b70);

        b71.setBackground(new java.awt.Color(102, 102, 102));
        b71.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b71.setMaximumSize(new java.awt.Dimension(50, 50));
        b71.setMinimumSize(new java.awt.Dimension(50, 50));
        b71.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b71);

        b72.setBackground(new java.awt.Color(0, 0, 0));
        b72.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b72.setMaximumSize(new java.awt.Dimension(50, 50));
        b72.setMinimumSize(new java.awt.Dimension(50, 50));
        b72.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b72);

        b73.setBackground(new java.awt.Color(102, 102, 102));
        b73.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b73.setMaximumSize(new java.awt.Dimension(50, 50));
        b73.setMinimumSize(new java.awt.Dimension(50, 50));
        b73.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b73);

        b74.setBackground(new java.awt.Color(0, 0, 0));
        b74.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b74.setMaximumSize(new java.awt.Dimension(50, 50));
        b74.setMinimumSize(new java.awt.Dimension(50, 50));
        b74.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b74);

        b75.setBackground(new java.awt.Color(102, 102, 102));
        b75.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b75.setMaximumSize(new java.awt.Dimension(50, 50));
        b75.setMinimumSize(new java.awt.Dimension(50, 50));
        b75.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b75);

        b76.setBackground(new java.awt.Color(0, 0, 0));
        b76.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b76.setMaximumSize(new java.awt.Dimension(50, 50));
        b76.setMinimumSize(new java.awt.Dimension(50, 50));
        b76.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b76);

        b77.setBackground(new java.awt.Color(102, 102, 102));
        b77.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b77.setMaximumSize(new java.awt.Dimension(50, 50));
        b77.setMinimumSize(new java.awt.Dimension(50, 50));
        b77.setPreferredSize(new java.awt.Dimension(50, 50));
        CheckersBoard.add(b77);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        boardPane.add(CheckersBoard, gridBagConstraints);

        quitButton.setBackground(new java.awt.Color(255, 51, 51));
        quitButton.setFont(new java.awt.Font("Unispace", 3, 18)); // NOI18N
        quitButton.setText("SALIR");
        quitButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quitButton.setFocusable(false);
        quitButton.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        quitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        quitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        blackLabel.setText(blackName);
        whiteLabel.setText(whiteName);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(turnLabel)
                        .addGap(160, 160, 160)))
                .addComponent(quitButton)
                .addGap(16, 16, 16))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(blackLabel))
                    .addComponent(whiteLabel)
                    .addComponent(boardPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(turnLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(blackLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boardPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(whiteLabel)
                .addGap(98, 98, 98))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //gameLogic.interrupt();
        dispose();
    }//GEN-LAST:event_quitButtonActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Tablero().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CheckersBoard;
    private javax.swing.JButton b00;
    private javax.swing.JButton b01;
    private javax.swing.JButton b02;
    private javax.swing.JButton b03;
    private javax.swing.JButton b04;
    private javax.swing.JButton b05;
    private javax.swing.JButton b06;
    private javax.swing.JButton b07;
    private javax.swing.JButton b10;
    private javax.swing.JButton b11;
    private javax.swing.JButton b12;
    private javax.swing.JButton b13;
    private javax.swing.JButton b14;
    private javax.swing.JButton b15;
    private javax.swing.JButton b16;
    private javax.swing.JButton b17;
    private javax.swing.JButton b20;
    private javax.swing.JButton b21;
    private javax.swing.JButton b22;
    private javax.swing.JButton b23;
    private javax.swing.JButton b24;
    private javax.swing.JButton b25;
    private javax.swing.JButton b26;
    private javax.swing.JButton b27;
    private javax.swing.JButton b30;
    private javax.swing.JButton b31;
    private javax.swing.JButton b32;
    private javax.swing.JButton b33;
    private javax.swing.JButton b34;
    private javax.swing.JButton b35;
    private javax.swing.JButton b36;
    private javax.swing.JButton b37;
    private javax.swing.JButton b40;
    private javax.swing.JButton b41;
    private javax.swing.JButton b42;
    private javax.swing.JButton b43;
    private javax.swing.JButton b44;
    private javax.swing.JButton b45;
    private javax.swing.JButton b46;
    private javax.swing.JButton b47;
    private javax.swing.JButton b50;
    private javax.swing.JButton b51;
    private javax.swing.JButton b52;
    private javax.swing.JButton b53;
    private javax.swing.JButton b54;
    private javax.swing.JButton b55;
    private javax.swing.JButton b56;
    private javax.swing.JButton b57;
    private javax.swing.JButton b60;
    private javax.swing.JButton b61;
    private javax.swing.JButton b62;
    private javax.swing.JButton b63;
    private javax.swing.JButton b64;
    private javax.swing.JButton b65;
    private javax.swing.JButton b66;
    private javax.swing.JButton b67;
    private javax.swing.JButton b70;
    private javax.swing.JButton b71;
    private javax.swing.JButton b72;
    private javax.swing.JButton b73;
    private javax.swing.JButton b74;
    private javax.swing.JButton b75;
    private javax.swing.JButton b76;
    private javax.swing.JButton b77;
    private javax.swing.JLabel blackLabel;
    private javax.swing.JPanel boardPane;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JLabel whiteLabel;
    // End of variables declaration//GEN-END:variables
}
