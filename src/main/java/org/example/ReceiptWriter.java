package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReceiptWriter
{
    private String formatItemLine(String name, double price) {
        return String.format("%-25s $%6.2f%n", name, price);
    }

    public void saveToFile(Order order) {
        try {
            // Make sure the directory exists; keep it consistent with the filename path.
            Files.createDirectories(Path.of("src/main/receipts"));

            String filename = "src/main/receipts/" + order.getTimestamp()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {

                writer.write("====================================\n");
                writer.write("        PIZZA PRONTO RECEIPT        \n");
                writer.write("====================================\n");
                writer.write("Order Time: " + order.getTimestamp()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
                writer.write("------------------------------------\n");

                writer.write("ITEMS\n");
                writer.write("------------------------------------\n");

                // --- PIZZAS ---
                for (Pizza p : order.getPizzas()) {
                    // Adjust the description to whatever you want to show as "name"
                    String pizzaName = p.getSize() + " " + p.getCrust() + " Pizza";
                    writer.write(formatItemLine(pizzaName, p.calculatePrice()));

                    // List toppings under each pizza
                    for (Topping t : p.getToppings()) {
                        writer.write("   + " + t.getName());
                        if (t.isExtra()) writer.write(" (extra)");
                        writer.write("\n");
                    }

                    // If you have a sauce list:
                    for (Sauce s : p.getSauces()) {
                        writer.write("   + Sauce: " + s + "\n");
                    }
                }

                // --- DRINKS ---
                for (Drink d : order.getDrinks()) {
                    writer.write(formatItemLine(d.getSize() + " " + d.getFlavor(), d.getPrice()));
                }

                // --- Garlic Knots ---
                for (GarlicKnots g : order.getGarlicKnots()) {
                    writer.write(formatItemLine("Garlic Knots (" + g.getCount() + ")", g.getPrice()));
                }

                writer.write("------------------------------------\n");
                writer.write(String.format("%-25s $%6.2f%n", "TOTAL:", order.calculateTotal()));
                writer.write("====================================\n");
                writer.write("      THANK YOU FOR YOUR ORDER!      \n");
                writer.write("====================================\n");
            }

            System.out.println("Receipt saved: " + filename);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}