public class Platformer
{
    public static void main(String[] a)
    {
        Platformer p = new Platformer();
        p.createLevel();
        p.printLevel();
    }

    private final int LEVEL_LENGTH = 20;

    private int level[];

    public Platformer()
    {
        level = new int[LEVEL_LENGTH];
    }

    public void createLevel()
    {
        for (int i = 0; i < LEVEL_LENGTH; i++)
        {
            level[i] = 5;
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