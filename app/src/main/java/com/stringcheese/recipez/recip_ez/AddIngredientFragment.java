package com.stringcheese.recipez.recip_ez;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIngredientFragment extends Fragment implements View.OnClickListener{
    ArrayList<Ingredient>ingredients;
    private ArrayAdapter<Ingredient> adapter;
    Recipe recipe = new Recipe();


    public AddIngredientFragment() {
        // Required empty public constructor
    }

    public static AddIngredientFragment newInstance() {
        Bundle args = new Bundle();
        AddIngredientFragment fragment = new AddIngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TextView myText;
    public myDBHandler dbHandler;
    public DataSource dataSource;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_ingredient, container, false);

        //Spinner
        Spinner modifier = (Spinner) rootView.findViewById(R.id.modifer_spinner);
        String[] types = new String[]{"oz", "cup", "gram", "tsp", "tbsp", "mL", "count"};
        ArrayAdapter<String> modadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, types);
        modifier.setAdapter(modadapter);

        //Button addIngredient = (Button) rootView.findViewById(R.id.add_ingredient);
        Button addIngredient = (Button) rootView.findViewById(R.id.add_button);
        addIngredient.setOnClickListener(this);

        ingredients = new ArrayList<Ingredient>();
        //addIngredient.setOnClickListener(this);
        //dbHandler = new myDBHandler(getActivity());
        dataSource = new DataSource(getContext());
        dataSource.open();


        ListView listView = (ListView)rootView.findViewById(R.id.ingredient_list);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                ingredients.remove(pos);
                Log.v("long clicked","pos: " + pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });



        return rootView;
    }

    //button clicked to add the ingredient
    public void addButtonClicked(View view){
        //retrieve from textfield
        EditText textfield = (EditText) getView().findViewById(R.id.input_field);
        EditText amountfield = (EditText) getView().findViewById(R.id.amount_field);
        Spinner spinner = (Spinner) getView().findViewById(R.id.modifer_spinner);

        String text = "";
        String modifier = "";
        float amount = 0;
        try{
            text = textfield.getText().toString();
            modifier = spinner.getSelectedItem().toString();
            amount = Float.parseFloat(amountfield.getText().toString());
            Log.d("ITEXT", text);
        }
        catch(Exception e){
            Log.d("ITEXT", "caught an exception");
            Log.d("ITEXT", e.toString());
        }
        Ingredient i = new Ingredient(text);
        i.set_amount(amount);
        i.set_amount_modifier(modifier);
        ingredients.add(i);
        refreshList();
    }

    //TODO: add method for deleting ingredient
    public void deleteButtonClicked(View view){

    }

    public void printDB(){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_button:
                addButtonClicked(v);
                break;
/*
            case R.id.add_ingredient:
                Intent intent = new Intent(getActivity(), add_ingredient_helper.class);
                startActivity(intent);
                break;
     */
        }
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

    public void refreshList(){
        ListView listView = (ListView)getView().findViewById(R.id.ingredient_list);
        adapter = new ArrayAdapter<Ingredient>(getContext(), android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(adapter);
    }

    public void addRecipeIngredients(long recipe_id){
        //pass in list of ingredient objects
        dataSource.addRecipeIngredients(recipe_id, ingredients);
    }
}
