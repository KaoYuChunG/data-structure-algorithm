package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createHuffmanTree(arr);

        preOrder(root);
    }

    //前列list
    public static void preOrder(Node root) {
        if(root != null) {
            root.preOrder();
        }else{
            System.out.println("為空~~");
        }
    }

    //list arr，並將arr每個元素構成Node
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //處裡過程是一個循環的過程
        while(nodes.size() > 1) {

            //排序從小到大
            Collections.sort(nodes);

            System.out.println("nodes =" + nodes);

            //取出根結點權值最小的兩顆二叉樹
            // 1.取出全值最小的節點
            Node leftNode = nodes.get(0);
            // 2.取出全值第二小的節點
            Node rightNode = nodes.get(1);

            // 3.構建一顆新的二叉樹
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 4.從ArrayList刪除處裡的二叉樹
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5.將paren add into nodes
            nodes.add(parent);
        }

        //return the root of node of huffman tree
        return nodes.get(0);
    }


}
//讓node 對象持續排序Collections 集合排序，用Comparable接口
class Node implements Comparable<Node> {
    int value; // 節點權值
    char c; //
    Node left; // 指向左子節點
    Node right; // 指向右子節點

    //前列list
    public void preOrder() {
        System.out.println(this);
        if(this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    @Override
    public int compareTo(Node o) {
        //表示從小到大排序
        return this.value - o.value;
        //表示從大到小排序
//        return -(this.value - o.value);
    }
}
