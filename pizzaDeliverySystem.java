import java.util.Scanner;

// main program class
class pizzaDeliverySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // display welcome message
        System.out.println("  WELCOME TO PIZZA PARADISE!");

        // get customer information
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter your delivery address: ");
        String address = scanner.nextLine();

        // create customer object
        customer cust = new customer(name, phone, address);

        // create order object
        order newOrder = new order(cust);

        // process order
        newOrder.selectPizza();
        newOrder.selectToppings();
        newOrder.setDeliveryTime();
        newOrder.confirmOrder();

        scanner.close();
    }
}