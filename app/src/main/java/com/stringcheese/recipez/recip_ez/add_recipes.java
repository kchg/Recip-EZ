package com.stringcheese.recipez.recip_ez;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class add_recipes extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public myDBHandler dbHandler;
    public DataSource dataSource;

    AddIngredientFragment ingredientFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipes);

        //set custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enter Recipe");

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //set tab view
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        //initialize database stuff
        dbHandler = new myDBHandler(this);
        dataSource = new DataSource(this);
        dataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_recipes, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.submitbutton:
                submitRecipe();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    //method that is called when user clicks submit recipe; package everything together and insert into database
    public void submitRecipe(){
        String recipe_name;
        Integer servings;
        String description;
        String directions;
        Recipe r = new Recipe();

        EditText editText = (EditText)findViewById(R.id.recipe_name);
        recipe_name = editText.getText().toString().trim();

        editText = (EditText)findViewById(R.id.recipe_servings);
        servings = Integer.parseInt(editText.getText().toString());

        editText = (EditText)findViewById(R.id.recipe_description);
        description = editText.getText().toString().trim();

        editText = (EditText)findViewById(R.id.recipe_directions);
        directions = editText.getText().toString().trim();

        r.set_recipename(recipe_name);
        r.set_servings(servings);
        r.set_description(description);
        r.set_directions(directions);

        Log.d("var", recipe_name);

        //TODO: retrieve list of ingredients from ui (should include ids) into a list, and place it into r


        long recipe_id = dataSource.addRecipe(r);
        ingredientFragment.addRecipeIngredients(recipe_id);

        try{
            wait(100);
        }
        catch(Exception e){
        }
        this.finish();

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //switches the tab fragment
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return AddRecipeFragment.newInstance();
                case 1:
                    ingredientFragment = AddIngredientFragment.newInstance();
                    return ingredientFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // show 2 pages: one for recipe details and one to add ingredients
            return 2;
        }

        //switches the tab title
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RECIPE";
                case 1:
                    return "INGREDIENTS";
            }
            return null;
        }
    }


}
