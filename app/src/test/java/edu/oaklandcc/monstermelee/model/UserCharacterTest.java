package edu.oaklandcc.monstermelee.model;

import junit.framework.TestCase;
import java.lang.reflect.Field;

public class UserCharacterTest extends TestCase {

    public void testUpgradeAttack_belowLimit() {
        //SETUP
        int startAttack = 100;
        int actualOutput;
        int expectedOutput = startAttack + UserCharacter.ATTACK_POINTS_INCREMENT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                startAttack, 100, 1, 2, 3, 4, 100);

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
                startAttack, 100, 1, 2, 3, 4, 100);

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
                100, 100, 1, 2, 3, 4, 100);

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
                100, 100, 1, 2, 3, 4, 100);

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
                startAttack, startCrit, 1, 2, 3, 4, 100);

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
                400, 100, 1, 2, 3, 4, 100);

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
                100, 100, 1, 2, 3, 4, 100);

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
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testIsDead() {
        //SETUP
        int startMaxHealth = 100;
        boolean actualOutput;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.takeDamage(startMaxHealth);
        actualOutput = testCharacter.isDead();

        //ASSERT
        assertTrue(actualOutput);
    }

    public void testGetCharImage() {
        //SETUP
        int charImage = 123;
        int actualOutput;
        int expectedOutput = charImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, charImage, 2, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getCharImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetAttackPoints() {
        //SETUP
        int startAttack = 100;
        int actualOutput;
        int expectedOutput = startAttack;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                startAttack, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getAttackPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCriticalHitPoints() {
        //SETUP
        int startCrit = 100;
        int actualOutput;
        int expectedOutput = startCrit;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                100, startCrit, 1, 2, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getCriticalHitPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCharHurtImage() {

        //SETUP
        int charHurtImage = 100;
        int actualOutput;
        int expectedOutput = charHurtImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, charHurtImage, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getCharHurtImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCharDeadImage() {

        //SETUP
        int charDeadImage = 100;
        int actualOutput;
        int expectedOutput = charDeadImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, charDeadImage, 100);

        //EXECUTE
        actualOutput = testCharacter.getCharDeadImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetCharAttackImage() {

        //SETUP
        int charAttackImage = 100;
        int actualOutput;
        int expectedOutput = charAttackImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, charAttackImage, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getCharAttackImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testTestSetName() {
        //SETUP
        String testName = "Test Name";
        String actualOutput;
        String expectedOutput = testName;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setName(testName);
        actualOutput = testCharacter.getName();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetMaxHealthPoints() {

        //SETUP
        int testMaxHealth = 100;
        int actualOutput;
        int expectedOutput = testMaxHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", 0,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setMaxHealthPoints(testMaxHealth);
        actualOutput = testCharacter.getMaxHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCurrentHealthPoints() {

        //SETUP
        int startMaxHealth = 100;
        int testHealth = 10;
        int actualOutput;
        int expectedOutput = testHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setCurrentHealthPoints(testHealth);
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetAttackPoints() {

        //SETUP
        int startAttackPoints = 100;
        int testAttackPoints = 10;
        int actualOutput;
        int expectedOutput = testAttackPoints;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                startAttackPoints, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setAttackPoints(testAttackPoints);
        actualOutput = testCharacter.getAttackPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCriticalHitPoints() {

        //SETUP
        int startCritPoints = 100;
        int testCritPoints = 10;
        int actualOutput;
        int expectedOutput = testCritPoints;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, startCritPoints, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setCriticalHitPoints(testCritPoints);
        actualOutput = testCharacter.getCriticalHitPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCharImage() {
        //SETUP
        int charImage = 123;
        int testCharImage = 456;
        int actualOutput;
        int expectedOutput = testCharImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, charImage, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.setCharImage(testCharImage);
        actualOutput = testCharacter.getCharImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCharHurtImage() {

        //SETUP
        int charHurtImage = 123;
        int testCharHurtImage = 456;
        int actualOutput;
        int expectedOutput = testCharHurtImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, charHurtImage, 3, 4, 100);

        //EXECUTE
        testCharacter.setCharHurtImage(testCharHurtImage);
        actualOutput = testCharacter.getCharHurtImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCharDeadImage() {

        //SETUP
        int charDeadImage = 123;
        int testCharDeadImage = 456;
        int actualOutput;
        int expectedOutput = testCharDeadImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, charDeadImage, 100);

        //EXECUTE
        testCharacter.setCharDeadImage(testCharDeadImage);
        actualOutput = testCharacter.getCharDeadImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testSetCharAttackImage() {

        //SETUP
        int charAttackImage = 123;
        int testCharAttackImage = 456;
        int actualOutput;
        int expectedOutput = testCharAttackImage;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, charAttackImage, 4, 100);

        //EXECUTE
        testCharacter.setCharAttackImage(testCharAttackImage);
        actualOutput = testCharacter.getCharAttackImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testAwardXP() throws NoSuchFieldException, IllegalAccessException {

        //SETUP
        int xpReward = 100;
        int startIntelligence = 100;
        int actualOutput;
        int expectedOutput = xpReward + UserCharacter.INTELLIGENCE_BONUS_XP * (startIntelligence / 100);

        UserCharacter testCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, 4, startIntelligence);

        //EXECUTE
        testCharacter.awardXP(xpReward);
        Field privateXPField = UserCharacter.class.getDeclaredField("experiencePoints");
        privateXPField.setAccessible(true);
        actualOutput = (int) privateXPField.get(testCharacter);

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testResetHealth() {
        //SETUP
        int startMaxHealth = 100;
        int actualOutput;
        int expectedOutput = startMaxHealth;

        UserCharacter testCharacter = new UserCharacter("Dummy", startMaxHealth,
                100, 100, 1, 2,
                3, 4, 100);

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
                100, 100, 1, 2,
                3, 4, 100);

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
                100, 100, 1, 2,
                3, 4, 100);

        //EXECUTE
        testCharacter.upgradeMaxHealth();
        actualOutput = testCharacter.getMaxHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);

    }

    public void testUpgradeCrit_atLimit() {
        //SETUP
        int startCrit = UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT;
        int actualOutput;
        int expectedOutput = UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, startCrit, 1, 2,
                3, 4, 100);

        //EXECUTE
        testCharacter.upgradeCrit();
        actualOutput = testCharacter.getCriticalHitPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeCrit_belowLimit() {
        //SETUP
        int startCrit = 100;
        int actualOutput;
        int expectedOutput = startCrit + UserCharacter.CRITICAL_ATTACK_POINTS_INCREMENT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, startCrit, 1, 2,
                3, 4, 100);

        //EXECUTE
        testCharacter.upgradeCrit();
        actualOutput = testCharacter.getCriticalHitPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeIntelligence_belowLimit() {
        //SETUP
        int startIntelligence = 100;
        int actualOutput;
        int expectedOutput = startIntelligence + UserCharacter.INTELLIGENCE_POINTS_INCREMENT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2,
                3, 4, startIntelligence);

        //EXECUTE
        testCharacter.upgradeIntelligence();
        actualOutput = testCharacter.getIntelligencePoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testUpgradeIntelligence_atLimit() {
        //SETUP
        int startIntelligence = UserCharacter.INTELLIGENCE_POINTS_LIMIT;
        int actualOutput;
        int expectedOutput = UserCharacter.INTELLIGENCE_POINTS_LIMIT;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2,
                3, 4, startIntelligence);

        //EXECUTE
        testCharacter.upgradeIntelligence();
        actualOutput = testCharacter.getIntelligencePoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetIntelligencePoints() {
        //SETUP
        int startIntelligence = 100;
        int actualOutput;
        int expectedOutput = startIntelligence;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, 4, startIntelligence);

        //EXECUTE
        actualOutput = testCharacter.getIntelligencePoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetAvailableStatPoints_beforeAward() {
        //SETUP
        int actualOutput;
        int expectedOutput = UserCharacter.STARTING_STAT_POINTS;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        actualOutput = testCharacter.getAvailableStatPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetAvailableStatPoints_afterXPAward() {
        //SETUP
        int xpAward = UserCharacter.XP_PER_LEVEL;
        int actualOutput;
        int expectedOutput = UserCharacter.STARTING_STAT_POINTS + UserCharacter.STAT_POINTS_PER_LEVEL;

        UserCharacter testCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, 4, 100);

        //EXECUTE
        testCharacter.awardXP(xpAward);
        actualOutput = testCharacter.getAvailableStatPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }
}