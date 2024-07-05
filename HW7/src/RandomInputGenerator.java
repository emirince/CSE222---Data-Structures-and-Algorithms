import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomInputGenerator {
    public static void generateRandomInput(String filename, int numNodes, int numAdd, int numRemove, int numSearch, int numUpdate) {
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            symbols.add("SYM" + i);
        }
        List<String> operations = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numAdd; i++) {
            String symbol = symbols.get(random.nextInt(symbols.size()));
            double price = Math.round((10 + (1000 - 10) * random.nextDouble()) * 100.0) / 100.0;
            int volume = 1000 + random.nextInt(1000000 - 1000 + 1);
            int marketCap = 1000000 + random.nextInt(1000000000 - 1000000 + 1);
            operations.add(String.format("ADD %s %.2f %d %d", symbol, price, volume, marketCap));
        }

        for (int i = 0; i < numRemove; i++) {
            String symbol = symbols.get(random.nextInt(symbols.size()));
            operations.add("REMOVE " + symbol);
        }

        for (int i = 0; i < numSearch; i++) {
            String symbol = symbols.get(random.nextInt(symbols.size()));
            operations.add("SEARCH " + symbol);
        }

        for (int i = 0; i < numUpdate; i++) {
            String symbol = symbols.get(random.nextInt(symbols.size()));
            String newSymbol = symbol + "_NEW";
            double newPrice = Math.round((10 + (1000 - 10) * random.nextDouble()) * 100.0) / 100.0;
            int newVolume = 1000 + random.nextInt(1000000 - 1000 + 1);
            int newMarketCap = 1000000 + random.nextInt(1000000000 - 1000000 + 1);
            operations.add(String.format("UPDATE %s %s %.2f %d %d", symbol, newSymbol, newPrice, newVolume, newMarketCap));
        }

        Collections.shuffle(operations);

        try (FileWriter fileWriter = new FileWriter(filename)) {
            for (String operation : operations) {
                fileWriter.write(operation + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Generate random input file
        generateRandomInput("random_input.txt", 10000, 1000, 3000, 1000, 2000);
    }
}
