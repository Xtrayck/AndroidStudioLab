package com.example.metropicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final String PREFS_NAME = "MetroPrefs";
    private static final String KEY_STATION = "selected_station";

    private TextView selectedStation;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedStation = findViewById(R.id.selected_station);
        Button selectStationButton = findViewById(R.id.select_station_button);

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedStation = preferences.getString(KEY_STATION, "Станція не вибрана");
        selectedStation.setText(savedStation);

        registerForContextMenu(selectedStation); // Підключаємо контекстне меню

        selectStationButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StationListActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String station = data.getStringExtra("station");
            selectedStation.setText(station);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_STATION, station);
            editor.apply();
        }
    }

    // Контекстне меню для скидання станції
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Скинути станцію");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            selectedStation.setText("Станція не вибрана");
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(KEY_STATION);
            editor.apply();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    // Головне меню (Скинути / Вийти)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 2, 0, "Скинути");
        menu.add(0, 3, 1, "Вийти");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 2) {
            selectedStation.setText("Станція не вибрана");
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(KEY_STATION);
            editor.apply();
            return true;
        } else if (item.getItemId() == 3) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
