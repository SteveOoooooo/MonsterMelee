package edu.oaklandcc.monstermelee.model;

import android.graphics.drawable.Drawable;

public abstract class BaseCharacter {

    private final double CRIT_CHANCE = 0.05;

    private String name;
    protected int maxHealthPoints;
    protected int currentHealthPoints;
    protected int attackPoints;
    protected int criticalHitPoints;
    private boolean dead;
    protected Drawable charImage;

    public BaseCharacter(String name, int maxHealthPoints, int currentHealthPoints, int attackPoints,
                         int criticalHitPoints, Drawable charImage){
        this.name = name;
        this.maxHealthPoints = maxHealthPoints;
        this.currentHealthPoints = currentHealthPoints;
        this.attackPoints = attackPoints;
        this.criticalHitPoints = criticalHitPoints;
        this.charImage = charImage;
        this.dead = false;
    }

    public void takeDamage(int damage){
        this.currentHealthPoints -= damage;

        if(this.currentHealthPoints <= 0) {
            this.currentHealthPoints = 0;
            this.dead = true;
        }
    }

    public int calculateHit(){
        int attackPointsTotal;

        if (Math.random() <= CRIT_CHANCE)
            attackPointsTotal = attackPoints + criticalHitPoints;
        else
            attackPointsTotal = attackPoints;

        return attackPointsTotal;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public boolean isDead() {
        return dead;
    }

    public Drawable getCharImage() {
        return charImage;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getCriticalHitPoints() {
        return criticalHitPoints;
    }
}