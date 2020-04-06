
import java.util.Random;

/**
 * Adventurer Lab Exam
 */
public class Adventurer
{
    private String name;
    private int hp, xp, level;
    private Random rng;
    private String[] inventory;
    private static final int MAX_HP = 100;
    private static final int XP_FOR_NEXT_LEVEL = 500;

    private static final int UNARMED_DMG_RANGE = 11;
    private static final int MIN_UNARMED_DMG = 0;

    private static final int DAGGER_DMG_RANGE = 11;
    private static final int MIN_DAGGER_DMG = 10;

    private static final int SWORD_DMG_RANGE = 11;
    private static final int MIN_SWORD_DMG = 20;

    private static final int MAX_INVENTORY = 10;
    public Adventurer (String name)
    {
        this.name = name;
        hp = MAX_HP;
        level = 1;
        xp = 0;
        rng = new Random();
        inventory = new String[MAX_INVENTORY];
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
        for (int i=0; i<inventory.length;i++) {
            if (inventory[i] != null && inventory[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public boolean pickup(String item)
    {
        for (int i=0; i<inventory.length;i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                return true;
            }
        }
        return false;
    }

    public boolean drop(String item)
    {
        int slot = hasItem(item);
        if (slot == -1)
            return false;
        else {
            inventory[slot] = null;
            return true;
        }
    }
}
