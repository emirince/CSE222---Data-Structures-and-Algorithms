import java.util.Scanner;
import java.io.File;

public class Main {    

    public static void main(String[] args) {
        // Define the file path
        String filePath = "content.txt";
        // Initialize counters for different types of data
        int counter = 0;
        // Initialize arrays to store different types of data
        String [][] orders = new String[10][];
        String [][] operators = new String[10][];
        String [][] retail_customers = new String[10][];
        String [][] corporate_customers = new String[10][];
        
        try { 
            // Read from the file
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            // Loop through each line in the file
            while (scanner.hasNextLine()) {
                // Flag to check if any field is blank
                int isBlank = 0;
                // Read the line
                String datas = scanner.nextLine();
                // Split the line by semicolon
                String[] pieces = datas.split(";");

                // Check the type of data and validate the number of fields
                if(pieces[0].equals("order")){
                    if(pieces.length != 6){
                        continue; // Skip invalid data
                    }
                }
                else if(pieces[0].equals("operator")){
                    if (pieces.length != 7){
                        continue;    
                    }
                }
                else if(pieces[0].equals("retail_customer")){
                    if(pieces.length != 7){
                        continue;
                    } 
                }
                else if(pieces[0].equals("corporate_customer")){
                    if(pieces.length != 8){
                        continue;
                    }
                }
                else{
                    continue; // Skip unknown data types
                }

                // Check for blank fields
                for(int i = 0; i < pieces.length; i++){
                    if(pieces[i].isEmpty()){
                        isBlank = 1;
                    }
                } 

                // Skip if any field is blank
                if(isBlank == 1){
                    continue;
                }
                // Increment counter for valid data
                counter++;
            }   
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } 

        // Store validated data in a separate array
        String [][] datasAfterSemicolumn = new String[counter][];

        counter = 0;

        try { 
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int isBlank = 0;
                String datas = scanner.nextLine();
                String[] pieces = datas.split(";");

                if(pieces[0].equals("order")){
                    if(pieces.length != 6){
                        continue;
                    }
                }
                else if(pieces[0].equals("operator")){
                    if (pieces.length != 7){
                        continue;    
                    }
                }
                else if(pieces[0].equals("retail_customer")){
                    if(pieces.length != 7){
                        continue;
                    } 
                }
                else if(pieces[0].equals("corporate_customer")){
                    if(pieces.length != 8){
                        continue;
                    }
                }
                else{
                    continue;
                }

                for(int i = 0; i < pieces.length; i++){
                    if(pieces[i].isEmpty()){
                        isBlank = 0;
                    }
                } 

                if(isBlank == 1){
                    continue;
                }

                datasAfterSemicolumn[counter] = pieces;
                counter++;
            }   
            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }    

        // Array to store unique data
        String[][] uniqueDatas = new String[20][];
        int uniqueIndex = 0;
                
