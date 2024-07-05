public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    public void sort() {
        // Get the length of the array
        int n = arr.length;
        // Iterate through the array
        for (int i = 0; i < n-1; i++) {
            // Assume the current index is the minimum index
            int minIndex = i;
            // Iterate through the unsorted part of the array to find the minimum element
            for (int j = i+1; j < n; j++) {
                comparison_counter++; // Increment the comparison counter when a comparison is made.
                // If the current element is smaller than the element at minIndex, update minIndex
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the minimum element with the current element (at index i)
            swap(i, minIndex);
        }
    }
    

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
