package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};


        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
        }

        System.out.println("Quick Sort");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("before=" + date1Str);

        quickSort(arr, 0, arr.length-1);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("after=" + date2Str);

//        System.out.println("arr=" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int left, int right) {
        int l = left;//左下標
        int r = right;//右下標

        //中軸值
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        //比pivot小放左邊，比pivot大放右邊。
        while( l < r) {

            //於pivot左邊一值找，直到找到大於pivot才跳出。
            while( arr[l] < pivot) {
                l += 1;
            }

            //於pivot右邊一值找，直到找到小於pivot才跳出。
            while(arr[r] > pivot) {
                r -= 1;
            }

            //以按照左邊全部小於pivot，右邊全部大於pivot
            if( l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //前移
            if(arr[l] == pivot) {
                r -= 1;
            }
            //後移
            if(arr[r] == pivot) {
                l += 1;
            }
        }

        //防溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        if(left < r) {
            quickSort(arr, left, r);
        }

        if(right > l) {
            quickSort(arr, l, right);
        }
    }
}
