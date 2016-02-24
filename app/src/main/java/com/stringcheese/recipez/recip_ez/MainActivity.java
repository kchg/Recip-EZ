package com.stringcheese.recipez.recip_ez;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //navigation drawer
        mDrawerList = (ListView) findViewById(R.id.navList);
        String[] osArray = { "Calendar", "Recipes", "Shopping List", "Settings", "Help" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        Intent newActivity = new Intent(MainActivity.this, AddRecipes.class);
                        startActivity(newActivity);
                        break;
                    case 1:
                        Intent listActivity = new Intent(MainActivity.this, AddRecipes.class);
                        startActivity(listActivity);
                        break;
                    case 2:
                        Intent settingActivity = new Intent(MainActivity.this, AddRecipes.class);
                        startActivity(settingActivity);
                        break;
                    case 3:
                        Intent helpActivity = new Intent(MainActivity.this, AddRecipes.class);
                        startActivity(helpActivity);
                        break;
                }
            }
        });

    }

    public void addDrawerItems(){
        String[] osArray = { "Calendar", "Recipes", "Shopping List", "Settings", "Help" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
