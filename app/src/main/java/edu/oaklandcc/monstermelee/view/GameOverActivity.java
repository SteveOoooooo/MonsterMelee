package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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

    Match currentMatch;
    ImageView userImage;
    TextView youDiedTextView;
    TextView gameOverTextView;
    Button homeButton;

    long animationDuration = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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
        playerAnimation.setDuration(animationDuration);

        final ObjectAnimator gameOverAnimation = ObjectAnimator.ofFloat(gameOverTextView, View.ALPHA, 0f, 1f);
        gameOverAnimation.setDuration(animationDuration);

        final ObjectAnimator homeButtonAnimation = ObjectAnimator.ofFloat(homeButton, View.ALPHA, 0f, 1f);
        homeButtonAnimation.setDuration(animationDuration);

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
    }
}
