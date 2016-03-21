package com.stringcheese.recipez.recip_ez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AddRecipeFragment extends Fragment {

    public AddRecipeFragment() {
        // Required empty public constructor
    }

    public static AddRecipeFragment newInstance() {
        Bundle args = new Bundle();
        AddRecipeFragment fragment = new AddRecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_recipes, container, false);

        return rootView;
    }

}
