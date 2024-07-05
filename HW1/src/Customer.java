public class Customer extends Person{
    private Order [] orders;
    private int operator_ID;
    private int numberOfOrders;
    private String customerType;

    public Customer(String customerTypeString, String name, String surname, String address, String phone, String ID, String operator_ID_String){
        super(name, surname, address, phone, ID);
        customerType = customerTypeString;
        orders = new Order[10];
        numberOfOrders = 0;
        operator_ID = Integer.parseInt(operator_ID_String);
    }

    public void print_customer(){
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + super.getName() + " " + super.getSurname());
        System.out.println("Adress: " + super.getAddress());
        System.out.println("Phone: " + super.getPhone());
        System.out.println("ID: " + super.getID());
        System.out.println("Operator ID: " + operator_ID);
    }

    public void print_orders(){
        if(numberOfOrders != 0){
            for(int i = 0; i < numberOfOrders; i++){
                System.out.print("Order #" + (i+1) + " => ");
                orders[i].print_order();
            }
        }
    }

    public void setName(String customerName){
        super.setName(customerName);
    } 

    public void setSurname(String customerSurname){
        super.setSurname(customerSurname);
    }

    public void setAddress(String customerAddress){
        super.setAddress(customerAddress);
    }

    public void setPhone(String customerPhone){
        super.setPhone(customerPhone);
    }

    public void setID(int customerID){
        super.setID(customerID);
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

    public void setOperatorID(int operator_ID){
        this.operator_ID = operator_ID;
    }

    public int getOperatorID(){
        return operator_ID;
    }
    
    public Order[] getOrders(){
        return orders;
    }

    public int getNumberOfOrders(){
        return numberOfOrders;
    }

    public void incrementNumberOfOrders(){
        numberOfOrders++;
    }


    public void define_orders(Order [] orderList){
        for(int i = 0; i < orderList.length; i++){
            if(super.getID() == orderList[i].getCustomerID()){
                orders[numberOfOrders] = orderList[i];
                numberOfOrders++;
            }
        }
    }


    public String getCustomerType(){
        return customerType;
    }

}