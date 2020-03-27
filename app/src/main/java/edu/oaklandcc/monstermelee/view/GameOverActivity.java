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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        UI.immersiveLandscape(this);

        Intent intent = getIntent();
        currentMatch = intent.getParcelableExtra("Match");

        userImage = findViewById(R.id.imageView_gameOver_UserImage);
        youDiedTextView = findViewById(R.id.textView_gameOver_YouDied);
        gameOverTextView = findViewById(R.id.textView_gameOver_GameOver);
        homeButton = findViewById(R.id.button_gameOver_Home);

        userImage.setBackground(getResources().getDrawable(
                currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        youDiedTextView.startAnimation(viewBounce);
        gameOverTextView.startAnimation(viewAlphaIncrease);
        homeButton.startAnimation(viewAlphaIncrease);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                goToHomeScreen();
            }
        });
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }
}
