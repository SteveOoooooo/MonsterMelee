package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EnemyCharacter extends BaseCharacter implements Parcelable {

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
    private int xpReward;

    public EnemyCharacter(String name, int maxHealthPoints, int attackPoints,
                          int criticalHitPoints, int charImage, int charHurtImage, int charAttackImage, int charDeadImage, int xpReward) {
        setName(name);
        setMaxHealthPoints(maxHealthPoints);
        setCurrentHealthPoints(maxHealthPoints);
        setAttackPoints(attackPoints);
        setCriticalHitPoints(criticalHitPoints);
        setCharImage(charImage);
        setCharHurtImage(charHurtImage);
        setCharAttackImage(charAttackImage);
        setCharDeadImage(charDeadImage);
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

    public int getXpReward() {
        return xpReward;
    }

}
