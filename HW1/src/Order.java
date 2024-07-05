    public class Order{
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public Order(String order_product_name, String order_count, String order_total_price, String order_status, String order_customer_id){
        product_name = order_product_name;
        count = Integer.parseInt(order_count);
        total_price = Integer.parseInt(order_total_price);
        status = Integer.parseInt(order_status);
        customer_ID = Integer.parseInt(order_customer_id);
    }

    public String getProductName(){
        return product_name;
    }

    public int getCount(){
        return count;
    }

    public int getTotalPrice(){
        return total_price;
    }

    public int getStatus(){
        return status;
    }

    public int getCustomerID(){
        return customer_ID;
    }

    public void print_order(){
        System.out.print("Product Name: " + product_name + " - Count: " + count + " - Total Price: " + total_price + " - Status: ");
        if(status == 0){
            System.out.println("Initialized");
        }
        else if(status == 1){
            System.out.println("Processing");
        }
        else if(status == 2){
            System.out.println("Completed");
        }
        else if(status == 3){
            System.out.println("Cancelled");
        }
        else{
            System.out.println("Unknown Status");
        }

    }
}