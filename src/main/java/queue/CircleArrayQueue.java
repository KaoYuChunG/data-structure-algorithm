package queue;

import java.util.Scanner;

public class CircleArrayQueue {

    public static void main(String[] args) {

        System.out.println("環形對列測試~~~");

        CircleArray  queue = new CircleArray (4);
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

class CircleArray {

    private int maxSize;

    //初始為0，第一個元素
    private int front;
    //初始為0，最後一個元素
    private int rear;
    private int[] arr;//摩尼數列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }


    public boolean isFull() {
        return (rear  + 1) % maxSize == front;
    }


    public boolean isEmpty() {
        return rear == front;
    }


    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("對列已滿，不能加入數據~");
            return;
        }

        arr[rear] = n;

        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {

        if (isEmpty()) {
            throw new RuntimeException("對列空，不能取數據~");
        }

        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }


    public void showQueue() {

        if (isEmpty()) {
            System.out.println("對列為空，沒數據~~");
            return;
        }

        for (int i = front; i < front + size() ; i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }


    public int headQueue() {

        if (isEmpty()) {
            throw new RuntimeException("對列為空，沒數據~~~~");
        }
        return arr[front];
    }
}
