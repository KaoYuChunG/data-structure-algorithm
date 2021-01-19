package Algorithm.floyd;

import java.util.Arrays;

public class FloydAlgorithm {

    public static void main(String[] args) {

        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };

        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };


        Graph graph = new Graph(vertex.length, matrix, vertex);

        graph.floyd();
        graph.show();
    }
}


class Graph {
    private char[] vertex; // 存放頂點的數組
    private int[][] dis; // 保存從各個頂點出發到其他頂點的距離，最後的結果，也是保留在該數組
    private int[][] pre;// 保存到達目標頂點的前驅頂點

    public Graph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 對pre數組初始化，注意存放的是前驅頂點的下標
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 顯示pre數組和dis數組
    public void show() {

        //為了顯示便於閱讀，優化輸出
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        for (int k = 0; k < dis.length; k++) {
            // 先將pre數組輸出的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            // 先將dis數組的一行數據
            for (int i = 0; i < dis.length; i++) {
                System.out.print("("+vertex[k]+"到"+vertex[i]+"的最短路徑是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();

        }

    }

    public void floyd() {
        int len = 0; //變量保存距離
        //對中堅頂點list k 就是中間頂點的下標 [A, B, C, D, E, F, G]
        for(int k = 0; k < dis.length; k++) { //
            //從i頂點開始出發 [A, B, C, D, E, F, G]
            for(int i = 0; i < dis.length; i++) {
                // 到達j頂點 // [A, B, C, D, E, F, G]
                for(int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];// => 求出從 i 頂點出發，經過 k 中間頂點，到達 j 頂點距離
                    if(len < dis[i][j]) {//如果lenС小於 dis[i][j]
                        dis[i][j] = len;//更新距離
                        pre[i][j] = pre[k][j];//更新前驅頂點
                    }
                }
            }
        }
    }
}

