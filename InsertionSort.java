import java.util.Random;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            
            // Shift elements to the right to create space
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            
            // Place key in its correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] array = new int[10];

        // Generate random numbers between 1 and 100
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) + 1;
        }

        System.out.println("Unsorted Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }

        // Perform Insertion Sort
        insertionSort(array);

        System.out.println("\nSorted Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
