package binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        System.out.println("Binary Sort Tree~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12

//        binarySortTree.delNode(12);
//
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(10);
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(3);
//
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(7);
        binarySortTree.delNode(7);

        System.out.println("root=" + binarySortTree.getRoot());


        System.out.println("刪除後");
        binarySortTree.infixOrder();

//      再刪除的例子中有這三種情況，用於在刪除過後能保持二叉排序樹的規則
//      兩顆子樹的 -->  7,3,10
//      一顆子樹的 -->  1
//      葉子節點的 -->  2, 5, 9, 12
    }
}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public Node search(int value) {
        if(root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if(root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //1.返回的node 為根結點的二叉排序樹的最小節點的值
    //2.刪除node為根結點的二叉排序樹的最小節點
    public int delRightTreeMin(Node node) {
        Node target = node;
//      循環的查找左子節點就會找到最小想值
        while(target.left != null) {
            target = target.left;
        }
//      對target就指向最小節點
//      刪除最小節點
        delNode(target.value);
        return target.value;
    }

    public void delNode(int value) {
        if(root == null) {
            return;
        }else {

            Node targetNode = search(value);

            if(targetNode == null) {
                return;
            }
//          當前二叉樹只有一個節點
            if(root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parent = searchParent(value);
//          耀珊的節點是葉子節點
            if(targetNode.left == null && targetNode.right == null) {
//              是左或右子節點
                if(parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { ////刪除二顆子樹的節點
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;


            } else { //刪除一顆子樹的節點
//              如果要刪之節點有左子
                if(targetNode.left != null) {
                    //判斷root
                    if(parent != null) {
                        //如果targetNode是parent的左子
                        if(parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//如果targetNode是parent的右子
                            parent.right = targetNode.left;
                        }
                    } else {//判斷root，直接left to root
                        root = targetNode.left;
                    }
                } else {//如果要刪之節點有右子
                    //判斷root
                    if(parent != null) {
                        //如果targetNode是parent的左子
                        if(parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {//如果targetNode是parent的右子
                            parent.right = targetNode.right;
                        }
                    } else {//判斷root，直接right to root
                        root = targetNode.right;
                    }
                }

            }

        }
    }

    public void add(Node node) {
        if(root == null) {
            root = node;//
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("為空!!");
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;
    public Node(int value) {

        this.value = value;
    }

    public Node search(int value) {
        if(value == this.value) {
            return this;
        } else if(value < this.value) {

            if(this.left  == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if(this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }

    public Node searchParent(int value) {

        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {

            if(value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }

    public void add(Node node) {
        if(node == null) {
            return;
        }

        if(node.value < this.value) {

            if(this.left == null) {
                this.left = node;
            } else {

                this.left.add(node);
            }
        } else {
            if(this.right == null) {
                this.right = node;
            } else {

                this.right.add(node);
            }

        }
    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    public void infixOrder() {
        if(this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null) {
            this.right.infixOrder();
        }
    }
}
