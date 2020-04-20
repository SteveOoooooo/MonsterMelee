package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import edu.oaklandcc.monstermelee.R;

public class EnemySequence implements Parcelable {

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
    private List<EnemyCharacter> enemies;
    private int currentEnemyIndex;

    public EnemySequence() {
        currentEnemyIndex = 0;
        enemies = new ArrayList<>();

        enemies.add(new EnemyCharacter("Reaper", 1500,
                100, 100, R.drawable.reaperright,
                R.drawable.reaperhurtright, R.drawable.reaperattackright,
                R.drawable.reaperdeadright, 200));
        enemies.add(new EnemyCharacter("Ogre", 3000,
                110, 150, R.drawable.ogreright,
                R.drawable.ogrehurtright, R.drawable.ogreattackright,
                R.drawable.ogredeadright, 300));
        enemies.add(new EnemyCharacter("Witch", 4000,
                120, 200, R.drawable.witchupright,
                R.drawable.witchhurt, R.drawable.witchattack,
                R.drawable.witchdead2, 400));
        enemies.add(new EnemyCharacter("Fire Imp", 5000,
                130, 250, R.drawable.fireimpidle,
                R.drawable.fireimphurt2, R.drawable.fireimpattack3,
                R.drawable.fireimpdeath4, 500));
        enemies.add(new EnemyCharacter("Prof", 6000,
                140, 300, R.drawable.prof3,
                R.drawable.profhurt, R.drawable.prof2,
                R.drawable.prof1, 600));
    }

    protected EnemySequence(Parcel in) {
        enemies = in.createTypedArrayList(EnemyCharacter.CREATOR);
        currentEnemyIndex = in.readInt();
    }

    EnemyCharacter getCurrentEnemy() {
        return enemies.get(currentEnemyIndex);
    }

    EnemyCharacter nextEnemy() {
        EnemyCharacter nextEnemy = null;
        this.currentEnemyIndex++;
        if (currentEnemyIndex < enemies.size())
            nextEnemy = enemies.get(currentEnemyIndex);
        return nextEnemy;
    }

    int getCurrentEnemyIndex(){
        return currentEnemyIndex;
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
