package Algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

    public static void main(String[] args) {
        //創建電台放入Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //將各個電台放入broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("Londrina");
        hashSet1.add("Maringa");
        hashSet1.add("Marilia");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("Sao Paulo");
        hashSet2.add("Londrina");
        hashSet2.add("Rio de Janeiro");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("porto Alegre");
        hashSet3.add("Maringa");
        hashSet3.add("Cambe");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("Maringa");
        hashSet4.add("Marilia");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("Cambe");
        hashSet5.add("Manaus");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地區
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("Londrina");
        allAreas.add("Maringa");
        allAreas.add("Marilia");
        allAreas.add("Sao Paulo");
        allAreas.add("Rio de Janeiro");
        allAreas.add("porto Alegre");
        allAreas.add("Cambe");
        allAreas.add("Manaus");

        //創建ArrayList, 存放選擇的電台
        ArrayList<String> selects = new ArrayList<String>();

        //定義一個鄰時的集合，在lista的過程中，存放lista過程中的電台覆蓋的地區和當前還沒有覆蓋得地區的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定義給maxKey，保存在一次lista過程中，能夠覆蓋最大未覆蓋的地區對應的電台的key
        //如果maxKey 不為Ϊnull , 則加入到 selects
        String maxKey = null;

        // 如果allAreas 不為0, 則表示還沒偶覆蓋到所有的地區
        while(allAreas.size() != 0) {
            //每進行一次while,需要 null maxKey
            maxKey = null;

            //lista broadcasts, 取出對應key
            for(String key : broadcasts.keySet()) {
                //每進行一次for
                tempSet.clear();
                //當前這個key能夠覆蓋的地區
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet 和 allAreas 集合的交集，交集會賦給 tempSet
                tempSet.retainAll(allAreas);
                //如果當前這個集合包含得為覆蓋地區的數量，比maxKey 指向得集合地區還多
                //就需要重置maxKey
                // tempSet.size() >broadcasts.get(maxKey).size()) 體現出算法的特點，每次都選擇最優的
                if(tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() >broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey != null, 就應該將maxKey 加入selects
            if(maxKey != null) {
                selects.add(maxKey);
                //將maxKey指向的電台覆蓋的地區，從 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }

        System.out.println("result" + selects);//[K1,K2,K3,K5]
    }
}
