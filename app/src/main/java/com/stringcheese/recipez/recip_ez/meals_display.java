package com.stringcheese.recipez.recip_ez;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class meals_display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //used to get data from db
        DataSource dataSource;
        dataSource = new DataSource(this);
        dataSource.open();

        List<Recipe> values = dataSource.getAllRecipes();
        List<String> names = new ArrayList<String>();
        for(Recipe r: values){
            Log.d("TAG", "item");
            Log.d("TAG", r.toString());
        }

        //set recipe list adapter
        ListView listView = (ListView)this.findViewById(R.id.recipe_list);
        ListAdapter adapter;
        adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }

}
