package com.example.domainmodeling.anemic;

import com.example.domainmodeling.anemic.solution.InvalidCustomerException;

public class ShoppingCartValidator {

    public static boolean IsShoppingCartValid(final ShoppingCart shoppingCart) throws InvalidCustomerException {
        if (shoppingCart.getCustomer() == null) {
            throw new InvalidCustomerException();
        }
        return true;
    }
}
