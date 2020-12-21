package com.example.domainmodeling.slides;

import java.math.BigDecimal;

public class PaymentService {
    private IPaymentClient paymentClient;
    private IPaymentRepository paymentRepository;

    public PaymentService(final IPaymentClient paymentClient, final IPaymentRepository paymentRepository) {
        this.paymentClient = paymentClient;
        this.paymentRepository = paymentRepository;
    }

    public Payment makePaymentAnemicModel(final Order order) {
        //apply discount (if applies)
        final Payment payment = new Payment();
        if (order.getDiscount().equals(BigDecimal.ZERO)) {
            payment.setAmountCharged(order.getAmount());
        } else {
            payment.setAmountCharged(order.getAmount().subtract(order.getDiscount()));
        }
        //make charge
        this.paymentClient.processPaymentAmount(payment.getAmountCharged());
        //store in db
        this.paymentRepository.save(payment);
        return payment;
    }

    public Payment makePayment(final Order order) {
        //apply discount (if applies)
        final Payment payment = order.createPayment();
        //make charge
        this.paymentClient.processPaymentAmount(payment.getAmountCharged());
        //store in db
        this.paymentRepository.save(payment);
        return payment;
    }

}


