package com.stringcheese.recipez.recip_ez;

/**
 * Created by Kevin on 3/1/2016.
 */
public class Ingredient {
    private int _id;
    private String _ingredientname;

    public Ingredient(){}

    public Ingredient(int id, String ingredientname){
        this._id = id;
        this._ingredientname = ingredientname;
    }

    public Ingredient(String ingredientname){
        this._ingredientname = ingredientname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_ingredientname(String _ingredientname) {
        this._ingredientname = _ingredientname;
    }

    public int get_id() {
        return _id;
    }

    public String get_ingredientname() {
        return _ingredientname;
    }
}
