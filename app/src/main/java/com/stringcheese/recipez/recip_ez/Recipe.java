package com.stringcheese.recipez.recip_ez;

import java.util.ArrayList;

/**
 * Created by Kevin on 3/1/2016.
 */
public class Recipe {
    private int _id;
    private String _recipename;
    private String _description;
    private String _directions;

    ArrayList ingredients = new ArrayList();

    public Recipe() {
    }

    public Recipe(String _recipename, String _description, ArrayList ingredients) {
        this._recipename = _recipename;
        this._description = _description;
        this.ingredients = ingredients;
    }

    public Recipe(int _id, String _recipename, String _description, ArrayList ingredients, String _directions) {
        this._id = _id;
        this._recipename = _recipename;
        this._description = _description;
        this.ingredients = ingredients;
        this._directions = _directions;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public ArrayList getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList ingredients) {
        this.ingredients = ingredients;
    }
}
