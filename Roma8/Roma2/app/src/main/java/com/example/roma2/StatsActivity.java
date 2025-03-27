package com.example.roma2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class StatsActivity extends AppCompatActivity {

    private ListView statsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsListView = findViewById(R.id.statsListView);

        GameStatsDatabaseHelper dbHelper = new GameStatsDatabaseHelper(this);
        Cursor cursor = dbHelper.getGameStats();

        // Вказуємо стовпці, які потрібно відобразити
        String[] from = {GameStatsDatabaseHelper.COLUMN_SCORE, GameStatsDatabaseHelper.COLUMN_DATE};
        int[] to = {R.id.scoreText, R.id.dateText}; // Вказуємо id елементів для відображення

        // Створюємо адаптер, який зв'язує курсор з елементами ListView
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.stats_item, // Лейаут елемента списку
                cursor,
                from,
                to,
                0
        );

        statsListView.setAdapter(adapter);
    }
}
