package edu.oaklandcc.monstermelee.viewControl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.utility.UI;

public class StartActivity extends AppCompatActivity {

    ImageButton startButton;
    Button creditButton;
    ImageView logoImage;
    Animation viewJiggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI.immersiveUI(this);
        setContentView(R.layout.activity_start);


        MediaPlayer  player = MediaPlayer.create(this,  R.raw.meleetheme);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();


        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);

        startButton = findViewById(R.id.button_start_start);
        logoImage = findViewById(R.id.imageView_start_logo);
        creditButton = findViewById(R.id.button_start_credits);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                startButton.startAnimation(viewJiggle);
                startGame();
            }
        });

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                creditButton.startAnimation(viewJiggle);
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
        this.finish();
    }

    private void showCredits() {

        Intent creditsIntent = new Intent(this, CreditsActivity.class);
        startActivity(creditsIntent);
        overridePendingTransition(R.anim.slide_in_above, R.anim.slide_out_below);
        this.finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.immersiveUI(this);
        }
    }
}
