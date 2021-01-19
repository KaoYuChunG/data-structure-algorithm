package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214};

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("before=" + date1Str);

        radixSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("after=" + date2Str);

        System.out.println("Radix　Sort  " + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        //得到數據中最大的數的位數
        for(int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大位數的幾位數
        int maxLength = (max + "").length();

        //二為數組包含一個一為數組
        //arr.length是為了防止溢出
        int[][] bucket = new int[10][arr.length];

        //bucketElementCounts[0] 就是紀錄　bucket[0]　桶的放入數據個數
        int[] bucketElementCounts = new int[10];

        for(int i = 0 , n = 1; i < maxLength; i++, n *= 10) {
            for(int j = 0; j < arr.length; j++) {
                //取出每個元素的對應值
                int digitOfElement = arr[j] / n % 10;
                //放入對應的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            //將桶的數據放入原數組
            for(int k = 0; k < bucketElementCounts.length; k++) {
                //如有數據才放入原數組
                if(bucketElementCounts[k] != 0) {

                    for(int l = 0; l < bucketElementCounts[k]; l++) {

                        arr[index++] = bucket[k][l];
                    }
                }
                //將每個bucketElementCounts[k] 歸零
                bucketElementCounts[k] = 0;

            }
        }
    }
}
