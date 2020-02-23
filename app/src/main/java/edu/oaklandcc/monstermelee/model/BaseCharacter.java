package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseCharacter implements Parcelable {

    public static double CRIT_CHANCE = 0.05;

    protected String name;
    protected int maxHealthPoints;
    protected int currentHealthPoints;
    protected int attackPoints;
    protected int criticalHitPoints;
    private boolean dead;
    protected int charImage;

    public BaseCharacter() {
        this.dead = false;
    }


    protected BaseCharacter(Parcel in) {
        name = in.readString();
        maxHealthPoints = in.readInt();
        currentHealthPoints = in.readInt();
        attackPoints = in.readInt();
        criticalHitPoints = in.readInt();
        dead = in.readByte() != 0;
        charImage = in.readInt();
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

    public void takeDamage(int damage) {
        this.currentHealthPoints -= damage;

        if (this.currentHealthPoints <= 0) {
            this.currentHealthPoints = 0;
            this.dead = true;
        }
    }

    public int calculateHit() {
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

    public int getCharImage() {
        return charImage;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getCriticalHitPoints() {
        return criticalHitPoints;
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
    }
}
