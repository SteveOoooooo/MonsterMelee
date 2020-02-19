package edu.oaklandcc.monstermelee.model;

public class EnemySequence {

    private EnemyCharacter[] enemies;
    private int currentEnemyIndex;

    public EnemySequence(EnemyCharacter[] enemies){
        this.enemies = enemies;
        currentEnemyIndex = 0;
    }

    public EnemyCharacter getCurrentEnemy(){
        return enemies[currentEnemyIndex];
    }

    public EnemyCharacter nextEnemy(){
        this.currentEnemyIndex++;
        return enemies[currentEnemyIndex];
    }
}
