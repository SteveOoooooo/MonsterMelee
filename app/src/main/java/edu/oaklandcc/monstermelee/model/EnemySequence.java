package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import edu.oaklandcc.monstermelee.R;

public class EnemySequence implements Parcelable {

    private List<EnemyCharacter> enemies;
    private int currentEnemyIndex;

    public EnemySequence(){
        this.enemies = enemies;
        currentEnemyIndex = 0;

        enemies = new ArrayList<>();

        enemies.add(new EnemyCharacter("Reaper", 2000,
                100, 50, R.drawable.reaperright,
                R.drawable.reaperhurtright, R.drawable.reaperattackright,
                R.drawable.reaperdeadright, 200));
        enemies.add(new EnemyCharacter("Ogre", 3000,
                100, 50, R.drawable.ogreright,
                R.drawable.ogrehurtright, R.drawable.ogreattackright,
                R.drawable.ogredeadright, 100));
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
        EnemyCharacter nextEnemy = null;
        this.currentEnemyIndex++;
        if (currentEnemyIndex < enemies.size())
            nextEnemy = enemies.get(currentEnemyIndex);
        return nextEnemy;
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
