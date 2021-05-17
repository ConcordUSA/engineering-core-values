package com.example.domainmodeling.anemic;

import com.example.domainmodeling.anemic.solution.InvalidCustomerException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartService {

    private ShoppingCartRepository repository;

    public ShoppingCartService(ShoppingCartRepository repository) {
        this.repository = repository;
    }

    public ShoppingCart createShoppingCart(final ShoppingCart shoppingCart) throws InvalidCustomerException {
        ShoppingCartValidator.IsShoppingCartValid(shoppingCart);

        return this.repository.save(shoppingCart);
    }

    public ShoppingCart addItemToShoppingCart(final String shoppingCartId, final CartItem item) {
        final ShoppingCart shoppingCart = this.repository.find(shoppingCartId);

        shoppingCart.getItems().add((item));

        this.repository.save(shoppingCart);

        return shoppingCart;
    }

    public ShoppingCart removeItemFromShoppingCart(final String shoppingCartId, final String itemId) {
        final ShoppingCart shoppingCart = this.repository.find(shoppingCartId);

        final List<CartItem> cartWithRemovedItem = shoppingCart.getItems()
                .stream()
                .filter(cartItem -> {
                    return cartItem.getId() != itemId;
                }).collect(Collectors.toList());

        shoppingCart.setItems(cartWithRemovedItem);

        return this.repository.save(shoppingCart);

    }

    public BigDecimal calculateShoppingCartTotal(final String shoppingCartId) {
        final ShoppingCart shoppingCart = this.repository.find(shoppingCartId);

        BigDecimal total = BigDecimal.ZERO;
        for (final CartItem cartItem : shoppingCart.getItems()) {
            total = total.add(cartItem.getPrice());
        }

        return total;
    }

    public List<CartItem> getItemsInCart(final String shoppingCartId) {
        final ShoppingCart shoppingCart = this.repository.find(shoppingCartId);

        final List<CartItem> cartItems = shoppingCart.getItems()
                .stream()
                .sorted(Comparator.comparing(CartItem::getPrice))
                .collect(Collectors.toList());

        return cartItems;

    }
}
