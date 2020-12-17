package com.example.mobprogramming.solution;

import java.util.List;

public class FoodOrder {
    private List<Food> food;

    public FoodOrder(List<Food> food){
        this.food = food;
    }

    public void make(){
        food.forEach(Food::makeFood);
    }
}
