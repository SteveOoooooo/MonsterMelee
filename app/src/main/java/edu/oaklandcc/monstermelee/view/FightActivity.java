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

public class FightActivity extends AppCompatActivity {
    private static final int animationDuration = 1000;
    private static final int animationDistance = 325;

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

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(animationDuration);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

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
        playerAnimation.setDuration(animationDuration);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                attackStep3();
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
        playerAnimation.setDuration(animationDuration);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(animationDuration);

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
    }

    private void attackStep4() {
        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharHurtImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));

        float playerStartX = userImageView.getX();
        float enemyStartX = enemyImageView.getX();

        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        playerAnimation.setDuration(animationDuration);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(animationDuration);

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
            }
        });
    }

    private void userDead() {
        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));
        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));

        float enemyStartX = enemyImageView.getX();

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));
                goToLoseScreen();
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
        playerAnimation.setDuration(animationDuration);
        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));
                goToWinScreen();
            }
        });
        animatorSet.play(playerAnimation);
        animatorSet.start();
    }

}
