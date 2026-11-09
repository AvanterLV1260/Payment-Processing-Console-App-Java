package org.example.model;

public class OrderItem extends Item {
    private final int quantity;
    private final double totalPrice;

    public OrderItem(String name, double price, int quantity) {
        super(name, price);
        this.quantity = quantity;
        this.totalPrice = price * quantity;
    }

    public double calculateTotal(){
        return totalPrice;
    }

    @Override
    public String toString(){
        return getName() + " x" + quantity + " = " + calculateTotal();
    }
}
