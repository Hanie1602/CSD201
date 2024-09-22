/* Test DW algorithm for balancing a BST author SuTV*/
public class DSW_Tester {

    public static Integer[] createIntArr(int max) {
        Integer[] a = new Integer[max];
        for (int i = 0; i < max; i++) {
            a[i] = max - i;
        }
        return a;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        int max = 14;   //You can change this number for testing more
        Integer a[] = createIntArr(max);
        //Create a BST of integers which isfull left degraded
        tree.add_Array(a);
        System.out.println("\nInitial tree:\n");
        tree.printAligned();
        tree.DSW_Balance();
        System.out.println("\nBalanced tree:\n");
        tree.printAligned();
    }
}//DSW_Tester
