package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.utility.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import edu.oaklandcc.monstermelee.R;

public class GiveUpActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;
    Animation viewJiggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_up);
        UI.immersiveLandscape(this);

        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);

        yesButton = findViewById(R.id.button_giveUp_yes);
        noButton = findViewById(R.id.button_giveUp_no);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                yesButton.startAnimation(viewJiggle);
                goToHomeScreen();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                noButton.startAnimation(viewJiggle);
                goBackToFight();
            }
        });
    }

    private void goBackToFight() {
        this.finish();
        overridePendingTransition(R.anim.slide_in_above, R.anim.slide_out_below);
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            UI.immersiveLandscape(this);
        }
    }
}
