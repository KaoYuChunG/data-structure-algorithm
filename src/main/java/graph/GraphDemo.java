package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GraphDemo {

    public static void main(String[] args) {
        int n = 5;
//        String Vertexs[] = {"A", "B", "C", "D", "E"};
//      測試深度廣度優先用
        int n2 = 8;
        String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        Graph graph = new Graph(n2);
        //添加節點
        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加邊
        //A-B A-C B-C B-D B-E
//      graph.insertEdge(0, 1, 1);
//		graph.insertEdge(0, 2, 1);
//		graph.insertEdge(1, 2, 1);
//		graph.insertEdge(1, 3, 1);
//		graph.insertEdge(1, 4, 1);

		//測試深度廣度優先用
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();

        System.out.println("Depth First Search");
        graph.dfs(); // A->B->C->D->E   路徑-->[1->2->4->8->5->3->6->7]

        System.out.println();
        System.out.println("Broad First Search");
        graph.bfs(); // A->B->C->D-E   路徑-->[1->2->3->4->5->6->7->8]
    }
}

class Graph {
    private ArrayList<String> vertexList; //存頂點集合
    private int[][] edges; //存圖對應的玲結矩陣
    private int numOfEdges; //邊的數目
    //紀錄訪問過的節點
    private boolean[] isVisited;

    public Graph(int n) {
        //初始化矩陣和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }
//  得到一個鄰結點的下標w
//  return 如果存在就返回對應的下標，反之返回-1
    public int getFirstNeighbor(int index) {
        for(int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
//  根據前一個鄰接結點的下標來獲取下一個鄰接結點
    public int getNextNeighbor(int v1, int v2) {
        for(int j = v2 + 1; j < vertexList.size(); j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度優先list 算法
    private void dfs(boolean[] isVisited, int i) {
        //輸出該訪問的結點
        System.out.print(getValueByIndex(i) + "->");
        //已經訪問過
        isVisited[i] = true;
        //差找i節點的第一個鄰結點w
        int w = getFirstNeighbor(i);
        while(w != -1) {//有鄰結點
            if(!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如w 節點已訪問過
            w = getNextNeighbor(i, w);
        }
    }

//  進行list所有的節點，並進行dfs
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //list all node to do the dfs
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

//  Broad First Search
    private void bfs(boolean[] isVisited, int i) {
        int u ; // 表示對列頭節點對應下標
        int w ; // 鄰接結點w
        //紀錄結點訪問的順序
        LinkedList queue = new LinkedList();
        //輸出訪問結點
        System.out.print(getValueByIndex(i) + "=>");
        //已訪問
        isVisited[i] = true;
        //將結點加入對列
        queue.addLast(i);

        while( !queue.isEmpty()) {
            // 取對列頭結點下標
            u = (Integer)queue.removeFirst();
            // 得到第一個鄰接結點的下標
            w = getFirstNeighbor(u);
            while(w != -1) {//找到

                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //標記已訪問
                    isVisited[w] = true;
                    //入對列
                    queue.addLast(w);
                }
                //以u為前驅點，找w後面的下一個鄰結點
                w = getNextNeighbor(u, w); //
            }
        }

    }

    //所有節點進行儣度優先
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public void showGraph() {
        for(int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }
    //邊的數目
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回i節點下標對應的數據  "A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

//  return weight entre v1 e v2
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //add node
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     *
     * @param v1 表示點的下標即使第幾個頂點  "A"-"B" "A"->0 "B"->1
     * @param v2 第二個頂點對應的下標
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
