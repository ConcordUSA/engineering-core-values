package com.example.domainmodeling.anemic;

public interface ShoppingCartRepository {

    ShoppingCart save(ShoppingCart shoppingCart);

    ShoppingCart find(String shoppingCartId);
}
