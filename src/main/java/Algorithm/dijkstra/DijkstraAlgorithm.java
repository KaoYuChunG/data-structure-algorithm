package Algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //鄰接矩陣
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以連接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //創建 Graph對象
        Graph graph = new Graph(vertex, matrix);

        graph.showGraph();

        graph.dsj(2);//C
        graph.showDijkstra();
    }
}

class Graph {
    private char[] vertex; // 頂點數組
    private int[][] matrix; // 鄰接矩陣
    private VisitedVertex vv; //已經訪問的頂點的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //show the result
    public void showDijkstra() {
        vv.show();
    }

    // show the graph
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //算法實現
    /**
     *
     * @param index 表示出發頂點對應的下標
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index頂點到周圍頂點的距離和前驅頂點
        for(int j = 1; j <vertex.length; j++) {
            index = vv.updateArr();// 選擇並返回新的訪問頂點
            update(index); // 更新index頂點到周圍頂點的距離和前驅頂點
        }
    }



    //更新index下標頂點到周圍頂點的距離和周圍頂點的前驅頂點
    private void update(int index) {
        int len = 0;
        //根據lista 鄰接矩陣的 matrix[index]行
        for(int j = 0; j < matrix[index].length; j++) {
            // len 是指出發頂點到index頂點的距離 + 從index頂點到頂點的距離的和
            len = vv.getDis(index) + matrix[index][j];
            // 如果j頂點沒有被訪問過，並且 len 小於出發頂點到 j 頂點的距離，就需要更新
            if(!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); //更新j頂點的前驅為index頂點
                vv.updateDis(j, len); //更新出發頂點到j頂點的距離
            }
        }
    }
}

// 已訪問頂點集合
class VisitedVertex {
    // 記錄各個頂點是否訪問過。 1 表示訪問過， 0未訪問，動態更新
    public int[] already_arr;
    // 每個下標對應的值為前一個頂點下標，動態更新
    public int[] pre_visited;
    // 紀錄出發頂點到其他所有頂點的距離，比如G為出發頂點，就會記錄G到其他頂點的距離，動態更新，求得最短距離就會存放到dis
    public int[] dis;

    /**
     *
     * @param length :表示頂點的個數
     * @param index: 出發頂點對應的下標，比如G頂點，下標就是6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化 dis數組
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1; //設置出發頂點被訪問過
        this.dis[index] = 0;//設置出發頂點的訪問距離0

    }
    /**
     * 判斷index頂點是否被訪問過
     * @param index
     * @return 訪問過返回true, 沒訪問過false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出發頂點到index頂點的距離
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }
    /**
     * 更新pre這個頂點的前驅頂點為index頂點
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }
    /**
     * 返回出發頂點到index頂點的距離
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }


    /**
     * 繼續選擇並返回新的訪問頂點，比如這裡的G完後，就是A點作為新的訪問頂點(不是出發頂點)
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for(int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min ) {
                min = dis[i];
                index = i;
            }
        }
        //更新 index 頂點被訪問過
        already_arr[index] = 1;
        return index;
    }

    //顯示最後結果
    //即將三個數組的情況輸出
    public void show() {

        System.out.println("==========================");
        //輸出already_arr
        for(int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //輸出pre_visited
        for(int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //輸出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //為了好看最後的最短距離
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }

}
