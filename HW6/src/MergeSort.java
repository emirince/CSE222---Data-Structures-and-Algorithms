public class MergeSort extends SortAlgorithm {
    
    public MergeSort(int input_array[]) {
        super(input_array);
    }
    
    private void merge(int l, int m, int r) {
        // Calculate the sizes of the left and right subarrays
        int n1 = m - l + 1;
        int n2 = r - m;
        
        // Create temporary arrays
        int L[] = new int[n1];
        int R[] = new int[n2];
        
        // Copy elements of the main array to temporary arrays
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }

        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        
        // Merge the two subarrays
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            comparison_counter++; // Increment the comparison counter when a comparison is made.
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of L[] and R[], if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    
    private void sort(int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
            
            // Sort the left and right subarrays
            sort(l, m);
            sort(m + 1, r);
            
            // Merge the sorted subarrays
            merge(l, m, r);
        }
    }
    
    @Override
    public void sort() {
        // Call the recursive sort method to sort the entire array
        sort(0, arr.length - 1);
    }

    
    @Override
    public void print() {
        System.out.print("Merge Sort\t=>\t");
        super.print();
    }
}
