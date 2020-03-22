package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class UserCharacter extends BaseCharacter implements Parcelable {

    public static final int INTELLIGENCE_POINTS_LIMIT = 1000;
    private static final int INTELLIGENCE_POINTS_INCREMENT = 100;
    private static final int INTELLIGENCE_BONUS_XP = 10;
    public static final int EXPERIENCE_POINTS_LIMIT = 1000;
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

    public UserCharacter(String name, int maxHealthPoints, int attackPoints,
                         int criticalHitPoints, int charImage, int charHurtImage, int charAttackImage, int charDeadImage, int intelligencePoints) {
        setName(name);
        setMaxHealthPoints(maxHealthPoints);
        setAttackPoints(attackPoints);
        setCriticalHitPoints(criticalHitPoints);
        setCharImage(charImage);
        setCurrentHealthPoints(maxHealthPoints);
        setCharImage(charImage);
        setCharHurtImage(charHurtImage);
        setCharAttackImage(charAttackImage);
        setCharDeadImage(charDeadImage);
        this.intelligencePoints = intelligencePoints;
        this.experiencePoints = 0;
        this.level = 1;
        this.availableStatPoints = STARTING_STAT_POINTS;
    }


    protected UserCharacter(Parcel in) {
        super(in);
        intelligencePoints = in.readInt();
        experiencePoints = in.readInt();
        level = in.readInt();
        availableStatPoints = in.readInt();
        setCurrentHealthPoints(in.readInt());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(intelligencePoints);
        dest.writeInt(experiencePoints);
        dest.writeInt(level);
        dest.writeInt(availableStatPoints);
        dest.writeInt(getCurrentHealthPoints());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserCharacter> CREATOR = new Creator<UserCharacter>() {
        @Override
        public UserCharacter createFromParcel(Parcel in) {
            return new UserCharacter(in);
        }

        @Override
        public UserCharacter[] newArray(int size) {
            return new UserCharacter[size];
        }
    };

    public void awardXP(int experiencePoints) {

        if (this.experiencePoints >= EXPERIENCE_POINTS_LIMIT)
            Log.e( "UpgradeError", "Experience points has exceeded limit.");
        else {
            this.experiencePoints += experiencePoints +
                    INTELLIGENCE_BONUS_XP * (intelligencePoints / 100D);
        }
        levelCheck();
    }

    public void resetHealth() {
        this.setCurrentHealthPoints(getMaxHealthPoints());
    }

    public void upgradeMaxHealth() {
        if (this.getMaxHealthPoints() > HEALTH_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded health point limit.: ");
        } else {
            this.setMaxHealthPoints(getMaxHealthPoints() + HEALTH_POINTS_INCREMENT);
            if (this.getMaxHealthPoints() > HEALTH_POINTS_LIMIT)
                this.setMaxHealthPoints(HEALTH_POINTS_LIMIT);
        }
        availableStatPoints--;
    }

    public void upgradeAttack() {
        if (this.getAttackPoints() > ATTACK_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded attack point limit.: ");
        } else {
            this.setAttackPoints(getAttackPoints() + ATTACK_POINTS_INCREMENT);
            if (this.getAttackPoints() > ATTACK_POINTS_LIMIT)
                this.setAttackPoints(ATTACK_POINTS_LIMIT);
        }
        availableStatPoints--;
    }

    public void upgradeCrit() {
        if (this.getCriticalHitPoints() > CRITICAL_ATTACK_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded critical hit point limit.: ");
        } else {
            this.setCriticalHitPoints(getCriticalHitPoints() + CRITICAL_ATTACK_POINTS_INCREMENT);
            if (this.getCriticalHitPoints() > CRITICAL_ATTACK_POINTS_LIMIT)
                this.setCriticalHitPoints(CRITICAL_ATTACK_POINTS_LIMIT);
        }
        availableStatPoints--;
    }

    public void upgradeIntelligence() {
        if (this.intelligencePoints > INTELLIGENCE_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded intelligence point limit.: ");
        } else {
            this.intelligencePoints += INTELLIGENCE_POINTS_INCREMENT;
            if (this.intelligencePoints > INTELLIGENCE_POINTS_LIMIT)
                this.intelligencePoints = INTELLIGENCE_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    private void levelCheck() {
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


