import java.util.Arrays;

public class SortAndSearch {
    private static int[] data1 = {25, 10, 32, 8, 1, 4, 9, 15};
    private static int[] data2 = {1, 6, 8, 9, 10, 6, 54, 3};
    private static int[] data3 = {2, 65, 21, 42, 63, 28, 8};
    public static void main(String[] args) {
        System.out.println("\nSequential Search");
        sequentialSearch(8, data1, data2, data3);
        System.out.println("\nSequential Sort");
        sequentialSort(data1, data2, data3);
        System.out.println();
        System.out.println("\nBinary Search");
        binarySearch(8, data1, data2, data3);
        System.out.println();
        System.out.println("\nQuickSort");
        quickSort(data1, data2, data3);
        System.out.println();
        System.out.println("\nBubble Sort");
        bubbleSort(data1, data2, data3);
        System.out.println();
        System.out.println("\nInsertion Sort");
        insertionSort(data1, data2, data3);
        System.out.println();
        System.out.println("\nMerge Sort");
        mergeSort(data1, data2, data3);
    }
    private static void sequentialSort(int[]... data){
        Sequential s = new Sequential();
        for(int[] d : data){
            s.sort(d);
            System.out.println(Arrays.toString(d));
        }
    }
    private static void sequentialSearch(int target, int[]... data){
        Sequential s = new Sequential();
        for(int[] d : data){
            System.out.printf("%s%nFound %d at : %d%n", Arrays.toString(d), target, s.search(d, target));
        }
    }
    private static void binarySearch(int target, int[]... data){
        Binary b = new Binary();
        for(int[] d : data){
            System.out.printf("%s%nFound %d at : %d%n", Arrays.toString(d), target, b.search(d, target));
        }
    }
    private static void quickSort(int[]... data){
        quickSort q = new  quickSort();
        for(int[] d : data){
            q.sort(d);
            System.out.println(Arrays.toString(d));
        }
    }
    private static void bubbleSort(int[]... data){
        bubbleSort b = new bubbleSort();
        for(int[] d : data){
            b.sort(d);
            System.out.println(Arrays.toString(d));
        }
    }
    private static void insertionSort(int[]... data){
        insertionSort i = new insertionSort();
        for(int[] d : data){
            i.sort(d);
            System.out.println(Arrays.toString(d));
        }
    }
    private static void mergeSort(int[]... data){
        for(int[] d : data){
            System.out.println(Arrays.toString(d));
        }
    }
}
class Sequential implements Sorting, Searching{
    int[] data;
    public Sequential(){

    }
    public Sequential(int[] arr){
        this.data = arr;
    }
    @Override
    public int[] sort(int[] arr) {
        int min = arr.length > 0 ? 0 : -1;
        if(min == -1) return null;
        for(int i=0; i<arr.length-1; i++){
            min = i;
            for(int j=i; j<arr.length; j++){
                if(arr[min] >= arr[j]){
                    min = j;
                }
            }
            int rest_data = arr[i];
            arr[i] = arr[min];
            arr[min] = rest_data;
        }
        return arr;
    }

    @Override
    public int search(int[] arr, int target) {
        for(int i=0; i<arr.length; i++){
            if(arr[i] == target){
                return i;
            }
        }
        return -1;
    }
}
class Binary implements Searching{
    @Override
    public int search(int[] arr, int target) {
        Arrays.sort(arr);
        return search(arr, 0, arr.length-1, target);
    }
    private int search(int[] arr, int low, int high, int target){
        int mid = (low+high)/2;
        if(arr[mid] == target){
            return mid;
        }
        else if(arr[mid] > target){
            return search(arr, low, mid-1, target);
        }
        else if(arr[mid] > target){
            return search(arr, mid+1, high, target);
        }
        return -1;
    }
}
class quickSort implements Sorting{
    @Override
    public int[] sort(int[] arr) {
        int mid = sort(arr, 0, arr.length-1);
        sort(arr, 0, mid-1);
        sort(arr, mid+1, arr.length-1);
        return arr;
    }
    private int sort(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low;
        if(low >= high) return -1;
        for(int cur=low; cur<=high; cur++){
            if(arr[cur] < pivot){
                swap(arr, cur, i);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }
    private void swap(int[] arr, int low, int high){
        int rest_data = arr[low];
        arr[low] = arr[high];
        arr[high] = rest_data;
    }
}
class mergeSort implements Sorting{
    @Override
    public int[] sort(int[] arr) {
        mergesort(arr, arr.length);
        return arr;
    }
    private void mergesort(int[] a, int n){
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergesort(l, mid);
        mergesort(r, n - mid);
    
        merge(a, l, r, mid, n - mid);
    }
    private void merge(
        int[] a, int[] l, int[] r, int left, int right) {
       
          int i = 0, j = 0, k = 0;
          while (i < left && j < right) {
              if (l[i] <= r[j]) {
                  a[k++] = l[i++];
              }
              else {
                  a[k++] = r[j++];
              }
          }
          while (i < left) {
              a[k++] = l[i++];
          }
          while (j < right) {
              a[k++] = r[j++];
          }
      }
}
class bubbleSort implements Sorting{
    @Override
    public int[] sort(int[] arr) {
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                if(arr[i]<arr[j]){
                    swap(arr, j, i);
                }
            }
        }
        return arr;
    }
    private void swap(int[] arr, int low, int high){
        int rest_data = arr[low];
        arr[low] = arr[high];
        arr[high] = rest_data;
    }
}
class insertionSort implements Sorting{
    @Override
    public int[] sort(int[] arr) {
        int cur = 0;
        for(int i=1; i<arr.length; i++){
            if(arr[cur] >= arr[i]) continue;
            for(int j=i; j>0; j--){
                if(arr[j]>arr[j-1]){
                    swap(arr, i, j);
                }
            }
        }
        return arr;
    }
    private void swap(int[] arr, int i, int j){
        int rest_data = arr[i];
        arr[i] = arr[j];
        arr[j] = rest_data;
    } 
}
interface Sorting{
    public int[] sort(int[] arr);
}
interface Searching{
    public int search(int[] arr, int target);
}

