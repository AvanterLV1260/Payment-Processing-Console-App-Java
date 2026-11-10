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
    private double totalPriceAfterDiscount;
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
        totalPriceAfterDiscount = discount.apply(totalPrice);
    }

    public double calculateTotal() {
        return totalPriceAfterDiscount;
    }

    public void markAsPaid(){
        //validate order is not empty
        if(items.isEmpty()){
            throw new IllegalArgumentException("Order is empty");
        }
        this.status = OrderStatus.PAID;
    }


    public boolean isPaid(){
        return this.status == OrderStatus.PAID;
    }
    @Override
    public String toString(){
        return  "Name:"+customerName +", "+ "Status:" + status +  ", " + "Items:" + items+ ", " + "Total price:" + calculateTotal();
    }
}

