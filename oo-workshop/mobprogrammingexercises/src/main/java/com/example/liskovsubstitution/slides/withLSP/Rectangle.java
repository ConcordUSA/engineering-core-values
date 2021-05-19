package com.example.liskovsubstitution.slides.withLSP;

public class Rectangle extends Shape{

    public int height;
    public int width;

    @Override
    public int Area() {
        return height * width;
    }
}
