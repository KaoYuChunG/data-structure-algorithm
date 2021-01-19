package recursion;

public class MiGong {

    public static void main(String[] args) {

        int[][] map = new int[8][7];

        //muro top and botton
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // muro right and left
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //muro interno
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("show map");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        System.out.println("show result");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //i and j are where start
    public static boolean setWay(int[][] map, int i, int j) {
        if(map[6][5] == 2) {
            return true;
        } else {
            if(map[i][j] == 0) {

                map[i][j] = 2;
                if(setWay(map, i+1, j)) { //down way
                    return true;
                } else if (setWay(map, i, j+1)) { //right way
                    return true;
                } else if (setWay(map, i-1, j)) { //up way
                    return true;
                } else if (setWay(map, i, j-1)){ //left way
                    return true;
                } else {
                    // 死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean setWay2(int[][] map, int i, int j) {
        if(map[6][5] == 2) {

            //fined, it is ok
            return true;
        } else {
            if(map[i][j] == 0) {

                map[i][j] = 2;

                if(setWay2(map, i-1, j)) {
                    return true;
                } else if (setWay2(map, i, j+1)) {
                    return true;
                } else if (setWay2(map, i+1, j)) {
                    return true;
                } else if (setWay2(map, i, j-1)){
                    return true;
                } else {

                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
