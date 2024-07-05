/**
 * Represents a Camera device.
 */
public class Camera implements Device {
    
    private String category;
    private String name;
    private Double price;
    private int quantity;

    private int categoryIndex;

    /**
     * Constructs a new Camera object with the specified name, price, and quantity.
     * 
     * @param name     The name of the camera.
     * @param price    The price of the camera.
     * @param quantity The quantity of the camera.
     */
    public Camera(String name, Double price, int quantity){
        category = "Camera";
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        categoryIndex = 0;
    }

    /**
     * Returns the category of the device.
     * 
     * @return The category of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the category attribute.
     */
    @Override
    public String getCategory(){
        return category;
    }


    /**
     * Returns the name of the device.
     * 
     * @return The name of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the name attribute.
     */
    @Override
    public String getName(){
        return name;
    }


    /**
     * Returns the price of the device.
     * 
     * @return The price of the device. 
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the price attribute.
     */
    @Override
    public Double getPrice(){
        return price;
    }


    /**
     * Returns the quantity of the device.
     * 
     * @return The quantity of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the quantity attribute.
     */
    @Override
    public int getQuantity(){
        return quantity;
    }


    /**
     * Returns the index of the category.
     * 
     * @return The index of the category.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the category index attribute.
     */
    @Override
    public int getCategoryIndex(){
        return categoryIndex;
    }


    /**
     * Sets the category of the device.
     * 
     * @param category The category to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the category attribute.
     */
    @Override
    public void setCategory(String category){
        this.category = category; 
    }


    /**
     * Sets the name of the device.
     * 
     * @param name The name to set.
     *  
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the name attribute.
     */
    @Override
    public void setName(String name){
        this.name = name;
    }


    /**
     * Sets the price of the device.
     * 
     * @param price The price to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the price attribute.
     */
    @Override
    public void setPrice(Double price){
        this.price = price;
    }


    /**
     * Sets the quantity of the device.
     * 
     * @param quantity The quantity to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the quantity attribute.
     */
    @Override
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }


    /**
     * Returns a string representation of the device.
     * 
     * @return A string representation of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it constructs a string directly from attributes.
     */
    @Override
    public String toString(){
        return "Category: " + category + ", Name: " + name + ", Price: " + price + "$, Quantity: " + quantity;
    }

}
