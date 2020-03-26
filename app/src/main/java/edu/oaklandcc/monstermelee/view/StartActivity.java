package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.utility.UI;

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
        UI.hideSystemUI(this);

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

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);
        logoImage.startAnimation(viewBounce);
        startButton.startAnimation(viewAlphaIncrease);
        creditButton.startAnimation(viewAlphaIncrease);
    }

    private void startGame() {
        Intent selectCharacterIntent = new Intent(this, SelectCharacterActivity.class);
        startActivity(selectCharacterIntent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    private void showCredits() {
        Intent creditsIntent = new Intent(this, CreditsActivity.class);
        startActivity(creditsIntent);
        overridePendingTransition(R.anim.slide_in_above, R.anim.slide_out_below);
    }
}
