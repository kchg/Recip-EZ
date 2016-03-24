package com.stringcheese.recipez.recip_ez;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Kevin on 3/24/2016.
 */
public class DataSource {
    private SQLiteDatabase db;
    private myDBHandler dbHelper;
    private String[] recipeColumns = { dbHelper.COLUMN_RECIPE_NAME,
            dbHelper.COLUMN_RECIPE_DESCRIPTION, dbHelper.COLUMN_RECIPE_DIRECTIONS };
    //TODO: ingredients

    public DataSource(Context context) {
        dbHelper = new myDBHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //add an ingredient to ingredients table
    public void addIngredient(Ingredient i){
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_INGREDIENT_NAME, i.get_ingredientname());
        db.insert(dbHelper.INGREDIENTS_TABLE, null, values);
    }

    //TODO: right now this just deletes everything
    public void deleteIngredient(){
        db.execSQL("DELETE FROM " + dbHelper.INGREDIENTS_TABLE  + ";");
    }

    //add a recipe with its ingredients
    public void addRecipe(Recipe r){
        long recipe_id;
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ContentValues values = new ContentValues();

        values.put(dbHelper.COLUMN_RECIPE_NAME, r.get_recipename());
        values.put(dbHelper.COLUMN_RECIPE_SERVINGS, r.get_servings());
        values.put(dbHelper.COLUMN_RECIPE_DESCRIPTION, r.get_description());
        values.put(dbHelper.COLUMN_RECIPE_DIRECTIONS, r.get_directions());

        //clean input
        recipe_id = db.insert(dbHelper.RECIPES_TABLE, null, values); //this should return the autoincremented recipe id

        ingredients = r.get_ingredients(); //should hold ingredient objects; each including their own ids

        for(Ingredient ingredient:ingredients){
            //TODO: add each combination of recipe_id and ingreient_id into the recipe_ingredients table
            //TODO: retrieve ingredient_id from object
        }

        //update the listview

    }




    public String ingredientsToString(){
        String dbString = "";
        String query = "SELECT * FROM " + dbHelper.INGREDIENTS_TABLE + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(dbHelper.COLUMN_INGREDIENT_NAME)) != null){
                dbString += c.getString(c.getColumnIndex(dbHelper.COLUMN_INGREDIENT_NAME));
                dbString += "\n";
            }
            c.moveToNext();
        }
        return dbString;
    }

    public List<Recipe> getAllRecipes(){
        List<Recipe> recipes = new ArrayList<Recipe>();

        Cursor cursor = db.query(myDBHandler.RECIPES_TABLE, recipeColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Recipe r = new Recipe();
            r.set_id(cursor.getLong(0));
            r.set_recipename(cursor.getString(1));
            r.set_servings(cursor.getInt(2));
           // r.set_description(cursor.getString(3));
           // r.set_directions(cursor.getString(4));
            recipes.add(r);
            cursor.moveToNext();
        }
        cursor.close();
        return recipes;

    }
}
