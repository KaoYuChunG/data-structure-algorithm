package tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        //需要創建棵樹
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(2, "jack");
        HeroNode node3 = new HeroNode(3, "john");
        HeroNode node4 = new HeroNode(4, "maria");
        HeroNode node5 = new HeroNode(5, "ben");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

		System.out.println("Pre Order"); // 1,2,3,5,4
		binaryTree.preOrder();


		System.out.println("Infix Order");
		binaryTree.infixOrder(); // 2,1,5,3,4

		System.out.println("Post Order");
		binaryTree.postOrder(); // 2,5,4,3,1

        System.out.println("Pre　Order　Search");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if (resNode != null) {
            System.out.printf("finded--> no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("not finded no = %d ", 5);
        }

        System.out.println("Infix　Order　Search");
        HeroNode resNode1 = binaryTree.infixOrderSearch(5);
        if (resNode1 != null) {
            System.out.printf("finded--> no=%d name=%s", resNode1.getNo(), resNode1.getName());
        } else {
            System.out.printf("not finded no = %d ", 5);
        }

        System.out.println("Post　Order　Search");
        HeroNode resNode2 = binaryTree.postOrderSearch(5);
        if (resNode2 != null) {
            System.out.printf("finded--> no=%d name=%s", resNode2.getNo(), resNode2.getName());
        } else {
            System.out.printf("not finded no = %d ", 5);
        }



//        System.out.println("before delete node no preOrder");
//        binaryTree.preOrder(); //  1,2,3,5,4
//        binaryTree.delNode(5);
//        //binaryTree.delNode(3);
//        System.out.println("after delete node no preOrder");
//        binaryTree.preOrder(); // 1,2,3,4

        System.out.println("before delete node no postOrder");
        binaryTree.postOrder();
        binaryTree.delNode(5);
        //binaryTree.delNode(3);
        System.out.println("after delete node no postOrder");
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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

    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//默認值null
    private HeroNode right;//默認值null
    public HeroNode(int no, String name) {
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
    public HeroNode getLeft() {
        return left;
    }
    public void setLeft(HeroNode left) {
        this.left = left;
    }
    public HeroNode getRight() {
        return right;
    }
    public void setRight(HeroNode right) {
        this.right = right;
    }
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
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

    public HeroNode preOrderSearch(int no) {
        System.out.println("entre Pre　Order　Search");

        if(this.no == no) {
            return this;
        }

        HeroNode resNode = null;
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

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
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

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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

//前序遍历: 先输出父节点，再遍历左子树和右子树
//中序遍历: 先遍历左子树，再输出父节点，再遍历右子树
//后序遍历: 先遍历左子树，再遍历右子树，最后输出父节点
//小结: 看输出父节点的顺序，就确定是前序，中序还是后序

