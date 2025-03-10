package com.example.roma2;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private ImageView authorImage;
    private Button answerButton1, answerButton2, answerButton3;
    private TextView scoreText;
    private int score = 0;
    private int attempts = 0;
    private static final int MAX_ATTEMPTS = 10;

    private int[] images = {R.drawable.shevchenko, R.drawable.ukrainka, R.drawable.franko, R.drawable.simonenko, R.drawable.stus};
    private String[] names = {"Тарас Шевченко", "Леся Українка", "Іван Франко", "Василь Симоненко", "Василь Стус"};
    private Random random = new Random();
    private int correctAnswerIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        authorImage = findViewById(R.id.authorImage);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        scoreText = findViewById(R.id.scoreText);

        startNewRound();

        answerButton1.setOnClickListener(answerClickListener);
        answerButton2.setOnClickListener(answerClickListener);
        answerButton3.setOnClickListener(answerClickListener);
    }

    private void startNewRound() {
        if (attempts >= MAX_ATTEMPTS) {
            endGame();
            return;
        }

        correctAnswerIndex = random.nextInt(names.length);
        authorImage.setImageResource(images[correctAnswerIndex]);

        int correctPosition = random.nextInt(3);
        Button[] buttons = {answerButton1, answerButton2, answerButton3};

        for (int i = 0, j = 0; i < 3; i++) {
            if (i == correctPosition) {
                buttons[i].setText(names[correctAnswerIndex]);
            } else {
                int wrongIndex;
                do {
                    wrongIndex = random.nextInt(names.length);
                } while (wrongIndex == correctAnswerIndex);
                buttons[i].setText(names[wrongIndex]);
            }
        }
    }

    private void endGame() {
        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }

    private View.OnClickListener answerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().equals(names[correctAnswerIndex])) {
                score++;
            }
            attempts++;

            scoreText.setText("Бали: " + score);

            startNewRound();
        }
    };
}
