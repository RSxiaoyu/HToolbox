package com.xiaoyu.htoolbox;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        SharedPreferences sp = getSharedPreferences(getString(R.string.dataset), MODE_PRIVATE);
        int base_timeout = sp.getInt("base_timeout", 2 * 60 * 1000);
        int extend_timeout = sp.getInt("extend_timeout", 10 * 60 * 1000);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize data
        List<Item> items = new ArrayList<>();
        items.add(new Item("基本休眠时间(ms)", "base_timeout", base_timeout));
        items.add(new Item("延长休眠时间(ms)", "extend_timeout", extend_timeout));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items);
        recyclerView.setAdapter(adapter);


    }
}