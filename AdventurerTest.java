import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AdventurerTest.
 */
public class AdventurerTest
{
    public static final int MAX_HP = 100;
    public static final int XP_FOR_NEXT_LEVEL = 500;
    public static final int MAX_INVENTORY = 10;
    public static final int MIN_UNARMED_DMG = 0;
    public static final int MIN_DAGGER_DMG = 5;
    public static final int MIN_SWORD_DMG = 15;

    private Adventurer adv;
    
    @Before
    public void setUp()
    {
        adv = new Adventurer("Conan");
    }

    @Test 
    public void testConstructor()
    {
        assertEquals("name", adv.getName(), "Conan");
        assertEquals("hp", adv.getHP(), MAX_HP);
        assertEquals("xp", adv.getXP(), 0);
        assertEquals("level", adv.getLevel(), 1);
    }

    @Test 
    public void testPhaseOne()
    {
        assertTrue(adv.attack() >= MIN_UNARMED_DMG);
        assertTrue(adv.attack() >= MIN_UNARMED_DMG);
        assertTrue(adv.attack() >= MIN_UNARMED_DMG);

        /* HP tests */
        int oldHP = adv.getHP();
        adv.takeDamage(-1);
        assertTrue(adv.getHP() == oldHP);

        oldHP = adv.getHP();
        adv.takeDamage(1);
        assertTrue(adv.getHP() == oldHP - 1);

        oldHP = adv.getHP();
        adv.takeDamage(1000);
        assertTrue(adv.getHP() == 0);

        oldHP = adv.getHP();
        adv.drinkHealingPotion(-100);
        assertTrue(adv.getHP() == oldHP);

        oldHP = adv.getHP();
        adv.drinkHealingPotion(1000);
        assertTrue(adv.getHP() >= MAX_HP);

        /* XP tests */
        int oldXP = adv.getXP();
        adv.earnXP(-1);
        assertTrue(adv.getXP() == oldXP);

        assertFalse(adv.levelUp());

        oldXP = adv.getXP();
        assertFalse( adv.earnXP(10) );
        assertTrue(adv.getXP() == oldXP + 10);

        assertFalse(adv.levelUp());

        oldXP = adv.getXP();
        assertTrue( adv.earnXP(XP_FOR_NEXT_LEVEL) );
        assertTrue(adv.getXP() > XP_FOR_NEXT_LEVEL);

        oldXP = adv.getXP();
        int oldLevel = adv.getLevel();
        assertTrue(adv.levelUp());
        assertTrue(adv.getLevel() == oldLevel + 1);
        assertTrue(adv.getXP() == oldXP - XP_FOR_NEXT_LEVEL);
    }

    @Test
    public void testPhaseTwo()
    {
        assertTrue(adv.hasItem("fist") == -1);

        for (int i=0; i< MAX_INVENTORY; i++) {
            assertTrue(adv.pickup(i+""));
        }
        assertFalse(adv.pickup("z")); // no more room

        assertFalse(adv.drop("xyz")); // can't drop, doesn't exist
        assertTrue(adv.drop("0"));  // drop first item
        assertTrue(adv.pickup("A")); // now have space for a new item

        /* unarmed attacks */
        assertTrue(adv.attack() > -1);
        assertTrue(adv.attack() > -1);       
        assertTrue(adv.attack() > -1);

        /* pick up a dagger and attack */
        assertTrue(adv.drop("1"));
        assertTrue(adv.pickup("dagger"));
        assertTrue(adv.attack() >= MIN_DAGGER_DMG);
        assertTrue(adv.attack() >= MIN_DAGGER_DMG);       
        assertTrue(adv.attack() >= MIN_DAGGER_DMG);

        /* pick up a sword and attack */
        assertTrue(adv.drop("2"));
        assertTrue(adv.pickup("sword"));
        assertTrue(adv.attack() >= MIN_SWORD_DMG);
        assertTrue(adv.attack() >= MIN_SWORD_DMG);       
        assertTrue(adv.attack() >= MIN_SWORD_DMG);

    }

}
