package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReceiptWriter {

    public void saveToFile(Order order) {
        try {
            Files.createDirectories(Path.of("receipts"));
            String filename = "receipts/" + order.getTimestamp()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write("--- PIZZA-licious Receipt ---\n");
                for (Pizza p : order.getPizzas()) writer.write(p + "\n");
                for (Drink d : order.getDrinks()) writer.write(d + "\n");
                for (GarlicKnots g : order.getGarlicKnots()) writer.write(g + "\n");
                writer.write(String.format("Total: $%.2f%n", order.calculateTotal()));
            }

            System.out.println("Receipt saved: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }
}