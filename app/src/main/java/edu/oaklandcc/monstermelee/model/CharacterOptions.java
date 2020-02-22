package edu.oaklandcc.monstermelee.model;

import java.util.ArrayList;
import java.util.List;

import edu.oaklandcc.monstermelee.R;

public class CharacterOptions {

    private List<UserCharacter> characterList;

    public CharacterOptions(){
        characterList = new ArrayList<>();

        characterList.add(new UserCharacter("Black", 500,
                500, 100,100,
                R.drawable.blackleft, 10));

        characterList.add(new UserCharacter("Blue", 500,
                500, 100,100,
                R.drawable.blueleft, 10));

        characterList.add(new UserCharacter("Green", 500,
                500, 100,100,
                R.drawable.greenleft, 10));
    }

    public UserCharacter selectCharacter(int index){
        return characterList.get(index);
    }
}
