package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.R;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {

    long ANIMATION_DURATION = 1500;

    Button homeButton;
    TextView titleText;
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        homeButton = findViewById(R.id.button_credits_home);
        titleText = findViewById(R.id.textView_credits_title);
        mainText = findViewById(R.id.textView_credits_mainText);

        homeButton.setAlpha(0.0f);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });

        final ObjectAnimator titleAnimation = ObjectAnimator.ofFloat(titleText, View.TRANSLATION_Y, -1000, 0);
        titleAnimation.setInterpolator(new BounceInterpolator());
        titleAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator mainTextAnimation = ObjectAnimator.ofFloat(mainText, View.TRANSLATION_Y, -700, 0);
        mainTextAnimation.setInterpolator(new BounceInterpolator());
        mainTextAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator homeButtonAnimation = ObjectAnimator.ofFloat(homeButton, View.ALPHA, 0f, 1f);
        homeButtonAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(titleAnimation).with(mainTextAnimation);
        animatorSet.play(titleAnimation).with(homeButtonAnimation);
        animatorSet.start();
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
