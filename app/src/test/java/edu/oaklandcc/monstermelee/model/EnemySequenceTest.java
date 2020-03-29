package edu.oaklandcc.monstermelee.model;

import junit.framework.TestCase;

import java.lang.reflect.Field;

import edu.oaklandcc.monstermelee.R;

public class EnemySequenceTest extends TestCase {

    public void testGetCurrentEnemy() {

        //SETUP
        EnemySequence testSequence = new EnemySequence();
        EnemyCharacter enemyTestCharacter = new EnemyCharacter("Reaper", 2000,
                100, 50, R.drawable.reaperright,
                R.drawable.reaperhurtright, R.drawable.reaperattackright,
                R.drawable.reaperdeadright, 200);
        EnemyCharacter outPutEnemyCharacter;

        //EXECUTE
        outPutEnemyCharacter = testSequence.getCurrentEnemy();

        //ASSERT
        assertEquals(enemyTestCharacter.getName(), outPutEnemyCharacter.getName());
        assertEquals(enemyTestCharacter.getMaxHealthPoints(), outPutEnemyCharacter.getMaxHealthPoints());
        assertEquals(enemyTestCharacter.getAttackPoints(), outPutEnemyCharacter.getAttackPoints());
        assertEquals(enemyTestCharacter.getCriticalHitPoints(), outPutEnemyCharacter.getCriticalHitPoints());
        assertEquals(enemyTestCharacter.getCharImage(), outPutEnemyCharacter.getCharImage());
        assertEquals(enemyTestCharacter.getCharAttackImage(), outPutEnemyCharacter.getCharAttackImage());
        assertEquals(enemyTestCharacter.getCharHurtImage(), outPutEnemyCharacter.getCharHurtImage());
        assertEquals(enemyTestCharacter.getCharDeadImage(), outPutEnemyCharacter.getCharDeadImage());
    }

    public void testNextEnemy() {
        //SETUP
        EnemySequence testSequence = new EnemySequence();
        EnemyCharacter enemyTestCharacter = new EnemyCharacter("Ogre", 3000,
                100, 50, R.drawable.ogreright,
                R.drawable.ogrehurtright, R.drawable.ogreattackright,
                R.drawable.ogredeadright, 100);
        EnemyCharacter outPutEnemyCharacter;

        //EXECUTE
        outPutEnemyCharacter = testSequence.nextEnemy();

        //ASSERT
        assertEquals(enemyTestCharacter.getName(), outPutEnemyCharacter.getName());
        assertEquals(enemyTestCharacter.getMaxHealthPoints(), outPutEnemyCharacter.getMaxHealthPoints());
        assertEquals(enemyTestCharacter.getAttackPoints(), outPutEnemyCharacter.getAttackPoints());
        assertEquals(enemyTestCharacter.getCriticalHitPoints(), outPutEnemyCharacter.getCriticalHitPoints());
        assertEquals(enemyTestCharacter.getCharImage(), outPutEnemyCharacter.getCharImage());
        assertEquals(enemyTestCharacter.getCharAttackImage(), outPutEnemyCharacter.getCharAttackImage());
        assertEquals(enemyTestCharacter.getCharHurtImage(), outPutEnemyCharacter.getCharHurtImage());
        assertEquals(enemyTestCharacter.getCharDeadImage(), outPutEnemyCharacter.getCharDeadImage());
    }

    public void testGetCurrentEnemyIndex() {

        //SETUP
        int outputIndex;
        int expectedIndex = 0;
        EnemySequence testSequence = new EnemySequence();


        //EXECUTE
        outputIndex = testSequence.getCurrentEnemyIndex();

        //ASSERT
        assertEquals(expectedIndex, outputIndex);

    }
}