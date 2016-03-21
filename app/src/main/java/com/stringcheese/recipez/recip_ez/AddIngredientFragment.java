package com.stringcheese.recipez.recip_ez;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddIngredientFragment extends Fragment {


    public AddIngredientFragment() {
        // Required empty public constructor
    }

    public static AddIngredientFragment newInstance() {
        Bundle args = new Bundle();
        AddIngredientFragment fragment = new AddIngredientFragment();
        fragment.setArguments(args);
        return fragment;
    }


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
        return rootView;
    }

}
