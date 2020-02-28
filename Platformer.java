import java.util.Random;

public class Platformer
{
    public static void main(String[] a)
    {
        Platformer p = new Platformer();
        p.createLevel();
        p.printLevel();
    }

    private final int LEVEL_LENGTH = 20;
    private final int NUM_HEIGHTS = 10;
    private final int MAX_SURVIVABLE_HEIGHT = 3;
    private final int MAX_JUMP_HEIGHT = 2;

    private int level[];

    public Platformer()
    {
        level = new int[LEVEL_LENGTH];
    }

    public void createLevel()
    {
        // Always start the level at the middle height.
        level[0] = NUM_HEIGHTS / 2;

        // Generate a level of random heights with heights between 0 and 9.
        Random gen = new Random();
        for (int i = 1; i < LEVEL_LENGTH; i++)
        {
            level[i] = gen.nextInt(NUM_HEIGHTS);
        }

        // "Smooth" out the level to make it survivable.
        for (int i = 1; i < LEVEL_LENGTH; i++)
        {
            // If the previous height is too tall (we would die if we fell off), 
            // increase the current height so it isn't deadly.
            if ( level[i-1] - level[i] > MAX_SURVIVABLE_HEIGHT )
            {
                level[i] = level[i-1] - MAX_SURVIVABLE_HEIGHT;
            }

            // If the previous is too low (cannot be jumped), decrease the current
            // height so we can make the jump.
            if ( level[i-1] - level[i] > -MAX_JUMP_HEIGHT ) {
                level[i] = level[i-1] - MAX_JUMP_HEIGHT;
            }
        }        
    }

    public void printLevel()
    {
        for (int i = 0; i < LEVEL_LENGTH; i++)
        {
            System.out.print(level[i] + " ");
        }
        System.out.println();
    }
}