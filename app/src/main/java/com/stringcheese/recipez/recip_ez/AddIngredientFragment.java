package com.stringcheese.recipez.recip_ez;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIngredientFragment extends Fragment implements View.OnClickListener{


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
/*
        Button next = (Button) rootView.findViewById(R.id.add_recipe);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), add_recipes.class);
                startActivity(i);
            }
        });

*/
        myText = (TextView) rootView.findViewById(R.id.testtext);
        Button add = (Button) rootView.findViewById(R.id.addbutton);
        Button delete = (Button) rootView.findViewById(R.id.deletebutton);
        Button addIngredient = (Button) rootView.findViewById(R.id.add_ingredient);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        addIngredient.setOnClickListener(this);
        //dbHandler = new myDBHandler(getActivity());
        dataSource = new DataSource(getContext());
        dataSource.open();
        printDB();
        return rootView;
    }

    public void addButtonClicked(View view){
        Ingredient ingredient = new Ingredient("Cheese");
        dataSource.addIngredient(ingredient);
        printDB();
    }

    public void deleteButtonClicked(View view){
        dataSource.deleteIngredient();
        printDB();
    }

    public void printDB(){
        String dbString = dataSource.ingredientsToString();
        myText.setText(dbString);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.addbutton:
                addButtonClicked(v);
                break;
            case R.id.deletebutton:
                deleteButtonClicked(v);
                break;
            case R.id.add_ingredient:
                Intent intent = new Intent(getActivity(), add_ingredient_helper.class);
                startActivity(intent);
                break;
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

}
