package com.example.recyclerViewLayout;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for the RecyclerView
        String[] titles = {"Item 1", "Item 2", "Item 3"};
        String[] descriptions = {"Description 1", "Description 2", "Description 3"};
        int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3}; // Replace with actual drawable resources

        itemAdapter = new ItemAdapter(titles, descriptions, images);
        recyclerView.setAdapter(itemAdapter);
    }
}