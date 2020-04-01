
/**
 * Wumpus
 */
import java.util.Random;
import java.util.Scanner;

public class Wumpus
{
    public final int SIZE = 5; // map size

    // Map characters.
    public final char EMPTY = '.';
    public final char WUMPUS = 'W';
    public final char PIT = 'P';
    public final char PLAYER = '@';

    private Scanner keyboard = new Scanner(System.in);
    private Random rng;
    private char[][] map;
    private int pRow, pCol; // player row and column
    private int wRow, wCol; // wumpus row and column
    private boolean gameOver = false;

    public void buildMap()
    {
        map = new char[SIZE][SIZE];
        for (int r=0; r < SIZE; r++) {
            for (int c=0; c < SIZE; c++) {
                map[r][c] = EMPTY;
            }
        }

        placeWumpus();
        placePits(2);
        placePlayer();
    }

    /* Checks the map at row,col and returns true if `c` is there. Ensures that
     * row and col aren't off the map.
     */
    public boolean isCharAt(int row, int col, char c)
    {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }

        if (map[row][col] == c) {
            return true;
        }
        else {
            return false;
        }  
    }

    /* Calculates a target row,col from the given starting row,col in the
     * specified direction (NESW). The target coords are turned [0]=row, [1]=col.
     */
    public int[] calcTargetCoords(char direction, int row, int col)
    {
        int tRow = row, tCol = col; // target row and column
        if (direction == 'N') {
            tRow--;
        }
        else if (direction == 'E') {
            tCol++;
        }
        else if (direction == 'S') {
            tRow++;
        }
        else if (direction == 'W') {
            tCol--;
        }
        return new int[] { tRow, tCol };
    }

    public void move(char direction)
    {
        int[] target = calcTargetCoords(direction, pRow, pCol);
        int tRow = target[0];
        int tCol = target[1];

        if (tRow < 0 || tRow >= SIZE || tCol < 0 || tCol >= SIZE) {
            System.out.println("You can't do that.");
            return;
        }

        if (map[tRow][tCol] == PIT) {
            System.out.println("You fall into a bottomless pit and die!");
            gameOver = true;
            return;
        }

        if (map[tRow][tCol] == WUMPUS) {
            System.out.println("You stumbled into the wumpus! You are dead.");
            gameOver = true;
            return;
        }

        map[pRow][pCol] = EMPTY; // remove the player
        map[tRow][tCol] = PLAYER; // place the player
        pRow = tRow;
        pCol = tCol; // store player position
    }

    public boolean pitNearby()
    {
        if ( isCharAt(pRow-1, pCol, PIT) ||
        isCharAt(pRow, pCol+1, PIT) ||
        isCharAt(pRow+1, pCol, PIT) ||
        isCharAt(pRow, pCol-1, PIT) )
        {
            return true;
        }
        else {
            return false;
        }
    }

    /* Places `ch` in an empty spot on the map and returns where it is placed. */
    public int[] placeChar(char ch)
    {
        boolean isPlaced = false;
        int r=-1, c=-1;
        
        while (! isPlaced ) {
            r = rng.nextInt(SIZE);
            c = rng.nextInt(SIZE);
            if (map[r][c] == EMPTY) {
                map[r][c] = ch;
                isPlaced = true;
            }
        }
        return new int[] { r, c };
    }

    public void placePits(int num)
    {
        for (int i=0; i < num; i++) {
            placeChar(PIT);
        }
    }

    public void placePlayer()
    {
        int[] loc = placeChar(PLAYER);
        pRow = loc[0];
        pCol = loc[1];
    }

    public void placeWumpus()
    {
        int[] loc = placeChar(WUMPUS);
        wRow = loc[0];
        wCol = loc[1];
    }

    public void play()
    {
        while (! gameOver) {
            printInfo();

            System.out.print("Move or shoot? ");
            char action = keyboard.next().toUpperCase().charAt(0);

            System.out.print("Which direction? ");
            char direction = keyboard.next().toUpperCase().charAt(0);

            if (action == 'M') {
                move(direction);
            }
            else if (action == 'S') {
                shoot(direction);
            }
            else if (action == 'P') { // cheat
                printMap();
            }
            else if (action == 'Q') {
                return;
            }
        }
    }

    public void printInfo()
    {
        int roomNum = pRow * SIZE + pCol + 1;
        System.out.println("You are in room " + roomNum);

        if (pitNearby()) {
            System.out.println("You feel a draft.");
        }

        if (wumpusNearby()) {
            System.out.println("You smell a wumpus!");
        }
    }

    public void printMap()
    {
        for (int r=0; r < SIZE; r++) {
            for (int c=0; c < SIZE; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }

    public void setup()
    {
        System.out.println("HUNT THE WUMPUS!");
        System.out.println();
        System.out.print("Random number seed: ");
        int seed = keyboard.nextInt();
        rng = new Random(seed);
        buildMap();
    }

    public void shoot(char direction) // fixme
    {
        int[] target = calcTargetCoords(direction, pRow, pCol);
        int tRow = target[0];
        int tCol = target[1];

        if (tRow < 0 || tRow >= SIZE || tCol < 0 || tCol >= SIZE) {
            System.out.println("You can't do that.");
            return;
        }

        if (map[tRow][tCol] == WUMPUS) {
            System.out.println("You killed the wumpus! Congratulations!");
            gameOver = true;
            return;
        }

        // Wumpus gets scared and moves.
        map[wRow][wCol] = EMPTY; // remove the wumpus
        placeWumpus();
    }

    public boolean wumpusNearby()
    {
        if ( isCharAt(pRow-1, pCol, WUMPUS) ||
        isCharAt(pRow, pCol+1, WUMPUS) ||
        isCharAt(pRow+1, pCol, WUMPUS) ||
        isCharAt(pRow, pCol-1, WUMPUS) )
        {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] a)
    {
        Wumpus game = new Wumpus();
        game.setup();
        game.play();
    }
}
