package com.example.domainmodeling.anemic.solution;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SolutionShoppingCartTest {

    private SolutionShoppingCart tested;
    private SolutionCustomer validTestCustomer;
    private List<SolutionCartItem> testItems;

    @Before
    public void setup() {
        validTestCustomer = new SolutionCustomer(UUID.randomUUID().toString(), "testCustomer");
        testItems = new ArrayList<>();
    }

    @Test(expected = InvalidCustomerException.class)
    public void givenAShoppingCartIsToBeCreated_whenACustomerDoesNotExist_ThrowsInvalidCustomerException() throws InvalidCustomerException {
        //arrange
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, null);

        //act
        tested.IsShoppingCartValid();
    }

    @Test
    public void givenAValidShoppingCartIsToBeCreated_whenShoppingCartContainsOneItem_thenShoppingCartTotalEqualsItemTotal(){
        //arrange
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
        BigDecimal expected = BigDecimal.TEN;
        SolutionCartItem newItem = new SolutionCartItem(UUID.randomUUID().toString(), "bread", expected);
        tested.addItemToShoppingCart(newItem);

        //act
        BigDecimal actual = tested.calculateShoppingCartTotal();

        //assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAShoppingCartWithOneItem_whenAnItemIsAddedToTheShoppingCart_thenShoppingCartTotalEqualsTotalOfBothItems(){
        //arrange
        SolutionCartItem itemOne = new SolutionCartItem(UUID.randomUUID().toString(), "bread", BigDecimal.TEN);
        SolutionCartItem itemTwo = new SolutionCartItem(UUID.randomUUID().toString(), "milk", BigDecimal.TEN);
        BigDecimal expected = new BigDecimal(20);
        testItems.add(itemOne);
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
        tested.addItemToShoppingCart(itemTwo);

        //act
        BigDecimal actual = tested.calculateShoppingCartTotal();

        //assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAShoppingCartWithOneItem_whenItemIsRemoved_thenShoppingCartTotalEqualsZero(){
        //arrange
        String itemId = UUID.randomUUID().toString();
        SolutionCartItem itemOne = new SolutionCartItem(itemId, "bread", BigDecimal.TEN);
        BigDecimal expected = new BigDecimal(0);
        testItems.add(itemOne);
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
        tested.removeItemFromShoppingCart(itemId);

        //act
        BigDecimal actual = tested.calculateShoppingCartTotal();

        //assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAShoppingCartWithTwoItem_whenAnItemIsRemoved_thenShoppingCartTotalEqualsTotalOfRemainingItem(){
        //arrange
        String removeId = UUID.randomUUID().toString();
        SolutionCartItem itemOne = new SolutionCartItem(UUID.randomUUID().toString(), "bread", BigDecimal.TEN);
        SolutionCartItem itemTwo = new SolutionCartItem(removeId, "milk", BigDecimal.ONE);
        BigDecimal expected = BigDecimal.TEN;
        testItems.add(itemOne);
        testItems.add(itemTwo);
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
        tested.removeItemFromShoppingCart(removeId);

        //act
        BigDecimal actual = tested.calculateShoppingCartTotal();

        //assert
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void givenAShoppingCartWithTwoItem_whenAnItemThatDoesNotExistIsRemoved_thenShoppingCartContainsSameTwoItems(){
        //arrange
        SolutionCartItem itemOne = new SolutionCartItem(UUID.randomUUID().toString(), "bread", BigDecimal.ONE);
        SolutionCartItem itemTwo = new SolutionCartItem(UUID.randomUUID().toString(), "milk", BigDecimal.TEN);
        testItems.add(itemOne);
        testItems.add(itemTwo);
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);
        tested.removeItemFromShoppingCart("1");

        //act
        List<SolutionCartItem> actual = tested.getItems();

        //assert
        Assert.assertEquals(testItems, actual);
    }

    @Test
    public void givenAShoppingCartWithThreeItems_whenItemsAreRetrieved_itemsAreSortedInDescendingOrderByPrice(){
        //arrange
        SolutionCartItem itemOne = new SolutionCartItem(UUID.randomUUID().toString(), "bread", BigDecimal.TEN);
        SolutionCartItem itemTwo = new SolutionCartItem(UUID.randomUUID().toString(), "milk", BigDecimal.ONE);
        SolutionCartItem itemThree = new SolutionCartItem(UUID.randomUUID().toString(), "eggs", new BigDecimal(3));
        testItems.add(itemOne);
        testItems.add(itemTwo);
        testItems.add(itemThree);
        tested = new SolutionShoppingCart(UUID.randomUUID().toString(), testItems, validTestCustomer);

        //act
        List<SolutionCartItem> actual = tested.getItems();

        //assert
        Assert.assertEquals(-1, actual.get(0).getPrice().compareTo(actual.get(1).getPrice()));
        Assert.assertEquals(-1, actual.get(1).getPrice().compareTo(actual.get(2).getPrice()));
    }
}
