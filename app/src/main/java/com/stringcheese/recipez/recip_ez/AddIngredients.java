package com.stringcheese.recipez.recip_ez;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddIngredients extends AppCompatActivity {

    public TextView myText;
    public myDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myText = (TextView) findViewById(R.id.testtext);
        dbHandler = new myDBHandler(this, null, 1);
        printDB();

    }

    public void addButtonClicked(View view){
        Ingredient ingredient = new Ingredient("Cheese");
        dbHandler.addIngredient(ingredient);
        printDB();
    }

    public void deleteButtonClicked(View view){
        dbHandler.deleteIngredient();
        printDB();
    }

    public void printDB(){
        String dbString = dbHandler.ingredientsToString();
        myText.setText(dbString);
    }
}
