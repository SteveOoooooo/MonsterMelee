package edu.oaklandcc.monstermelee.model;

import android.graphics.drawable.Drawable;

public class EnemyCharacter extends BaseCharacter {

    private int xpReward;

    public EnemyCharacter(String name, int maxHealthPoints, int currentHealthPoints, int attackPoints,
                          int criticalHitPoints, Drawable charImage, int xpReward) {
        super(name, maxHealthPoints, currentHealthPoints, attackPoints, criticalHitPoints,charImage);
        this.xpReward = xpReward;
    }

    public int getXpReward(){
        return xpReward;
    }

}
