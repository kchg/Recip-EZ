package com.stringcheese.recipez.recip_ez;


import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class GroceryFragment extends Fragment {
    //TODO: show a compiled grocery list, with all of the amounts added together and functionality to cross out items

    //adapter you can use to fill the listview; use recyclerview if you want it to look better

    //use to retrieve data from database
    List<String> ingredientInfo;
    private DataSource dataSource;



    public GroceryFragment() {

    }

    private List<Ingredient> masterList = new ArrayList<Ingredient>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grocery, container, false);

        ListView lv = (ListView) v.findViewById(R.id.ingredients_list);

        ListAdapter adapter;
        dataSource = new DataSource(getContext());
        dataSource.open(); //opens the database
        myDBHandler dbHelper = new myDBHandler(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> bItems;
        List<String> lItems;
        List<String> dItems;

        Collection <MealData> al = CalendarRecipes.recipes.values();

        //Chris: This does not work, the code inside this loop never gets run
        for (MealData meal : CalendarRecipes.recipes.values()) {
            bItems = null;
            bItems = meal.getBreakfastItems();
            for (String breakfastItem : bItems) {
                Log.d("DR", breakfastItem);
                long tempid = 0;
                Cursor ci = db.rawQuery("SELECT * FROM recipes WHERE recipe_name = \"" + breakfastItem + "\";", null);
                ci.moveToFirst();


                if(ci != null && ci.moveToFirst()){
                    Log.d("D/DR", "print test");
                    tempid = ci.getLong(ci.getColumnIndex(myDBHandler.COLUMN_ID));
                    Log.d("Z", Long.toString(tempid));
                    Log.d("DR", ci.getString(ci.getColumnIndex(myDBHandler.COLUMN_RECIPE_NAME)));
                }
                Cursor c = db.rawQuery("SELECT * FROM recipe_ingredients WHERE recipe_id = " + tempid + ";", null);

                if(c != null && c.moveToFirst()) {
                    masterList.addAll(dataSource.getRecipeIngredients(c.getString(c.getColumnIndex(myDBHandler.COLUMN_RECIPE_ID))));
                }
                ci.close();
                c.close();
            }

            lItems = null;

            lItems = meal.getLunchItems();
            for(String lunchItem : lItems){
                int tempid = 0;
                Log.d("DR", lunchItem);
                Cursor ci = db.rawQuery("SELECT * FROM recipes WHERE recipe_name = \"" + lunchItem + "\";", null);
                if(ci != null && ci.moveToFirst()){
                    tempid = Integer.parseInt(ci.getString(ci.getColumnIndex(myDBHandler.COLUMN_ID)));
                }
                Cursor c = db.rawQuery("SELECT * FROM recipe_ingredients WHERE recipe_id = " + tempid + ";", null);
                if(c != null && c.moveToFirst()) {
                    masterList.addAll(dataSource.getRecipeIngredients(c.getString(c.getColumnIndex(myDBHandler.COLUMN_RECIPE_ID))));
                }
                ci.close();
                c.close();
            }

            dItems = null;
            dItems = meal.getDinnerItems();
            for(String dinnerItem : dItems){
                int tempid = 0;
                Log.d("DR", dinnerItem);
                Cursor ci = db.rawQuery("SELECT * FROM recipes WHERE recipe_name = \"" + dinnerItem + "\";", null);
                if(ci != null && ci.moveToFirst()){
                    tempid = Integer.parseInt(ci.getString(ci.getColumnIndex(myDBHandler.COLUMN_ID)));
                }
                Cursor c = db.rawQuery("SELECT * FROM recipe_ingredients WHERE recipe_id = " + tempid + ";", null);
                c.moveToFirst();
                while(!c.isAfterLast()){
                    masterList.addAll(dataSource.getRecipeIngredients(c.getString(c.getColumnIndex(myDBHandler.COLUMN_RECIPE_ID))));
                }

                ci.close();
                c.close();
            }

        }



        ingredientInfo = new ArrayList<>();
        for (Ingredient elem : masterList) {
            Log.d("Z", elem.get_ingredientname());
            StringBuilder ingredients = new StringBuilder();
            ingredients.append(elem.get_ingredientname());
            ingredients.append(" ");
            ingredients.append(elem.get_amount());
            ingredients.append(" ");
            ingredients.append(elem.get_amount_modifier());

            ingredientInfo.add(ingredients.toString());
        }
        //the recipe_ingredients table is set up with recipe_id, ingredient_id, amount, and amount modifier
        //amount modifier is one of {"oz", "cup", "gram", "tsp", "tbsp", "mL", "count"}, you'll probably have to do conversion between these to add them together
        //join with the ingredients table to get the ingredient name
        //lv.setAdapter(adapter);

        //this should work if there is something in masterList, it works for Kevin - we do not need multiple listviews
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ingredientInfo);
        lv.setAdapter(adapter);

        return v;

    }

}
