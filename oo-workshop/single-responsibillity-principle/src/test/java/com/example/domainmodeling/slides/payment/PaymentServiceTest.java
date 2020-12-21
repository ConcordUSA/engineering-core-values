package com.example.domainmodeling.slides.payment;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Test
    void givenAPaymentIsToBeProcessed_whenADiscountIsApplied_thenThePaymentIsTotalMinusDiscount() {

        //arrange
        final IPaymentRepository paymentRepository = mock(IPaymentRepository.class);
        final IPaymentClient paymentClient = mock(IPaymentClient.class);
        final PaymentService paymentService = new PaymentService(paymentClient, paymentRepository);

        final BigDecimal orderAmount = BigDecimal.TEN;
        final BigDecimal discountAmount = BigDecimal.ONE;
        final Order orderWithDiscount = new Order("delicious burger", orderAmount, discountAmount);

        final BigDecimal expectedTotal = orderAmount.subtract(discountAmount);
        //act
        final Payment payment = paymentService.makePaymentAnemicModel(orderWithDiscount);
        //assert
        assertEquals(payment.getAmountCharged(), expectedTotal);
        verify(paymentClient).processPaymentAmount(expectedTotal);
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void givenAPaymentIsToBeProcessed_whenADiscountIsZero_thenThePaymentIsTotal() {

        //arrange
        final IPaymentRepository paymentRepository = mock(IPaymentRepository.class);
        final IPaymentClient paymentClient = mock(IPaymentClient.class);
        final PaymentService paymentService = new PaymentService(paymentClient, paymentRepository);

        final BigDecimal orderAmount = BigDecimal.TEN;
        final BigDecimal discountAmount = BigDecimal.ZERO;
        final Order orderWithDiscount = new Order("delicious burger", orderAmount, discountAmount);

        final BigDecimal expectedTotal = orderAmount.subtract(discountAmount);
        //actR
        final Payment payment = paymentService.makePaymentAnemicModel(orderWithDiscount);
        //assert
        assertEquals(payment.getAmountCharged(), expectedTotal);
        verify(paymentClient).processPaymentAmount(expectedTotal);
        verify(paymentRepository).save(any(Payment.class));
    }
}
