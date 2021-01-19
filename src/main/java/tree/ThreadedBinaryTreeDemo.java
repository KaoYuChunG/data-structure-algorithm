package tree;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(3, "jack");
        HeroNode2 node3 = new HeroNode2(6, "smith");
        HeroNode2 node4 = new HeroNode2(8, "mary");
        HeroNode2 node5 = new HeroNode2(10, "king");
        HeroNode2 node6 = new HeroNode2(14, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        HeroNode2 leftNode = node5.getLeft();
        HeroNode2 rightNode = node5.getRight();

        //10號及節點5
        System.out.println("10 　前驅節點 ="  + leftNode); //3
        System.out.println("10　後繼節點="  + rightNode); //1

        System.out.println("list threaded　Binary　Tree");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14, 6
    }
}

class ThreadedBinaryTree {
    private HeroNode2 root;

    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedList() {
        HeroNode2 node = root;
        while(node != null) {
            //循環找到LeftType==1 的節點，第一個找到就是8節點
            //後面隨著list而變化，因為當LeftType==1時，說明該節點按照線索化處理後的有較節點
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }

            System.out.println(node);
            //如果當前節點的右指針指向事後季節點，就醫值輸出
            while(node.getRightType() == 1) {
               //獲取當前節點的後繼節點
                node = node.getRight();
                System.out.println(node);
            }
            //替換這個list節點
            node = node.getRight();
        }
    }

    //node　就是當前需要線索化的節點
    public void threadedNodes(HeroNode2 node) {

        //如node == null，不能線索化
        if(node == null) {
            return;
        }

        //先線索化左子樹
        threadedNodes(node.getLeft());

//      這部分是比較難的部分，處裡當前節點的前驅節點
//      以8節點為例
//      8節點的left=null，8節點的leftType = 1
        if(node.getLeft() == null) {
//          讓當前節點的左指針指向前驅節點
            node.setLeft(pre);
//          修改當前節點的左指針的類型，指向前驅節點
            node.setLeftType(1);
        }
//      處裡後繼節點
        if (pre != null && pre.getRight() == null) {
//          讓前驅節點的右指針指向當前節點
            pre.setRight(node);
//          修改前驅節點的右指針類型
            pre.setRightType(1);
        }

        //關鍵重要，每處裡一個節點，讓當前節點是下一個節點的前驅節點
        pre = node;

//      線索化右子樹
        threadedNodes(node.getRight());

    }

    public void delNode(int no) {
        if(root != null) {

            if(root.getNo() == no) {
                root = null;
            } else {

                root.delNode(no);
            }
        }else{
            System.out.println("為空~");
        }
    }

    //先中後左之後在右
    public void preOrder() {
        if(this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("為空~");
        }
    }
    //先左後中之後在右
    public void infixOrder() {
        if(this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("為空~");
        }
    }

    //先左後右之後在中間
    public void postOrder() {
        if(this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("為空~");
        }
    }

    public HeroNode2 preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    public HeroNode2 infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode2 postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;//默認值null
    private HeroNode2 right;//默認值null
//
//  如果 leftType==0　表示指向的是左子樹，如果1則表示指向前驅節點
//  如果　rightType==0　表示指向的是右子樹，如果1則表示指向後繼節點
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public HeroNode2 getLeft() {
        return left;
    }
    public void setLeft(HeroNode2 left) {
        this.left = left;
    }
    public HeroNode2 getRight() {
        return right;
    }
    public void setRight(HeroNode2 right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ", name=" + name + "]";
    }

    public void delNode(int no) {


        if(this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if(this.left != null) {
            this.left.delNode(no);
        }
        if(this.right != null) {
            this.right.delNode(no);
        }
    }

    public void preOrder() {
        //先輸入父節點
        System.out.println(this);

        //向左第歸
        if(this.left != null) {
            this.left.preOrder();
        }
        //向右第歸
        if(this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        //向左第歸
        if(this.left != null) {
            this.left.infixOrder();
        }

        //先輸入父節點
        System.out.println(this);

        //向右第歸
        if(this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        //向左第歸
        if(this.left != null) {
            this.left.postOrder();
        }
        //向右第歸
        if(this.right != null) {
            this.right.postOrder();
        }
        //先輸入父節點
        System.out.println(this);
    }

    public HeroNode2 preOrderSearch(int no) {
        System.out.println("entre Pre　Order　Search");

        if(this.no == no) {
            return this;
        }

        HeroNode2 resNode = null;
        if(this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        if(this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }

        System.out.println("entre Infix Order Search");
        if(this.no == no) {
            return this;
        }

        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 resNode = null;
        if(this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }

        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("entre Post　Order　Search");
        if(this.no == no) {
            return this;
        }
        return resNode;
    }
}
