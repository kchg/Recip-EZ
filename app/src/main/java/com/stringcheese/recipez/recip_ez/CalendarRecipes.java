package com.stringcheese.recipez.recip_ez;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by alex on 4/12/16.
 */
public class CalendarRecipes {
    public static HashMap<GregorianCalendar, Vector<Vector<String>>> recipes;

    public CalendarRecipes() {
        recipes = new HashMap<>();
    }
}
