
/**
 * Main Battleship game class.
 */
import java.util.Scanner;
import java.util.Random;

public class Battleship
{
    private Board player, computer;

    public void computerPlays()
    {
        System.out.println();

        Random rand = new Random();
        int rowNum = rand.nextInt(10);
        int col = rand.nextInt(10);
        char row = (char) (rowNum + 'A');

        System.out.println("The computer shoots at " + row + "" + col);

        if (player.shootAt(row, col) ) {
            computer.recordHit(row, col);
        }
        else {
            computer.recordMiss(row, col);
        }
    }

    public void playerPlays()
    {
        System.out.println();
        System.out.println(player);

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter coordinates: ");
        char row = keyboard.next().charAt(0);
        int col = keyboard.nextInt();

        if (computer.shootAt(row, col) ) {
            player.recordHit(row, col);
        }
        else {
            player.recordMiss(row, col);
        }
    }

    public boolean gameOver()
    {
        if (player.hasNoShips() || computer.hasNoShips()) {
            if (player.hasNoShips()) {
                System.out.println("Sorry, the computer has won.");
            }
            else {
                System.out.println("You defeated the computer!");
            }

            printFinalBoards();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean noHorizontalSpace(int col, int shipLength)
    {
        if (col > Board.SIZE - shipLength) { 
            return true;
        }
        else {
            return false;
        }
    }

    public boolean runIntoShipHorizontal(int row, int col, int shipLength, char[][] board)
    {
        for (int c=col; c < col+shipLength; c++) {
            if (board[row][c] != '.') {
                return true;
            }
        }
        return false;
    }

    public boolean noVerticalSpace(int row, int shipLength)
    {
        if (row > Board.SIZE - shipLength) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean runIntoShipVertical(int row, int col, int shipLength, char[][] board)
    {
        for (int r=row; r < Board.SIZE && r < row+shipLength; r++) {
            if (board[r][col] != '.') {
                return true;
            }
        }
        return false;
    }

    public void placeShipHorizontal(int row, int col, int shipLength, char shipChar, char[][] board)
    {
        for (int c=col; c < col+shipLength; c++) {
            board[row][c] = shipChar;
        }
    }

    public void placeShipVertical(int row, int col, int shipLength, char shipChar, char[][] board)
    {
        for (int r=row; r < row+shipLength; r++) {
            board[r][col] = shipChar;
        }
    }

    public void placeShip(String shipName, int shipLength, char[][] board, boolean isPlayer)
    {
        Scanner keyboard = new Scanner(System.in);
        Random rand = new Random();

        boolean shipPlaced = false;

        while ( ! shipPlaced ) {
            int col, row;
            char orientation;

            if (isPlayer) {
                System.out.println(player.lowerToString());

                System.out.println("Place your " + shipName + "...");

                System.out.print("[H]orizontal or [V]ertical? ");
                orientation = keyboard.next().charAt(0);

                System.out.print("Coordinates: ");
                char rowChar = keyboard.next().charAt(0);
                col = keyboard.nextInt();
                row = rowChar - 'A';
            }
            else {
                if (rand.nextInt(100) < 50) {
                    orientation = 'H';
                }
                else {
                    orientation = 'V';
                }
                col = rand.nextInt(10);
                row = rand.nextInt(10);
            }

            if (orientation == 'H') {
                if (noHorizontalSpace(col, shipLength) || runIntoShipHorizontal(row, col, shipLength, board)) {
                    if (isPlayer)
                        System.out.println("Sorry, that's illegal.");
                }
                else {
                    // place the ship!
                    placeShipHorizontal(row, col, shipLength, shipName.charAt(0), board);
                    shipPlaced = true;
                }
            }
            else {
                if (noVerticalSpace(row, shipLength) || runIntoShipVertical(row, col, shipLength, board)) {
                    if (isPlayer)
                        System.out.println("Sorry, that's illegal.");
                }
                else {
                    // place the ship!
                    placeShipVertical(row, col, shipLength, shipName.charAt(0), board);
                    shipPlaced = true;
                }
            }
        }
    }

    public void placeComputerShips()
    {
        char[][] board = computer.getLowerBoard();

        placeShip("Aircraft Carrier", 5, board, false);
        placeShip("Battleship", 4, board, false);
        placeShip("Cruiser", 3, board, false);
        placeShip("Submarine", 3, board, false);
        placeShip("PT Boat", 2, board, false);

        System.out.println(computer.lowerToString());
    }

    public void placePlayerShips()
    {
        char[][] board = player.getLowerBoard();

        placeShip("Aircraft Carrier", 5, board, true);
        placeShip("Battleship", 4, board, true);
        placeShip("Cruiser", 3, board, true);
        placeShip("Submarine", 3, board, true);
        placeShip("PT Boat", 2, board, true);
    }

    public void play()
    {
        while ( ! gameOver() ) {
            playerPlays();
            computerPlays();
        }
    }

    public void printFinalBoards()
    {
        System.out.println();
        System.out.println("     ---PLAYER---");
        System.out.println(player.lowerToString());
        System.out.println("    ---COMPUTER---");
        System.out.println(computer.lowerToString());
    }

    public void setup()
    {
        player = new Board();
        placePlayerShips();

        computer = new Board();    
        placeComputerShips();
    }

    public static void main(String[] a)
    {
        Battleship game = new Battleship();
        game.setup();
        game.play();
    }
}
