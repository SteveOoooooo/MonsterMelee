package edu.oaklandcc.monstermelee.view;

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
import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;

public class WonMatchActivity extends AppCompatActivity {

    Match match;
    ImageView userImage;
    ImageView enemyImage;
    TextView youWonTextView;
    Button continueButton;
    Animation viewJiggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_match);
        UI.immersiveLandscape(this);

        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);

        Intent intent = getIntent();
        match = intent.getParcelableExtra("Match");

        userImage = findViewById(R.id.imageView_wonMatch_userImage);
        enemyImage = findViewById(R.id.imageView_wonMatch_enemyImage);
        youWonTextView = findViewById(R.id.textView_wonMatch_YouWon);
        continueButton = findViewById(R.id.button_wonMatch_continue);

        userImage.setBackground(getResources().getDrawable(
                match.getUserCharacter().getCharImage(), getTheme()));
        enemyImage.setBackground(getResources().getDrawable(
                match.getEnemyCharacter().getCharDeadImage(), getTheme()));

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        youWonTextView.startAnimation(viewBounce);
        continueButton.startAnimation(viewAlphaIncrease);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continueButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                continueButton.startAnimation(viewJiggle);
                match.getUserCharacter().awardXP(match.getEnemyCharacter().getXpReward());
                if (match.nextMatch() == null)
                    goToBeatGameScreen();
                else
                    goToSelectStatsScreen();
            }
        });
    }

    private void goToBeatGameScreen() {
        Intent selectStatsIntent = new Intent(this, BeatGameActivity.class);
        selectStatsIntent.putExtra("Match", match);
        startActivity(selectStatsIntent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    private void goToSelectStatsScreen() {
        Intent selectStatsIntent = new Intent(this, SelectStatsActivity.class);
        selectStatsIntent.putExtra("Match", match);
        startActivity(selectStatsIntent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.immersiveLandscape(this);
        }
    }
}