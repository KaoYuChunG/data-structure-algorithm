package search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//		System.out.println("resIndex=" + resIndex);

//        int arr[] = { 1, 2, 3, 4, 1, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        // 當 left > right 說明沒找到此值，故返回-1
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向右第歸
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //　向左第歸
            return binarySearch(arr, left, mid - 1, findVal);
        } else {

            return mid;
        }
    }

    //改進版，當數組中有相同值時，也要加入返回值
    //只需要改最後一個else即可。
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 當 left > right 說明沒找到此值，故返回-1
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向右第歸
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { //　向左第歸
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //當找到時要向左向右檢查
            List<Integer> resIndexlist = new ArrayList<Integer>();


            //向左找
            int temp = mid - 1;
            while(true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //temp 放入 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }

            resIndexlist.add(mid);

            //向右找
            temp = mid + 1;
            while(true) {
                if (temp < arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                //temp 放入 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp左移
            }

            return resIndexlist;
        }
    }
}
