package com.concordusa.unittestdemo.service;

public class MutableInteger {

    private Integer currentValue;

    public MutableInteger(final Integer initialValue) {
        currentValue = initialValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void add(final Integer increment) {
        currentValue = currentValue + increment;
    }

    public void subtract(final Integer decrement){
        currentValue = currentValue - decrement;
    }
}
