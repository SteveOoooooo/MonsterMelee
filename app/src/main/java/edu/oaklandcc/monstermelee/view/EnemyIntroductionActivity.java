package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.utility.UI;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.EnemySequence;
import edu.oaklandcc.monstermelee.model.Match;
import edu.oaklandcc.monstermelee.model.UserCharacter;

public class EnemyIntroductionActivity extends AppCompatActivity {

    EnemySequence enemySequence;
    UserCharacter userCharacter;
    Match match;

    ImageView EnemyImageView;
    TextView EnemyNameTextView;
    Button FightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_introduction);
        UI.hideSystemUI(this);

        EnemyImageView = findViewById(R.id.imageView_enemyIntro_enemyImage);
        EnemyNameTextView = findViewById(R.id.textView_enemyIntro_enemyName);
        FightButton = findViewById(R.id.button__enemyIntro_fight);

        Intent intent = getIntent();
        match = intent.getParcelableExtra("Match");

        EnemyImageView.setBackground(this.getResources().getDrawable(match.getEnemyCharacter().getCharImage(), getTheme()));
        EnemyNameTextView.setText(match.getEnemyCharacter().getName());

        FightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFight();
            }
        });
    }

    private void startFight() {
        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra("Match", match);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_in_below, R.transition.slide_out_above);
    }
}
