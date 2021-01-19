package Algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        //String str2 = "BBC";

        int[] next = kmpNext("ABCDABD"); //[0, 1, 2, 0]
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); // 15
    }


    /**
     *
     * @param str1 原字符
     * @param str2 子字符
     * @param next 部分匹配表，是子字符串對應的部分匹配表
     * @return 如果是-1 就是沒有匹配到，否則返回第一個匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        //list
        for(int i = 0, j = 0; i < str1.length(); i++) {

            //需要處理 str1.charAt(i) != str2.charAt(j), 去調整j的大小
            //KMP算法核心點，可以驗證...
            while( j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }

            if(str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if(j == str2.length()) {//找到 // j = 3 i
                return i - j + 1;
            }
        }
        return  -1;
    }

    //獲取到一個字符串的部分匹配值表
    public static  int[] kmpNext(String dest) {
        //創建一個next數組保存部分匹配
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是長度為1 部分匹配就是0
        for(int i = 1, j = 0; i < dest.length(); i++) {
            //當dest.charAt(i) != dest.charAt(j) 需要從next[j-1]獲取新的j
            //直到發現有  dest.charAt(i) == dest.charAt(j)成立才會褪出
            //這時kmp算法的核心碼
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];
            }

            //當dest.charAt(i) == dest.charAt(j) 滿足時，部分匹配值就是+1
            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
