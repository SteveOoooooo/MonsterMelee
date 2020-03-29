package edu.oaklandcc.monstermelee.model;

import junit.framework.TestCase;

public class MatchTest extends TestCase {

    public void testUserAttack() {

        //SETUP
        int userAttack = 100;
        int userCrit = 100;
        int startEnemyStartHealth;
        int expectedHealthNoCrit;
        int expectedHealthWithCrit;
        int actualHealth;

        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                userAttack, userCrit, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //Execute
        startEnemyStartHealth = testMatch.getEnemyCharacter().getCurrentHealthPoints();
        testMatch.userAttack();
        actualHealth = testMatch.getEnemyCharacter().getCurrentHealthPoints();
        expectedHealthNoCrit = startEnemyStartHealth - userAttack;
        expectedHealthWithCrit = startEnemyStartHealth - userAttack - userCrit;

        //ASSERT
        assertTrue((actualHealth == expectedHealthNoCrit)||(actualHealth == expectedHealthWithCrit));
    }

    public void testEnemyAttack() {

        //SETUP
        int userHealth = 1000;
        int enemyAttackPoints;
        int enemyCrit;
        int expectedHealthNoCrit;
        int expectedHealthWithCrit;
        int actualHealth;

        UserCharacter userTestCharacter = new UserCharacter("Dummy", userHealth,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //Execute
        enemyAttackPoints = enemyTestSequence.getCurrentEnemy().getAttackPoints();
        enemyCrit = enemyTestSequence.getCurrentEnemy().getCriticalHitPoints();
        testMatch.enemyAttack();

        expectedHealthNoCrit = userHealth - enemyAttackPoints;
        expectedHealthWithCrit = userHealth - enemyAttackPoints - enemyCrit;
        actualHealth = userTestCharacter.getCurrentHealthPoints();

        //ASSERT
        assertTrue((actualHealth == expectedHealthNoCrit)||(actualHealth == expectedHealthWithCrit));
    }

    public void testEnemyIsDead() {

        //SETUP
        int userAttack = UserCharacter.ATTACK_POINTS_LIMIT;
        int userCrit = 1000;
        int sentinal = 1000;
        boolean output;

        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                userAttack, userCrit, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //Execute
        while(testMatch.getEnemyCharacter().getCurrentHealthPoints() > 0  && sentinal > 0){
            testMatch.userAttack();
            sentinal--;
        }

        output = testMatch.enemyIsDead();

        //ASSERT
        assertTrue(output);
    }

    public void testUserIsDead() {
        //SETUP

        int sentinal = 1000;
        boolean output;

        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //Execute
        while(testMatch.getEnemyCharacter().getCurrentHealthPoints() > 0  && sentinal > 0){
            testMatch.enemyAttack();
            sentinal--;
        }

        output = testMatch.userIsDead();

        //ASSERT
        assertTrue(output);
    }

    public void testGetUserCharacter() {

        //SETUP
        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //EXECUTE

        //ASSERT
        assertEquals(userTestCharacter, testMatch.getUserCharacter());
    }

    public void testGetEnemyCharacter() {
        //SETUP
        String expectedName = "Reaper";
        String actualName;
        EnemyCharacter enemyTestCharacter;
        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //EXECUTE
        enemyTestCharacter = testMatch.getEnemyCharacter();
        actualName = enemyTestCharacter.getName();

        //ASSERT
        assertEquals(expectedName, actualName);
    }

    public void testNextMatch() {
        //SETUP
        String expectedName = "Ogre";
        String actualName;
        EnemyCharacter enemyTestCharacter;
        UserCharacter userTestCharacter = new UserCharacter("Dummy", 500,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //EXECUTE
        testMatch.nextMatch();
        enemyTestCharacter = testMatch.getEnemyCharacter();
        actualName = enemyTestCharacter.getName();

        //ASSERT
        assertEquals(expectedName, actualName);
    }

    public void testIsFirstEnemy() {

        //SETUP
        boolean output;
        UserCharacter userTestCharacter = new UserCharacter("Dummy", 100,
                100, 100, 1, 2, 3, 4, 100);
        EnemySequence enemyTestSequence = new EnemySequence();
        Match testMatch = new Match(userTestCharacter, enemyTestSequence);

        //EXECUTE
        output = testMatch.isFirstEnemy();

        //ASSERT
        assertTrue(output);
    }
}