package com.example.roma2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EndGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        int score = getIntent().getIntExtra("score", 0);
        TextView finalScoreText = findViewById(R.id.finalScoreText);
        finalScoreText.setText("Ваші бали: " + score);

        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(v -> {
            Intent intent = new Intent(EndGameActivity.this, StatsActivity.class);
            startActivity(intent);
        });
    }
}
