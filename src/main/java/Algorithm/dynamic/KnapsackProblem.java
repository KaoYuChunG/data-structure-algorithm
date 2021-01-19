package Algorithm.dynamic;

public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品重量
        int[] val = {1500, 3000, 2000}; //物品價值，val[i] 就是解說裡的v[i]
        int m = 4; //背包容量
        int n = val.length; //物品個數



        //創建二為數組
        //v[i][j] 表示在前i個物品中能夠裝入容量為j的背包中的最大價值
        int[][] v = new int[n+1][m+1];
        //為了記錄放入商品的情況，擬定一個二為數組
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列，這裡在本程序中不可以去處哩，因為默認就是0
        for(int i = 0; i < v.length; i++) {
            v[i][0] = 0; //將第一列設置為0
        }
        for(int i=0; i < v[0].length; i++) {
            v[0][i] = 0; //將第一行設置為0
        }


        //根據前面得到的公式來做動態規劃處理
        for(int i = 1; i < v.length; i++) { //不處裡第一行 i 是從 1 開始的
            for(int j=1; j < v[0].length; j++) {// 不處裡第一行 j 是從 1 開始的
                //核心公式
                if(w[i-1]> j) { // 因為程序i式從1 開始的，因此原來公式的 w[i] 修改成 w[i-1]
                    v[i][j]=v[i-1][j];
                } else {

                    //因為i從1開始的，因此公式需要調整成....
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //調整成下面這個
                    //為了記錄商品存放到背包的情況，不能直接的使用上面的公式，需要使用if-else來實現公式
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把當前的情況記錄到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }

                }
            }
        }

        //輸出v....
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("============================");
        //輸出最後放入得哪些商品
        //list the path, 這樣輸出是輸出所有的，而不只是最後的
//		for(int i = 0; i < path.length; i++) {
//			for(int j=0; j < path[i].length; j++) {
//				if(path[i][j] == 1) {
//					System.out.printf("第%d個商品放入到背包\n", i);
//				}
//			}
//		}

        //改進的
        int i = path.length - 1; //行的最大下標
        int j = path[0].length - 1;  //列的最大下標
        while(i > 0 && j > 0 ) { //從path得最後開始找
            if(path[i][j] == 1) {
                System.out.printf("第%d個商品放入到背包\n", i);
                j -= w[i-1]; //w[i-1]
            }
            i--;
        }
    }
}
