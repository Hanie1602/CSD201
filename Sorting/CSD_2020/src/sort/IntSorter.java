/*
    Class implements some sorting algorithms on array of integers
    @author Su Than Van
 */
package sort;

import java.util.LinkedList;

public class IntSorter {

    public static void swap(int a[], int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    //Selection sort: O(n^2)
    //Get index of minimum value from start to end position in a[]
    public static int getMinIndex(int a[], int first, int last) {
        int minIndex = first;
        for (int i = first + 1; i <= last; i++) {    //O(n)
            if (a[minIndex] > a[i]) 
                minIndex = i;   
        }
        return minIndex;
    }
    
    public static void selectionSort(int a[], int n){   //O(n^2)
        int minIndex;
        for(int i =0; i<n-1; i++) { //O(n)
            minIndex = getMinIndex(a, i, n-1);  //O(n)
            swap(a, minIndex, i);
        }
    }
    
    //Inseertion sort: O (n^2) but no swap occurs
    public static void inseertionSort(int a[], int n){
        int i, j, tmp;
        for(i=1; i<n; i++){ //O(n)
            tmp=a[i];
            //Determine the right postion of tmp
            j=i-1;
            while(j >= 0 && a[j]>tmp){  //O(n)
                a[j+1] = a[j];  //shift right all elements > tmp
                j--;
            }
            //Put tmp to the right position
            a[j+1] = tmp;
        }
    }
    
    //Bubble sort: O(n^2)
    public static void bubbleSort(int a[], int n){
        int i, j;
        for(i=0; i<n-1; i++)   //O(n)
            for(j=n-1; j>i; j--) //O(n)
                if(a[j] < a[j-1])   swap(a, j, j-1);             
    }
    
    //Quick sort 1: O(nlogn)
    //Pivote is the first element of the group
    public static void quickSort(int a[], int first, int last){
        //Choose the pivoteIndex and pivote
        int pivoteIndex = first;    //You can choose other ways to test
        int pivote = a[pivoteIndex];
        //swap(a, first, pivoteIndex); //move the pivote to the beginning
        int lower = first + 1, upper = last;
        while(lower <= upper) { //partitioning the group to 2 sub-groups
            //At the left side, chose the first element having value >= pivote
            while(lower <= upper && a[lower] < pivote)  lower++;
            //At the right side, choose the first element having value < pivote
            while(lower <= upper && a[upper] >= pivote) upper--;
            if(lower < upper)   swap(a, lower++, upper--);
            else lower++;
        }
        
        //Upper is the suitable position fr the pivote (a[first])
        swap(a, upper, first);
        if(first < upper-1) quickSort(a, first, upper-1);   //sort lft subarray
        if(upper+1 < last)  quickSort(a, upper+1, last);    //sort right subarray
    }
    
    public static void quickSort (int a[], int n){
        quickSort(a, 0, n-1);
    }
    
    //Merge sort: O(nlogn)
    static int[] temp;  //Temporary array for merging
    //Array contains 2 sorted subarrays: a[first]..a[mid], a[mid+1]..a[last]
    //Merge 2 sorted sub-arrays to a sorted array
    private static void merge(int[] a, int first, int last){
        int mid = (first + last) / 2;
        int i = first;  //index to left subarray
        int j = mid + 1;    //index to right subarray
        int k = 0;  //index to the array temp
        //Merging
        while(i <= mid && j <= last)    // WHILE NOT COMPLETED
            if(a[i] < (a[j]))   temp[k++] = a[i++];
            else temp[k++] = a[j++];
        while(i <= mid) temp[k++] = a[i++]; //copy remainder in left side
        while(j <= last)    temp[k++] = a[j++]; //copy remainder in right side
        
        //Recovery data in a[]: copy temp[] to a[]
        for(k=0, i=first; i<=last; a[i++] = temp[k++]);
    }
    
    //merge sort a[] from index first to index last
    private static void mergesort(int[] a, int first, int last){
        int mid = (first + last) / 2;
        if(first < mid) mergesort(a, first, mid);   //merge sort left subarray
        if(mid+1 < last)    mergesort(a, mid+1, last);  //merge sort right subarray
        merge(a, first, last);  //Merge 2 subarrays
    }
    
    public static void mergeSort(int[] a, int n){
        if(n<2) return;
        temp = new int[n];  //allocate memory of the array temp
        mergesort(a, 0, n-1);
    }
    
    //Heap sort: O(nlogn)
    public static void moveDown(int[] a, int first, int last){
        //father = first, Left = 2*father + 1, Right = Left+1
        int largest = 2*first+1;    //suppose that largest is left child
        while(largest <= last){
            //Checking the right child is greater left child?
            if(largest < last && a[largest] < a[largest+1]) largest++;
            if(a[first] < a[largest]){  //father < child
                swap(a, first, largest);    //swap father and largest child
                first = largest;    //move down to sub-tree
                largest = 2*first + 1;
            }
            else largest = last + 1;    //for exist the loop
        }
    }
    
    public static void heapSort(int a[], int n){
        //tranfer the initial array to max heap
        for(int i = n/2 - 1; i >= 0; --i)   moveDown(a, i, n-1);
        for(int i = n-1; i >= 1; --i){
            swap(a, 0, i);  //move the largest value to the end of group
            moveDown(a, 0, i-1);    //transfer the remainder to max heap
        }
    }
    
    //Get number of digits in a positive integer
    public static int countDigits(int n){
        return (int)(Math.log10(n)) + 1;
    }
    
    public static int maxAbsolute(int a[], int n){
        int result = Integer.MIN_VALUE;
        for(int x : a){
            if(x<0) x = -x;
            if(result < x)  result = x;
        }
        return result;
    }
    
    public static void radixSort(int a[], int n){
        int RADIX = 10;
        int longest = maxAbsolute(a, n);
        int digits = countDigits(longest);
        int d, j, k, factor;
        //Create 10 queues
        LinkedList<Integer>[] queues = new LinkedList[RADIX];
        for(d=0; d<RADIX; d++)  queues[d] = new LinkedList();
        factor = 1;
        int queueIndex;
        for(d=1; d <= digits; d++){
            //Move elements in array to suitable queues based on the dth digits
            for(j=0; j<n; j++){
                queueIndex = (a[j]/factor) % RADIX;
                queues[queueIndex].addLast(a[j]);
            }
            
            //Move values in queues to the array
            k=0;    //array index
            for(j=0; j<RADIX; j++)
                while(!queues[j].isEmpty()) a[k++] = queues[j].removeFirst();
            factor *= RADIX;
        }
    }
}   //End of he IntSorter class
