package org.example.menu;

import org.example.config.AppConfig;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.PaymentResult;
import org.example.payment.PaymentMethod;
import org.example.payment.PaymentMethodFactory;
import org.example.payment.PaymentProcessor;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final PaymentProcessor paymentProcessor = new PaymentProcessor();

    private Order currentOrder;
    private static final String APPLE = "apple";
    private static final String ORANGE = "orange";
    private static final String PS5 = "ps5";

    private static List<Item> availableItemsInStock = List.of
            (
                    new Item(APPLE, 1),
                    new Item(ORANGE, 2),
                    new Item(PS5, 600)
            );


    public void start() {
        AppConfig config = AppConfig.getInstance();
        System.out.println("Welcome to " + config.getApplicationName());

        boolean running = true;
        while (running) {
            printMenu();

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> createOrder();
                case "2" -> addItem();
                case "3" -> viewOrder();
                case "4" -> payOrder();
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void createOrder() {
        System.out.println("Customer name:");
        String customerName = scanner.nextLine();
        if (customerName.isBlank()) {
            System.out.println("Customer name is required");
            return;
        }

        currentOrder = Order.builder()
                .customerName(customerName)
                .build();
        System.out.println("Order created for " + customerName);
    }

    private void addItem() {
        if (currentOrder == null) {
            System.out.println("There is no current order");
            return;
        }
        if (currentOrder.isPaid()) {
            System.out.println("There is already a paid order, create a new one");
            return;
        }

        int ListNumber = 1;
        for (Item item : availableItemsInStock) {
            System.out.println(ListNumber + ". " + item.toString());
            ListNumber++;
        }

        System.out.println("Choose item:");
        String choice = scanner.nextLine();

        Item selectedItem = switch (choice) {
            case "1" -> availableItemsInStock.get(0);
            case "2" -> availableItemsInStock.get(1);
            case "3" -> availableItemsInStock.get(2);
            default -> null;
        };

        if (selectedItem == null) {
            System.out.println("Invalid item");
            return;
        }

        System.out.println("Quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        currentOrder.addItem(new OrderItem(selectedItem.getName(), selectedItem.getPrice(), quantity));
        System.out.println("Item added to order");
    }

    private void viewOrder() {
        if (currentOrder == null) {
            System.out.println("There is no current order");
            return;
        }

        System.out.println("Customer: " + currentOrder.getCustomerName());
        System.out.println("Status: " + currentOrder.getStatus());
        System.out.println("Items:");

        for (OrderItem item : currentOrder.getItems()) {
            System.out.println("- " + item);
        }

        System.out.println("Total: " + currentOrder.calculateTotal());
    }

    private void payOrder() {
        if (currentOrder == null) {
            System.out.println("There is no current order");
            return;
        }


        System.out.println("""
                Select payment method:
                1. Credit Card
                2. PayPal
                3. Gift Card
                """);
        String option = scanner.nextLine();

        PaymentMethod paymentMethod = switch (option) {
            case "1" -> createCreditCardPayment();
            case "2" -> createPaypalPayment();
            case "3" -> createGiftCardPayment();
            default -> null;
        };

        if (paymentMethod == null) {
            System.out.println("Invalid payment method");
            return;
        }


        PaymentResult result = paymentProcessor.process(currentOrder, paymentMethod);
        System.out.println(result.getMessage());
    }

    private PaymentMethod createCreditCardPayment() {
        System.out.println("Card number:");
        String cardNumber = scanner.nextLine();

        System.out.println("Card holder name:");
        String cardHolderName = scanner.nextLine();

        return PaymentMethodFactory.createCreditCardPayment(cardNumber, cardHolderName);
    }

    private PaymentMethod createPaypalPayment() {
        System.out.println("E-mail:");
        String email = scanner.nextLine();

        return PaymentMethodFactory.createPaypalPayment(email);
    }

    private PaymentMethod createGiftCardPayment() {
        System.out.println("Gift card number:");
        String code = scanner.nextLine();

        System.out.println("Your balance:");
        double balance = Double.parseDouble(scanner.nextLine());


        return PaymentMethodFactory.createGiftCardPayment(code, balance);
    }

    private void printMenu() {
        System.out.println("""
                1. Create order
                2. Add item to order
                3. View order
                4. Pay order
                0. Exit
                """);
    }
}
