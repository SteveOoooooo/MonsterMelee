package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.utility.UI;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.EnemySequence;
import edu.oaklandcc.monstermelee.model.Match;
import edu.oaklandcc.monstermelee.model.UserCharacter;

public class SelectCharacterActivity extends AppCompatActivity {

    private List<UserCharacter> characterList;

    TextView textView0;
    ImageButton button0;
    ProgressBar healthBar0;
    ProgressBar attackBar0;
    ProgressBar critBar0;
    ProgressBar intelligenceBar0;

    TextView textView1;
    ImageButton button1;
    ProgressBar healthBar1;
    ProgressBar attackBar1;
    ProgressBar critBar1;
    ProgressBar intelligenceBar1;

    TextView textView2;
    ImageButton button2;
    ProgressBar healthBar2;
    ProgressBar attackBar2;
    ProgressBar critBar2;
    ProgressBar intelligenceBar2;

    TextView titleText;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_select_character);
        UI.immersiveLandscape(this);

        characterList = new ArrayList<>();
        characterList.add(new UserCharacter("Lava", 500,
                500, 100,
                R.drawable.blackleft, R.drawable.blackhurtleft, R.drawable.blackattackleft,
                R.drawable.blackdeadleft, 100));
        characterList.add(new UserCharacter("Ice", 300,
                300, 200,
                R.drawable.blueleft, R.drawable.bluehurtleft, R.drawable.blueattackleft,
                R.drawable.bluedeadleft, 200));
        characterList.add(new UserCharacter("Earth", 200,
                200, 500,
                R.drawable.greenleft, R.drawable.greenhurtleft, R.drawable.greenattackleft,
                R.drawable.greendeadleft, 300));

        titleText = findViewById(R.id.textView_selectChar_title);

        textView0 = findViewById(R.id.textView_selectChar_char0);
        textView0.setText(characterList.get(0).getName());
        button0 = findViewById(R.id.button_selectChar_character0);
        button0.setBackground(getResources().getDrawable(characterList.get(0).getCharImage(), getTheme()));
        healthBar0 = findViewById(R.id.progressBar_selectChar_health0);
        healthBar0.setProgress(100 * characterList.get(0).getMaxHealthPoints() / UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar0 = findViewById(R.id.progressBar_selectChar_attack0);
        attackBar0.setProgress(100 * characterList.get(0).getAttackPoints() / UserCharacter.ATTACK_POINTS_LIMIT);
        critBar0 = findViewById(R.id.progressBar_selectChar_crit0);
        critBar0.setProgress(100 * characterList.get(0).getCriticalHitPoints() / UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar0 = findViewById(R.id.progressBar_selectChar_intelligence0);
        intelligenceBar0.setProgress(100 * characterList.get(0).getIntelligencePoints() / UserCharacter.INTELLIGENCE_POINTS_LIMIT);

        textView1 = findViewById(R.id.textView_selectChar_char1);
        textView1.setText(characterList.get(1).getName());
        button1 = findViewById(R.id.button_selectChar_character1);
        button1.setBackground(getResources().getDrawable(characterList.get(1).getCharImage(), getTheme()));
        healthBar1 = findViewById(R.id.progressBar_selectChar_health1);
        healthBar1.setProgress(100 * characterList.get(1).getMaxHealthPoints() / UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar1 = findViewById(R.id.progressBar_selectChar_attack1);
        attackBar1.setProgress(100 * characterList.get(1).getAttackPoints() / UserCharacter.ATTACK_POINTS_LIMIT);
        critBar1 = findViewById(R.id.progressBar_selectChar_crit1);
        critBar1.setProgress(100 * characterList.get(1).getCriticalHitPoints() / UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar1 = findViewById(R.id.progressBar_selectChar_intelligence1);
        intelligenceBar1.setProgress(100 * characterList.get(1).getIntelligencePoints() / UserCharacter.INTELLIGENCE_POINTS_LIMIT);

        textView2 = findViewById(R.id.textView_selectChar_char2);
        textView2.setText(characterList.get(2).getName());
        button2 = findViewById(R.id.button_selectChar_character2);
        button2.setBackground(getResources().getDrawable(characterList.get(2).getCharImage(), getTheme()));
        healthBar2 = findViewById(R.id.progressBar_selectChar_health2);
        healthBar2.setProgress(100 * characterList.get(2).getMaxHealthPoints() / UserCharacter.HEALTH_POINTS_LIMIT);
        attackBar2 = findViewById(R.id.progressBar_selectChar_attack2);
        attackBar2.setProgress(100 * characterList.get(2).getAttackPoints() / UserCharacter.ATTACK_POINTS_LIMIT);
        critBar2 = findViewById(R.id.progressBar_selectChar_crit2);
        critBar2.setProgress(100 * characterList.get(2).getCriticalHitPoints() / UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        intelligenceBar2 = findViewById(R.id.progressBar_selectChar_intelligence2);
        intelligenceBar2.setProgress(100 * characterList.get(2).getIntelligencePoints() / UserCharacter.INTELLIGENCE_POINTS_LIMIT);

        backButton = findViewById(R.id.button_selectChar_back);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelected(characterList.get(0));
                button0.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelected(characterList.get(1));
                button1.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSelected(characterList.get(2));
                button2.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToStart();
            }
        });

        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);
        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);

        button0.startAnimation(viewBounce);
        button1.startAnimation(viewBounce);
        button2.startAnimation(viewBounce);
        titleText.startAnimation(viewBounce);

        backButton.startAnimation(viewAlphaIncrease);
        textView0.startAnimation(viewAlphaIncrease);
        textView1.startAnimation(viewAlphaIncrease);
        textView2.startAnimation(viewAlphaIncrease);
    }

    private void goBackToStart() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_above, R.anim.slide_out_below);
    }

    private void characterSelected(UserCharacter selectedCharacter) {
        EnemySequence enemySequence = new EnemySequence();
        Match match = new Match(selectedCharacter, enemySequence);

        Intent selectStatsIntent = new Intent(this, SelectStatsActivity.class);
        selectStatsIntent.putExtra("Match", match);
        startActivity(selectStatsIntent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }
}
