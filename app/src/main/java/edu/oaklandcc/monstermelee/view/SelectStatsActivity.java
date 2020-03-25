package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.utility.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;
import edu.oaklandcc.monstermelee.model.UserCharacter;

public class SelectStatsActivity extends AppCompatActivity {

    Button healthButtonAdd;
    Button attackButtonAdd;
    Button intelligenceButtonAdd;
    Button critButtonAdd;

    ProgressBar healthProgressBar;
    ProgressBar attackProgressBar;
    ProgressBar intelligenceProgressBar;
    ProgressBar critProgressBar;

    ImageView characterImageView;
    TextView characterNameTextView;

    ProgressBar statPointsProgressBar;
    TextView statPointsAvailableTextView;
    int startingStatPoints;

    Match match;
    UserCharacter userCharacter;

    Button backButton;
    Button fightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_select_stats);
        UI.hideSystemUI(this);

        backButton = findViewById(R.id.button_selectStats_back);
        fightButton = findViewById(R.id.button_selectStats_fight);
        characterImageView = findViewById(R.id.imageView_selectStats_character);
        characterNameTextView = findViewById(R.id.textView_selectStats_charName);
        statPointsProgressBar = findViewById(R.id.progressBar_selectStats_pointsAvaliable);
        statPointsAvailableTextView = findViewById(R.id.textView_selectStats_statPointsAvailable);
        healthButtonAdd = findViewById(R.id.button_selectStats_healthAdd);
        attackButtonAdd = findViewById(R.id.button_selectStats_attackAdd);
        intelligenceButtonAdd = findViewById(R.id.button_selectStats_intelligenceAdd);
        critButtonAdd = findViewById((R.id.button_selectStats_critAdd));
        healthProgressBar = findViewById(R.id.progressBar_selectStats_health);
        attackProgressBar = findViewById(R.id.progressBar_selectStats_attack);
        intelligenceProgressBar = findViewById(R.id.progressBar_selectStats_intelligence);
        critProgressBar = findViewById(R.id.progressBar_selectStats_crit);

        final Intent intent = getIntent();
        match = intent.getParcelableExtra("Match");
        userCharacter = match.getUserCharacter();

        startingStatPoints = userCharacter.getAvailableStatPoints();

        characterImageView.setBackground(getResources().getDrawable(userCharacter.getCharImage(), getTheme()));
        characterNameTextView.setText(userCharacter.getName());

        if (!match.isFirstEnemy())
            backButton.setVisibility(View.GONE);

        updateHealth(false);
        updateAttack(false);
        updateIntelligence(false);
        updateCrit(false);
        fightButton.setEnabled(false);
        updateStatPoints();

        healthButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHealth(true);
            }
        });
        attackButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAttack(true);
            }
        });
        critButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCrit(true);
            }
        });

        intelligenceButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIntelligence(true);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToCharacterSelect();
            }
        });

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToEnemyIntro();
            }
        });

    }

    private void updateStatPoints() {
        int availableStatPoints = userCharacter.getAvailableStatPoints();
        if (startingStatPoints > 0)
            statPointsProgressBar.setProgress(100 * availableStatPoints / startingStatPoints);
        else
            statPointsProgressBar.setProgress(0);
        statPointsAvailableTextView.setText(availableStatPoints + " points remaining");
    }

    private void updateHealth(boolean upgrade) {
        if (upgrade) userCharacter.upgradeMaxHealth();
        healthProgressBar.setProgress(100 * userCharacter.getMaxHealthPoints() / userCharacter.HEALTH_POINTS_LIMIT);
        updateButtonEnabled();
        updateStatPoints();
    }

    private void updateAttack(boolean upgrade) {
        if (upgrade) userCharacter.upgradeAttack();
        attackProgressBar.setProgress(100 * userCharacter.getAttackPoints() / userCharacter.ATTACK_POINTS_LIMIT);
        updateButtonEnabled();
        updateStatPoints();
    }

    private void updateCrit(boolean upgrade) {
        if (upgrade) userCharacter.upgradeCrit();
        critProgressBar.setProgress(100 * userCharacter.getCriticalHitPoints() / userCharacter.CRITICAL_ATTACK_POINTS_LIMIT);
        updateButtonEnabled();
        updateStatPoints();
    }

    private void updateIntelligence(boolean upgrade) {
        if (upgrade) userCharacter.upgradeIntelligence();
        intelligenceProgressBar.setProgress(100 * userCharacter.getIntelligencePoints() / userCharacter.INTELLIGENCE_POINTS_LIMIT);
        updateButtonEnabled();
        updateStatPoints();
    }

    private void updateButtonEnabled() {
        if (userCharacter.getMaxHealthPoints() >= UserCharacter.HEALTH_POINTS_LIMIT || userCharacter.getAvailableStatPoints() <= 0)
            healthButtonAdd.setEnabled(false);
        if (userCharacter.getAttackPoints() >= UserCharacter.ATTACK_POINTS_LIMIT || userCharacter.getAvailableStatPoints() <= 0)
            attackButtonAdd.setEnabled(false);
        if (userCharacter.getCriticalHitPoints() >= UserCharacter.CRITICAL_ATTACK_POINTS_LIMIT || userCharacter.getAvailableStatPoints() <= 0)
            critButtonAdd.setEnabled(false);
        if (userCharacter.getIntelligencePoints() >= UserCharacter.INTELLIGENCE_POINTS_LIMIT || userCharacter.getAvailableStatPoints() <= 0)
            intelligenceButtonAdd.setEnabled(false);
        if (userCharacter.getAvailableStatPoints() == 0)
            fightButton.setEnabled(true);
    }

    private void goBackToCharacterSelect(){
        Intent selectCharacterIntent = new Intent(this, SelectCharacterActivity.class);
        startActivity(selectCharacterIntent);
        overridePendingTransition(R.transition.slide_in_above, R.transition.slide_out_below);
    }

    private void goToEnemyIntro(){
        Intent intent = new Intent(this, EnemyIntroductionActivity.class);
        intent.putExtra("Match", match);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_in_below, R.transition.slide_out_above);
    }
}
