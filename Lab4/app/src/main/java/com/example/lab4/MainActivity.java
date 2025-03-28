package com.example.lab4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private Button btnUkrainian, btnEnglish, btnFont;
    private int fontIndex = 0;
    private final String[] fontStyles = {"sans-serif", "serif", "monospace"};
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        preferences = getSharedPreferences("settings", MODE_PRIVATE);
        String language = preferences.getString("language", "");
        int savedFlag = preferences.getInt("flag", 0);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        btnUkrainian = findViewById(R.id.btnUkrainian);
        btnEnglish = findViewById(R.id.btnEnglish);
        btnFont = findViewById(R.id.btnFont);


        if (language.isEmpty()) {
            imageView.setImageResource(R.drawable.welcome_image);
        } else {
            imageView.setImageResource(savedFlag);
            setLocale(language);
        }


        btnUkrainian.setOnClickListener(v -> changeLanguage("uk", R.drawable.flag_ukraine));


        btnEnglish.setOnClickListener(v -> changeLanguage("en", R.drawable.flag_uk));

        btnFont.setOnClickListener(v -> changeFont());
    }


    private void changeLanguage(String lang, int flagResId) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", lang);
        editor.putInt("flag", flagResId);
        editor.apply();


        setLocale(lang);


        finish();
        startActivity(getIntent());
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }


    private void changeFont() {
        fontIndex = (fontIndex + 1) % fontStyles.length;
        textView.setTypeface(android.graphics.Typeface.create(fontStyles[fontIndex], android.graphics.Typeface.NORMAL));
    }
}
