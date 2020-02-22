package edu.oaklandcc.monstermelee.model;

import android.graphics.drawable.Drawable;

public class UserCharacter extends BaseCharacter {

    public static final int INTELLIGENCE_POINTS_LIMIT = 1000;
    private static final int INTELLIGENCE_POINTS_INCREMENT = 100;
    private static final int INTELLIGENCE_BONUS_XP = 10;
    public static final int EXPERIENCE_POINTS_LIMIT = 1000;
    private static final int EXPERIENCE_POINTS_BASE_INCREMENT = 100;
    private static final int XP_PER_LEVEL = 100;
    public static final int HEALTH_POINTS_LIMIT = 1000;
    private static final int HEALTH_POINTS_INCREMENT = 100;
    public static final int ATTACK_POINTS_LIMIT = 1000;
    private static final int ATTACK_POINTS_INCREMENT = 100;
    public static final int CRITICAL_ATTACK_POINTS_LIMIT = 1000;
    private static final int CRITICAL_ATTACK_POINTS_INCREMENT = 100;
    private static final int STARTING_STAT_POINTS = 2;
    private static final int STAT_POINTS_PER_LEVEL = 1;

    private int intelligencePoints;
    private int experiencePoints;
    private int level;
    private int availableStatPoints;

    public UserCharacter(String name, int maxHealthPoints, int currentHealthPoints, int attackPoints,
                         int criticalHitPoints, int charImage, int intelligencePoints){
        super(name, maxHealthPoints, currentHealthPoints, attackPoints, criticalHitPoints,charImage);
        this.intelligencePoints = intelligencePoints;
        this.experiencePoints = 0;
        this.level = 1;
        this.availableStatPoints = STARTING_STAT_POINTS;
    }

    public void awardXP (int experiencePoints) throws Exception{

        if (this.experiencePoints >= EXPERIENCE_POINTS_LIMIT)
            throw new Exception("Experience points has exceeded limit.");
        else {
            this.experiencePoints += EXPERIENCE_POINTS_BASE_INCREMENT +
                    INTELLIGENCE_BONUS_XP * (intelligencePoints / 100);
            if(experiencePoints > EXPERIENCE_POINTS_LIMIT)
                this.experiencePoints = EXPERIENCE_POINTS_LIMIT;
        }
        levelCheck();
    }

    public void resetHealth(){
        this.currentHealthPoints = maxHealthPoints;
    }

    public void upgradeMaxHealth() throws Exception{
        if(this.maxHealthPoints > HEALTH_POINTS_LIMIT){
            throw new Exception("Health points has exceeded limit.");
        }
        else {
            this.maxHealthPoints += HEALTH_POINTS_INCREMENT;
            if (this.maxHealthPoints > HEALTH_POINTS_LIMIT)
                this.maxHealthPoints = HEALTH_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    public void upgradeAttack() throws Exception{
        if(this.attackPoints > ATTACK_POINTS_LIMIT){
            throw new Exception("Attack points has exceeded limit.");
        }
        else {
            this.attackPoints += ATTACK_POINTS_INCREMENT;
            if(this.attackPoints > ATTACK_POINTS_LIMIT)
                this.attackPoints = ATTACK_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    public void upgradeCrit() throws Exception{
        if(this.criticalHitPoints > CRITICAL_ATTACK_POINTS_LIMIT){
            throw new Exception("Critical attack points has exceeded limit.");
        }
        else {
            this.criticalHitPoints += CRITICAL_ATTACK_POINTS_INCREMENT;
            if(this.criticalHitPoints > CRITICAL_ATTACK_POINTS_LIMIT)
                this.criticalHitPoints = CRITICAL_ATTACK_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    public void upgradeIntelligence() throws Exception{
        if(this.intelligencePoints > INTELLIGENCE_POINTS_LIMIT){
            throw new Exception("Intelligence points has exceeded limit.");
        }
        else {
            this.intelligencePoints += INTELLIGENCE_POINTS_INCREMENT;
            if(this.intelligencePoints > INTELLIGENCE_POINTS_LIMIT)
                this.intelligencePoints = INTELLIGENCE_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    private void levelCheck(){
        int startLevel = this.level;
        this.level = experiencePoints / XP_PER_LEVEL;

        availableStatPoints += (this.level - startLevel) * STAT_POINTS_PER_LEVEL;
    }

    public int getIntelligencePoints() {
        return intelligencePoints;
    }

    public int getAvailableStatPoints() {
        return availableStatPoints;
    }
}

