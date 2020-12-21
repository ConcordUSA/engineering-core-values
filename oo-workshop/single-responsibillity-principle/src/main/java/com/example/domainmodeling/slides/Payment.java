package com.example.domainmodeling.slides;

import java.math.BigDecimal;

public class Payment {
    public Payment() {
    }

    public Payment(final BigDecimal amountCharged, final BigDecimal discountApplied) {
        this.amountCharged = amountCharged;
        this.discountApplied = discountApplied;
    }

    private BigDecimal amountCharged;
    private BigDecimal discountApplied;

    public BigDecimal getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(final BigDecimal amountCharged) {
        this.amountCharged = amountCharged;
    }

    public BigDecimal getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(final BigDecimal discountApplied) {
        this.discountApplied = discountApplied;
    }
}
