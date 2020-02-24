package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EnemyCharacter extends BaseCharacter implements Parcelable {

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

    protected EnemyCharacter(Parcel in) {
        super(in);
        xpReward = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(xpReward);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EnemyCharacter> CREATOR = new Creator<EnemyCharacter>() {
        @Override
        public EnemyCharacter createFromParcel(Parcel in) {
            return new EnemyCharacter(in);
        }

        @Override
        public EnemyCharacter[] newArray(int size) {
            return new EnemyCharacter[size];
        }
    };

    public int getXpReward(){
        return xpReward;
    }

}
