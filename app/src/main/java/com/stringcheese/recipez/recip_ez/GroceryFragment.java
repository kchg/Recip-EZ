package com.stringcheese.recipez.recip_ez;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GroceryFragment extends Fragment {
    //TODO: show a compiled grocery list, with all of the amounts added together and functionality to cross out items

    //adapter you can use to fill the listview; use recyclerview if you want it to look better
    private ArrayAdapter<Ingredient>adapter;

    //use to retrieve data from database
    private DataSource dataSource;

    public GroceryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocery, container, false);

        ListView lv = (ListView) v.findViewById(R.id.ingredients_list);
        dataSource = new DataSource(getContext());
        dataSource.open(); //opens the database

        //the recipe_ingredients table is set up with recipe_id, ingredient_id, amount, and amount modifier
        //amount modifier is one of {"oz", "cup", "gram", "tsp", "tbsp", "mL", "count"}, you'll probably have to do conversion between these to add them together
        //join with the ingredients table to get the ingredient name

        //lv.setAdapter(adapter);

        return v;

    }

}
