package com.example.domainmodeling.slides.payment;

import java.math.BigDecimal;

public interface IPaymentClient {

    void processPaymentAmount(BigDecimal amount);
}
