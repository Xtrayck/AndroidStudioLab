package com.example.listviewsample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.app.ListActivity;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    
        String[] stationsArray = getResources().getStringArray(R.array.stations);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stationsArray);


        setListAdapter(adapter);

        ListView lv = getListView();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String selectedStation = (String) parent.getItemAtPosition(position);


                Toast.makeText(getApplicationContext(), "Вибрана станція: " + selectedStation, Toast.LENGTH_LONG).show();
            }
        });
    }
}
