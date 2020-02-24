package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class UserCharacter extends BaseCharacter implements Parcelable{

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

    public UserCharacter(String name, int maxHealthPoints, int attackPoints,
                         int criticalHitPoints, int charImage, int intelligencePoints) {
        this.name = name;
        this.maxHealthPoints = maxHealthPoints;
        this.attackPoints = attackPoints;
        this.criticalHitPoints = criticalHitPoints;
        this.charImage = charImage;
        this.intelligencePoints = intelligencePoints;
        this.experiencePoints = 0;
        this.level = 1;
        this.availableStatPoints = STARTING_STAT_POINTS;
        this.currentHealthPoints = maxHealthPoints;
    }


    protected UserCharacter(Parcel in) {
        super(in);
        intelligencePoints = in.readInt();
        experiencePoints = in.readInt();
        level = in.readInt();
        availableStatPoints = in.readInt();
        currentHealthPoints = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(intelligencePoints);
        dest.writeInt(experiencePoints);
        dest.writeInt(level);
        dest.writeInt(availableStatPoints);
        dest.writeInt(currentHealthPoints);
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

    public void awardXP(int experiencePoints) throws Exception {

        if (this.experiencePoints >= EXPERIENCE_POINTS_LIMIT)
            throw new Exception("Experience points has exceeded limit.");
        else {
            this.experiencePoints += EXPERIENCE_POINTS_BASE_INCREMENT +
                    INTELLIGENCE_BONUS_XP * (intelligencePoints / 100);
            if (experiencePoints > EXPERIENCE_POINTS_LIMIT)
                this.experiencePoints = EXPERIENCE_POINTS_LIMIT;
        }
        levelCheck();
    }

    public void resetHealth() {
        this.currentHealthPoints = maxHealthPoints;
    }

    public void upgradeMaxHealth() {
        if (this.maxHealthPoints > HEALTH_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded health point limit.: ");
        } else {
            this.maxHealthPoints += HEALTH_POINTS_INCREMENT;
            if (this.maxHealthPoints > HEALTH_POINTS_LIMIT)
                this.maxHealthPoints = HEALTH_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    public void upgradeAttack() {
        if (this.attackPoints > ATTACK_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded attack point limit.: ");
        } else {
            this.attackPoints += ATTACK_POINTS_INCREMENT;
            if (this.attackPoints > ATTACK_POINTS_LIMIT)
                this.attackPoints = ATTACK_POINTS_LIMIT;
        }
        availableStatPoints--;
    }

    public void upgradeCrit() {
        if (this.criticalHitPoints > CRITICAL_ATTACK_POINTS_LIMIT) {
            Log.e("UpgradeError", "Exceeded critical hit point limit.: ");
        } else {
            this.criticalHitPoints += CRITICAL_ATTACK_POINTS_INCREMENT;
            if (this.criticalHitPoints > CRITICAL_ATTACK_POINTS_LIMIT)
                this.criticalHitPoints = CRITICAL_ATTACK_POINTS_LIMIT;
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


