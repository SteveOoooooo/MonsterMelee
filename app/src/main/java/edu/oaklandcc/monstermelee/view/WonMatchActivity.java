package edu.oaklandcc.monstermelee.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;

public class WonMatchActivity extends AppCompatActivity {

    long ANIMATION_DURATION = 1500;

    Match match;
    ImageView userImage;
    ImageView enemyImage;
    TextView youWonTextView;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won_match);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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

        continueButton.setAlpha(0);

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(youWonTextView, View.TRANSLATION_Y, -1000, 0);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator continueButtonAnimation = ObjectAnimator.ofFloat(continueButton, View.ALPHA, 0f, 1f);
        continueButtonAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(playerAnimation).before(continueButtonAnimation);
        animatorSet.start();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
    }

    private void goToSelectStatsScreen() {

        Intent selectStatsIntent = new Intent(this, SelectStatsActivity.class);
        selectStatsIntent.putExtra("Match", match);
        startActivity(selectStatsIntent);
    }
}