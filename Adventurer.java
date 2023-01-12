
import java.util.Random;
import java.util.ArrayList;

/**
 * Adventurer Lab Exam
 */
public class Adventurer
{
    private String name;
    private int hp, xp, level;
    private Random rng;
    private ArrayList<Item> inventory;
    
    private static final int MAX_HP = 500;
    private static final int XP_FOR_NEXT_LEVEL = 200;

    // 21 not 20 because Random.nextInt(x) is exclusive of the high end
    private static final int UNARMED_DMG_RANGE = 21; 
    private static final int MIN_UNARMED_DMG = 0;

    private static final int DAGGER_DMG_RANGE = 11;
    private static final int MIN_DAGGER_DMG = 20;

    private static final int SWORD_DMG_RANGE = 11;
    private static final int MIN_SWORD_DMG = 30;

    private static final int MAX_INVENTORY = 10;
    public Adventurer (String name)
    {
        this.name = name;
        hp = MAX_HP;
        level = 1;
        xp = 0;
        rng = new Random();
        inventory = new ArrayList<Item>();
    }

    public String getName() { return name; }

    public int getHP() { return hp; }

    public int getLevel() { return level; }

    public int getXP() { return xp; }

    public int attack()
    {
        int dmg;
        if (hasItem("sword") > -1)
            dmg = rng.nextInt(SWORD_DMG_RANGE) + MIN_SWORD_DMG;
        else if (hasItem("dagger") > -1)
            dmg = rng.nextInt(DAGGER_DMG_RANGE) + MIN_DAGGER_DMG;
        else 
            dmg = rng.nextInt(UNARMED_DMG_RANGE) + MIN_UNARMED_DMG;
        return dmg;
    }

    public void takeDamage(int dmg) 
    {
        if (dmg <= 0)
            return;

        hp -= dmg;
        if (hp <= 0) {

            level = 1;
            hp = xp = 0;
        }
    }

    public void drinkHealingPotion(int amt)
    {
        if (amt <= 0)
            return;
        hp += amt;     
    }

    public boolean earnXP(int amt)
    {
        if (amt <= 0)
            return false;

        xp += amt;
        if (xp > XP_FOR_NEXT_LEVEL) {
            return true;
        }
        return false;
    }

    public boolean levelUp()
    {
        if (xp >= XP_FOR_NEXT_LEVEL) {
            level++;
            xp -= XP_FOR_NEXT_LEVEL;
            return true;
        }

        return false;
    }

    public void reportStatus()
    {
        System.out.println("prints stuff");
    }

    public int hasItem(String item)
    {
        for (int i=0; i<inventory.size();i++) {
            if (inventory.get(i).description.equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean pickup(String item, int weight)
    {
        Item i = new Item(item, weight);
        inventory.add(i);
        return true;
    }

    public boolean drop(String item)
    {
        int slot = hasItem(item);
        if (slot == -1)
            return false;
        else {
            inventory.remove(slot);
            return true;
        }
    }
}
