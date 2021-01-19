package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
//        int[] arr = { 10, 11, 7, 6, 8, 9 };

        AVLTree avlTree = new AVLTree();

        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("infix Order");
        avlTree.infixOrder();

        System.out.println("Self-balancing binary search tree~~");
        System.out.println("height of root =" + avlTree.getRoot().height()); //outwith 4, with 3
        System.out.println("height of left=" + avlTree.getRoot().leftHeight()); // outwith 3, with 2
        System.out.println("height of right=" + avlTree.getRoot().rightHeight()); // outwith 1, with 2
        System.out.println("Node current=" + avlTree.getRoot());//8

    }
}

class AVLTree {
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

    //返回左子樹高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子樹高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回該節點為根結點的樹的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋
    private void leftRotate() {

        //以當前根節點的值創建一個節點
        Node newNode = new Node(value);
        //把新的節點的左子樹設置成當前節點的左子樹
        newNode.left = left;
        //把新的節點的右子樹設置成右子樹的左子樹
        newNode.right = right.left;
        //把當前節點的值替換成右子點的值
        value = right.value;
        //把當前節點的右子樹設置成當前節點的右子樹的右子樹
        right = right.right;
        //把當前的左子樹設置成新的節點
        left = newNode;


    }

    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {

            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }

    }

    public Node searchParent(int value) {

        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {

            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }

    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {

            if (this.left == null) {
                this.left = node;
            } else {

                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {

                this.right.add(node);
            }

        }

//      ==================關鍵部分============================
        //當添加一個節點後，右子樹高度 - 左子樹高度 > 1，左旋
        if(rightHeight() - leftHeight() > 1) {
            //如右子樹的左子樹高度大於它的右子樹的右子樹的高度
            //在雙璇時
            if(right != null && right.leftHeight() > right.rightHeight()) {
                //先對右子節點進行右旋轉
                right.rightRotate();
                //然後再對當前節點進行左旋轉
                leftRotate();
            } else {
                //直接進行左旋轉
                leftRotate();
            }
            return ; //必須的return!!
        }

        //當添加一個節點後，左子樹高度 - 右子樹高度 > 1，右旋
        if(leftHeight() - rightHeight() > 1) {
            //如它的左子樹的右子樹高度大於它的左子樹高度
            if(left != null && left.rightHeight() > left.leftHeight()) {
                //先對當前節點左節點左旋
                left.leftRotate();
                //之後在右璇
                rightRotate();
            } else {
                //直接進行右旋轉
                rightRotate();
            }
        }
//      ==============================================
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}
