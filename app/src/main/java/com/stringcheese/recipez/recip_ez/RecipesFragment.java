package com.stringcheese.recipez.recip_ez;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements View.OnClickListener {
    public DataSource dataSource;
    private ArrayAdapter<Recipe> adapter;

    public RecipesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipes, container, false);

        FloatingActionButton addrecipebutton = (FloatingActionButton) v.findViewById(R.id.fab_add);
        addrecipebutton.setOnClickListener(this);

        //used to get data from db
        dataSource = new DataSource(getContext());
        dataSource.open();

        List<Recipe> values = dataSource.getAllRecipes();
        List<String> names = new ArrayList<String>();
        for(Recipe r: values){
            Log.d("TAG", "item");
            Log.d("TAG", r.toString());
        }

        //set recipe list adapter
        ListView listView = (ListView)v.findViewById(R.id.recipe_list);
        adapter = new ArrayAdapter<Recipe>(getContext(), android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe selectedItem = (Recipe) adapter.getItem(position);
                Intent i = new Intent(getActivity(), RecipeDetails.class);
                i.putExtra("recipe_name",selectedItem.get_recipename());
                startActivity(i);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_add:
                Intent intent = new Intent(getActivity(), add_recipes.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Log.d("RESUME", "resumed");
        try{
            wait(100);
        }
        catch(Exception e){
        }
        updateList();
    }

    public void updateList(){

        adapter.notifyDataSetChanged();
    }
}
