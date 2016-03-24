package com.stringcheese.recipez.recip_ez;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/1/2016.
 */
public class Recipe {
    private long _id;
    private String _recipename;
    private String _description;
    private String _directions;
    private int _servings;

    ArrayList _ingredients = new ArrayList();

    public Recipe() {
    }

    public Recipe(String _recipename, String _description, ArrayList ingredients) {
        this._recipename = _recipename;
        this._description = _description;
        this._ingredients = ingredients;
    }

    public Recipe(String _recipename, Integer _servings, String _description, String _directions, ArrayList _ingredients) {
        this._recipename = _recipename;
        this._servings = _servings;
        this._description = _description;
        this._ingredients = _ingredients;
        this._directions = _directions;
    }

    //what to display in recipe list
    @Override
    public String toString() {
        return this._recipename;
    }

    public long get_id() {
        return _id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_recipename() {
        return _recipename;
    }
    public void set_recipename(String _recipename) {
        this._recipename = _recipename;
    }

    public String get_description() {
        return _description;
    }
    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_directions() {
        return _directions;
    }
    public void set_directions(String _directions) {
        this._directions = _directions;
    }

    public int get_servings() {
        return _servings;
    }
    public void set_servings(int _servings) {
        this._servings = _servings;
    }

    public ArrayList get_ingredients() {
        return _ingredients;
    }
    public void set_ingredients(ArrayList ingredients) {
        this._ingredients = ingredients;
    }
}
