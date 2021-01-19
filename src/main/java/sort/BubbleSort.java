package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {

        int arr[] = {3, 9, -1, 20, 10};

//        normal(arr);
//        bubbleSort(arr);
        bubbleSortTest();

    }

    public static void normal(int[] arr) {
        int temp = 0;

        //O(n^2)
        for(int i =0; i < arr.length -1; i++) {
            for(int j =0; j < arr.length -1 -i; j++) {
                if(arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("time ---->" + (i+1));
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        //O(n^2)
        for(int i =0; i < arr.length -1; i++) {
            for(int j =0; j < arr.length -1 -i; j++) {
                if(arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("time ---->" + i+1);
//            System.out.println(Arrays.toString(arr));

            if(!flag) {
                break;
            }else {
                flag = false;
            }
        }
    }

    public static void bubbleSortTest() {
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("before==>" + date1Str);

        bubbleSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("after==>" + date2Str);
    }
}
