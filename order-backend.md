# Payment Processing Console App

## How it starts

The app shows a welcome message and the main menu:

1. Create order
2. Add item to order
3. View order
4. Pay order
0. Exit

If the user types anything else other then 0-4, the app says "Invalid option" and shows the menu again.

## Create order

- The user types a customer name and a new order is created.
- If the name is empty, the app shows an error and goes back to the menu.
- Creating a new order replaces the previous one.

## Add item

- The user writes the item name, price, quantity themselves.
- Items can only be added if an order exists and if it's not paid yet.

## View order

- Shows the customer name, order status (NEW or PAID), the items and the total price.

## Pay order

The user chooses how to pay. There are 3 methods for now in total, which are:

- **Credit card:** asks for a card number (16 digits) and the card holder's name. If it's over or below 16 digits, an error appears and returns you to the menu.
- **PayPal:** asks for an email address.
- **Gift card:** asks for a gift card code and its balance. The payment fails if the balance is too low.

The order can't be paid if it is empty or already paid.
If the payment works, the order is marked as **PAID**.