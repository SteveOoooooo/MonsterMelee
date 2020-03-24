package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import edu.oaklandcc.monstermelee.R;


public class StartActivity extends AppCompatActivity {

    long ANIMATION_DURATION = 1500;

    ImageButton startButton;
    Button creditButton;
    ImageView logoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_start);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        startButton = findViewById(R.id.button_start_start);
        logoImage = findViewById(R.id.imageView_start_logo);
        creditButton = findViewById(R.id.button_start_credits);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCredits();
            }
        });

        startButton.setAlpha(0f);
        creditButton.setAlpha(0f);

        final ObjectAnimator logoAnimation = ObjectAnimator.ofFloat(logoImage, View.TRANSLATION_Y, -700, 0);
        logoAnimation.setInterpolator(new BounceInterpolator());
        logoAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator startButtonAnimation = ObjectAnimator.ofFloat(startButton, View.ALPHA, 0f, 1f);
        startButtonAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator creditButtonAnimation = ObjectAnimator.ofFloat(creditButton, View.ALPHA, 0f, 1f);
        creditButtonAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(logoAnimation).with(startButtonAnimation);
        animatorSet.play(startButtonAnimation).with(creditButtonAnimation);
        animatorSet.start();
    }

    private void startGame() {
        Intent selectCharacterIntent = new Intent(this, SelectCharacterActivity.class);
        startActivity(selectCharacterIntent);
    }

    private void showCredits() {
        Intent creditsIntent = new Intent(this, CreditsActivity.class);
        startActivity(creditsIntent);
    }
}
