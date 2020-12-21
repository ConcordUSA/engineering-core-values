package com.example.domainmodeling.mobprogramming;

import com.example.domainmodeling.mobprogramming.solution.InvalidCustomerException;

public class ShoppingCartValidator {

    public static boolean IsShoppingCartValid(final ShoppingCart shoppingCart) throws InvalidCustomerException {
        if (shoppingCart.getCustomer() == null) {
            throw new InvalidCustomerException();
        }
        return true;
    }
}
