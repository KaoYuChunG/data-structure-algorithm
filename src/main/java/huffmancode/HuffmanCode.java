package huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length); //40

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes);
//
//        System.out.println("Huffman Tree code");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        System.out.println("Pre Order");
//        huffmanTreeRoot.preOrder();
//
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("~huffman Codes= " + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffman Code Bytes=" + Arrays.toString(huffmanCodeBytes));//17

        //===========壓縮後的===================
//        byte[] huffmanCodesBytes= huffmanZip(contentBytes);
//        System.out.println("result by huffaman code:" + Arrays.toString(huffmanCodesBytes) + " lenght= " + huffmanCodesBytes.length);


        //===========解壓===================
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("原來的字符=" + new String(sourceBytes)); // "i like like like java do you like a java"

        //===========壓縮文件===================
        //完成的壓縮文件不能直接解壓，因為是用你的方法去壓縮的，所以要用你的方式去解壓
//      String srcFile = "C://Users/xxx/Desktop/posta.png";
//		String dstFile = "C://Users/xxx/Desktop/posta.zip";
//
//		zipFile(srcFile, dstFile);
//		System.out.println("壓縮文件 ok~~");

        //===========解壓文件===================
        String srcFile = "C://Users/xxx/Desktop/posta.zip";
        String dstFile = "C://Users/xxx/Desktop/posta2.png";
        unZipFile(srcFile, dstFile);
        System.out.println("解壓文件 ok~~");
    }

    //解壓文件--有問題
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;

        try {

            is = new FileInputStream(zipFile);
            //創建一個與  is關連的對象輸入流
            ois = new ObjectInputStream(is);

            //讀取byte數組  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //讀取huffaman code
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //將bytes 數組寫入到目標文件
            os = new FileOutputStream(dstFile);
            //寫數據到dstFile文件
            os.write(bytes);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {

                System.out.println(e2.getMessage());
            }

        }
    }

    //壓縮文件
    public static void zipFile(String srcFile, String dstFile) {

        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;
        try {
//          創建文件的輸出流
            is = new FileInputStream(srcFile);
//          創建一個和原文件大小依樣的byte[]
            byte[] b = new byte[is.available()];
//          讀文件
            is.read(b);
//          直接對原文件壓縮
            byte[] huffmanBytes = huffmanZip(b);
//          創建文件的輸出流並存放壓縮文件
            os = new FileOutputStream(dstFile);
//          創建一個和文件出流關聯的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
//          把huffaman 編碼後的文字數組寫入壓縮文件
            oos.writeObject(huffmanBytes);
//          以對象方式寫入huffaman code，是為了以後我們恢復原文件時使用
//          注意，一定要把編碼寫入壓縮文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }
    }

    //寫一個方法完成對壓縮數據的解碼。並返回原來的數組
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
        //先得到huffmanBytes對應的二進制字符串。
        StringBuilder stringBuilder = new StringBuilder();
        //將byte數組轉成二進制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判斷是不是最後一的字節
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //把array放在指定的huffaman進行編碼
        //把huffaman編碼表進行調換，因為反向查詢
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //創建一個集合放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成索引來掃描stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的計數器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //遞增的取出 key 1
                String key = stringBuilder.substring(i, i+count);//i ��������count�ƶ���ָ��ƥ�䵽һ���ַ�
                b = map.get(key);
                if(b == null) {//沒匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移到 count
        }
        //當for結束後list就會放有所有字符  "i like like like java do you like a java"
        //把list 中的數據放入到byte[] 並返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

//  完成數據解壓
//  將壓縮後的編碼轉成二進制，然後對應huffaman code to output

//  將一個byte 轉成一個二禁制的字符串
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b; //將b 轉成 int
        //如果是正數，還存在補高位
        if(flag) {
            temp |= 256; //按位與 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回是temp對應的二進制補碼
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    // 封裝所有壓縮過程
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根據 nodes 創建的huffaman
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //對應的huffaman code(根據 tree)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根據生成的huffaman code，壓縮得到壓縮後的huffaman code字節數組
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //此方法將字符串對應的byte[]數組，通過生成的編碼表返回一個huffaman碼壓縮的byte[]
    /**
     *
     * @param bytes 淵史的字符串對應 byte[]
     * @param huffmanCodes 生成的huffaman map
     * @return 返回huffaman code 處裡後的byte[]
     * examplo String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的 array --> "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 對應 byte[] huffmanCodeBytes  即 8位 byte,放入 huffmanCodeBytes
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用 huffmanCodes 將  bytes 轉成  huffaman 對應 array
        StringBuilder stringBuilder = new StringBuilder();
        //list bytes array
        for(byte b: bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println("test stringBuilder~~~=" + stringBuilder.toString());

        //統計返回  byte[] huffmanCodeBytes 長度
        //也可用這樣來形成一樣的結果 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if(stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //
        // 創建存的壓縮後的byte 數組
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//紀錄第幾個byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //��Ϊ��ÿ8λ��Ӧһ��byte,���Բ��� +8
            String strByte;
            if(i+8 > stringBuilder.length()) {//不夠8位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //將strByte 轉成byte,放入 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成數的對應編碼
    //1. 將編碼存放在 Map<Byte,String> 的形式
    //   如{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
    //2. 在生成編碼表示，需要去併接路徑，定義一個StringBuilder 存某個葉子節點的路徑
    static StringBuilder stringBuilder = new StringBuilder();


    //於方便調用，重寫 getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if(root == null) {
            return null;
        }
        //處裡root的左子樹
        getCodes(root.left, "0", stringBuilder);
        //處裡root的右子樹
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 將傳入的node節點的所有葉子節點的編碼得到，並放入到huffmanCodes集合
     * @param node  傳入節點
     * @param code  路徑， 左子節點0, 右子節點 1
     * @param stringBuilder 用於併接路徑
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //將code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if(node != null) { //如果node == null不處裡
            //判斷當前node 是葉子節點還是非葉子節點
            if(node.data == null) { //非葉子節點
                //第歸處裡
                //向左
                getCodes(node.left, "0", stringBuilder2);
                //向右
                getCodes(node.right, "1", stringBuilder2);
            } else { //說明是一個葉子節點
                //表示找到某個葉子節點的最後
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if(root != null) {
            root.preOrder();
        }else {
            System.out.println("空值");
        }
    }

    //bytes 接收字節數組
    private static List<Node> getNodes(byte[] bytes) {

        //create one ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();

        //listing bytes , 統計每一個byte出現次數 ->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map 還沒有這個字符數據，也就是說第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //把每一個鍵值對轉成一個node對象，並加入倒nodes集合，並list it
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;

    }

    private static Node createHuffmanTree(List<Node> nodes) {

        while(nodes.size() > 1) {
            //從小到大排序
            Collections.sort(nodes);
            //取出第一個最小的二叉樹
            Node leftNode = nodes.get(0);
            //取出第二個最小的二叉樹
            Node rightNode = nodes.get(1);
            //創建一個新的二叉樹，他的根結點沒有data只有權值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //將已經處裡的兩個二叉樹從nodes刪除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //將新的二叉樹加入到nodes
            nodes.add(parent);

        }
        //nodes 的最後節點，也就是huffaman tree的根結點
        return nodes.get(0);

    }
}

class Node implements Comparable<Node>  {
    Byte data; // 存放字符本身，如'a' => 97 ' ' => 32
    int weight; //表示字符出現的次數
    Node left;//
    Node right;
    public Node(Byte data, int weight) {

        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
        // 從小到大排序
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

    //ǰ�����
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }
}
