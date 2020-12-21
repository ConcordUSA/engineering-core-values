package com.example.domainmodeling.mobprogramming;

public interface ShoppingCartRepository {

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart find(String shoppingCartId);
}
