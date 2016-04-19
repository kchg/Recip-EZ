package com.stringcheese.recipez.recip_ez;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 4/12/16.
 */
public class MealData {
    List<String> breakfastItems;
    List<String> lunchItems;
    List<String> dinnerItems;

    public MealData() {
        breakfastItems = new ArrayList<String>();
        lunchItems = new ArrayList<String>();
        dinnerItems = new ArrayList<String>();
    }

    List<String> getBreakfastItems() {
        return breakfastItems;
    }

    List<String> getLunchItems() {
        return lunchItems;
    }

    List<String> getDinnerItems() {
        return dinnerItems;
    }

    void setBreakfastItems(String b) {
        breakfastItems.add(b);
    }

    void setLunchItems(String l) {
        lunchItems.add(l);
    }

    void setDinnerItems(String d) {
        dinnerItems.add(d);
    }

    List<String> mergeLists() {
        List<String> mergedList = new ArrayList<>(breakfastItems);
        mergedList.addAll(lunchItems);
        mergedList.addAll(dinnerItems);

        return mergedList;
    }
}
