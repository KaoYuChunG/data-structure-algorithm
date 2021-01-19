package Algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class HorseChessboard {

    private static int X; // 棋盤的列數
    private static int Y; // 棋盤的行數
    //創建一個數組，標記棋盤的各個位置是否被訪問過
    private static boolean visited[];
    //使用一個屬性，標記是否棋盤的所有位置都被訪問
    private static boolean finished; // 如果為true,表示成功

    public static void main(String[] args) {
        System.out.println("Start~~");

        X = 8;
        Y = 8;
        int row = 1; //初始得行，從1 開始
      int column = 1; ////初始得列，從1 開始
        //創建棋盤
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是false

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + " millisecond");

        //輸出棋盤的最後情況
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param chessboard 棋盤
     * @param row 當前位置的行 從0開始
     * @param column 當前位置的列 從0開始
     * @param step 是第幾步，初始位置是第1步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //row = 4 X = 8 column = 4 = 4 * 8 + 4 = 36
        visited[row * X + column] = true; //標記該位置已訪問
        //獲取當前位置可以走得下一個位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //對ps進行排序的規則就是對ps的所有得Point對象的下一步的位置數目，進行非遞減排序
        //使用greedy algorithm 來優化
        sort(ps);
        //lista ps
        while(!ps.isEmpty()) {
            Point p = ps.remove(0);//取出下一個可以走的位置
            //判斷該點是否已經訪問過
            if(!visited[p.y * X + p.x]) {//說明還沒有訪問過
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判斷騎士是否完成任務，使用  step 和應該走的步數比較
        //如果沒有達到數量，則表示沒有完成任務，將整個棋盤置0
        //說明: step < X * Y 成立的情況有兩種
        //1. 棋盤到目位置，還沒有走完
        //2. 棋盤處於一個回朔過程
        if(step < X * Y && !finished ) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }

    }

    /**
     *根據刀前位置(Point對象)計算騎士還能走那些位置(Point)，並放入到一個集合中(ArrayList), 最多8個位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {

        ArrayList<Point> ps = new ArrayList<Point>();

        Point p1 = new Point();
        //表示騎士可以走5這個位置
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y -1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走6這個位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走7這個位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走0這個位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走1這個位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走2這個位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走3這個位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示騎士可以走4這個位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    //根據當前這一步的所有得下一步的選擇位置，進行非遞減排序，減少回溯的次數
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                //獲取到o1的下一步的所有位置個數
                int count1 = next(o1).size();
                //獲取到o2的下一步的所有位置個數
                int count2 = next(o2).size();
                if(count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
    }
}
