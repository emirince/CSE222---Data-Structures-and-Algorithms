import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize stock data managers
        List<StockDataManager> stockDataManagers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            stockDataManagers.add(new StockDataManager());
        }

        ArrayList<Integer> sizes = new ArrayList<>();
        for (int i = 1000; i <= 50000; i += 1000) {
            sizes.add(i);
        }

        ArrayList<Long> dataAddY = new ArrayList<>();
        ArrayList<Long> dataSearchY = new ArrayList<>();
        ArrayList<Long> dataRemoveY = new ArrayList<>();

        // Warm-up phase
        warmUpPhase(stockDataManagers, sizes);

        // Populate stock data managers with initial data
        for (int i = 0; i < sizes.size(); i++) {
            for (int j = 0; j < sizes.get(i); j++) {
                stockDataManagers.get(i).addOrUpdateStock("TEST" + j, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
            }
        }

        // Perform full analysis
        for (int i = 0; i < sizes.size(); i++) {
            performFullAnalysis(stockDataManagers.get(i), 100, dataAddY, dataSearchY, dataRemoveY);
        }

        GUIVisualization gui = new GUIVisualization("scatter", dataAddY, dataSearchY, dataRemoveY, sizes);
        gui.setVisible(true);
    }

    // Process a command from the input file
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], tokens[2], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }

    private static void performFullAnalysis(StockDataManager manager, int size, ArrayList<Long> dataAddY, ArrayList<Long> dataSearchY, ArrayList<Long> dataRemoveY) {
        long totalAddTime = 0;
        long totalSearchTime = 0;
        long totalRemoveTime = 0;

        for (int i = 0; i < 50; i++) {
            totalAddTime += performOperation(manager, size, "ADD");
            totalSearchTime += performOperation(manager, size, "SEARCH");
            totalRemoveTime += performOperation(manager, size, "REMOVE");
        }

        long averageAddTime = totalAddTime / 50;
        long averageSearchTime = totalSearchTime / 50;
        long averageRemoveTime = totalRemoveTime / 50;

        dataAddY.add(averageAddTime);
        dataSearchY.add(averageSearchTime);
        dataRemoveY.add(averageRemoveTime);
    }

    private static long performOperation(StockDataManager manager, int size, String operation) {
        long startTime = System.nanoTime();
        switch (operation) {
            case "ADD":
                for (int i = 0; i < size; i++) {
                    manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                }
                break;
            case "SEARCH":
                for (int i = 0; i < size; i++) {
                    manager.searchStock("SYM" + i);
                }
                break;
            case "REMOVE":
                for (int i = 0; i < size; i++) {
                    manager.removeStock("SYM" + i);
                }
                break;
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / size;
    }

    private static void warmUpPhase(List<StockDataManager> managers, ArrayList<Integer> sizes) {
        for (StockDataManager manager : managers) {
            for (int i = 0; i < 1000; i++) {
                manager.addOrUpdateStock("WARM" + i, Math.random() * 100, (long) (Math.random() * 1000000), (long) (Math.random() * 1000000000));
                manager.searchStock("WARM" + i);
                manager.removeStock("WARM" + i);
            }
        }
    }
}
