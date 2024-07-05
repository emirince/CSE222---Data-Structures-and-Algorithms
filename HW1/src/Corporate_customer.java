public class Corporate_customer extends Customer{
    private String company_name;

    public Corporate_customer(String name, String surname, String address, String phone, String ID, String operator_ID, String company_name){
        super("corporate", name, surname, address, phone, ID, operator_ID);
        this.company_name = company_name;
    }

    public void print_customer(){
        System.out.println("*** Customer Screen ***");
        System.out.println("Name & Surname: " + super.getName() + " " + super.getSurname());
        System.out.println("Adress: " + super.getAddress());
        System.out.println("Phone: " + super.getPhone());
        System.out.println("ID: " + super.getID());
        System.out.println("Operator ID: " + super.getOperatorID());
        System.out.println("Company Name: " + company_name);
        if(super.getNumberOfOrders() != 0){
            for(int i = 0; i < super.getNumberOfOrders(); i++){
                System.out.print("Order #" + (i+1) + " => ");
                super.getOrders()[i].print_order();                
            }
        }
    }

    public int getOperatorID(){
        return super.getOperatorID();
    }
}