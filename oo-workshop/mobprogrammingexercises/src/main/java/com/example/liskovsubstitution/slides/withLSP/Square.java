package com.example.liskovsubstitution.slides.withLSP;

public class Square extends Shape{

    public int side;

    @Override
    public int Area() {
        return side * side;
    }
}
