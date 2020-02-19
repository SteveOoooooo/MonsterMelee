package edu.oaklandcc.monstermelee.model;

public class Match {
    private EnemyCharacter enemyCharacter;
    private UserCharacter userCharacter;
    private Turn currentTurn;
    private int currentHit;


    public Match(UserCharacter user, EnemyCharacter enemy){
        this.userCharacter = user;
        this.enemyCharacter = enemy;
        this.currentTurn = Turn.USER;
    }

    public Turn getCurrentTurn() {
        return currentTurn;
    }

    public int getCurrentHit() {
        return currentHit;
    }

    public void nextTurn(){
        if (currentTurn == Turn.USER){
            currentTurn = Turn.ENEMY;
            this.currentHit = enemyCharacter.getAttackPoints();
        }
        else{
            currentTurn = Turn.USER;
            this.currentHit = userCharacter.getAttackPoints();
        }
    }
}
