package com.example.domainmodeling.anemic;

import com.example.domainmodeling.anemic.solution.InvalidCustomerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCartTest {

    private ShoppingCart tested;
    private Customer validTestCustomer;
    private List<CartItem> testItems;

    @Before
    public void setup() {
        validTestCustomer = new Customer(UUID.randomUUID().toString(), "testCustomer");
        testItems = new ArrayList<>();
    }

    @Test(expected = InvalidCustomerException.class)
    public void givenAShoppingCartIsToBeCreated_whenACustomerDoesNotExist_ThrowsInvalidCustomerException() throws InvalidCustomerException {
//        //arrange
//        tested = new ShoppingCart(UUID.randomUUID().toString(), testItems, null);
//
//        //act
//        tested.IsShoppingCartValid();
    }

    @Test
    public void givenAValidShoppingCartIsToBeCreated_whenShoppingCartContainsOneItem_thenShoppingCartTotalEqualsItemTotal(){
//        //arrange
//        tested = new ShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
//        BigDecimal expected = BigDecimal.TEN;
//        CartItem newItem = new CartItem(UUID.randomUUID().toString(), "bread", expected);
//        tested.addItemToShoppingCart(newItem);
//
//        //act
//        BigDecimal actual = tested.calculateShoppingCartTotal();
//
//        //assert
//        Assert.assertEquals(expected, actual);
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
