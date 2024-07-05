import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Represents an inventory of electronic devices.
 */
public class Inventory {
    private List<ArrayList<Device>> categoryList;

    /**
     * Constructs a new Inventory object.
     * Initializes the categoryList with empty ArrayLists for each category.
     * 
     * @timeComplexity: O(n), where n is the number of categories (5 in this case).
     */
    public Inventory(){
        categoryList = new LinkedList<>();
        for(int i = 0; i < 5; i++){
            categoryList.add(new ArrayList<>());
        }
    }

    /**
     * Retrieves the quantity of a device with the given name.
     * 
     * @param name The name of the device.
     * @return The quantity of the device, or 0 if not found.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public int getQuantity(String name){
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){
                if(device.getName().equalsIgnoreCase(name)){
                    return device.getQuantity();
                }
            }
        }
        return 0;
    }

    /**
     * Retrieves the price of a device with the given name.
     * 
     * @param name The name of the device.
     * @return The price of the device, or 0.0 if not found.
     * 
     * @timeComplexity: Time Complexity: O(n), where n is the total number of devices in all categories.
     */
    public Double getPrice(String name){
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){
                if(device.getName().equalsIgnoreCase(name)){
                    return device.getPrice();
                }
            }
        }
        return 0.0;
    }

    /**
     * Adds a device to the inventory.
     * 
     * @param device The device to add.
     * 
     * @timeComplexity:  O(n), where n is the total number of devices in all categories.
     */
    public boolean addDevice(Device device){
        String deviceName = device.getName();
        boolean deviceAlreadyInInventory = false;
        for(ArrayList<Device> deviceList: categoryList){
            for(Device deviceIterator: deviceList){
                if(deviceIterator.getName().equalsIgnoreCase(deviceName)){
                    deviceAlreadyInInventory = true;
                }
            }
        }
        
        if(deviceAlreadyInInventory){
            return false;
        }
        else{
            int deviceCategoryIndex = device.getCategoryIndex();
            categoryList.get(deviceCategoryIndex).add(device);
            return true;
        }   
    }

    /**
     * Removes a device from the inventory by name.
     * 
     * @param name The name of the device to remove.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public boolean removeDevice(String name){
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){
                if(device.getName().equalsIgnoreCase(name)){
                    deviceList.remove(device);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the price and/or quantity of a device.
     * 
     * @param name     The name of the device to update.
     * @param newName  The new name for the device.
     * @param newPrice The new price for the device. Set to -1.0 to keep the current price.
     * @param newQuantity The new quantity for the device. Set to -1 to keep the current quantity.
     * @return true if the device was found and updated, false otherwise.
     * 
     * @timeComplexity: Time Complexity: O(n), where n is the total number of devices in all categories.
     */
    
    public Boolean updateDevice(String name, String newName, Double newPrice, int newQuantity){
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){
                if(device.getName().equalsIgnoreCase(name)){
                    if(!newName.equals("q")){
                        device.setName(newName);
                    }
                    if(newPrice != -1.0){
                        device.setPrice(newPrice);
                    }
                    if(newQuantity != -1){
                        device.setQuantity(newQuantity);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Displays all devices in the inventory.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public void displayAllDevices(){
        int index = 1;
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){
                System.out.print(index + ". ");
                System.out.println(device.toString());
                index++;
            }
        }
    }
    
    /**
     * Finds and displays the cheapest device in the inventory.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public void findCheapestDevice(){
        Device cheapestDevice = null;
        double minPrice = Double.MAX_VALUE;
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){ 
                if(device.getPrice() < minPrice){
                    minPrice = device.getPrice();
                    cheapestDevice = device;
                }
            }
        }
        if(cheapestDevice != null){
            System.out.println(cheapestDevice.toString());
        }
    
        else{
            System.out.println("There is no device in the inventory");
        }
    }

    /**
     * Sorts and displays all devices in the inventory by price.
     * 
     * @timeComplexity: Time Complexity: O(n log n), where n is the total number of devices in all categories.
     */
    public void sortDevicesByPrice(){
        List<Device> allDevices = new LinkedList<>();
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){ 
                allDevices.add(device);
            }
        }
        allDevices.sort(Comparator.comparingDouble(Device::getPrice));
        int index = 1;
        for(Device device: allDevices){
            System.out.print(index + ". ");
            System.out.println(device.toString());
            index++;
        }
    }

    /**
     * Calculates the total value of the inventory.
     * 
     * @return The total value of the inventory.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public Double calculateTotalValue(){
        double totalValue = 0;
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){ 
                totalValue += device.getPrice() * device.getQuantity();
            }
        }
        return totalValue;
    }

    /**
     * Restocks a device in the inventory by name.
     * 
     * @param name     The name of the device to restock.
     * @param quantity The quantity to add to the current quantity.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public void restockDevice(String name, int quantity){
        for(ArrayList<Device> deviceList: categoryList){
            for(Device device: deviceList){ 
                if(device.getName().equalsIgnoreCase(name)){
                    device.setQuantity(quantity + device.getQuantity());
                    if(quantity > 0){
                        System.out.println(name + " restocked. New quantity: " + device.getQuantity());
                    }
                    else if(quantity == 0){
                        System.out.println(name + " stock doesn't change. New quantity: " + device.getQuantity());
                    }
                    else{
                        System.out.println(name + " stock reduced. New quantity: " + device.getQuantity());
                    }
                    return;
                }
            }
        }

    }

    /**
     * Exports an inventory report to the "report.txt file.
     * 
     * @timeComplexity: O(n), where n is the total number of devices in all categories.
     */
    public void exportInventoryReport(){
        String fileName = "report.txt";
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            writer.println("Electronics Shop Inventory Report");
            writer.println("Generated on: " + LocalDate.now());
            writer.println();
            writer.println("---------------------------------------------------------------------------------");
            writer.printf("| %-5s | %-15s | %-25s | %-10s | %-10s |\n", "No.", "Category", "Name", "Price", "Quantity");
            writer.println("---------------------------------------------------------------------------------");
    
            int index = 0;
    
            for (ArrayList<Device> deviceList : categoryList) {
                for (Device device : deviceList) {
                    index++;
                    writer.printf("| %-5d | %-15s | %-25s | %-10f | %-10d |\n", index, device.getCategory(), device.getName(), device.getPrice(), device.getQuantity());
                }
            }
    
            writer.println("---------------------------------------------------------------------------------");
            writer.println();
            writer.println("Summary:");
            writer.println("-Total Number of Devices: " + index);
            writer.println("-Total Inventory Value: " + calculateTotalValue());
            writer.println();
            writer.println("End of Report");
    
            System.out.println("Inventory report exported to " + fileName);
    
            writer.close();
        } catch (Exception e) {
            System.err.println("Error exporting inventory report: " + e.getMessage());
        }
    }
}