public class Retail_customer extends Customer{

    public Retail_customer(String name, String surname, String address, String phone, String ID, String rc_operator_ID){
        super("retail", name, surname, address, phone, ID, rc_operator_ID);
    }

    public void print_customer(){
        super.print_customer();
    }
    
}