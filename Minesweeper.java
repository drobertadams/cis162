import java.util.Random;
import java.util.Scanner;

/**
 * Minesweeper (non-recursive)
 */
public class Minesweeper
{
    /* Size of the map, number of mines to place, map characters. */
    public static final int  SIZE = 5;
    public static final int  NUM_MINES = 3;
    public static final char EMPTY = '.';
    public static final char CLEARED = ' ';
    public static final char MINE = '*';
    public static final char FLAG = 'F';

    private char[][] board; // the board
    private char[][] flags; // flags "overlay"
    private Random rng;
    private int numMoves;

    /* End of game helper. */
    private int numCorrectlyFlagged;

    public Minesweeper()
    {
        rng = new Random();
        board = new char[SIZE][SIZE];
        flags = new char[SIZE][SIZE];
        numCorrectlyFlagged = 0;
        numMoves = 0;

        for (int r=0; r < SIZE; r++) {
            for (int c=0; c < SIZE; c++) {
                board[r][c] = EMPTY;
                flags[r][c] = EMPTY;
            }
        }

        for (int m=0; m < NUM_MINES; m++) {
            boolean isPlaced = false;
            while ( ! isPlaced) {
                int r = rng.nextInt(SIZE);
                int c = rng.nextInt(SIZE);
                if (board[r][c] == EMPTY) {
                    board[r][c] = MINE;
                    isPlaced = true;
                }
            }
        }

    }

    /* Prints out the gameboard. */
    public void print(boolean showMines)
    {
        /* Print top row of numbers. */
        System.out.print("  ");
        for (int c=0; c<SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        /* Print the board. */
        for (int r=0; r<SIZE; r++) {
            /* Print row letter. */
            System.out.print((char)('A' + r) + " ");

            for (int c=0; c<SIZE; c++) {
                if (flags[r][c] == FLAG) {
                    System.out.print("F ");
                }
                else if (board[r][c] == MINE) {
                    if (showMines) {
                        System.out.print(MINE + " ");
                    }
                    else {
                        System.out.print(". ");
                    }
                }
                else {     
                    System.out.print(board[r][c] + " ");
                }
            }
            System.out.println();
        }
    }

    public int checkForMine(int r, int c)
    {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
            return 0;
        }  

        if (board[r][c] == MINE) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public void reveal(int r, int c)
    {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
            return;
        }

        if (board[r][c] == MINE || board[r][c] == CLEARED) {
            return;
        }

        int numMinesAround = 
            checkForMine(r-1, c-1) + checkForMine(r-1, c) + checkForMine(r-1, c+1) + 
            checkForMine(r, c-1) + checkForMine(r, c+1) +
            checkForMine(r+1, c-1) + checkForMine(r+1, c) + checkForMine(r+1, c+1);

        board[r][c] = (char) ('0' + numMinesAround);
    }

    /* Returns true if the game is over. */
    public boolean select(int r, int c)
    {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
            return false;
        }

        if (board[r][c] == MINE) {
            System.out.println("\n!!! BOOM !!! You lose.\n");
            return true;
        }
        else if (board[r][c] != CLEARED) {
            board[r][c] = CLEARED; 

            reveal(r-1, c-1);
            reveal(r-1, c);
            reveal(r-1, c+1);
            reveal(r, c-1);
            reveal(r, c+1);
            reveal(r+1, c-1);
            reveal(r+1, c);
            reveal(r+1, c+1);
        }
        return false;
    }

    /* Returns true if by flagging r,c the game is over. */
    public boolean flag(int r, int c)
    {
        if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
            return false;
        }

        if (flags[r][c] != FLAG) {
            /* Add a flag. */
            flags[r][c] = FLAG;

            if (board[r][c] == MINE) {
                numCorrectlyFlagged++;
                if (numCorrectlyFlagged == NUM_MINES) {
                    System.out.println("\n!!! YOU WON !!!");
                    return true;
                }
            }
        }
        else {
            /* Remove a flag. */
            flags[r][c] = EMPTY;
            if (board[r][c] == MINE) {
                numCorrectlyFlagged--;
            }
        }

        return false;
    }

    public void play()
    {
        Scanner s = new Scanner(System.in);
        boolean gameOver = false;

        System.out.println("M I N E S W E E P E R");
        System.out.println();
        System.out.println("There are " + NUM_MINES + " mines on the map.");
        System.out.println();

        while ( ! gameOver ) {
            print(false);

            System.out.print("[S]elect or [F]lag ROW COL: ");
            char command = s.next().charAt(0);
            if (command == 'S') {
                int r = s.next().charAt(0) - 'A';
                int c = s.nextInt();
                gameOver = select(r, c);
                numMoves++;
            }
            else if (command == 'F') {
                int r = s.next().charAt(0) - 'A';
                int c = s.nextInt();
                gameOver = flag(r, c);
                numMoves++;
            }
            else if (command == 'P') { // cheat
                print(true);
            }
            else if (command == 'Q') {
                return;
            }
            else {
                System.out.println("I don't understand " + command + ".");
            }
            System.out.println();
        }
        System.out.println("Total moves: " + numMoves);
        print(true);
    }

    public static void main(String[] a)
    {
        Minesweeper game = new Minesweeper();
        game.play();
    }
}
