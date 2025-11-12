package org.example;

import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private static Order currentOrder;

    public static void main(String[] args) {
        showHomeScreen();
    }

    private static void showHomeScreen() {
        int choice;
        do {
            System.out.println("\nPizzaPronto");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> startNewOrder();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    private static void startNewOrder() {
        currentOrder = new Order();
        showOrderScreen();
    }

    private static void showOrderScreen() {
        int choice;
        do {
            System.out.println("\n=== New Order ===");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addPizza();
                case 2 -> addDrink();
                case 3 -> addGarlicKnots();
                case 4 -> checkout();
                case 0 -> cancelOrder();
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    private static void checkout() {
        currentOrder.displayOrderDetails();
        currentOrder.saveReceipt();
        System.out.println("Order saved. Returning to Home Screen...");
    }

    private static void cancelOrder() {
        System.out.println("Order canceled.");
        currentOrder = null;
    }

    private static void addPizza() {
        System.out.println("\n--- Create Your Pizza ---");

        // --- Select Pizza Size ---
        PizzaSize[] sizes = PizzaSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d) %s ($%.2f)%n", i + 1, sizes[i].getLabel(), sizes[i].getBasePrice());
        }
        System.out.print("Choose pizza size: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        PizzaSize size = sizes[sizeChoice - 1];

        // --- Select Crust Type ---
        CrustType[] crusts = CrustType.values();
        for (int i = 0; i < crusts.length; i++) {
            System.out.printf("%d) %s%n", i + 1, crusts[i]);
        }
        System.out.print("Choose crust type: ");
        int crustChoice = Integer.parseInt(scanner.nextLine());
        CrustType crust = crusts[crustChoice - 1];

        Pizza pizza = new Pizza(size, crust);

        // --- Stuffed Crust ---
        System.out.print("Would you like stuffed crust? (y/n): ");
        pizza.setStuffedCrust(scanner.nextLine().equalsIgnoreCase("y"));

        // --- Add Meats ---
        Meat[] meats = Meat.values();
        System.out.println("\nAvailable meats:");
        for (int i = 0; i < meats.length; i++) {
            System.out.printf("%d) %s%n", i + 1, meats[i]);
        }

        while (true) {
            System.out.print("Add a meat topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= meats.length) {
                Meat meat = meats[choice - 1];
                System.out.print("Extra portion? (y/n): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("y");
                pizza.addTopping(new Topping(meat.toString(), ToppingType.MEAT, extra));
            }
        }

        // --- Add Cheeses ---
        Cheese[] cheeses = Cheese.values();
        System.out.println("\nAvailable cheeses:");
        for (int i = 0; i < cheeses.length; i++) {
            System.out.printf("%d) %s%n", i + 1, cheeses[i]);
        }

        while (true) {
            System.out.print("Add a cheese topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= cheeses.length) {
                Cheese cheese = cheeses[choice - 1];
                System.out.print("Extra portion? (y/n): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("y");
                pizza.addTopping(new Topping(cheese.toString(), ToppingType.CHEESE, extra));
            }
        }

        // --- Add Regular Toppings ---
        RegularTopping[] regulars = RegularTopping.values();
        System.out.println("\nIncluded toppings:");
        for (int i = 0; i < regulars.length; i++) {
            System.out.printf("%d) %s%n", i + 1, regulars[i]);
        }

        while (true) {
            System.out.print("Add a regular topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= regulars.length) {
                RegularTopping regular = regulars[choice - 1];
                pizza.addTopping(new Topping(regular.toString(), ToppingType.REGULAR, false));
            }
        }

        // --- Add Sauces ---
        Sauce[] sauces = Sauce.values();
        System.out.println("\nAvailable sauces:");
        for (int i = 0; i < sauces.length; i++) {
            System.out.printf("%d) %s%n", i + 1, sauces[i]);
        }

        while (true) {
            System.out.print("Add a sauce (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= sauces.length) {
                pizza.addSauce(sauces[choice - 1]);
            }
        }

        // --- Done ---
        System.out.println("\n✅ Pizza added: " + pizza);
        currentOrder.addPizza(pizza);
    }

    private static void addDrink() {
        System.out.println("\n--- Add a Drink ---");

        DrinkSize[] sizes = DrinkSize.values();
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%d) %s ($%.2f)%n", i + 1, sizes[i], sizes[i].getPrice());
        }

        System.out.print("Choose drink size: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        DrinkSize size = sizes[sizeChoice - 1];

        System.out.print("Enter drink flavor (e.g., cola, lemonade, root beer): ");
        String flavor = scanner.nextLine();

        Drink drink = new Drink(size.toString(), flavor, size.getPrice());
        currentOrder.addDrink(drink);

        System.out.println("\n✅ Drink added: " + drink);
    }

    private static void addGarlicKnots() {
        System.out.println("\n--- Add Garlic Knots ---");
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        GarlicKnots knots = new GarlicKnots(quantity, 1.50);
        currentOrder.addGarlicKnots(knots);

        System.out.println("\n✅ Garlic Knots added: " + knots);
    }


}