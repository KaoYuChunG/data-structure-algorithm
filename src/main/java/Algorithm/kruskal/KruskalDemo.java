package Algorithm.kruskal;

import java.util.Arrays;

public class KruskalDemo {

    private int edgeNum; //邊的個數
    private char[] vertexs; //頂點數組
    private int[][] matrix; //鄰接矩陣
    //使用 INF 表示兩個頂點不能連通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //算法的鄰接矩陣
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};


        //創建KruskalCase 對象實例
        KruskalDemo kruskalCase = new KruskalDemo(vertexs, matrix);
        //輸出構建的
        kruskalCase.print();
        kruskalCase.kruskal();

    }

    //construtor
    public KruskalDemo(char[] vertexs, int[][] matrix) {
        //初始化頂點數和邊的個數
        int vlen = vertexs.length;

        //初始化頂點，copiar 方式
        this.vertexs = new char[vlen];
        for(int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化邊，使用的是copia方式
        this.matrix = new int[vlen][vlen];
        for(int i = 0; i < vlen; i++) {
            for(int j= 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //統計邊的數
        for(int i =0; i < vlen; i++) {
            for(int j = i+1; j < vlen; j++) {
                if(this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }
    public void kruskal() {
        int index = 0; //表示最後結果數組的索引
        int[] ends = new int[edgeNum]; //用於保存已有最小生成樹中的每個頂點在最小生成樹中的終點
        //創建結果數組，保存最後的最小生成樹
        EData[] rets = new EData[edgeNum];

        //獲取圖中所有的邊的集合，一共有12邊
        EData[] edges = getEdges();
        System.out.println("圖得邊的集合=" + Arrays.toString(edges) + " 共"+ edges.length); //12

        //按照邊的全值大小進行排序(小到大)
        sortEdges(edges);

        //list edges 數組，將邊添加到最小生成樹中時，判斷是準備加入的邊否形成了迴路，如果沒有，就加入rets，否則不能加入。
        for(int i=0; i < edgeNum; i++) {
            //獲取到第i條邊的第一個頂點(起點)
            int p1 = getPosition(edges[i].start); //p1=4
            //獲取到第i條邊的第二個頂點(终點)
            int p2 = getPosition(edges[i].end); //p2 = 5

            //獲取p1這個頂點在已有最小生成樹中的終點
            int m = getEnd(ends, p1); //m = 4
            //獲取p2這個頂點在已有最小生成樹中的終點
            int n = getEnd(ends, p2); // n = 5
            //是否構成迴路
            if(m != n) { //沒構成迴路
                ends[m] = n; // 設置 m 在已有最小生成樹中的終點 <E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                rets[index++] = edges[i]; //有一條邊加入到rets數組
            }
        }
        //<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>
        //統計並打印 最小生成樹，輸出  rets
        System.out.println("最小生成樹為");
        for(int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }


    }


    public void print() {
        System.out.println("鄰接矩陣: \n");
        for(int i = 0; i < vertexs.length; i++) {
            for(int j=0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 對邊進行排序處裡，冒泡排序
     * @param edges 邊的集合
     */
    private void sortEdges(EData[] edges) {
        for(int i = 0; i < edges.length - 1; i++) {
            for(int j = 0; j < edges.length - 1 - i; j++) {
                if(edges[j].weight > edges[j+1].weight) {//交換
                    EData tmp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = tmp;
                }
            }
        }
    }
    /**
     *
     * @param ch 頂點的值'A','B'
     * @return 返回ch頂點對應的下標，如果找不到，返回-1
     */
    private int getPosition(char ch) {
        for(int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch) {//找到
                return i;
            }
        }
        //找不到返回-1
        return -1;
    }
    /**
     * 獲取途中邊放到EData[] 數組中。
     * 通過matrix 鄰接矩陣來獲取
     * EData[] 形式 [['A','B', 12], ['B','F',7], .....]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for(int i = 0; i < vertexs.length; i++) {
            for(int j=i+1; j <vertexs.length; j++) {
                if(matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }
    /**
     * 獲取下標為i的頂點的終點，用於判斷兩個點的終點是否相同
     * @param ends 數組就是紀錄了各個點對應的終點是哪一個，在數組list過程中形成。
     * @param i : 表示傳入的點對應的下標
     * @return 返回的就是下標為i的這個點對應的中點的下標
     */
    private int getEnd(int[] ends, int i) { // i = 4 [0,0,0,0,5,0,0,0,0,0,0,0]
        while(ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

//此類的對象實例就表示一條邊
class EData {
    char start; //邊的一個點
    char end; //邊的另一個點
    int weight; //權值ֵ

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }


}
