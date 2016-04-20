package com.stringcheese.recipez.recip_ez;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alex on 4/12/16.
 */
public class CalendarRecipes {
    public static HashMap<GregorianCalendar, MealData> recipes;
    public static List<String> mealNames;
    public static int addedFlag;

    public CalendarRecipes() {
        mealNames = new ArrayList<>();
        recipes = new HashMap<>();
        addedFlag = 0;
    }
}
