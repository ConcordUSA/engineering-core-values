package com.example.liskovsubstitution.slides.withoutLSP;

public class ObjectFactory {

    private static Rectangle getNewRectangle() {
        return new Square();
    }

    public static void main(String args[]) {

        Rectangle r = ObjectFactory.getNewRectangle();

        r.setWidth(5);
        r.setHeight(10);
        //User knows that r is a rectangle and assumes that they'll
        //be able to set the width and height like they would for the base class

        System.out.println(r.getArea());
        //The user is surprised to see that the area is 100 instead of 50
    }
}
