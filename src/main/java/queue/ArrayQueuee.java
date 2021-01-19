package queue;

import java.util.Scanner;

public class ArrayQueuee {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("s(show): 顯示對列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加數據到對列");
            System.out.println("g(get): 從對列中取出數據");
            System.out.println("h(head): 查看對列數據");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("輸入一的數");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的數據是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("對列的頭是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出程序");
    }
}

class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;


    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }


    public boolean isFull() {
        return rear == maxSize - 1;
    }


    public boolean isEmpty() {
        return rear == front;
    }


    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("對列滿了，不能添加了!!");
            return;
        }
        rear++;
        arr[rear] = n;
    }


    public int getQueue() {

        if (isEmpty()) {

            throw new RuntimeException("對列空，不能取數據!!");
        }
        front++;
        return arr[front];

    }


    public void showQueue() {

        if (isEmpty()) {
            System.out.println("對列沒數據");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }


    public int headQueue() {

        if (isEmpty()) {
            throw new RuntimeException("對列沒數據!!");
        }
        return arr[front + 1];
    }
}
