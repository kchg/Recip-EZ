package com.stringcheese.recipez.recip_ez;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyani on 4/12/2016.
 */

public class meals_display extends AppCompatActivity {

    Button btnClosePopup;
    Button btnBf, btnLunch, btnDin;
    Recipe selectedItem;
    String meals;

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
        final ListAdapter adapter;
        adapter = new ArrayAdapter<Recipe>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (Recipe) adapter.getItem(position);
                initiatePopupWindow();
            }
        });

        /*
        Bundle bundle = new Bundle();
        bundle.putString("message", meals);
        // set Fragmentclass Arguments
        CalendarFragment fragobj = new CalendarFragment();
        fragobj.setArguments(bundle);
        */


    }
    private PopupWindow pwindo;

    private void initiatePopupWindow() {
        try {
            // We need to get the instance of the LayoutInflater
            LayoutInflater inflater = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_screen_popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            pwindo = new PopupWindow(layout, 600, 600, true);
            pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);

            btnBf = (Button) layout.findViewById(R.id.breakfastButton);
            btnBf.setOnClickListener(breakfast_button_click_listener);

            btnLunch = (Button) layout.findViewById(R.id.lunchButton);
            btnLunch.setOnClickListener(lunch_button_click_listener);

            btnDin = (Button) layout.findViewById(R.id.dinnerButton);
            btnDin.setOnClickListener(dinner_button_click_listener);

            btnClosePopup = (Button) layout.findViewById(R.id.btn_close_popup);
            btnClosePopup.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private View.OnClickListener breakfast_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            String print = selectedItem.get_recipename();
            CalendarRecipes.mealNames.add("b");
            CalendarRecipes.mealNames.add(print);

            Toast.makeText(getApplicationContext(),"You have "+print+" for breakfast.",Toast.LENGTH_SHORT).show();
            pwindo.dismiss();
        }
    };

    private View.OnClickListener lunch_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            String print = selectedItem.get_recipename();
            CalendarRecipes.mealNames.add("l");
            CalendarRecipes.mealNames.add(print);

            Toast.makeText(getApplicationContext(),"You have "+print+" for lunch.",Toast.LENGTH_SHORT).show();
            pwindo.dismiss();
        }
    };

    private View.OnClickListener dinner_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            String print = selectedItem.get_recipename();
            CalendarRecipes.mealNames.add("d");
            CalendarRecipes.mealNames.add(print);

            Toast.makeText(getApplicationContext(),"You have "+print+" for dinner.",Toast.LENGTH_SHORT).show();
            pwindo.dismiss();
        }
    };

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),meals,Toast.LENGTH_SHORT).show();
            pwindo.dismiss();
        }
    };

}
