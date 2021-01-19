package recursion;

public class Queue8 {

    int max = 8;

    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("exist %d way", count);
        System.out.println();
        System.out.printf("judge Count ==> %d", judgeCount); // 1.5w
    }

    private void check(int n) {
        if(n == max) {
            print();
            return;
        }


        for(int i = 0; i < max; i++) {

            array[n] = i;

            if(judge(n)) {

                check(n+1);
            }

        }
    }

    //judge if exist the conflito with last
    private boolean judge(int n) {
        judgeCount++;
        for(int i = 0; i < n; i++) {
            //array[i] == array[n] 同一列　不行
            //　Math.abs(n-i) == Math.abs(array[n] - array[i]　　斜線　不行
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]) ) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
