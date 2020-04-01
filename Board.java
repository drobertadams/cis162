
/**
 * Board game with upper and lower halves.
 */
public class Board
{
    public static final int SIZE = 10;

    private char[][] upper, lower;
    private int aircraftCarrier, battleship, cruiser, submarine, ptboat;

    public Board()
    {
        upper = new char[SIZE][SIZE];
        for (int r=0; r < SIZE; r++) {
            for (int c=0; c < SIZE; c++) {
                upper[r][c] = '.';
            }
        }

        lower = new char[SIZE][SIZE];
        for (int r=0; r < SIZE; r++) {
            for (int c=0; c < SIZE; c++) {
                lower[r][c] = '.';
            }
        }
  
        aircraftCarrier = 5;
        battleship = 4;
        cruiser = 3;
        submarine = 3;
        ptboat = 2;
    }
    
    public boolean hasNoShips()
    {
        if (aircraftCarrier == 0 && battleship == 0 && 
             cruiser == 0 && submarine == 0 && ptboat == 0) 
        {
            return true;
        }
        else {
            return false;
        }
    }
    
    public char[][] getLowerBoard()
    {
        return lower;
    }

    public String lowerToString()
    {
        String result = "  0 1 2 3 4 5 6 7 8 9\n";
        for (int r=0; r < SIZE; r++) {
            result += (char) ('A' + r) + " ";
            for (int c=0; c < SIZE; c++) {
                result += lower[r][c] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void recordHit(char row, int col)
    {
        int rowNum = row - 'A'; // -65
        upper[rowNum][col] = '#';
    }

    public void recordMiss(char row, int col)
    {
        int rowNum = row - 'A'; // -65
        upper[rowNum][col] = 'o';
    }

    public boolean shootAt(char row, int col)
    {
        int rowNum = row - 'A'; // -65

        if ( lower[rowNum][col] == '.' || 
             lower[rowNum][col] == 'o' || 
             lower[rowNum][col] == '#') 
        {
            System.out.println(row + "" + col + " is a miss.");
            lower[rowNum][col] = 'o';
            return false;
        }
        else {
            System.out.println(row + "" + col + " is a HIT!");
            
            char shipLetter = lower[rowNum][col];
            if (shipLetter == 'A') {
                aircraftCarrier--;
                if (aircraftCarrier == 0) {
                    System.out.println("You sunk the aircraft carrier!");
                }
            }
            if (shipLetter == 'B') {
                battleship--;
                if (battleship == 0) {
                    System.out.println("You sunk the battleship!");
                }

            }
            if (shipLetter == 'C') {
                cruiser--;
                if (cruiser == 0) {
                    System.out.println("You sunk the cruiser!");
                }

            }
            if (shipLetter == 'S') {
                submarine--;
                if (submarine == 0) {
                    System.out.println("You sunk the submarine!");
                }

            }
            if (shipLetter == 'P') {
                ptboat--;
                if (ptboat == 0) {
                    System.out.println("You sunk the PT boat!");
                }

            }
            
            lower[rowNum][col] = '#';
            return true;
        }
    }

    public String toString()
    {
        String result = "  0 1 2 3 4 5 6 7 8 9\n";
        for (int r=0; r < SIZE; r++) {
            result += (char) ('A' + r) + " ";
            for (int c=0; c < SIZE; c++) {
                result += upper[r][c] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] a)
    {
        Board b = new Board();
        System.out.println(b);
        System.out.println(b.lowerToString());
        assert b.shootAt('E', 3) == true : "Should be a hit";
        assert b.shootAt('E', 2) == false : "Should be a miss";
    }
}
