package edu.oaklandcc.monstermelee.model;

import junit.framework.TestCase;

public class EnemyCharacterTest extends TestCase {

    public void testTakeDamage_aboveZero() {
        //SETUP
        int startHealth = 100;
        int damageTaken = 50;
        int actualOutput;
        int expectedOutput = startHealth - damageTaken;

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startHealth,
                100, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startHealth,
                100, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("Reaper", 100,
                startAttack, startCrit, 1,2, 3,
                4, 200);

        //EXECUTE
        actualOutput = testCharacter.calculateHit();

        //ASSERT
        assertTrue(actualOutput == expectedOutputNoCrit ||
                actualOutput == expectedOutputWithCrit);
    }

    public void testTestGetName() {
        String assignedName = "Name";
        String actualOutput;
        String expectedOutput = assignedName;

        EnemyCharacter testCharacter = new EnemyCharacter(assignedName, 100,
                100, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startMaxHealth,
                100, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startMaxHealth,
                100, 50, 1,2, 3,
                4, 200);

        //EXECUTE
        actualOutput = testCharacter.getCurrentHealthPoints();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testIsDead() {
        //SETUP
        int startMaxHealth = 100;
        boolean actualOutput;

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startMaxHealth,
                100, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 50, charImage,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                startAttack, 50, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, startCrit, 1,2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, charHurtImage, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, 3,
                charDeadImage, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, charAttackImage,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", startMaxHealth,
                100, 100, 1, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                startAttackPoints, 100, 1, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, startCritPoints, 1, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, charImage, 2, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, charHurtImage, 3,
                4, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, 3,
                charDeadImage, 200);

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

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, charAttackImage,
                4, 200);

        //EXECUTE
        testCharacter.setCharAttackImage(testCharAttackImage);
        actualOutput = testCharacter.getCharAttackImage();

        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }

    public void testGetXpReward() {

        //SETUP
        int xpReward = 100;
        int actualOutput;
        int expectedOutput = xpReward;

        EnemyCharacter testCharacter = new EnemyCharacter("dummy", 100,
                100, 100, 1, 2, 3,
                4, xpReward);

        //EXECUTE
        actualOutput = testCharacter.getXpReward();


        //ASSERT
        assertEquals(expectedOutput, actualOutput);
    }
}