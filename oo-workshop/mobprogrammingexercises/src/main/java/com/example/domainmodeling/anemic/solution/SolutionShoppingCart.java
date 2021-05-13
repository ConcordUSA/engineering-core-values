package com.example.domainmodeling.anemic.solution;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionShoppingCart {

    private String id;
    private List<SolutionCartItem> items;
    private SolutionCustomer customer;

    public SolutionShoppingCart(String id, List<SolutionCartItem> items, SolutionCustomer customer) {
        this.id = id;
        this.items = items;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SolutionCartItem> getItems() {

        final List<SolutionCartItem> cartItems = this.items
                .stream()
                .sorted(Comparator.comparing(SolutionCartItem::getPrice))
                .collect(Collectors.toList());

        return cartItems;
    }

    public void setItems(List<SolutionCartItem> items) {
        this.items = items;
    }

    public SolutionCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(SolutionCustomer customer) {
        this.customer = customer;
    }

    public boolean IsShoppingCartValid() throws InvalidCustomerException {

        if (this.customer == null) {
            throw new InvalidCustomerException();
        }
        return true;
    }

    public void addItemToShoppingCart(final SolutionCartItem item) {

        this.items.add(item);
    }

    public void removeItemFromShoppingCart(final String itemId) {

        final List<SolutionCartItem> cartWithRemovedItem = this.items
                .stream()
                .filter(cartItem -> {
                    return cartItem.getId() != itemId;
                }).collect(Collectors.toList());

        this.items = cartWithRemovedItem;
    }

    public BigDecimal calculateShoppingCartTotal() {

        BigDecimal total = BigDecimal.ZERO;
        for (final SolutionCartItem cartItem : this.items) {
            total = total.add(cartItem.getPrice());
        }

        return total;
    }
}
