public class Operator extends Person{
    private int wage;
    private Customer[] customers;
    private int numberOfCustomers;

    public Operator(String operator_name, String operator_surname, String operator_address, String operator_phone, String operator_ID, String operator_wage){
        super(operator_name, operator_surname, operator_address, operator_phone, operator_ID);
        wage = Integer.parseInt(operator_wage);
        customers = new Customer[10];
        numberOfCustomers = 0;
    }

    public String getName(){
        return super.getName();
    }

    public String getSurname(){
        return super.getSurname();
    }

    public String getAddress(){
        return super.getAddress();
    }

    public String getPhone(){
        return super.getPhone();
    }

    public int getID(){
        return super.getID();
    }

    public int getWage(){
        return wage;
    }

    public int getNumberOfCustomers(){
        return numberOfCustomers;
    }

    public void print_operator(){
        System.out.println("*** Operator Screen ***");
        System.out.println("----------------------------");
        System.out.println("Name & Surname: " + super.getName() + " " + super.getSurname());
        System.out.println("Adress: " + super.getAddress());
        System.out.println("Phone: " + super.getPhone());
        System.out.println("ID: " + super.getID());
        System.out.println("Wage: " + wage);
        System.out.println("----------------------------");
    }

    public void print_customer(){
        if(numberOfCustomers == 0){
            System.out.println("This operator doesn't have any customer.");
            System.out.println("----------------------------");
        }
        else{
            for(int i = 0; i < numberOfCustomers; i++){                
                if(customers[i].getCustomerType() == "retail"){
                    System.out.println("Customer #" + (i+1) + " (a retail customer) :");
                }
                else if(customers[i].getCustomerType() == "corporate"){
                    System.out.println("Customer #" + (i+1) + " (a corporate customer) :");
                }
                System.out.println("Name & Surname: " + customers[i].getName() + " " + customers[i].getSurname());
                System.out.println("Adress: " + customers[i].getAddress());
                System.out.println("Phone: " + customers[i].getPhone());
                System.out.println("ID: " + customers[i].getID());
                System.out.println("Operator ID: " + customers[i].getOperatorID());
                if(customers[i].getNumberOfOrders() != 0){
                    for(int z = 0; z < customers[i].getNumberOfOrders(); z++){
                        System.out.print("Order # " + (z+1) + " => Product Name: " + customers[i].getOrders()[z].getProductName() + " - Count: " + customers[i].getOrders()[z].getCount() + " - Total Price: " + customers[i].getOrders()[z].getTotalPrice());
                        if(customers[i].getOrders()[z].getStatus() == 0){
                            System.out.println(" - Status: Initialized");
                        }
                        else if(customers[i].getOrders()[z].getStatus() == 1){
                            System.out.println(" - Status: Processing");
                        }

                        else if(customers[i].getOrders()[z].getStatus() == 2){
                            System.out.println(" - Status: Completed");
                        }

                        else if(customers[i].getOrders()[z].getStatus() == 3){
                            System.out.println(" - Status: Cancelled");
                        }
                        
                    }
                }
                System.out.println("----------------------------");
            }
        }
    }

    public void define_customer(Customer [] customerList){
        for(int i = 0; i < customerList.length; i++){
            if(super.getID() == customerList[i].getOperatorID()){
                customers[numberOfCustomers] = customerList[i];
                numberOfCustomers++;
            }
        }
    }
}