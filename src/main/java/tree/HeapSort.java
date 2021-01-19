package tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {

    public static void main(String[] args) {
//        int arr[] = {4, 6, 8, 5, 9};

        //測試時間
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("before=" + date1Str);

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("after=" + date2Str);
    }

    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("Heap Sort!!");

        for(int i = arr.length / 2 -1; i >=0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 將堆頂元素與末尾元素交換，將最大元素移到數組末端
        //　重新調整結構，使其滿足堆定義，然後繼續交換堆頂元素與前末尾元素，反覆執行調整加交換步驟，直到整個序列有序
        for(int j = arr.length-1;j >0; j--) {
            //交換
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

//        System.out.println("數組=" + Arrays.toString(arr));
    }

    //核心碼
    public  static void adjustHeap(int arr[], int i, int lenght) {
        int temp = arr[i];

        for(int k = i * 2 + 1; k < lenght; k = k * 2 + 1) {
            if(k+1 < lenght && arr[k] < arr[k+1]) { //說明左子節點的值小於右子節點的值
                k++; // k 指向右子節點
            }
            if(arr[k] > temp) { //如果子節點大於父節點
                arr[i] = arr[k]; //把較大的值給於當前節點
                i = k; //!!! i 指向 k,繼續循環比較
            } else {
                break;//!
            }
        }
        //當for結束後，已將為父節點的i的最大值放在了最頂
        arr[i] = temp;//將temp值放到調整後的位置。
    }
}
