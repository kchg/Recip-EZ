package com.stringcheese.recipez.recip_ez;

/**
 * Created by Kevin on 3/1/2016.
 */
public class Ingredient {
    private int _id;
    private String _ingredientname;
    private int _recipe_id;
    private int _amount;
    private int _amount_modifier;


    public Ingredient(){}

    public Ingredient(int id, String ingredientname){
        this._id = id;
        this._ingredientname = ingredientname;
    }

    //recipe_ingredients constructor
    public Ingredient(int recipe_id, int ingredient_id, int amount, int amount_modifier){
        this._id = ingredient_id;
        this._recipe_id = recipe_id;
        this._amount = amount;
        this._amount_modifier = amount_modifier;
    }

    public Ingredient(String ingredientname){
        this._ingredientname = ingredientname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return this._amount + " ounces " + this._ingredientname;
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

    public int get_amount() {
        return _amount;
    }

    public void set_amount(int _amount) {
        this._amount = _amount;
    }

    public int get_amount_modifier() {
        return _amount_modifier;
    }

    public void set_amount_modifier(int _amount_modifier) {
        this._amount_modifier = _amount_modifier;
    }

    public int get_recipe_id() {
        return _recipe_id;
    }

    public void set_recipe_id(int _recipe_id) {
        this._recipe_id = _recipe_id;
    }
}
