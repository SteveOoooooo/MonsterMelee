package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.CharacterOptions;
import edu.oaklandcc.monstermelee.model.UserCharacter;


public class StartActivity extends AppCompatActivity {

    ImageButton startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.button_Start);

        startButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               startGame();
            }
        });
    }


    private void startGame(){

        CharacterOptions characterOptions = new CharacterOptions();

        characterOptions.addCharacter(new UserCharacter("Black", 500,
                500, 100, 50,
                getResources().getDrawable(R.drawable.blackleft, getTheme()), 100));
        characterOptions.addCharacter(new UserCharacter("Blue", 500,
                500, 100, 50,
                getResources().getDrawable(R.drawable.blueleft, getTheme()), 100));
        characterOptions.addCharacter(new UserCharacter("Green", 500,
                500, 100, 50,
                getResources().getDrawable(R.drawable.greenleft, getTheme()), 100));

        Intent intent = new Intent(this, SelectCharacterActivity.class);
        //intent.putExtra("characterOptions",characterOptions);
        startActivity(intent);
    }
}
