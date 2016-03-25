package com.stringcheese.recipez.recip_ez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*
Format of database
ingredients table: ingredient_id(PK), ingredient_name
recipes table: recipe_id(PK), recipe_name, description
recipe_ingredients table: recipe_id, ingredient_id, amount, amount modifier (cups, ounces ,grams ,etc).
*/

/**
 * Created by Kevin on 3/1/2016.
 */
public class myDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recipez.db";

    public static final String COLUMN_ID = "_id";

    public static final String INGREDIENTS_TABLE = "ingredients";
    public static final String COLUMN_INGREDIENT_ID = "ingredient_id";
    public static final String COLUMN_INGREDIENT_NAME = "ingredient_name";


    public static final String RECIPES_TABLE = "recipes";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_RECIPE_NAME = "recipe_name";
    public static final String COLUMN_RECIPE_SERVINGS = "recipe_servings";
    public static final String COLUMN_RECIPE_DESCRIPTION = "recipe_description";
    public static final String COLUMN_RECIPE_DIRECTIONS = "recipe_directions";

    public static final String RECIPE_INGREDIENTS_TABLE = "recipe_ingredients";
    public static final String COLUMN_RECIPE_INGREDIENTS_AMOUNT = "amount";
    public static final String COLUMN_RECIPE_INGREDIENTS_AMOUNT_MODIFIER = "amount_modifier";
    //uses COLUMN_RECIPE_ID and COLUMN_INGREDIENT_ID


    public static final String CREATE_INGREDIENTS_TABLE = "CREATE table IF NOT EXISTS " + INGREDIENTS_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INGREDIENT_NAME + " TEXT );";
    public static final String CREATE_RECIPES_TABLE = "CREATE TABLE IF NOT EXISTS " + RECIPES_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPE_NAME + " TEXT, " + COLUMN_RECIPE_SERVINGS + " INTEGER, " + COLUMN_RECIPE_DESCRIPTION + " TEXT, " + COLUMN_RECIPE_DIRECTIONS + " TEXT );";
    public static final String CREATE_RECIPE_INGREDIENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + RECIPE_INGREDIENTS_TABLE + "(" + COLUMN_RECIPE_ID + " INTEGER NOT NULL, " + COLUMN_INGREDIENT_ID + " INTEGER NOT NULL, " + COLUMN_RECIPE_INGREDIENTS_AMOUNT  + " INTEGER, " + COLUMN_RECIPE_INGREDIENTS_AMOUNT_MODIFIER + " TEXT );";

    public myDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INGREDIENTS_TABLE);
        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);
        Log.v("TAG", "Created tables");
        Log.v("TAG", CREATE_RECIPES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + INGREDIENTS_TABLE);
        onCreate(db);
    }



    ////////////////////////////////////////////DML//////////////////////////////////////////////

    //add an ingredient to ingredients table
    public void addIngredient(Ingredient i){
        ContentValues values = new ContentValues();
        values.put(COLUMN_INGREDIENT_NAME, i.get_ingredientname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(INGREDIENTS_TABLE, null, values);
    }

    //TODO: right now this just deletes everything
    public void deleteIngredient(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + INGREDIENTS_TABLE  + ";");
    }

    //add a recipe with its ingredients
    public void addRecipe(Recipe r){
        long recipe_id;
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECIPE_NAME, r.get_recipename());
        values.put(COLUMN_RECIPE_SERVINGS, r.get_servings());
        values.put(COLUMN_RECIPE_DESCRIPTION, r.get_description());
        values.put(COLUMN_RECIPE_DIRECTIONS, r.get_directions());
        recipe_id = db.insert(RECIPES_TABLE, null, values); //this should return the autoincremented recipe id

        ingredients = r.get_ingredients(); //should hold ingredient objects; each including their own ids

        for(Ingredient ingredient:ingredients){
            //TODO: add each combination of recipe_id and ingreient_id into the recipe_ingredients table
            //TODO: retrieve ingredient_id from object
        }
    }




    public String ingredientsToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + INGREDIENTS_TABLE + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_INGREDIENT_NAME)) != null){
                dbString += c.getString(c.getColumnIndex(COLUMN_INGREDIENT_NAME));
                dbString += "\n";
            }
            c.moveToNext();
        }
        return dbString;
    }
}
