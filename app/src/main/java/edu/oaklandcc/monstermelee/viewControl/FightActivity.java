package edu.oaklandcc.monstermelee.viewControl;import androidx.appcompat.app.AppCompatActivity;import android.animation.Animator;import android.animation.AnimatorListenerAdapter;import android.animation.AnimatorSet;import android.animation.ObjectAnimator;import android.app.Activity;import android.content.Intent;import android.media.MediaPlayer;import android.os.Bundle;import android.view.HapticFeedbackConstants;import android.view.View;import android.view.animation.AccelerateDecelerateInterpolator;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.Button;import android.widget.ImageButton;import android.widget.ImageView;import android.widget.ProgressBar;import edu.oaklandcc.monstermelee.R;import edu.oaklandcc.monstermelee.model.Match;import edu.oaklandcc.monstermelee.utility.UI;public class FightActivity extends AppCompatActivity {    private static final int ANIMATION_DURATION = 1000;    float animationDistance;    Match currentMatch;    Button attackButton;    ImageButton exitButton;    ImageView userImageView;    ImageView enemyImageView;    ProgressBar userProgressBar;    ProgressBar enemyProgressBar;    Animation viewJiggle;    Activity currentAtivity;    MediaPlayer meleeSound;    MediaPlayer opponentSound;    MediaPlayer deadSound;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        UI.immersiveLandscape(this);        setContentView(R.layout.activity_fight);        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);        currentAtivity = this;        attackButton = findViewById(R.id.button_fight_attack);        exitButton = findViewById(R.id.imageButton_fight_exit);        userImageView = findViewById(R.id.imageView_fight_userCharacter);        enemyImageView = findViewById(R.id.imageView_fight_enemyCharacter);        userProgressBar = findViewById(R.id.progressBar_fight_userHealth);        enemyProgressBar = findViewById(R.id.progressBar_fight_enemyHealth);        Intent intent = getIntent();        meleeSound = MediaPlayer.create(FightActivity.this,R.raw.melee);        opponentSound = MediaPlayer.create(FightActivity.this,R.raw.opponent);        deadSound = MediaPlayer.create(FightActivity.this,R.raw.dead);        currentMatch = intent.getParcelableExtra("Match");        currentMatch.getUserCharacter().resetHealth();        userImageView.setBackground(getResources().getDrawable(                currentMatch.getUserCharacter().getCharImage(), getTheme()));        updateHealthProgressBar();        enemyImageView.setBackground(getResources().getDrawable(                currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        updateHealthProgressBar();        exitButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                exitButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);                exitButton.startAnimation(viewJiggle);                goToGiveUpScreen();            }        });        attackButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                attackButton.startAnimation(viewJiggle);                attackButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);                attackStep1();            }        });        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);        enemyImageView.startAnimation(viewBounce);        userImageView.startAnimation(viewBounce);        attackButton.startAnimation(viewAlphaIncrease);        exitButton.startAnimation(viewAlphaIncrease);        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);    }    private void goToGiveUpScreen() {        Intent intent = new Intent(this, GiveUpActivity.class);        startActivity(intent);        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);    }    private void updateHealthProgressBar() {        enemyProgressBar.setProgress(100 * currentMatch.getEnemyCharacter().getCurrentHealthPoints()                / currentMatch.getEnemyCharacter().getMaxHealthPoints());        userProgressBar.setProgress(100 * currentMatch.getUserCharacter().getCurrentHealthPoints()                / currentMatch.getUserCharacter().getMaxHealthPoints());    }    private void goToWinScreen() {        Intent intent = new Intent(this, WonMatchActivity.class);        intent.putExtra("Match", currentMatch);        startActivity(intent);    }    private void goToLoseScreen() {        Intent intent = new Intent(this, GameOverActivity.class);        intent.putExtra("Match", currentMatch);        startActivity(intent);    }    private void attackStep1() {        attackButton.setEnabled(false);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        animationDistance = (enemyStartX - playerStartX) / 3;        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                meleeSound.start();                currentMatch.userAttack();                updateHealthProgressBar();                enemyProgressBar.startAnimation(viewJiggle);                UI.vibrate(currentAtivity);                if (currentMatch.enemyIsDead())                    enemyDead();                else                    attackStep2();            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();    }    private void attackStep2() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharHurtImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                attackStep3();                meleeSound.start();            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();    }    private void attackStep3() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                currentMatch.enemyAttack();                updateHealthProgressBar();                userProgressBar.startAnimation(viewJiggle);                UI.vibrate(currentAtivity);                if (currentMatch.userIsDead())                    userDead();                else                    attackStep4();            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();        opponentSound.start();    }    private void attackStep4() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharHurtImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));                attackButton.setEnabled(true);                opponentSound.start();            }        });    }    private void userDead() {        userProgressBar.startAnimation(viewJiggle);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));        float enemyStartX = enemyImageView.getX();        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));                goToLoseScreen();                deadSound.start();            }        });        animatorSet.play(enemyAnimation);        animatorSet.start();    }    private void enemyDead() {        enemyProgressBar.startAnimation(viewJiggle);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharDeadImage(), getTheme()));        float playerStartX = userImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setDuration(ANIMATION_DURATION);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));                goToWinScreen();               MediaPlayer win = MediaPlayer.create(FightActivity.this,R.raw.win);               win.start();            }        });        animatorSet.play(playerAnimation);        animatorSet.start();    }    public void onWindowFocusChanged(boolean hasFocus) {        super.onWindowFocusChanged(hasFocus);        if (hasFocus) {            UI.immersiveLandscape(this);        }    }}