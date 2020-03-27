package edu.oaklandcc.monstermelee.model;

import junit.framework.TestCase;
import edu.oaklandcc.monstermelee.R;

public class UserCharacterTest extends TestCase {

    public void testUpgradeAttack_belowLimit() {
        //SETUP
        int startAttack = 100;
        int actualOutput;
        int expectedOutput = startAttack + UserCharacter.ATTACK_POINTS_INCREMENT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                startAttack, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.upgradeAttack();
        actualOutput = testCharacter.getAttackPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeAttack_atLimit() {
        //SETUP
        int startAttack = UserCharacter.ATTACK_POINTS_LIMIT;
        int actualOutput;
        int expectedOutput = UserCharacter.ATTACK_POINTS_LIMIT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                startAttack, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.upgradeAttack();
        actualOutput = testCharacter.getAttackPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testTakeDamage_aboveZero() {
        //SETUP
        int startHealth = 100;
        int damageTaken = 50;
        int actualOutput;
        int expectedOutput = startHealth - damageTaken;

        UserCharacter testCharacter = new UserCharacter("Dummy", startHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.takeDamage(damageTaken);
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testTakeDamage_belowZero() {
        //SETUP
        int startHealth = 100;
        int damageTaken = 200;
        int actualOutput;
        int expectedOutput = 0;

        UserCharacter testCharacter = new UserCharacter("Dummy", startHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.takeDamage(damageTaken);
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testCalculateHit() {
        //SETUP
        int startAttack = 100;
        int startCrit = 100;
        int actualOutput;
        int expectedOutputNoCrit = startAttack;
        int expectedOutputWithCrit = startAttack + startCrit;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                startAttack, startCrit, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        actualOutput = testCharacter.calculateHit();

        //ASSERT
        assertTrue(actualOutput == expectedOutputNoCrit ||
                actualOutput == expectedOutputWithCrit);
    }

    public void testTestGetName() {
        //SETUP
        String assignedName = "Name";
        String actualOutput;
        String expectedOutput = assignedName;

        UserCharacter testCharacter = new UserCharacter(assignedName, 500,
                400, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        actualOutput = testCharacter.getName();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetMaxHealthPoints() {
        //SETUP
        int startMaxHealth = 100;
        int actualOutput;
        int expectedOutput = startMaxHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        actualOutput = testCharacter.getMaxHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCurrentHealthPoints() {
        //SETUP
        int startMaxHealth = 100;
        int actualOutput;
        int expectedOutput = startMaxHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testIsDead() {
        //SETUP
        int startMaxHealth = 100;
        boolean actualOutput;
        boolean expectedOutput = true;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.takeDamage(startMaxHealth);
        actualOutput = testCharacter.isDead();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCharImage() {
        //SETUP
        int charImage = 123;
        int actualOutput;
        int expectedOutput = charImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, charImage, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        actualOutput = testCharacter.getCharImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetAttackPoints() {
    }

    public void testGetCriticalHitPoints() {
    }

    public void testGetCharHurtImage() {
    }

    public void testGetCharDeadImage() {
    }

    public void testGetCharAttackImage() {
    }

    public void testTestSetName() {
    }

    public void testSetMaxHealthPoints() {
    }

    public void testSetCurrentHealthPoints() {
    }

    public void testSetAttackPoints() {
    }

    public void testSetCriticalHitPoints() {
    }

    public void testSetCharImage() {
    }

    public void testSetCharHurtImage() {
    }

    public void testSetCharDeadImage() {
    }

    public void testSetCharAttackImage() {
    }

    public void testAwardXP() {
    }

    public void testResetHealth() {
        //SETUP
        int startMaxHealth = 100;
        int actualOutput;
        int expectedOutput = startMaxHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.takeDamage(50);
        testCharacter.resetHealth();
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeMaxHealth_belowLimit() {
        //SETUP
        int startMaxHealth = 100;
        int actualOutput;
        int expectedOutput = startMaxHealth + UserCharacter.HEALTH_POINTS_INCREMENT;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.upgradeMaxHealth();
        actualOutput = testCharacter.getMaxHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeMaxHealth_atLimit() {
        //SETUP
        int startMaxHealth = UserCharacter.HEALTH_POINTS_LIMIT;
        int actualOutput;
        int expectedOutput = UserCharacter.HEALTH_POINTS_LIMIT;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, R.drawable.blackleft, R.drawable.blackhurtleft,
                R.drawable.blackattackleft, R.drawable.blackdeadleft, 100);

        //EXECUTE
        testCharacter.upgradeMaxHealth();
        actualOutput = testCharacter.getMaxHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);

    }

    public void testUpgradeCrit() {
    }

    public void testUpgradeIntelligence() {
    }

    public void testGetIntelligencePoints() {
    }

    public void testGetAvailableStatPoints() {
    }
}