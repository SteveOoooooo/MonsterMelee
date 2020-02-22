package edu.oaklandcc.monstermelee.model;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CharacterOptions {

    private List<UserCharacter> characterList;

    public CharacterOptions(){
        characterList = new ArrayList<>();
    }

    public UserCharacter selectCharacter(int index){
        return characterList.get(index);
    }
    public void addCharacter(UserCharacter userCharacter){
        characterList.add(userCharacter);
    }
}
