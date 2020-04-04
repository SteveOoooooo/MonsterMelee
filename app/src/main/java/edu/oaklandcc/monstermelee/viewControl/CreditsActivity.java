package edu.oaklandcc.monstermelee.viewControl;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.utility.UI;

public class CreditsActivity extends AppCompatActivity {

    Button homeButton;
    TextView titleText;
    LinearLayout textGroup;
    Animation viewJiggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UI.immersiveUI(this);
        setContentView(R.layout.activity_credits);

        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);

        homeButton = findViewById(R.id.button_credits_home);
        titleText = findViewById(R.id.textView_credits_title);
        textGroup = findViewById(R.id.linearLayout_credits_textLayout);

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        titleText.startAnimation(viewBounce);
        homeButton.startAnimation(viewAlphaIncrease);
        textGroup.startAnimation(viewAlphaIncrease);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                homeButton.startAnimation(viewJiggle);
                goToHomeScreen();
            }
        });
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
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