        // Check for unique entries among retail customers, corporate customers, and operators
        for (int i = 0; i < datasAfterSemicolumn.length; i++) {
            if (datasAfterSemicolumn[i][0].equals("retail_customer") || datasAfterSemicolumn[i][0].equals("corporate_customer") || datasAfterSemicolumn[i][0].equals("operator")) {
                boolean isUnique = true;
                for (int j = 0; j < uniqueIndex; j++) {
                    if (datasAfterSemicolumn[i][5].equals(uniqueDatas[j][5])) {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique) {
                    uniqueDatas[uniqueIndex] = datasAfterSemicolumn[i];
                    uniqueIndex++;
                }
            }
        }

        // Include orders in unique data
        for(int i = 0; i < counter; i++){
            if(datasAfterSemicolumn[i][0].equals("order")){
                uniqueDatas[uniqueIndex] = datasAfterSemicolumn[i];
                uniqueIndex++;
            }
        }

        // Trim the unique data array to remove unused space
        String [][] uniqueDatasUpdated = new String[uniqueIndex][];

        for(int i = 0; i < uniqueIndex; i++){
            uniqueDatasUpdated[i] = uniqueDatas[i];
        }

        // Initialize counts for different types of objects
        int orderCount = 0;
        int operatorCount = 0;
        int retailCustomerCount = 0;
        int corporateCustomerCount = 0;
        
        // Create objects for different types of data
        for(int i = 0; i < uniqueIndex; i++){
            
            if(uniqueDatasUpdated[i][0].equals("order")){

                int count =  Integer.parseInt(uniqueDatasUpdated[i][2]);
                int price =  Integer.parseInt(uniqueDatasUpdated[i][3]);
                int status = Integer.parseInt(uniqueDatasUpdated[i][4]);
                int customer_id = Integer.parseInt(uniqueDatasUpdated[i][5]);

                // Validate data before creating objects
                if((count <= 0 || count > Integer.MAX_VALUE) || (price < 0 || price > Integer.MAX_VALUE) || (status < 0 || status > Integer.MAX_VALUE) || (customer_id < 0 || customer_id > Integer.MAX_VALUE)){
                    continue;
                }

                else{
                    orders[orderCount] = uniqueDatasUpdated[i];
                    orderCount++;
                }
            }

            else if(uniqueDatasUpdated[i][0].equals("operator")){

                int id = Integer.parseInt(uniqueDatasUpdated[i][5]);
                int wage = Integer.parseInt(uniqueDatasUpdated[i][6]);

                if((id < 0 || id > Integer.MAX_VALUE) || (wage < 0 || wage > Integer.MAX_VALUE)){
                    continue;
                }
                
                else{
                    operators[operatorCount] = uniqueDatasUpdated[i];
                    operatorCount++;
                }    
            }

            else if(uniqueDatasUpdated[i][0].equals("corporate_customer") ){

                int id = Integer.parseInt(uniqueDatasUpdated[i][5]);
                int operator_id = Integer.parseInt(uniqueDatasUpdated[i][6]);

                if((id < 0 || id > Integer.MAX_VALUE) || (operator_id < 0 || operator_id > Integer.MAX_VALUE)){
                    continue;
                }
                else{
                    corporate_customers[corporateCustomerCount] = uniqueDatasUpdated[i];
                    corporateCustomerCount++;
                }
                
            }

            else if(uniqueDatasUpdated[i][0].equals("retail_customer")){

                int id = Integer.parseInt(uniqueDatasUpdated[i][5]);
                int operator_id = Integer.parseInt(uniqueDatasUpdated[i][6]);

                if((id < 0 || id > Integer.MAX_VALUE) || (operator_id < 0 || operator_id > Integer.MAX_VALUE)){
                    continue;
                }
                else{
                    retail_customers[retailCustomerCount] = uniqueDatasUpdated[i];
                    retailCustomerCount++;
                }
            }
        }

        // Check if all required data types are present
        if(orderCount == 0 || operatorCount == 0 ||  retailCustomerCount == 0 || corporateCustomerCount == 0){
            System.err.println("A line in the text file should have all of the items described above, according to its type (order, retail_customer, corporate_customer, or operator)");
            System.exit(1);
        }

        // Create arrays of objects for different types of data
        Order[] orderObjects = new Order[orderCount];
        Operator[] operatorObjects = new Operator[operatorCount];
        Retail_customer[] retailCustomerObjects = new Retail_customer[retailCustomerCount];
        Corporate_customer[] corporateCustomerObjects = new Corporate_customer[corporateCustomerCount];

        // Initialize objects with data
        for(int i = 0; i < orderCount; i++){
            orderObjects[i] = new Order(orders[i][1], orders[i][2], orders[i][3], orders[i][4], orders[i][5]);
        }

        for(int i = 0; i < operatorCount; i++){
            operatorObjects[i] = new Operator(operators[i][1], operators[i][2], operators[i][3], operators[i][4], operators[i][5], operators[i][6]);
        }

        for(int i = 0; i < retailCustomerCount; i++){
            retailCustomerObjects[i] = new Retail_customer(retail_customers[i][1], retail_customers[i][2], retail_customers[i][3], retail_customers[i][4], retail_customers[i][5], retail_customers[i][6]);
        }

        for(int i = 0; i < corporateCustomerCount; i++){
            corporateCustomerObjects[i] = new Corporate_customer(corporate_customers[i][1], corporate_customers[i][2], corporate_customers[i][3], corporate_customers[i][4], corporate_customers[i][5], corporate_customers[i][6], corporate_customers[i][7]);
        }

        // Define orders for retail customers and corporate customers
        for(int i = 0; i < retailCustomerCount; i++){
            retailCustomerObjects[i].define_orders(orderObjects);
        }

        for(int i = 0; i < corporateCustomerCount; i++){
            corporateCustomerObjects[i].define_orders(orderObjects);
        }
        
        // Associate customers with operators
        for(int i = 0; i < operatorCount; i++){
            operatorObjects[i].define_customer(retailCustomerObjects);
        }

        for(int i = 0; i < operatorCount; i++){
            operatorObjects[i].define_customer(corporateCustomerObjects);
        }
        
        // Check if any operator, retail customer, or corporate customer exists with the given ID
        //boolean isThereAnyOperator = false;
        //boolean isThereAnyRetailCustomer = false;
        //boolean isThereAnyCorporateCustomer = false;

        int isThereAnyOperator = 0;
        int isThereAnyRetailCustomer = 0;
        int isThereAnyCorporateCustomer = 0;

        System.out.println("Please enter your ID...");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.close();

        for(int i = 0; i < operatorCount; i++){
            if(operatorObjects[i].getID() == input){
                operatorObjects[i].print_operator();
                operatorObjects[i].print_customer();
                isThereAnyOperator = 1;
            }
        }

        if(isThereAnyOperator == 1){
            System.exit(0);
        }

        else{
            for(int i = 0; i < retailCustomerCount; i++){
                if(retailCustomerObjects[i].getID() == input){
                    retailCustomerObjects[i].print_customer();
                    isThereAnyRetailCustomer = 1;
                }
            }

            if(isThereAnyRetailCustomer == 1){
                System.exit(0);
            }

            else{
                for(int i = 0; i < corporateCustomerCount; i++){
                    if(corporateCustomerObjects[i].getID() == input){
                        corporateCustomerObjects[i].print_customer();
                        isThereAnyCorporateCustomer = 1;
                    }
                }

                if(isThereAnyCorporateCustomer == 1){
                    System.exit(0);
                }

                else{
                    System.out.println("No operator/customer was found with ID " +  input + ". Please try again.");
                }
    
            }
        }
    }
}
