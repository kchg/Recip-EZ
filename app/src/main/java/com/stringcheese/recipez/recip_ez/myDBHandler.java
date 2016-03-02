package com.stringcheese.recipez.recip_ez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String DATABASE_NAME = "recipez.db";

    private static final String COLUMN_ID = "_id";

    private static final String INGREDIENTS_TABLE = "ingredients";
    private static final String COLUMN_INGREDIENT_ID = "ingredient_id";
    private static final String COLUMN_INGREDIENT_NAME = "ingredient_name";
    private static final String COLUMN_INGREDIENT_DESCRIPTION = "ingredient_description";


    private static final String RECIPES_TABLE = "recipes";
    private static final String COLUMN_RECIPE_ID = "recipe_id";
    private static final String COLUMN_RECIPE_NAME = "recipe_name";
    private static final String COLUMN_RECIPE_DESCRIPTION = "recipe_description";

    private static final String RECIPE_INGREDIENTS_TABLE = "recipe_ingredients";
    private static final String COLUMN_RECIPE_INGREDIENTS_AMOUNT = "amount";
    private static final String COLUMN_RECIPE_INGREDIENTS_AMOUNT_MODIFIER = "amount_modifier";
    //uses COLUMN_RECIPE_ID and COLUMN_INGREDIENT_ID


    private static final String CREATE_INGREDIENTS_TABLE = "CREATE table IF NOT EXISTS " + INGREDIENTS_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_INGREDIENT_NAME + " TEXT );";
    private static final String CREATE_RECIPES_TABLE = "CREATE TABLE IF NOT EXISTS " + RECIPES_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RECIPE_NAME + " TEXT, " + COLUMN_RECIPE_DESCRIPTION + " TEXT );";
    private static final String CREATE_RECIPE_INGREDIENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + RECIPE_INGREDIENTS_TABLE + "(" + COLUMN_RECIPE_ID + " INTEGER NOT NULL, " + COLUMN_INGREDIENT_ID + " INTEGER NOT NULL, " + COLUMN_RECIPE_INGREDIENTS_AMOUNT  + " INTEGER, " + COLUMN_RECIPE_INGREDIENTS_AMOUNT_MODIFIER + " TEXT );";

    public myDBHandler(Context context, String name, int version){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INGREDIENTS_TABLE);
        db.execSQL(CREATE_RECIPES_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);
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
        db.close();
    }

    //TODO: right now this just deletes everything
    public void deleteIngredient(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + INGREDIENTS_TABLE  + ";");
    }

    //add a recipe with its ingredients
    public void addRecipe(Recipe r){
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECIPE_NAME, r.get_recipename());
        values.put(COLUMN_RECIPE_DESCRIPTION, r.get_description());
        //TODO: handle arraylist of ingredients
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
        db.close();
        return dbString;
    }
}
