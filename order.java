import java.io.*;
import java.util.Scanner;

// order class implements both interfaces
class order implements orderInterface, deliveryInterface {
    private customer cust;
    private pizza pizzaOrder;
    private String deliveryTime;
    private double total;
    private Scanner scanner;

    // available toppings array
    private String[] availableToppings = {"Pepperoni", "Mushrooms", "Olives", "Sausage", "Onions"};

    order(customer c) {
        cust = c;
        scanner = new Scanner(System.in);
        total = 0;
        deliveryTime = "";
    }

    // implement orderInterface method
    public void displayMenu() {
        System.out.println("\n===== PIZZA SIZES =====");
        System.out.println("1. Small - $8.99");
        System.out.println("2. Medium - $11.99");
        System.out.println("3. Large - $14.99");
        System.out.println("=======================");
    }

    public void selectPizza() {
        displayMenu();
        System.out.print("Select size (1-3): ");
        int choice = scanner.nextInt();

        double basePrice = 0;
        String sizeName = "";

        // switch statement for size selection
        switch(choice) {
            case 1:
                basePrice = 8.99;
                sizeName = "Small";
                break;
            case 2:
                basePrice = 11.99;
                sizeName = "Medium";
                break;
            case 3:
                basePrice = 14.99;
                sizeName = "Large";
                break;
            default:
                basePrice = 11.99;
                sizeName = "Medium";
                choice = 2;
                System.out.println("Invalid choice. Defaulting to Medium.");
        }

        pizzaOrder = new pizza(sizeName + " Pizza", basePrice, choice);
        total += basePrice;
    }

    public void selectToppings() {
        System.out.println("\n===== TOPPINGS ($1.50 each) =====");

        // loop to display toppings
        for(int i = 0; i < availableToppings.length; i++) {
            System.out.println((i + 1) + ". " + availableToppings[i]);
        }
        System.out.println("==================================");

        boolean addMore = true;

        // while loop for adding toppings
        while(addMore) {
            System.out.print("Select topping (1-" + availableToppings.length + ", 0 to finish): ");
            int choice = scanner.nextInt();

            if(choice == 0) {
                addMore = false;
            } else if(choice > 0 && choice <= availableToppings.length) {
                pizzaOrder.addTopping(availableToppings[choice - 1]);
                total += 1.50;
                System.out.println(availableToppings[choice - 1] + " added!");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // implement orderInterface method
    public double calculateTotal() {
        double tax = total * 0.0825;
        return total + tax;
    }

    // implement deliveryInterface method
    public void setDeliveryTime() {
        System.out.println("\n===== DELIVERY TIME =====");
        System.out.println("1. ASAP (30-45 minutes)");
        System.out.println("2. Schedule for later");
        System.out.print("Choose option: ");

        int choice = scanner.nextInt();

        if(choice == 1) {
            deliveryTime = "ASAP (30-45 minutes)";
        } else {
            scanner.nextLine(); // clear buffer
            System.out.print("Enter time (HH:MM format): ");
            String time = scanner.nextLine();
            deliveryTime = time;
            validateDeliveryTime();
        }
    }

    // implement deliveryInterface method
    public void validateDeliveryTime() {
        // simple validation - check if contains colon
        if(!deliveryTime.contains(":")) {
            System.out.println("Invalid time format. Defaulting to ASAP.");
            deliveryTime = "ASAP (30-45 minutes)";
        }
    }

    // implement orderInterface method
    public void confirmOrder() {
        double tax = total * 0.0825;
        double finalTotal = calculateTotal();

        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Customer: " + cust.getName());
        System.out.println("Phone: " + cust.getPhone());
        System.out.println("Address: " + cust.getAddress());
        System.out.println("Pizza: " + pizzaOrder.getSizeName());
        System.out.println("Toppings: " + pizzaOrder.getToppings());
        System.out.println("Subtotal: $" + String.format("%.2f", total));
        System.out.println("Tax: $" + String.format("%.2f", tax));
        System.out.println("Total: $" + String.format("%.2f", finalTotal));
        System.out.println("Delivery: " + deliveryTime);
        System.out.println("=========================\n");

        System.out.print("Confirm order? (Y/N): ");
        scanner.nextLine(); // clear buffer
        String confirm = scanner.nextLine();

        if(confirm.equalsIgnoreCase("Y")) {
            saveToFile();
            System.out.println("\nOrder confirmed! Thank you for choosing Pizza Paradise!");
        } else {
            System.out.println("\nOrder cancelled.");
        }
    }

    private void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter("order_" + cust.getName() + ".txt");

            writer.println("PIZZA PARADISE ORDER");
            writer.println("====================");
            writer.println("Customer: " + cust.getName());
            writer.println("Phone: " + cust.getPhone());
            writer.println("Address: " + cust.getAddress());
            writer.println("Pizza: " + pizzaOrder.getSizeName());
            writer.println("Toppings: " + pizzaOrder.getToppings());
            writer.println("Subtotal: $" + String.format("%.2f", total));
            writer.println("Tax: $" + String.format("%.2f", total * 0.0825));
            writer.println("Total: $" + String.format("%.2f", calculateTotal()));
            writer.println("Delivery Time: " + deliveryTime);

            writer.close();
            System.out.println("Order saved to file: order_" + cust.getName() + ".txt");
        } catch(IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}