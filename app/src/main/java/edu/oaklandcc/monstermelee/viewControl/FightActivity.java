package edu.oaklandcc.monstermelee.viewControl;import androidx.appcompat.app.AppCompatActivity;import android.animation.Animator;import android.animation.AnimatorListenerAdapter;import android.animation.AnimatorSet;import android.animation.ObjectAnimator;import android.app.Activity;import android.content.Intent;import android.media.AudioAttributes;import android.media.SoundPool;import android.os.Bundle;import android.view.HapticFeedbackConstants;import android.view.View;import android.view.animation.AccelerateDecelerateInterpolator;import android.view.animation.Animation;import android.view.animation.AnimationUtils;import android.widget.Button;import android.widget.ImageButton;import android.widget.ImageView;import android.widget.ProgressBar;import edu.oaklandcc.monstermelee.R;import edu.oaklandcc.monstermelee.model.Match;import edu.oaklandcc.monstermelee.utility.UI;public class FightActivity extends AppCompatActivity {    private static final int ANIMATION_DURATION = 1000;    float animationDistance;    Match currentMatch;    Button attackButton;    ImageButton exitButton;    ImageView userImageView;    ImageView enemyImageView;    ProgressBar userProgressBar;    ProgressBar enemyProgressBar;    Animation viewJiggle;    Activity currentAtivity;    AudioAttributes audioAttributes = new AudioAttributes.Builder()            .setUsage(AudioAttributes.USAGE_GAME)            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)            .build();    SoundPool gameSounds = new SoundPool.Builder()            .setAudioAttributes(audioAttributes)            .setMaxStreams(5)            .build();    int meleeSound;    int injuredSound;    int deadSound;// soundId for reuse later on    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        UI.immersiveUI(this);        setContentView(R.layout.activity_fight);        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);        currentAtivity = this;        attackButton = findViewById(R.id.button_fight_attack);        exitButton = findViewById(R.id.imageButton_fight_exit);        userImageView = findViewById(R.id.imageView_fight_userCharacter);        enemyImageView = findViewById(R.id.imageView_fight_enemyCharacter);        userProgressBar = findViewById(R.id.progressBar_fight_userHealth);        enemyProgressBar = findViewById(R.id.progressBar_fight_enemyHealth);        Intent intent = getIntent();        meleeSound = gameSounds.load(this, R.raw.melee, 1);        injuredSound = gameSounds.load(this, R.raw.opponent, 1);        deadSound = gameSounds.load(this, R.raw.dead,1);        currentMatch = intent.getParcelableExtra("Match");        currentMatch.getUserCharacter().resetHealth();        userImageView.setBackground(getResources().getDrawable(                currentMatch.getUserCharacter().getCharImage(), getTheme()));        updateHealthProgressBar();        enemyImageView.setBackground(getResources().getDrawable(                currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        updateHealthProgressBar();        exitButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                exitButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);                exitButton.startAnimation(viewJiggle);                goToGiveUpScreen();            }        });        attackButton.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                attackButton.startAnimation(viewJiggle);                attackButton.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);                attackStep1();            }        });        Animation viewBounce = AnimationUtils.loadAnimation(this, R.anim.view_bounce);        Animation viewAlphaIncrease = AnimationUtils.loadAnimation(this, R.anim.view_alpha_increase);        enemyImageView.startAnimation(viewBounce);        userImageView.startAnimation(viewBounce);        attackButton.startAnimation(viewAlphaIncrease);        exitButton.startAnimation(viewAlphaIncrease);        viewJiggle = AnimationUtils.loadAnimation(this, R.anim.view_jiggle);    }    private void goToGiveUpScreen() {        Intent intent = new Intent(this, GiveUpActivity.class);        startActivity(intent);        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);    }    private void updateHealthProgressBar() {        enemyProgressBar.setProgress(100 * currentMatch.getEnemyCharacter().getCurrentHealthPoints()                / currentMatch.getEnemyCharacter().getMaxHealthPoints());        userProgressBar.setProgress(100 * currentMatch.getUserCharacter().getCurrentHealthPoints()                / currentMatch.getUserCharacter().getMaxHealthPoints());    }    private void goToWinScreen() {        Intent intent = new Intent(this, WonMatchActivity.class);        intent.putExtra("Match", currentMatch);        startActivity(intent);    }    private void goToLoseScreen() {        Intent intent = new Intent(this, GameOverActivity.class);        intent.putExtra("Match", currentMatch);        startActivity(intent);    }    private void attackStep1() {        attackButton.setEnabled(false);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        animationDistance = (enemyStartX - playerStartX) / 3;        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                currentMatch.userAttack();                updateHealthProgressBar();                enemyProgressBar.startAnimation(viewJiggle);                UI.vibrate(currentAtivity);                if (currentMatch.enemyIsDead()) {                    gameSounds.play(meleeSound, 1, 1, 0, 0, 2);                    gameSounds.play(deadSound, 1, 1, 0, 0, 1);                    enemyDead();                }                else {                    gameSounds.play(meleeSound, 1, 1, 0, 0, 2);                    gameSounds.play(injuredSound, 1, 1, 0, 0, 1);                    attackStep2();                }            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();    }    private void attackStep2() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharHurtImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                attackStep3();            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();    }    private void attackStep3() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        final ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX + animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        final ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX - animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                currentMatch.enemyAttack();                updateHealthProgressBar();                userProgressBar.startAnimation(viewJiggle);                UI.vibrate(currentAtivity);                if (currentMatch.userIsDead()){                    gameSounds.play(meleeSound, 1, 1, 0, 0, 2);                    gameSounds.play(deadSound, 1, 1, 0, 0, 1);                    userDead();                }                else {                    gameSounds.play(meleeSound, 1, 1, 0, 0, 2);                    gameSounds.play(injuredSound, 1, 1, 0, 0, 1);                    attackStep4();                }            }        });        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();    }    private void attackStep4() {        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharHurtImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));        float playerStartX = userImageView.getX();        float enemyStartX = enemyImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        playerAnimation.setDuration(ANIMATION_DURATION);        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.play(playerAnimation).with(enemyAnimation);        animatorSet.start();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));                attackButton.setEnabled(true);            }        });    }    private void userDead() {        userProgressBar.startAnimation(viewJiggle);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharDeadImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharAttackImage(), getTheme()));        float enemyStartX = enemyImageView.getX();        ObjectAnimator enemyAnimation = ObjectAnimator.ofFloat(enemyImageView, View.X, enemyStartX, enemyStartX + animationDistance);        enemyAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        enemyAnimation.setDuration(ANIMATION_DURATION);        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharImage(), getTheme()));                goToLoseScreen();            }        });        animatorSet.play(enemyAnimation);        animatorSet.start();    }    private void enemyDead() {        enemyProgressBar.startAnimation(viewJiggle);        userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharAttackImage(), getTheme()));        enemyImageView.setBackground(getResources().getDrawable(currentMatch.getEnemyCharacter().getCharDeadImage(), getTheme()));        float playerStartX = userImageView.getX();        ObjectAnimator playerAnimation = ObjectAnimator.ofFloat(userImageView, View.X, playerStartX, playerStartX - animationDistance);        playerAnimation.setDuration(ANIMATION_DURATION);        playerAnimation.setInterpolator(new AccelerateDecelerateInterpolator());        AnimatorSet animatorSet = new AnimatorSet();        animatorSet.addListener(new AnimatorListenerAdapter() {            @Override            public void onAnimationEnd(Animator animation) {                super.onAnimationEnd(animation);                userImageView.setBackground(getResources().getDrawable(currentMatch.getUserCharacter().getCharImage(), getTheme()));                goToWinScreen();            }        });        animatorSet.play(playerAnimation);        animatorSet.start();    }    @Override    public void onWindowFocusChanged(boolean hasFocus) {        super.onWindowFocusChanged(hasFocus);        if (hasFocus) {            UI.immersiveUI(this);        }    }    @Override    protected void onDestroy() {        super.onDestroy();        gameSounds.release();        gameSounds = null;    }}