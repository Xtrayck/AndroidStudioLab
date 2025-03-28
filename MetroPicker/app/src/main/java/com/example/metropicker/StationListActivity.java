package com.example.metropicker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StationListActivity extends ListActivity {

    private final String[] stations = {
            "Академмістечко", "Житомирська", "Святошин", "Нивки", "Берестейська",
            "Шулявська", "Політехнічний інститут", "Вокзальна", "Університет", "Театральна"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stations));
    }

    @Override
    protected void onListItemClick(ListView l, android.view.View v, int position, long id) {
        String station = stations[position];
        Intent resultIntent = new Intent();
        resultIntent.putExtra("station", station);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    // Головне меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Повернутися");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            finish(); // Закриває активність
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
