import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingVisualization extends JPanel {
    private int[] selectionArray, insertionArray;
    private int highlightSelection1 = -1, highlightSelection2 = -1;
    private int highlightInsertion1 = -1, highlightInsertion2 = -1;
    private boolean[] sortedSelection, sortedInsertion;

    public SortingVisualization(int[] array) {
        this.selectionArray = array.clone();
        this.insertionArray = array.clone();
        this.sortedSelection = new boolean[array.length];
        this.sortedInsertion = new boolean[array.length];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawArray(g, selectionArray, 50, "Selection Sort", highlightSelection1, highlightSelection2, sortedSelection);
        drawArray(g, insertionArray, 250, "Insertion Sort", highlightInsertion1, highlightInsertion2, sortedInsertion);
    }

    private void drawArray(Graphics g, int[] array, int yOffset, String title, int h1, int h2, boolean[] sorted) {
        int size = 50;
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString(title, 20, yOffset - 10);

        for (int i = 0; i < array.length; i++) {
            int x = 50 + i * (size + 10);
            if (sorted[i]) {
                g.setColor(Color.GREEN); // Sorted elements in green
            } else if (i == h1 || i == h2) {
                g.setColor(Color.RED); // Highlight comparisons in red
            } else {
                g.setColor(Color.LIGHT_GRAY);
            }
            g.fillRect(x, yOffset, size, size);
            g.setColor(Color.BLACK);
            g.drawRect(x, yOffset, size, size);
            g.drawString(String.valueOf(array[i]), x + 15, yOffset + 30);
        }
    }

    public void visualizeSorting() {
        new Thread(this::selectionSort).start();
        new Thread(this::insertionSort).start();
    }

    private void selectionSort() {
        for (int i = 0; i < selectionArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < selectionArray.length; j++) {
                highlightSelection1 = j;
                highlightSelection2 = minIndex;
                repaintAndPause();
                if (selectionArray[j] < selectionArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = selectionArray[minIndex];
            selectionArray[minIndex] = selectionArray[i];
            selectionArray[i] = temp;
            sortedSelection[i] = true; // Mark as sorted
            highlightSelection1 = highlightSelection2 = -1;
            repaintAndPause();
        }
        sortedSelection[selectionArray.length - 1] = true; // Last element is sorted
        repaint();
    }

    private void insertionSort() {
        for (int i = 1; i < insertionArray.length; i++) {
            int key = insertionArray[i];
            int j = i - 1;
            while (j >= 0 && insertionArray[j] > key) {
                highlightInsertion1 = j;
                highlightInsertion2 = j + 1;
                repaintAndPause();
                insertionArray[j + 1] = insertionArray[j];
                j--;
            }
            insertionArray[j + 1] = key;
            sortedInsertion[i] = true; // Mark sorted elements
            highlightInsertion1 = highlightInsertion2 = -1;
            repaintAndPause();
        }
        repaint();
    }

    private void repaintAndPause() {
        repaint();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        int[] array = new Random().ints(10, 1, 100).toArray();
        JFrame frame = new JFrame("Sorting Algorithm Visualization");
        SortingVisualization panel = new SortingVisualization(array);
        frame.add(panel);
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.visualizeSorting();
    }
}
