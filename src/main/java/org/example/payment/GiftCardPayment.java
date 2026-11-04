package org.example.payment;

import org.example.model.PaymentResult;

public class GiftCardPayment extends PaymentMethod{
    private final String code;
    private double balance;

    public GiftCardPayment(String code, double balance) {
        //contructor
        super("GiftCardPayment");
        this.code = code;
        this.balance =balance;
    }


    @Override
    public PaymentResult processPayment(double amount) {
        //check if balance is enough
        if (this.balance >= amount) {
            this.balance = balance - amount;


            return new PaymentResult(true, "Paid " + amount + " using gift card");


        }
        return new PaymentResult(false, "Not enough balance on gift card");

    }}

