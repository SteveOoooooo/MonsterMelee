package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.utility.UI;

public class BeatGameActivity extends AppCompatActivity {

    long ANIMATION_DURATION = 1500;

    Match currentMatch;

    TextView beatAllMonstersTextView;
    TextView youAreAwesomeTextView;
    Button homeButton;
    ImageView userImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat_game);
        UI.hideSystemUI(this);

        beatAllMonstersTextView = findViewById(R.id.textView_beatGame_beatAllMonsters);
        youAreAwesomeTextView = findViewById(R.id.textView_beatGame_awesome);
        homeButton = findViewById(R.id.button_beatGame_home);
        userImageView = findViewById(R.id.imageView_beatGame_userImage);

        Intent intent = getIntent();
        currentMatch = intent.getParcelableExtra("Match");

        userImageView.setBackground(getResources().getDrawable(
                currentMatch.getUserCharacter().getCharImage(), getTheme()));

        youAreAwesomeTextView.setAlpha(0);
        homeButton.setAlpha(0);

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(beatAllMonstersTextView, View.TRANSLATION_Y,  -1000, 0);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator gameOverAnimation = ObjectAnimator.ofFloat(youAreAwesomeTextView, View.ALPHA, 0f, 1f);
        gameOverAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator homeButtonAnimation = ObjectAnimator.ofFloat(homeButton, View.ALPHA, 0f, 1f);
        homeButtonAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(playerAnimation).before(gameOverAnimation);
        animatorSet.play(gameOverAnimation).with(homeButtonAnimation);
        animatorSet.start();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_in_below, R.transition.slide_out_above);
    }
}
