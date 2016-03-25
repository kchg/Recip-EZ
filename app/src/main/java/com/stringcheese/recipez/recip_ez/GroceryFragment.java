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


/**
 * A simple {@link Fragment} subclass.
 */
public class GroceryFragment extends Fragment {
    ArrayList<Ingredient>ingredients;
    private ArrayAdapter<Ingredient>adapter;
    private DataSource dataSource;


    public GroceryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocery, container, false);
        ListView lv = (ListView) v.findViewById(R.id.ingredients_list);
        ingredients = new ArrayList<Ingredient>();
        dataSource = new DataSource(getContext());
        dataSource.open();
        populate();
        adapter = new ArrayAdapter<Ingredient>(getContext(), android.R.layout.simple_list_item_1, ingredients);
        lv.setAdapter(adapter);


        // Inflate the layout for this fragment
        return v;

    }

    //populate the listview
    public void populate(){
        ingredients = dataSource.ingredientsToList();
    }
}
