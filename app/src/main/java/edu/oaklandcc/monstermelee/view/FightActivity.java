package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import edu.oaklandcc.monstermelee.R;
import edu.oaklandcc.monstermelee.model.Match;
import edu.oaklandcc.monstermelee.utility.UI;

public class FightActivity extends AppCompatActivity {
    private static final int ANIMATION_DURATION = 1000;

    float animationDistance;

    Match currentMatch;

    Button attackButton;
    ImageButton exitButton;
    ImageView userImageView;
    ImageView enemyImageView;
    ProgressBar playerProgressBar;
    ProgressBar enemyProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        UI.immersiveLandscape(this);

        attackButton = findViewById(R.id.button_fight_attack);
        exitButton = findViewById(R.id.imageButton_fight_exit);

        userImageView = findViewById(R.id.imageView_fight_userCharacter);
        enemyImageView = findViewById(R.id.imageView_fight_enemyCharacter);

        playerProgressBar = findViewById(R.id.progressBar_fight_userHealth);
        enemyProgressBar = findViewById(R.id.progressBar_fight_enemyHealth);

        Intent intent = getIntent();

        currentMatch = intent.getParcelableExtra("Match");
        currentMatch.getUserCharacter().resetHealth();

        userImageView.setBackground(getResources().getDrawable(
                currentMatch.getUserCharacter().getCharImage(), getTheme()));
        updateHealthProgressBar();

        enemyImageView.setBackground(getResources().getDrawable(
                currentMatch.getEnemyCharacter().getCharImage(), getTheme()));
        updateHealthProgressBar();

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGiveUpScreen();
            }
        });

        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attackStep1();
            }
        });

    }

    private void goToGiveUpScreen() {
        Intent intent = new Intent(this, GiveUpActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }

    private void updateHealthProgressBar() {
        enemyProgressBar.setProgress(100 * currentMatch.getEnemyCharacter().getCurrentHealthPoints()
                / currentMatch.getEnemyCharacter().getMaxHealthPoints());
        playerProgressBar.setProgress(100 * currentMatch.getUserCharacter().getCurrentHealthPoints()
                / currentMatch.getUserCharacter().getMaxHealthPoints());
    }

    private void goToWinScreen() {
        Intent intent = new Intent(this, WonMatchActivity.class);
        intent.putExtra("Match", currentMatch);
        startActivity(intent);
    }

    private void goToLoseScreen() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("Match", currentMatch);
        startActivity(intent);
    }

    private void attackStep1() {
        attackButton.setEnabled(false);

        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));

        float playerStartX = userImageView.getX();
        float enemyStartX = enemyImageView.getX();
        animationDistance = (enemyStartX - playerStartX) / 3;

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // add sound

                currentMatch.userAttack();
                updateHealthProgressBar();

                if (currentMatch.enemyIsDead())
                    enemyDead();
                else
                    attackStep2();
            }
        });

        animatorSet.play(playerAnimation).with(enemyAnimation);
        animatorSet.start();
    }

    private void attackStep2() {

        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharHurtImage(), getTheme()));

        float playerStartX = userImageView.getX();
        float enemyStartX = enemyImageView.getX();

        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                attackStep3();
                // add sound
            }
        });

        animatorSet.play(playerAnimation).with(enemyAnimation);
        animatorSet.start();
    }

    private void attackStep3() {

        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));

        float playerStartX = userImageView.getX();
        float enemyStartX = enemyImageView.getX();

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentMatch.enemyAttack();
                updateHealthProgressBar();
                if (currentMatch.userIsDead())
                    userDead();
                else
                    attackStep4();
            }
        });

        animatorSet.play(playerAnimation).with(enemyAnimation);
        animatorSet.start();
        //add sound
    }

    private void attackStep4() {
        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharHurtImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));

        float playerStartX = userImageView.getX();
        float enemyStartX = enemyImageView.getX();

        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(ANIMATION_DURATION);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(playerAnimation).with(enemyAnimation);
        animatorSet.start();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));
                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));
                attackButton.setEnabled(true);

                //add sound
            }
        });
    }

    private void userDead() {
        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));

        float enemyStartX = enemyImageView.getX();

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(ANIMATION_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));
                goToLoseScreen();

                //add sound
            }
        });
        animatorSet.play(enemyAnimation);
        animatorSet.start();
    }

    private void enemyDead() {
        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharDeadImage(), getTheme()));

        float playerStartX = userImageView.getX();
        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setDuration(ANIMATION_DURATION);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));
                goToWinScreen();
                //add sound
            }
        });
        animatorSet.play(playerAnimation);
        animatorSet.start();
    }

}
