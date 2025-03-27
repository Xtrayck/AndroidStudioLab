package com.example.paintapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

public class PaintActivity extends Activity {

    private PaintView paintView;
    private SeekBar seekBarThickness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Створюємо PaintView
        paintView = new PaintView(this);

        // Створюємо LinearLayout для контейнера
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Додаємо PaintView в макет
        layout.addView(paintView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                0, 1f));  // Задаємо вагу для PaintView

        // Створюємо SeekBar для зміни товщини лінії
        seekBarThickness = new SeekBar(this);
        seekBarThickness.setMax(50);  // Максимальна товщина лінії
        seekBarThickness.setProgress(10);  // Товщина за замовчуванням

        seekBarThickness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                paintView.setLineThickness(progress);  // Оновлюємо товщину лінії
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Створюємо кнопки для зміни кольору лінії
        Button buttonBlack = new Button(this);
        buttonBlack.setText("Чорний");
        buttonBlack.setOnClickListener(v -> paintView.setLineColor(Color.BLACK));

        Button buttonRed = new Button(this);
        buttonRed.setText("Червоний");
        buttonRed.setOnClickListener(v -> paintView.setLineColor(Color.RED));

        Button buttonGreen = new Button(this);
        buttonGreen.setText("Зелений");
        buttonGreen.setOnClickListener(v -> paintView.setLineColor(Color.GREEN));

        Button buttonBlue = new Button(this);
        buttonBlue.setText("Синій");
        buttonBlue.setOnClickListener(v -> paintView.setLineColor(Color.BLUE));

        // Додаємо елементи в макет
        layout.addView(seekBarThickness, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(buttonBlack);
        layout.addView(buttonRed);
        layout.addView(buttonGreen);
        layout.addView(buttonBlue);

        setContentView(layout);
    }
}
