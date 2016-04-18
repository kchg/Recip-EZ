package com.stringcheese.recipez.recip_ez;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

public class RecipeDetails extends AppCompatActivity {
    public DataSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String recipename;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSource = new DataSource(getBaseContext());
        dataSource.open();

        Intent i = getIntent();
        recipename = i.getStringExtra("recipe_name");
        populate(recipename);

    }
    public void populate(String name){
        TextView recipe_name = (TextView) this.findViewById(R.id.recipe_name);

        TextView recipe_servings = (TextView) this.findViewById(R.id.recipe_servings);
        TextView recipe_directions = (TextView) this.findViewById(R.id.recipe_directions);
        TextView recipe_description = (TextView) this.findViewById(R.id.recipe_description);


        Recipe r = dataSource.getRecipeDetails(name);
        if(r.get_recipename() != "") recipe_name.setText(r.get_recipename());
        recipe_servings.setText(String.valueOf(r.get_servings()));
        if(r.get_directions() != "") recipe_directions.setText(r.get_directions());
        if(r.get_description() != "") recipe_description.setText(r.get_description());



        //populate ingredients list
        ListView listView = (ListView)this.findViewById(R.id.ingredient_list);
        ArrayAdapter<Ingredient> adapter;

        List<Ingredient> ingredients = dataSource.getRecipeIngredients(String.valueOf(r.get_id()));

        adapter = new ArrayAdapter<Ingredient>(getBaseContext(), android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(adapter);




    }
}
