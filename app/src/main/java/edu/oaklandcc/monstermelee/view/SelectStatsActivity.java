package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.oaklandcc.monstermelee.R;
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

    UserCharacter userCharacter;

    Button backButton;
    Button fightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        setContentView(R.layout.activity_select_stats);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        final Intent intent = getIntent();
        userCharacter = intent.getParcelableExtra("SelectedCharacter");
        startingStatPoints = userCharacter.getAvailableStatPoints();

        characterImageView = findViewById(R.id.imageView_Character);
        characterImageView.setBackground(getResources().getDrawable(userCharacter.getCharImage(), getTheme()));

        characterNameTextView = findViewById(R.id.textView_Char);
        characterNameTextView.setText(userCharacter.getName());

        statPointsProgressBar = findViewById(R.id.progressBar_pointsAvaliable);
        statPointsAvailableTextView = findViewById(R.id.textView_statPointsAvailable);

        healthButtonAdd = findViewById(R.id.button_HealthAdd);
        attackButtonAdd = findViewById(R.id.button_AttackAdd);
        intelligenceButtonAdd = findViewById(R.id.button_IntelligenceAdd);
        critButtonAdd = findViewById((R.id.button_CritAdd));

        healthProgressBar = findViewById(R.id.progressBar_Health);
        updateHealth(false);

        attackProgressBar = findViewById(R.id.progressBar_Attack);
        updateAttack(false);

        intelligenceProgressBar = findViewById(R.id.progressBar_Intelligence);
        updateIntelligence(false);

        critProgressBar = findViewById(R.id.progressBar_Crit);
        updateCrit(false);

        backButton = findViewById(R.id.button_BackToChacacterSeelct);
        fightButton = findViewById(R.id.button_goToEnemyIntro);

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
        statPointsProgressBar.setProgress(100 * availableStatPoints / startingStatPoints);
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
    }

    private void goBackToCharacterSelect(){
        Intent selectCharacterIntent = new Intent(this, SelectCharacterActivity.class);
        startActivity(selectCharacterIntent);
    }

    private void goToEnemyIntro(){
        Intent intent = new Intent(this, EnemyIntroductionActivity.class);
        intent.putExtra("UserCharacter", userCharacter);
        startActivity(intent);
    }
}
