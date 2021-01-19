package tree;

public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        //必須創建Binary　Tree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }
}

class ArrBinaryTree {
    private int[] arr;//存數據節點的數組

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void preOrder(int index) {
        if(arr == null || arr.length == 0) {
            System.out.println("數組為空");
        }
        //輸出當前元素
        System.out.println(arr[index]);
        //向左第歸
        if((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1 );
        }
        //向右第歸
        if((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}
