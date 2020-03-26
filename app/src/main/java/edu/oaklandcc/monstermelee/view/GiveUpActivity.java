package edu.oaklandcc.monstermelee.view;

import androidx.appcompat.app.AppCompatActivity;
import edu.oaklandcc.monstermelee.utility.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import edu.oaklandcc.monstermelee.R;

public class GiveUpActivity extends AppCompatActivity {

    Button yesButton;
    Button noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_up);
        UI.hideSystemUI(this);

        yesButton = findViewById(R.id.button_giveUp_yes);
        noButton = findViewById(R.id.button_giveUp_no);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomeScreen();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToFight();
            }
        });
    }

    private void goBackToFight() {
        this.finish();
        overridePendingTransition(R.anim.slide_in_above, R.anim.slide_out_below);
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_below, R.anim.slide_out_above);
    }
}
