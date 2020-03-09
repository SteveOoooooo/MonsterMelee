package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

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

    private static final int animationDuration = 1000;
    private static final int animationDistance = 325;

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
                attackStep1();           }
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

    private void goToWinScreen(){

    }

    private void goToLoseScreen(){

    }


    private void attackStep1() {

        float playerStartX = playerImageView.getX();
        float enemyStartX = enemyImageView.getX();

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.X,  playerStartX, playerStartX + animationDistance);
        playerAnimation.setDuration(animationDuration);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX -animationDistance);
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

        float playerStartX = playerImageView.getX();
        float enemyStartX = enemyImageView.getX();

        playerImageView.setTranslationX(animationDistance);
        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setDuration(animationDuration);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
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
        float playerStartX = playerImageView.getX();
        float enemyStartX = enemyImageView.getX();

        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.X,  playerStartX, playerStartX + animationDistance);
        playerAnimation.setDuration(animationDuration);

        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX -animationDistance);
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
        float playerStartX = playerImageView.getX();
        float enemyStartX = enemyImageView.getX();

        playerImageView.setTranslationX(animationDistance);
        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.X, playerStartX, playerStartX - animationDistance);
        playerAnimation.setDuration(animationDuration);

        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(playerAnimation).with(enemyAnimation);
        animatorSet.start();
    }

    private void userDead(){
        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.TRANSLATION_X, 0f, animationDistance);
        enemyAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                goToLoseScreen();
            }
        });
        animatorSet.play(enemyAnimation);
        animatorSet.start();
    }

    private void enemyDead(){
        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(playerImageView, View.TRANSLATION_X, 0f, -animationDistance);
        playerAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                goToWinScreen();
            }
        });
        animatorSet.play(playerAnimation);
        animatorSet.start();
    }

}
