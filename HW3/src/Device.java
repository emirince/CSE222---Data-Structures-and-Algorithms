/**
 * This interface represents a generic electronic device.
 * Implementing classes should provide concrete implementations for the methods defined here.
 */
public interface Device {
    
    /**
     * Returns the category of the device.
     * 
     * @return The category of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the category attribute.
     */
    String getCategory();
    
    /**
     * Returns the name of the device.
     * 
     * @return The name of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the name attribute.
     */
    String getName();
    
    /**
     * Returns the price of the device.
     * 
     * @return The price of the device. 
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the price attribute.
     */
    Double getPrice();
    
    /**
     * Returns the quantity of the device.
     * 
     * @return The quantity of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the quantity attribute.
     */
    int getQuantity();

    /**
     * Returns the index of the category.
     * 
     * @return The index of the category.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly retrieves the category index attribute.
     */
    int getCategoryIndex();

    /**
     * Sets the category of the device.
     * 
     * @param category The category to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the category attribute.
     */
    void setCategory(String category);

    /**
     * Sets the name of the device.
     * 
     * @param name The name to set.
     *  
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the name attribute.
     */
    void setName(String name);

    /**
     * Sets the price of the device.
     * 
     * @param price The price to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the price attribute.
     */
    void setPrice(Double price);

    /**
     * Sets the quantity of the device.
     * 
     * @param quantity The quantity to set.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it directly sets the quantity attribute.
     */
    void setQuantity(int quantity);

    /**
     * Returns a string representation of the device.
     * 
     * @return A string representation of the device.
     * 
     * @timeComplexity: O(1) - Constant time complexity as it constructs a string directly from attributes.
     */
    String toString();
}
