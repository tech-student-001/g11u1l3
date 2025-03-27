import java.util.Random;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] array = new int[10];

        // Populate array with random numbers from 1 to 100
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(100) + 1; // Generates numbers from 1 to 100
        }

        System.out.println("Original Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        
        selectionSort(array);

        System.out.println("\nSorted Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
