package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.CharacterOptions;

public class SelectCharacterActivity extends AppCompatActivity {

    TextView textView0;
    TextView textView1;
    TextView textView2;

    ImageButton button0;
    ImageButton button1;
    ImageButton button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_character);

        textView0 = findViewById(R.id.textView_Char0);
        textView1 = findViewById(R.id.textView_Char1);
        textView2 = findViewById(R.id.textView_Char2);

        button0 = findViewById(R.id.button_Character0);
        button1 = findViewById(R.id.button_Character1);
        button2 = findViewById(R.id.button_Character2);
 /*
        textView0.setText(characterOptions.selectCharacter(0).getName());
        textView1.setText(characterOptions.selectCharacter(1).getName());
        textView2.setText(characterOptions.selectCharacter(2).getName());

        button0.setBackground(characterOptions.selectCharacter(0).getCharImage());
        button1.setBackground(characterOptions.selectCharacter(1).getCharImage());
        button2.setBackground(characterOptions.selectCharacter(2).getCharImage());

  */
    }
}
