package com.example.mobprogramming.solution;

import java.util.List;

public class Milkshake implements Food {
    private List<MilkshakeIngredient> milkshakeIngredients;

    public Milkshake(List<MilkshakeIngredient> milkshakeIngredients){
        this.milkshakeIngredients = milkshakeIngredients;
    }

    public void makeFood(){
        makeMilkshake();
    }

    public void makeMilkshake(){
        milkshakeIngredients.forEach(MilkshakeIngredient::getIngredients);
    }
}
