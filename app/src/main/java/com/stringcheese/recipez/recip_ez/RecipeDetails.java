package com.stringcheese.recipez.recip_ez;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new DataSource(getBaseContext());
        dataSource.open();

        Intent i = getIntent();
        recipename = i.getStringExtra("recipe_name");


        getSupportActionBar().setTitle(recipename);
        populate(recipename);

    }
    public void populate(String name){
        //TextView recipe_name = (TextView) this.findViewById(R.id.recipe_name);




        TextView recipe_servings = (TextView) this.findViewById(R.id.recipe_servings);
        TextView recipe_directions = (TextView) this.findViewById(R.id.recipe_directions);
        TextView recipe_description = (TextView) this.findViewById(R.id.recipe_description);


        Recipe r = dataSource.getRecipeDetails(name);
        //if(r.get_recipename() != "") recipe_name.setText(r.get_recipename());
        recipe_servings.setText(String.valueOf(r.get_servings()));
        if(r.get_directions() != "") recipe_directions.setText(r.get_directions());
        if(r.get_description() != "") recipe_description.setText(r.get_description());



        //populate ingredients list
        ListView listView = (ListView)this.findViewById(R.id.ingredient_list);
        ArrayAdapter<Ingredient> adapter;

        List<Ingredient> ingredients = dataSource.getRecipeIngredients(String.valueOf(r.get_id()));

        adapter = new ArrayAdapter<Ingredient>(getBaseContext(), android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(adapter);


        //set image
        if(r.get_image()!=null) {

            //imgView.setImageURI((Uri.parse(r.get_image())));
            File f = new File(r.get_image());
            if(!f.exists()) Log.v("TAG", "FILE DOESN'T EXIST");

            ImageView myImage = (ImageView) findViewById(R.id.toolbar_image);

            //Picasso.with(this).load("file://"+r.get_image()).config(Bitmap.Config.RGB_565).fit().centerCrop().into(myImage);
            Picasso.with(this).load(Uri.parse(r.get_image())).into(myImage);
            Log.v("TAG", r.get_image());
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
