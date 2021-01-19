package search;

import java.util.Arrays;

//要在有序數組中實行
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr, 89));// 0

    }

    //要先創建好fib的數組
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;
        int mid = 0;
        int f[] = fib();

        System.out.println("fib=" +Arrays.toString(f));

        while(high > f[k] - 1) {
            k++;
        }

        int[] temp = Arrays.copyOf(a, f[k]);

        for(int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;

                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;

                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
