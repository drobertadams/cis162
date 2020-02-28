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
            // Keep generating heights until one is acceptable.
            boolean isAcceptable = false;
            int height = 0;
            while ( ! isAcceptable ) {
                height = gen.nextInt(NUM_HEIGHTS);

                // If the candidate height isn't too low, then it's acceptable.
                if (level[i-1] > height) {
                    if (level[i-1] - height <= MAX_SURVIVABLE_HEIGHT) {
                        isAcceptable = true;
                    }
                }
                else if (level[i-1] < height) {
                    if (height - level[i-1] <= MAX_JUMP_HEIGHT) {
                        isAcceptable = true;
                    }
                }
                else {
                    isAcceptable = true;
                }
            }
            level[i] = height;
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