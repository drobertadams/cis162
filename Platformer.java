import java.util.Random;
import java.util.Scanner;

public class Platformer
{
    public static void main(String[] a)
    {
        Scanner s = new Scanner(System.in);
        String command = "";
        Platformer p = new Platformer();
        
        while (command.length() == 0) {
            p.createLevel();
            p.printLevel();
            System.out.print("ENTER for another level, Q to quit.");
            command = s.nextLine();
        }
        
    }
    
    /** Length of the level. */
    private final int LEVEL_LENGTH = 20;
    
    /** Maximum height. */ 
    private final int MAX_HEIGHT = 10;
    
    /** How far can the player fall and still survive. */
    private final int MAX_SURVIVABLE_HEIGHT = 3;
    
    /** How far can the player jump up. */
    private final int MAX_JUMP_HEIGHT = 2;

    /** The level (array of heights). */
    private int level[];

    /** *********************************************************************
     * Creates a new level.
     */
    public Platformer()
    {
        level = new int[LEVEL_LENGTH];
        createLevel();
    }
    
    /** *********************************************************************
     * Ensures that the player can drop safely from one level to the next. 
     * If not then increase the height so it is survivable. 
     * Ignore pits as a starting point.
     */
    private void avoidDeathFalls()
    {
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i] != 0 && level[i-1] - level[i] > MAX_SURVIVABLE_HEIGHT) {
                level[i] = level[i-1] - MAX_SURVIVABLE_HEIGHT;
            }
        }
    }
    
    /** *********************************************************************
     * Ensures that the player can jump up from one level to the next. 
     * If they can't then decrease the height so it is jumpable. 
     * Ignore pits as a starting jump spot.
     */
    private void avoidImpossibleJumps()
    {
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i] != 0 && level[i] - level[i-1] > MAX_JUMP_HEIGHT) {
                level[i] = level[i-1] + MAX_JUMP_HEIGHT;
            }
        }
    }
    
    /** *********************************************************************
     * Ensures we don't have two pits in a row (can't jump over them). If 
     * we do, move the second one up to height 1.
     */
    private void avoidWidePits()
    {
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i-1] == 0 && level[i] == 0) {
                level[i] = 1;
            }
        }
    }
    
    /** *********************************************************************
     * Does the heavy lifing of creating the heights array.
     */
    public void createLevel()
    {
        // Always start the level at the middle height.
        level[0] = MAX_HEIGHT / 2;

        generateRandomHeights();
        avoidWidePits();
        avoidImpossibleJumps();
        avoidDeathFalls();
    }
    
    /** *********************************************************************
     * Initializes level with random heights from 0 to MAX_HEIGHT.
     */
    private void generateRandomHeights()
    {
        Random gen = new Random();
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            level[i] = gen.nextInt(MAX_HEIGHT);
        }
    }

    /** *********************************************************************
     * Prints the level heights.
     */
    public void printLevel()
    {
        for (int i = 0; i < LEVEL_LENGTH; i++)
        {
            System.out.print(level[i] + " ");
        }
        System.out.println();
    }
}