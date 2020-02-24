package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.EnemyCharacter;
import edu.oaklandcc.monstermelee.model.EnemySequence;
import edu.oaklandcc.monstermelee.model.Match;
import edu.oaklandcc.monstermelee.model.UserCharacter;

public class EnemyIntroductionActivity extends AppCompatActivity {

    EnemySequence enemySequence;
    UserCharacter userCharacter;
    Match nextMatch;

    ImageView EnemyImageView;
    TextView EnemyNameTextView;
    Button FightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_introduction);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        EnemyImageView = findViewById(R.id.imageView_EnemyImage);
        EnemyNameTextView = findViewById(R.id.textView_EnemyName);
        FightButton = findViewById(R.id.button_goToBattle);

        Intent intent = getIntent();

        if (intent.getParcelableExtra("EnemySequence") == null){

            List<EnemyCharacter> enemies = new ArrayList<>();

            enemies.add(new EnemyCharacter("Reaper",100, 100, 100, 50, R.drawable.reaperright, 200));
            enemies.add(new EnemyCharacter("Ogre",100, 100, 100, 50, R.drawable.ogreright, 100));

            enemySequence = new EnemySequence(enemies);
        }
        else{
            enemySequence = intent.getParcelableExtra("EnemySequence");
            enemySequence.nextEnemy();
        }

        userCharacter = intent.getParcelableExtra("UserCharacter");

        nextMatch = new Match(userCharacter, enemySequence);

        EnemyImageView.setBackground(this.getResources().getDrawable(enemySequence.getCurrentEnemy().getCharImage(), getTheme()));
        EnemyNameTextView.setText(enemySequence.getCurrentEnemy().getName());

        FightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFight();
            }
        });
    }

    private void startFight() {
        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra("NextMatch", nextMatch);
        startActivity(intent);
    }
}
