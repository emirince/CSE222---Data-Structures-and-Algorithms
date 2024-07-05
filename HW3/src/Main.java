import java.util.Scanner;

public class Main {public static void main(String[] args) {
        
        int choice;
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        do{
            System.out.println("\nWelcome to the Electronics Inventory Management System!\n");
            System.out.println("Please select an option:");
            System.out.println("1. Add a new device");
            System.out.println("2. Remove a device");
            System.out.println("3. Update device details");
            System.out.println("4. List all devices");
            System.out.println("5. Find the cheapest device");
            System.out.println("6. Sort devices by price");
            System.out.println("7. Calculate total inventory value");
            System.out.println("8. Restock a device");
            System.out.println("9. Export inventory report");
            System.out.println("0. Exit\n");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            if(choice == 1){

                Boolean isDeviceAddSuccess = false;

                System.out.print("Enter category name: ");
                String category = scanner.nextLine();

                System.out.print("Enter device name: ");
                String name = scanner.nextLine();

                System.out.print("Enter price: ");
                Double price = scanner.nextDouble();

                while(price < 0.0 || price > Double.MAX_VALUE){
                    System.out.print("Invalid price input. Enter price again: ");
                    price = scanner.nextDouble();
                }

                System.out.print("Enter quantity: ");
                int quantity = scanner.nextInt();

                while(quantity < 0 || quantity > Integer.MAX_VALUE){
                    System.out.print("Invalid quantity input. Enter quantity again: ");
                    quantity = scanner.nextInt();
                }

                if(category.equalsIgnoreCase("Camera")){
                    isDeviceAddSuccess = inventory.addDevice(new Camera(name, price, quantity));
                }

                else if(category.equalsIgnoreCase("Computer")){
                    isDeviceAddSuccess = inventory.addDevice(new Computer(name, price, quantity));
                }

                else if(category.equalsIgnoreCase("Headphone")){
                    isDeviceAddSuccess = inventory.addDevice(new Headphone(name, price, quantity));
                }

                else if(category.equalsIgnoreCase("Phone")){
                    isDeviceAddSuccess = inventory.addDevice(new Phone(name, price, quantity));
                }

                else if(category.equalsIgnoreCase("TV")){
                    isDeviceAddSuccess = inventory.addDevice(new TV(name, price, quantity));
                }

                else{
                    System.out.println("\nInvalid category name. Please try again.");
                    continue;
                }

                if(isDeviceAddSuccess){
                    System.out.println("\n" + category + ", " + name + ", " + price + "$, " + quantity + " amount added...");
                }

                else{
                    System.out.println("\nThere is already a device named " + name + " in the inventory.");
                }
            }

            else if(choice == 2){
                System.out.print("Enter the name of the device that you want to remove: ");
                String name = scanner.nextLine();

                boolean isRemoved = inventory.removeDevice(name);                

                if(isRemoved){
                    System.out.println(name + " is removed from the inventory");
                }

                else{
                    System.out.println(name + " is not found in the inventory");
                }
            } 

            else if(choice == 3){
                System.out.print("Enter the name of the device to update: ");
                String name = scanner.nextLine();

                System.out.print("Enter new name (leave blank to keep current price): ");
                String newName = scanner.nextLine();

                boolean isNameUpdated = false;

                if(newName.isEmpty()){
                    newName = "q";
                }
                else{
                    isNameUpdated = true;
                }

                System.out.print("Enter new price (leave blank to keep current price): ");
                String price = scanner.nextLine();

                Double newPrice;

                if(price.isEmpty()){
                    newPrice = -1.0;
                }
                else{
                    newPrice = Double.parseDouble(price);

                    while(newPrice < 0.0 || newPrice > Double.MAX_VALUE){
                        System.out.print("Invalid price input. Enter new price again: ");
                        newPrice = scanner.nextDouble();
                    }
                }

                System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                String quantity = scanner.nextLine();

                int newQuantity;

                if(quantity.isEmpty()){
                    newQuantity = -1;
                }
                else{
                    newQuantity = Integer.parseInt(quantity);

                    while(newQuantity < 0 || newQuantity > Integer.MAX_VALUE){
                        System.out.print("Invalid quantity input. Enter quantity again: ");
                        newQuantity = scanner.nextInt();
                    }
                }

                Boolean isUpdateSuccess = inventory.updateDevice(name, newName, newPrice, newQuantity);

                if(isUpdateSuccess && isNameUpdated){
                    System.out.println(name + " details update: Name - " + newName + ", Price - " + inventory.getPrice(newName) + "$, Quantity - " + inventory.getQuantity(newName));
                }

                else if(isUpdateSuccess && !isNameUpdated){
                    System.out.println(name + " details update: Name - " + name + ", Price - " + inventory.getPrice(name) + "$, Quantity - " + inventory.getQuantity(name));
                }

                else{
                    System.out.println(name + " is not on the inventory\n");
                }
            } 

            else if(choice == 4){
                System.out.println("Device List: ");
                inventory.displayAllDevices();
            } 

            else if(choice == 5){
                System.out.println("The cheapest device is:");
                inventory.findCheapestDevice();
            } 

            else if(choice == 6){
                System.out.println("Devices sorted by price:");
                inventory.sortDevicesByPrice();
            } 

            else if(choice == 7){
                System.out.print("Total inventory value: ");
                System.out.println(inventory.calculateTotalValue());
            } 

            else if(choice == 8){
                System.out.print("Enter the name of the device to restrock: ");
                String name = scanner.nextLine();

                System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                String addOrRemove = scanner.nextLine();

                int quantity;


                if(addOrRemove.equalsIgnoreCase("Add")){
                    System.out.print("Enter the quantity to add: ");
                    quantity = scanner.nextInt();

                    while(quantity < 0 || quantity > Integer.MAX_VALUE){
                        System.out.print("Invalid quantity input. Enter the quantity to add again: ");
                        quantity = scanner.nextInt();
                    }

                    inventory.restockDevice(name, quantity);
                }
                else if(addOrRemove.equalsIgnoreCase("Remove")){
                    System.out.print("Enter the quantity to remove: ");
                    quantity = scanner.nextInt();

                    while(quantity < 0 || quantity > Integer.MAX_VALUE){
                        System.out.print("Invalid quantity input. Enter the quantity to add again: ");
                        quantity = scanner.nextInt();
                    }

                    inventory.restockDevice(name, -quantity);
                }
                else{
                    System.out.println("Invalid entry.\n");
                }
            } 

            else if(choice == 9){
                inventory.exportInventoryReport();
            } 

            else if(choice == 0){
                System.out.println("Exiting program.");
            }

            else{
                System.out.println("Invalid choice. Please try again.");
            }

        }while(choice != 0);
    
        scanner.close();
    }
    
}
