package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseCharacter implements Parcelable {

    private static double CRIT_CHANCE = 0.20;

    private String name;
    private int maxHealthPoints;
    private int currentHealthPoints;
    private int attackPoints;
    private int criticalHitPoints;
    private boolean dead;
    private int charImage;
    private int charHurtImage;
    private int charDeadImage;
    private int charAttackImage;

    BaseCharacter() {
        this.dead = false;
    }

    BaseCharacter(Parcel in) {
        name = in.readString();
        maxHealthPoints = in.readInt();
        currentHealthPoints = in.readInt();
        attackPoints = in.readInt();
        criticalHitPoints = in.readInt();
        dead = in.readByte() != 0;
        charImage = in.readInt();
        charHurtImage = in.readInt();
        charDeadImage = in.readInt();
        charAttackImage = in.readInt();
    }

    public static final Creator<BaseCharacter> CREATOR = new Creator<BaseCharacter>() {
        @Override
        public BaseCharacter createFromParcel(Parcel in) {
            return new BaseCharacter(in);
        }

        @Override
        public BaseCharacter[] newArray(int size) {
            return new BaseCharacter[size];
        }
    };

    void takeDamage(int damage) {
        this.currentHealthPoints -= damage;

        if (this.currentHealthPoints <= 0) {
            this.currentHealthPoints = 0;
            this.dead = true;
        }
    }

    int calculateHit() {
        int attackPointsTotal;

        if (Math.random() <= CRIT_CHANCE)
            attackPointsTotal = attackPoints * (1 + (criticalHitPoints/1000));
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

    boolean isDead() {
        return dead;
    }

    public int getCharImage() {
        return charImage;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getCriticalHitPoints() {
        return criticalHitPoints;
    }

    public int getCharHurtImage() {
        return charHurtImage;
    }

    public int getCharDeadImage() {
        return charDeadImage;
    }

    public int getCharAttackImage() {
        return charAttackImage;
    }

    void setName(String name) {
        this.name = name;
    }

    void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    void setCriticalHitPoints(int criticalHitPoints) {
        this.criticalHitPoints = criticalHitPoints;
    }

    void setCharImage(int charImage) {
        this.charImage = charImage;
    }

    void setCharHurtImage(int charHurtImage) {
        this.charHurtImage = charHurtImage;
    }

    void setCharDeadImage(int charDeadImage) {
        this.charDeadImage = charDeadImage;
    }

    void setCharAttackImage(int charAttackImage) {
        this.charAttackImage = charAttackImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(maxHealthPoints);
        parcel.writeInt(currentHealthPoints);
        parcel.writeInt(attackPoints);
        parcel.writeInt(criticalHitPoints);
        parcel.writeByte((byte) (dead ? 1 : 0));
        parcel.writeInt(charImage);
        parcel.writeInt(charHurtImage);
        parcel.writeInt(charDeadImage);
        parcel.writeInt(charAttackImage);
    }
}
