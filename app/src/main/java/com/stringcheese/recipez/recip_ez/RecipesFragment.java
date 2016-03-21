package com.stringcheese.recipez.recip_ez;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements View.OnClickListener {


    public RecipesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipes, container, false);
        Button addrecipe = (Button) v.findViewById(R.id.add_recipe);
        addrecipe.setOnClickListener(this);
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_recipe:
                Toast.makeText(getActivity(), "button clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), add_recipes.class);
                startActivity(intent);
                break;
        }
    }
}
