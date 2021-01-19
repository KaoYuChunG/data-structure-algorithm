package Algorithm.prim;

import java.util.Arrays;

public class PrimAlgorithm {

    public static void main(String[] args) {

        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //鄰接矩陣的關係使用二為數組表示,10000這個數表示兩點不相通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};


        MGraph graph = new MGraph(verxs);

        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);

        minTree.showGraph(graph);

        minTree.prim(graph, 0);//
    }
}


//創建最小生成樹
class MinTree {
    //創建圖的鄰接矩陣
    /**
     *
     * @param graph 圖對象
     * @param verxs 圖對應的頂點個數
     * @param data 圖的各個頂點的值
     * @param weight 圖的鄰接矩陣
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for(i = 0; i < verxs; i++) {//頂點
            graph.data[i] = data[i];
            for(j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //顯示圖的鄰接矩陣
    public void showGraph(MGraph graph) {
        for(int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //prim的算法，得到最小生成樹
    /**
     *
     * @param graph 圖
     * @param v 表示從圖的第幾個頂點開始生成'A'->0 'B'->1...
     */
    public void prim(MGraph graph, int v) {
        //visited[] 標記結點是否被訪問
        int visited[] = new int[graph.verxs];
        //visited[] 默認元素為0，表示沒被訪問過
//		for(int i =0; i <graph.verxs; i++) {
//			visited[i] = 0;
//		}

        //把當前這個結點標記為已訪問
        visited[v] = 1;
        //h1 和 h2 紀錄兩個頂點的下標
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000; //將 minWeight 初始成一個數，後面在lista過程中會被替換
        for(int k = 1; k < graph.verxs; k++) {//因為有 graph.verxs頂點，prim算法結束後，有 graph.verxs-1邊

            //這個是確定每一次生成的子圖，和哪個節點的距離最近
            for(int i = 0; i < graph.verxs; i++) {// i結點表示被訪問過的節點
                for(int j = 0; j< graph.verxs;j++) {//j結點表示還沒被訪問過的節點
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替換minWeight(尋找已經訪問過的節點和未訪問過的節點的全值最小的邊)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到最小的邊
            System.out.println("stree<" + graph.data[h1] + "," + graph.data[h2] + "> Weight" + minWeight);
            //將當前這個結點標記為已經訪問
            visited[h2] = 1;
            //minWeight 重新設置為最大值 10000
            minWeight = 10000;
        }

    }
}

class MGraph {
    int verxs; //圖的節點個數
    char[] data;//存放結點數據
    int[][] weight; //存放邊，就是鄰接矩陣

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
