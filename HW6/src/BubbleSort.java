public class BubbleSort extends SortAlgorithm {

	public BubbleSort(int input_array[]) {
		super(input_array);
	}
	
    @Override
    public void sort() {
        // Get the length of the array
        int n = arr.length;
        // Initialize a flag to track whether any swap has occurred
        boolean swapped;
        // Iterate through the array
        for (int i = 0; i < n-1; i++) {
            swapped = false; // Reset the swapped flag for each pass
            // Iterate through the unsorted part of the array
            for (int j = 0; j < n-i-1; j++) {
                comparison_counter++; // Increment the comparison counter when a comparison is made.
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j+1]) {
                    swap(j, j+1);
                    swapped = true; // Set the flag to true indicating that a swap has occurred
                }
            }
            // If no elements were swapped in this pass, the array is already sorted, so break out of the loop
            if (!swapped) {
                break;
            }
        }
    }


    
    @Override
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
