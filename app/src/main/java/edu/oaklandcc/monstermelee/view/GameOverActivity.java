package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.utility.UI;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;

public class GameOverActivity extends AppCompatActivity {

    long ANIMATION_DURATION = 1500;

    Match currentMatch;
    ImageView userImage;
    TextView youDiedTextView;
    TextView gameOverTextView;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        UI.hideSystemUI(this);

        Intent intent = getIntent();
        currentMatch = intent.getParcelableExtra("Match");

        userImage = findViewById(R.id.imageView_gameOver_UserImage);
        youDiedTextView = findViewById(R.id.textView_gameOver_YouDied);
        gameOverTextView = findViewById(R.id.textView_gameOver_GameOver);
        homeButton = findViewById(R.id.button_gameOver_Home);

        userImage.setBackground(getResources().getDrawable(
                currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));

        gameOverTextView.setAlpha(0);
        homeButton.setAlpha(0);

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(youDiedTextView, View.TRANSLATION_Y,  -1000, 0);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator gameOverAnimation = ObjectAnimator.ofFloat(gameOverTextView, View.ALPHA, 0f, 1f);
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
