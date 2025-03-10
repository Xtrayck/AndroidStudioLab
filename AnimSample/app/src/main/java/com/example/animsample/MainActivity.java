package com.example.animsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView ship = findViewById(R.id.shipView);
        Animation shipAnim = AnimationUtils.loadAnimation(this, R.anim.ship_anim);
        ship.startAnimation(shipAnim);


        Button openRelativeLayout = findViewById(R.id.openRelativeLayout);
        openRelativeLayout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RelativeLayoutActivity.class);
            startActivity(intent);


        });
    }
}
