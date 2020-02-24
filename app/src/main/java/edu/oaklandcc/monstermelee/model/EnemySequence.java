package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class EnemySequence implements Parcelable {

    private List<EnemyCharacter> enemies;
    private int currentEnemyIndex;

    public EnemySequence(List<EnemyCharacter> enemies){
        this.enemies = enemies;
        currentEnemyIndex = 0;
    }


    protected EnemySequence(Parcel in) {
        enemies = in.createTypedArrayList(EnemyCharacter.CREATOR);
        currentEnemyIndex = in.readInt();
    }

    public static final Creator<EnemySequence> CREATOR = new Creator<EnemySequence>() {
        @Override
        public EnemySequence createFromParcel(Parcel in) {
            return new EnemySequence(in);
        }

        @Override
        public EnemySequence[] newArray(int size) {
            return new EnemySequence[size];
        }
    };

    public EnemyCharacter getCurrentEnemy(){
        return enemies.get(currentEnemyIndex);
    }

    public EnemyCharacter nextEnemy(){
        this.currentEnemyIndex++;
        return enemies.get(currentEnemyIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(enemies);
        parcel.writeInt(currentEnemyIndex);
    }
}
