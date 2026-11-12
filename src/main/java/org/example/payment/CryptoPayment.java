package org.example.payment;

import org.example.model.PaymentResult;

public class CryptoPayment extends PaymentMethod {
    private final String privateKey;

    public CryptoPayment(String privateKey) {
        super("CryptoPayment");
        this.privateKey = privateKey;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (privateKey == null || privateKey.isEmpty()) {
            return new PaymentResult(false, "Private key cannot be null or empty");
        }
        if (privateKey.length() < 8) {
            return new PaymentResult(false, "Private key must be at least 8 characters long");
        }
        return new PaymentResult(true, "Paid " + amount + " using crypto");
    }
}
