package com.example.domainmodeling.anemic.solution;

import org.junit.Test;

public class ShoppingCartTest {

    @Test(expected = InvalidCustomerException.class)
    public void givenAShoppingCartIsToBeCreated_whenACustomerDoesNotExist_ThrowsInvalidCustomerException(){

    }

    public void givenAValidShoppingCartIsToBeCreated_whenShoppingCartContainsOneItem_thenShoppingCartTotalEqualsItemTotal(){

    }

    public void givenAShoppingCartWithOneItem_whenAnItemIsAddedToTheShoppingCart_thenShoppingCartTotalEqualsTotalOfBothItems(){

    }

    public void givenAShoppingCartWithOneItem_whenItemIsRemoved_thenShoppingCartTotalEqualsZero(){

    }

    public void givenAShoppingCartWithTwoItem_whenAnItemIsRemoved_thenShoppingCartTotalEqualsTotalOfRemainingItem(){

    }
    public void givenAShoppingCartWithTwoItem_whenAnItemThatDoesNotExistIsRemoved_thenShoppingCartContainsSameTwoItems(){

    }

    public void givenAShoppingCartWithThreeItems_whenItemsAreRetrieved_itemsAreSortedInDescendingOrderByPrice(){

    }
}
