public class QuickSort extends SortAlgorithm {

    public QuickSort(int input_array[]) {
        super(input_array);
    }

    private int partition(int low, int high) {
        // Choose the pivot element (in this implementation, the last element)
        int pivot = arr[high];
        // Index of smaller element
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparison_counter++; // Increment the comparison counter when a comparison is made.
            // If current element is smaller than or equal to pivot
            if (arr[j] < pivot) {
                // Increment index of smaller element and swap arr[i] and arr[j]
                i++;
                swap(i, j);
            }
        }
        // Swap arr[i+1] and arr[high] (or the pivot element)
        swap(i + 1, high);
        // Return the partition index
        return i + 1;
    }

    private void sort(int low, int high) {
        // Recursive sorting function
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(low, high);
            // Separately sort elements before partition and after partition
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    @Override
    public void sort() {
        // Call the recursive sort method to sort the entire array
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
        System.out.print("Quick Sort\t=>\t");
        super.print();
    }
}
