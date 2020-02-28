package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;

public class FightActivity extends AppCompatActivity {

    Match currentMatch;

    Button attackButton;
    ImageButton exitButton;

    ImageView playerImageView;
    ImageView enemyImageView;

    ProgressBar playerProgressBar;
    ProgressBar enemyProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        attackButton = findViewById(R.id.button_Attack);
        exitButton = findViewById(R.id.imageButton_exit);

        playerImageView = findViewById(R.id.imageView_userCharacter);
        enemyImageView = findViewById(R.id.imageView_enemyCharacter);

        playerProgressBar = findViewById(R.id.progressBar_characterHealth);
        enemyProgressBar = findViewById(R.id.progressBar_enemyHealth);

        Intent intent = getIntent();

        currentMatch = intent.getParcelableExtra("Match");
        currentMatch.getUserCharacter().resetHealth();

        playerImageView.setBackground(getResources().getDrawable(
                currentMatch.getUserCharacter().getCharImage(), getTheme()));
        updatePlayerHealthProgressBar();

        enemyImageView.setBackground(getResources().getDrawable(
                currentMatch.getEnemyCharacter().getCharImage(), getTheme()));
        updateEnemyHealthProgressBar();

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGiveUpScreen();
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attack();
            }
        });
    }

    private void attack() {

        currentMatch.userAttack();
        animateCharacters(500, 325);
        updateEnemyHealthProgressBar();


        if (!currentMatch.enemyIsDead()) {
            currentMatch.enemyAttack();
            updatePlayerHealthProgressBar();
            //animateCharacters(500, -325);
        }
        else {
            goToWinScreen();
        }

        if (currentMatch.userIsDead()){
            goToLoseScreen();
        }
    }

    private void goToGiveUpScreen() {
        Intent intent = new Intent(this, GiveUpActivity.class);
        startActivity(intent);
    }

    private void updateEnemyHealthProgressBar() {
        enemyProgressBar.setProgress(100 * currentMatch.getEnemyCharacter().getCurrentHealthPoints()
                / currentMatch.getEnemyCharacter().getMaxHealthPoints());
    }

    private void updatePlayerHealthProgressBar() {
        playerProgressBar.setProgress(100 * currentMatch.getUserCharacter().getCurrentHealthPoints()
                / currentMatch.getUserCharacter().getMaxHealthPoints());
    }


    private void goToWinScreen(){

    }

    private void goToLoseScreen(){

    }


    private void animateCharacters(int animationDuration, float animationDistance){
        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.TRANSLATION_X, 0f, animationDistance);
        playerAnimation.setDuration(animationDuration);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.TRANSLATION_X, 0f, -animationDistance);
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(playerAnimation).with(enemyAnimation);

        animatorSet.start();


    }
}
