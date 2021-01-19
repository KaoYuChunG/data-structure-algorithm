package Algorithm.dac;

public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {

        if(num == 1) {//只有一個盤
            System.out.println("第一個從 " + a + "->" + c);
        } else {
            //如果有 n >= 2 情況可以假設最下面的是一個盤，所有上面的算是另一個盤
            //1. 先把最上面的所有盤 A-> B 移動過程會使用到C
            hanoiTower(num - 1, a, c, b);
            //2. 把最下邊的盤 A-> C
            System.out.println("第" + num + "個盤從 " + a + "->" + c);
            //3. 把B塔的所有盤從 B->C , 移動過程使用到 a塔
            hanoiTower(num - 1, b, a, c);

        }
    }
}
