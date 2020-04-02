package edu.oaklandcc.monstermelee.viewControl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.utility.UI;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;

public class EnemyIntroductionActivity extends AppCompatActivity {

    Match match;

    ImageView enemyImageView;
    TextView enemyNameTextView;
    TextView prepareToBattleTextView;
    Button fightButton;
    Animation viewJiggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI.immersiveLandscape(this);
        setContentView(R.layout.activity_enemy_introduction);

        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);

        enemyImageView = findViewById(R.id.imageView_enemyIntro_enemyImage);
        enemyNameTextView = findViewById(R.id.textView_enemyIntro_enemyName);
        fightButton = findViewById(R.id.button__enemyIntro_fight);
        prepareToBattleTextView = findViewById(R.id.textView_enemyIntro_prepareToBattle);

        Intent intent = getIntent();
        match = intent.getParcelableExtra("Match");

        enemyImageView.setBackground(this.getResources().getDrawable(match.getEnemyCharacter().getCharImage(), getTheme()));
        enemyNameTextView.setText(match.getEnemyCharacter().getName());

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        prepareToBattleTextView.startAnimation(viewBounce);
        enemyImageView.startAnimation(viewAlphaIncrease);
        enemyNameTextView.startAnimation(viewAlphaIncrease);
        fightButton.startAnimation(viewAlphaIncrease);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fightButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                fightButton.startAnimation(viewJiggle);
                startFight();
            }
        });
    }

    private void startFight() {
        Intent intent = new Intent(this, FightActivity.class);
        intent.putExtra("Match", match);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.immersiveLandscape(this);
        }
    }
}
