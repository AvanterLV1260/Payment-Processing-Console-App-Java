package org.example.payment;

public class PaymentMethodFactory {
    public static PaymentMethod createCreditCardPayment(String cardNumber,String cardHolderName){
        return new CreditCardPayment(cardNumber,cardHolderName);
    }

    //create factories for PayPal and gift card
    // shouldn't passowrd also be a thing? Not asked so, no.
    public static PaymentMethod createPaypalPayment(String email) {
        return new PaypalPayment(email);
    }

    public static PaymentMethod createGiftCardPayment(String code, double balance) {
        return new GiftCardPayment(code, balance);

    }
}
