package com.example.domainmodeling.slides;


import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


class OrderTest {

    @Test
    public void givenAPaymentIsToBeProcessed_whenADiscountIsApplied_thenThePaymentIsTotalMinusDiscount() {
        //arrange
        final BigDecimal orderAmount = BigDecimal.TEN;
        final BigDecimal discountAmount = BigDecimal.ONE;
        final Order orderWithDiscount = new Order("delicious burger", orderAmount, discountAmount);
        final BigDecimal expectedTotal = orderAmount.subtract(discountAmount);

        //act
        final Payment payment = orderWithDiscount.createPayment();
        //assert
        assertEquals(payment.getAmountCharged(), expectedTotal);

    }

    @Test
    public void givenAPaymentIsToBeProcessed_whenADiscountIsZero_thenThePaymentIsTotal() {
        //arrange
        final BigDecimal orderAmount = BigDecimal.TEN;
        final BigDecimal discountAmount = BigDecimal.ZERO;
        final Order orderWithDiscount = new Order("delicious burger", orderAmount, discountAmount);
        final BigDecimal expectedTotal = orderAmount.subtract(discountAmount);

        //act
        final Payment payment = orderWithDiscount.createPayment();
        //assert
        assertEquals(payment.getAmountCharged(), expectedTotal);
    }
}
