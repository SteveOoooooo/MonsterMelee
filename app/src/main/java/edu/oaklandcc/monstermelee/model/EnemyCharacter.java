package edu.oaklandcc.monstermelee.model;

public class EnemyCharacter extends BaseCharacter {

    private int xpReward;

    public EnemyCharacter(String name, int maxHealthPoints, int currentHealthPoints, int attackPoints,
                          int criticalHitPoints, int charImage, int xpReward) {
        this.name = name;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = maxHealthPoints;
        this.attackPoints = attackPoints;
        this.criticalHitPoints = criticalHitPoints;
        this.charImage = charImage;
        this.xpReward = xpReward;
    }

    public int getXpReward(){
        return xpReward;
    }

}
