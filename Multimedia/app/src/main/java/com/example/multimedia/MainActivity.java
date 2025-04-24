package com.example.multimedia;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    List<ItemData> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            gridView = findViewById(R.id.gridView);
            items = new ArrayList<>();

            // Añadir tus recursos (estos deben existir en drawable y raw)
            items.add(new ItemData(R.drawable.image1, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image2, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image3, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image4, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image5, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image6, R.raw.audio1, R.raw.video1));
            items.add(new ItemData(R.drawable.image7, R.raw.audio1, R.raw.video1));


            ImageAdapter adapter = new ImageAdapter(this, items);
            gridView.setAdapter(adapter);
        }
}