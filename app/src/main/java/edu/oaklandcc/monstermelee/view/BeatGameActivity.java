package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.utility.UI;

public class BeatGameActivity extends AppCompatActivity {

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

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        youAreAwesomeTextView.startAnimation(viewAlphaIncrease);
        homeButton.startAnimation(viewAlphaIncrease);
        beatAllMonstersTextView.startAnimation(viewBounce);

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
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }
}
