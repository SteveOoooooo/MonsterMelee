package edu.oaklandcc.monstermelee.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {
    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
    private EnemySequence enemySequence;
    private UserCharacter userCharacter;
    private int currentHit;

    public Match(UserCharacter user, EnemySequence enemy) {
        this.userCharacter = user;
        this.enemySequence = enemy;
    }

    private Match(Parcel in) {
        enemySequence = in.readParcelable(EnemyCharacter.class.getClassLoader());
        userCharacter = in.readParcelable(UserCharacter.class.getClassLoader());
        currentHit = in.readInt();
    }

    public int userAttack() {
        int hitPoints = userCharacter.calculateHit();
        enemySequence.getCurrentEnemy().takeDamage(hitPoints);
        return hitPoints;
    }

    public int enemyAttack() {
        int hitPoints = enemySequence.getCurrentEnemy().calculateHit();
        userCharacter.takeDamage(hitPoints);
        return hitPoints;
    }

    public boolean enemyIsDead() {
        return enemySequence.getCurrentEnemy().isDead();
    }

    public boolean userIsDead() {
        return userCharacter.isDead();
    }

    public UserCharacter getUserCharacter() {
        return userCharacter;
    }

    public EnemyCharacter getEnemyCharacter() {
        return enemySequence.getCurrentEnemy();
    }

    public EnemyCharacter nextMatch() {
        return enemySequence.nextEnemy();
    }

    public boolean isFirstEnemy(){
        return (enemySequence.getCurrentEnemyIndex() == 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(enemySequence, i);
        parcel.writeParcelable(userCharacter, i);
        parcel.writeInt(currentHit);
    }
}
