package org.example;

import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);
    private static Order currentOrder;

    public static void main(String[] args)
    {
        showHomeScreen();
    }

    private static void showHomeScreen()
    {
        int choice;
        do
        {
            System.out.println(
                    "\u001B[31m" + "\033[1m" +
                    "PPPP   III  ZZZZZ  ZZZZZ   AAA        PPPP   RRRR    OOO   N   N  TTTTT  OOO\n" +
                    "P   P   I      ZZ     ZZ  A   A       P   P  R   R  O   O  NN  N    T   O   O\n" +
                    "PPPP    I     ZZ     ZZ   AAAAA       PPPP   RRRR   O   O  N N N    T   O   O\n" +
                    "P       I    ZZ     ZZ    A   A       P      R  R   O   O  N  NN    T   O   O\n" +
                    "P      III  ZZZZZ  ZZZZZ  A   A       P      R   R   OOO   N   N    T    OOO\n\u001B[0m");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1 -> startNewOrder();
                case 0 -> System.out.println("Thanks for visiting Pizza Pronto!");
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    private static void startNewOrder()
    {
        currentOrder = new Order();
        showOrderScreen();
    }

    private static void showOrderScreen()
    {
        int choice;
        do
        {
            System.out.println("\n=== New Order ===");
            System.out.println("1) Add Pizza");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Garlic Knots");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice)
            {
                case 1 -> addPizza();
                case 2 -> addDrink();
                case 3 -> addGarlicKnots();
                case 4 -> checkout();
                case 0 -> cancelOrder();
                default -> System.out.println("Invalid option, try again.");
            }
        } while (choice != 0);
    }

    private static void checkout()
    {
        currentOrder.displayOrderDetails();
        currentOrder.saveReceipt();
        System.out.println("✅ Order saved. Returning to Home Screen");
        System.exit(0);
    }

    private static void cancelOrder()
    {
        System.out.println("Order canceled. Returning to Home Screen");
        currentOrder = null;
    }

    private static void addPizza()
    {
        System.out.println("\n--- Pizza Options ---");
        System.out.println("1) Build Your Own Pizza");
        System.out.println("2) Signature Pizza");
        System.out.println("0) Back");
        System.out.print("Choose option: ");

        int option = Integer.parseInt(scanner.nextLine());


        if (option == 0)
        {
            System.out.println("Returning to previous menu...");
            return;
        }

        Pizza pizza;

        if (option == 2)
        {
            pizza = chooseSignaturePizza();

            if (pizza == null)
            {
                return;
            }
        }
        else if (option == 1)
        {
            pizza = buildCustomPizza();
        }
        else
        {
            System.out.println("Invalid option. Returning...");
            return;
        }

        currentOrder.addPizza(pizza);
        System.out.println("Pizza added!");
    }


    private static Pizza buildCustomPizza()
    {
        System.out.println("\n--- Create Your Pizza ---");

        // --- Select Pizza Size ---
        PizzaSize[] sizes = PizzaSize.values();
        for (int i = 0; i < sizes.length; i++)
            System.out.printf("%d) %s ($%.2f)%n", i + 1, sizes[i].getLabel(), sizes[i].getBasePrice());
        System.out.print("\nChoose pizza size: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        PizzaSize size = sizes[sizeChoice - 1];

        // --- Select Crust Type ---
        CrustType[] crusts = CrustType.values();
        for (int i = 0; i < crusts.length; i++)
            System.out.printf("%d) %s%n", i + 1, crusts[i]);
        System.out.print("\nChoose crust type: ");
        int crustChoice = Integer.parseInt(scanner.nextLine());
        CrustType crust = crusts[crustChoice - 1];

        Pizza pizza = new Pizza(size, crust);

        // --- Add Meats ---
        Meat[] meats = Meat.values();
        System.out.println("\nAvailable meats:");
        for (int i = 0; i < meats.length; i++)
            System.out.printf("%d) %s%n", i + 1, meats[i]);

        while (true)
        {
            System.out.print("Add a meat topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= meats.length)
            {
                Meat meat = meats[choice - 1];
                System.out.print("Extra meat? (y/n): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("y");
                pizza.addTopping(new Topping(meat.toString(), ToppingType.MEAT, extra));
            }
        }

        // --- Add Cheeses ---
        Cheese[] cheeses = Cheese.values();
        System.out.println("\nAvailable cheeses:");
        for (int i = 0; i < cheeses.length; i++)
            System.out.printf("%d) %s%n", i + 1, cheeses[i]);

        while (true)
        {
            System.out.print("Add a cheese topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= cheeses.length)
            {
                Cheese cheese = cheeses[choice - 1];
                System.out.print("Extra cheese? (y/n): ");
                boolean extra = scanner.nextLine().equalsIgnoreCase("y");
                pizza.addTopping(new Topping(cheese.toString(), ToppingType.CHEESE, extra));
            }
        }

        // --- Add Regular Toppings ---
        RegularTopping[] regulars = RegularTopping.values();
        System.out.println("\nAvailable toppings (no extra charge) :");
        for (int i = 0; i < regulars.length; i++)
            System.out.printf("%d) %s%n", i + 1, regulars[i]);

        while (true)
        {
            System.out.print("Add a regular topping (0 to finish): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) break;
            if (choice >= 1 && choice <= regulars.length)
            {
                RegularTopping regular = regulars[choice - 1];
                pizza.addTopping(new Topping(regular.toString(), ToppingType.REGULAR, false));
            }
        }

        // --- Add Sauces ---
        Sauce[] sauces = Sauce.values();
        System.out.println("\nChoose a sauce:");

        for (int i = 0; i < sauces.length; i++) {
            System.out.printf("%d) %s%n", i + 1, sauces[i]);
        }

        int sauceChoice;
        while (true) {
            System.out.print("Select sauce: ");
            sauceChoice = Integer.parseInt(scanner.nextLine());
            if (sauceChoice >= 1 && sauceChoice <= sauces.length) break;
            System.out.println("Invalid selection, try again.");
        }

        pizza.addSauce(sauces[sauceChoice - 1]);

        return pizza;
    }

    private static Pizza chooseSignaturePizza() {
        System.out.println("\n--- Signature Pizzas ---");
        System.out.println("1) Margherita");
        System.out.println("2) Veggie");
        System.out.println("0) Back");
        System.out.print("Choose signature pizza: ");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 0) {
            System.out.println("Returning to previous menu...");
            return null;
        }

        return switch (choice) {
            case 1 -> new MargheritaPizza();
            case 2 -> new VeggiePizza();
            default -> {
                System.out.println("Invalid option. Returning to menu...");
                yield null;
            }
        };
    }


    private static void addDrink() {
        System.out.println("\n--- Add a Drink ---");

        // ----- Drink Size Selection -----
        DrinkSize[] sizes = DrinkSize.values();
        for (int i = 0; i < sizes.length; i++)
            System.out.printf("%d) %s ($%.2f)%n", i + 1, sizes[i], sizes[i].getPrice());

        System.out.print("Choose drink size: ");
        int sizeChoice = Integer.parseInt(scanner.nextLine());
        DrinkSize size = sizes[sizeChoice - 1];


        // ----- Drink Type Selection -----
        System.out.println("\nAvailable Drink Types:");
        DrinkType[] types = DrinkType.values();

        // Print all except NONE (which is option 0)
        int index = 1;
        for (DrinkType t : types) {
            if (t == DrinkType.NONE) continue;
            System.out.println(index + ") " + t.getLabel());
            index++;
        }
        System.out.println("0) " + DrinkType.NONE.getLabel());

        System.out.print("Choose drink type: ");
        int typeChoice = Integer.parseInt(scanner.nextLine());

        DrinkType drinkType;
        if (typeChoice == 0) {
            drinkType = DrinkType.NONE;
        } else {
            drinkType = types[typeChoice - 1];
        }

        // ----- Create Drink Object -----
        Drink drink = new Drink(size.toString(), drinkType.getLabel(), size.getPrice());
        currentOrder.addDrink(drink);

        System.out.println("\n✅ Drink added: " + drink);
    }

    private static void addGarlicKnots()
    {
        System.out.println("\n--- Add Garlic Knots ---");
        System.out.println("($1.50 per)");
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        GarlicKnots knots = new GarlicKnots(quantity, 1.50);
        currentOrder.addGarlicKnots(knots);

        System.out.println("\n✅ Garlic Knots added: " + knots);
    }
}