package org.example.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private final String customerName;
    private final List<OrderItem> items;
    private OrderStatus status;
    private double totalPrice;
    private Discount discount = new NoDiscount();

    // Lombok generates Order.builder().customerName(...).build() from this constructor
    @Builder
    private Order(String customerName) {
        //validate customerName
        if (customerName == null || customerName.isBlank()){
            throw new IllegalArgumentException("Customer name is required");
        }
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item){
        //prevent adding items if order is already paid
        if(isPaid()){
            throw new IllegalArgumentException("Order is already paid");
        }
        items.add(item);
        totalPrice += item.calculateTotal();
    }

    public double calculateTotal() {
        return discount.apply(totalPrice);
    }

    public void markAsPaid(){
        //validate order is not empty
        if(items.isEmpty()){
            throw new IllegalArgumentException("Order is empty");
        }
        this.status = OrderStatus.PAID;
    }

    public void applyDiscount(Discount discount){
        this.discount = discount;
    }

    public boolean isPaid(){
        return this.status == OrderStatus.PAID;
    }
}
