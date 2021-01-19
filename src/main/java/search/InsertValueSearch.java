package search;

public class InsertValueSearch {

    public static void main(String[] args) {


        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);

        System.out.println("index = " + index);


    }

    //數組必須是有序的，主要是在求中間值時的調用次數變少了。
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        System.out.println("Insert Value Search~~");

        //退出的關鍵碼
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        //關鍵碼
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) { // ˵��Ӧ�����ұߵݹ�
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // ˵������ݹ����
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

//    注意事项：
//    对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快。
//    关键字分布不均匀的情况下，该方法不一定比折半查找要好。

}
