import java.util.Random;

public class Platformer
{
    public static void main(String[] a)
    {
        Platformer p = new Platformer();
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
        createLevel();
    }

    public void createLevel()
    {
        // Always start the level at the middle height.
        level[0] = NUM_HEIGHTS / 2;

        // Generate a level of random heights with heights between 0 and 9.
        Random gen = new Random();
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            level[i] = gen.nextInt(NUM_HEIGHTS);
        }

        // Ensure we don't have two pits in a row (can't jump over them). If we
        // do, move the second one up to height 1.
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i-1] == 0 && level[i] == 0) {
                level[i] = 1;
            }
        }

        // Ensure that we can jump up from one level to the next. If we can't
        // then decrease the height so it is jumpable. Ignore pits.
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i] != 0 && level[i] - level[i-1] > MAX_JUMP_HEIGHT) {
                level[i] = level[i-1] + MAX_JUMP_HEIGHT;
            }
        }

        // Ensure that we can drop safely from one level to the next. If not,
        // the increase the height so it is survivable. Ignore pits.
        for (int i = 1; i < LEVEL_LENGTH; i++) {
            if (level[i] != 0 && level[i-1] - level[i] > MAX_SURVIVABLE_HEIGHT) {
                level[i] = level[i-1] - MAX_SURVIVABLE_HEIGHT;
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