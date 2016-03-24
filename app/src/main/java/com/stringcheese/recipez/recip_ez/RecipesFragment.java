package com.stringcheese.recipez.recip_ez;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements View.OnClickListener {
    private DataSource dataSource;
    private ArrayAdapter<Recipe> adapter;

    public RecipesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipes, container, false);
        Button addrecipe = (Button) v.findViewById(R.id.add_recipe);
        addrecipe.setOnClickListener(this);

        //used to get data from db
        dataSource = new DataSource(getContext());
        dataSource.open();

        List<Recipe> values = dataSource.getAllRecipes();

        //set recipe list adapter
        ListView listView = (ListView)v.findViewById(R.id.recipe_list);
        adapter = new ArrayAdapter<Recipe>(getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_recipe:
                Intent intent = new Intent(getActivity(), add_recipes.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }
}
