package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.UserCharacter;

public class SelectCharacterActivity extends AppCompatActivity {

    private List<UserCharacter> characterList;

    TextView textView0;
    ImageButton button0;
    ProgressBar healthBar0;
    ProgressBar attackBar0;
    ProgressBar critBar0;
    ProgressBar intelligenceBar0;

    TextView textView1;
    ImageButton button1;
    ProgressBar healthBar1;
    ProgressBar attackBar1;
    ProgressBar critBar1;
    ProgressBar intelligenceBar1;

    TextView textView2;
    ImageButton button2;
    ProgressBar healthBar2;
    ProgressBar attackBar2;
    ProgressBar critBar2;
    ProgressBar intelligenceBar2;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_select_character);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        characterList = new ArrayList<>();

        characterList.add(new UserCharacter("Black", 500,
                500, 100,
                R.drawable.blackleft, 100));
        characterList.add(new UserCharacter("Blue", 300,
                300, 200,
                R.drawable.blueleft, 200));
        characterList.add(new UserCharacter("Green", 200,
                200, 500,
                R.drawable.greenleft, 300));

        textView0 = findViewById(R.id.textView_Char0);
        textView0.setText(characterList.get(0).getName());
        button0 = findViewById(R.id.button_Character0);
        button0.setBackground(getResources().getDrawable(characterList.get(0).getCharImage(), getTheme()));
        healthBar0 = findViewById(R.id.progressBar_Health0);
        healthBar0.setProgress(100*characterList.get(0).getMaxHealthPoints()/UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar0 = findViewById(R.id.progressBar_Attack0);
        attackBar0.setProgress(100*characterList.get(0).getAttackPoints()/UserCharacter.ATTACK_POINTS_LIMIT);
        critBar0 = findViewById(R.id.progressBar_Crit0);
        critBar0.setProgress(100*characterList.get(0).getCriticalHitPoints()/UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar0 = findViewById(R.id.progressBar_Intelligence0);
        intelligenceBar0.setProgress(100*characterList.get(0).getIntelligencePoints()/UserCharacter.INTELLIGENCE_POINTS_LIMIT);


        textView1 = findViewById(R.id.textView_Char1);
        textView1.setText(characterList.get(1).getName());
        button1 = findViewById(R.id.button_Character1);
        button1.setBackground(getResources().getDrawable(characterList.get(1).getCharImage(), getTheme()));
        healthBar1 = findViewById(R.id.progressBar_Health1);
        healthBar1.setProgress(100*characterList.get(1).getMaxHealthPoints()/UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar1 = findViewById(R.id.progressBar_Attack1);
        attackBar1.setProgress(100*characterList.get(1).getAttackPoints()/UserCharacter.ATTACK_POINTS_LIMIT);
        critBar1 = findViewById(R.id.progressBar_Crit1);
        critBar1.setProgress(100*characterList.get(1).getCriticalHitPoints()/UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar1 = findViewById(R.id.progressBar_Intelligence1);
        intelligenceBar1.setProgress(100*characterList.get(1).getIntelligencePoints()/UserCharacter.INTELLIGENCE_POINTS_LIMIT);

        textView2 = findViewById(R.id.textView_Char2);
        textView2.setText(characterList.get(2).getName());
        button2 = findViewById(R.id.button_Character2);
        button2.setBackground(getResources().getDrawable(characterList.get(2).getCharImage(), getTheme()));
        healthBar2 = findViewById(R.id.progressBar_Health2);
        healthBar2.setProgress(100*characterList.get(2).getMaxHealthPoints()/UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar2 = findViewById(R.id.progressBar_Attack2);
        attackBar2.setProgress(100*characterList.get(2).getAttackPoints()/UserCharacter.ATTACK_POINTS_LIMIT);
        critBar2 = findViewById(R.id.progressBar_Crit2);
        critBar2.setProgress(100*characterList.get(2).getCriticalHitPoints()/UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar2 = findViewById(R.id.progressBar_Intelligence2);
        intelligenceBar2.setProgress(100*characterList.get(2).getIntelligencePoints()/UserCharacter.INTELLIGENCE_POINTS_LIMIT);

        backButton = findViewById(R.id.button_backToStart);

        button0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                characterSelected(characterList.get(0));
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                characterSelected(characterList.get(1));
            }
        });

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                characterSelected(characterList.get(2));
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToStart();
            }
        });
    }

    private void goBackToStart() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    private void characterSelected(UserCharacter selectedCharacter){

        Intent selectStatsIntent = new Intent(this, SelectStatsActivity.class);
        selectStatsIntent.putExtra("SelectedCharacter", selectedCharacter);
        startActivity(selectStatsIntent);
    }
}
