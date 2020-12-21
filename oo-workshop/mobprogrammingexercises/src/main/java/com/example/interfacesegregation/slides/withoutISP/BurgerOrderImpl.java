package com.example.interfacesegregation.slides.withoutISP;

public class BurgerOrderImpl implements OrderInterface {
    @Override
    public void orderBurger(int quantity) {
        System.out.println("Received order of "+quantity+" burgers");
    }

    @Override
    public void orderFries(int fries) {
        throw new UnsupportedOperationException("No fries in burger only order");
    }

    @Override
    public void orderCombo(int quantity, int fries) {
        throw new UnsupportedOperationException("No combo in burger only order");
    }
}



