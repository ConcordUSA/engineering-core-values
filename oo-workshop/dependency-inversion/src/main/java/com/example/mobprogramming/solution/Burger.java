package com.example.mobprogramming.solution;

import java.util.List;

public class Burger implements Food{
    private List<BurgerIngredient> ingredients;

    public Burger(List<BurgerIngredient> ingredients){
        this.ingredients = ingredients;
    }

    public void makeFood(){
        makeBurger();
    }

    public void makeBurger(){
      ingredients.forEach(BurgerIngredient::getIngredients);
    }
}
