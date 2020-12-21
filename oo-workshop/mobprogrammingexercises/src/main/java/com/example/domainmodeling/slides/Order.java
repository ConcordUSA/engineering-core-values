package com.example.domainmodeling.slides;

import java.math.BigDecimal;

public class Order {

    public Order(final String productName, final BigDecimal amount, final BigDecimal discount) {
        this.productName = productName;
        this.amount = amount;
        this.discount = discount;
    }

    private String productName;
    private BigDecimal amount;
    private BigDecimal discount;

    public Payment createPayment(){
        final Payment payment = new Payment();
        if (this.discount.equals(BigDecimal.ZERO)) {
            payment.setAmountCharged(this.amount);
        } else {
            payment.setAmountCharged(this.getAmount().subtract(this.discount));
        }

        return payment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(final BigDecimal discount) {
        this.discount = discount;
    }


}
