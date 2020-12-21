package com.example.interfacesegregation.slides.withoutISP;

public class ComboOrderImpl implements OrderInterface {
    @Override
    public void orderBurger(int quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void orderFries(int fries) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void orderCombo(int quantity, int fries) {
        System.out.println("Received order of "+quantity+" burgers and "+ fries+" fries");
    }
}
