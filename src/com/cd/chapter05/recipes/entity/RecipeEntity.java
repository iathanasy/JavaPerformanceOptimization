package com.cd.chapter05.recipes.entity;

import java.util.List;
import com.alibaba.fastjson.JSON;

public class RecipeEntity {

    private  String id;

    private  Recipe recipe;

    private  List<CookStep> cookSteps;

    public RecipeEntity() {
    }

    public RecipeEntity(String id, Recipe recipe, List<CookStep> cookSteps) {
        this.id = id;
        this.recipe = recipe;
        this.cookSteps = cookSteps;
    }

    public RecipeEntity(Recipe recipe, List<CookStep> cookSteps) {
        this((JSON.toJSONString(recipe)), recipe, cookSteps);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<CookStep> getCookSteps() {
        return cookSteps;
    }

    public void setCookSteps(List<CookStep> cookSteps) {
        this.cookSteps = cookSteps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
