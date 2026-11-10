package org.example.payment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.model.PaymentResult;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PaymentMethod {
    private final String providerName;

    public PaymentResult pay(double amount){
        if (amount <= 0){
            return new PaymentResult(false, "Amount must be greater than zero.");
        }
        return processPayment(amount);
    }

    protected abstract PaymentResult processPayment(double amount);
}
