package com.example.domainmodeling.slides.payment;

public interface IPaymentRepository {

    Order save(Payment order);
}
