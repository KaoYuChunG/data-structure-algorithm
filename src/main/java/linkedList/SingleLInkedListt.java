package linkedList;

import java.util.Stack;

public class SingleLInkedListt {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "Tom", "Tome");
        HeroNode hero2 = new HeroNode(2, "John", "Joao");
        HeroNode hero3 = new HeroNode(3, "Andre", "Alexadre");
        HeroNode hero4 = new HeroNode(4, "Peter", "Pedro");


        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        =====================================
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//
//        System.out.println("鍊表顯示~~");
//        singleLinkedList.list();
//        ======================================

//		System.out.println("��ת������~~");
//		reversetList(singleLinkedList.getHead());
//		singleLinkedList.list();

//        System.out.println("���������ӡ������, û�иı�����Ľṹ~~");
//        reversePrint(singleLinkedList.getHead());


//        =====================================
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero3);

        System.out.println("Order list~~");
		singleLinkedList.list();
//        =====================================

        System.out.println("~~reverset List by stack~~");
        //stack 是先進後出
        reversePrint(singleLinkedList.getHead());


//        =====================================
//        System.out.println("~~reverset List~~");
//		reversetList(singleLinkedList.getHead());
//		singleLinkedList.list();

        System.out.println("Update list~~");
		HeroNode newHeroNode = new HeroNode(2, "Gustavo", "GoGo~~");
		singleLinkedList.update(newHeroNode);
		singleLinkedList.list();
//     　=====================================

		singleLinkedList.del(1);
		singleLinkedList.del(4);
		System.out.println("deleted elements ~~");
		singleLinkedList.list();

		System.out.println("有效的節點個數=" + getLength(singleLinkedList.getHead()));

//      =====================================
		HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
		System.out.println("res=" + res);


    }

    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;

        while(cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    public static void reversetList(HeroNode head) {
        if(head.next == null || head.next.next == null) {
            return ;
        }

        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");

        while(cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
    }

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if(head.next == null) {
            return null;
        }

        int size = getLength(head);

        if(index <=0 || index > size) {
            return null;
        }

        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for(int i =0; i< size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static int getLength(HeroNode head) {
        if(head.next == null) {
            return 0;
        }
        int length = 0;

        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode) {

        HeroNode temp = head;

        while(true) {
            if(temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {

        HeroNode temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {

                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag) {
            System.out.printf("編號 %d 已存在，不可加入\n", heroNode.no);
        } else {

            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode newHeroNode) {

        if(head.next == null) {
            System.out.println("鍊表為空~");
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;
        while(true) {
            if (temp == null) {
                break;
            }
            if(temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("沒找到%d節點\n", newHeroNode.no);
        }
    }

    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while(true) {
            if(temp.next == null) {
                break;
            }
            if(temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.next.next;
        }else {
            System.out.printf("要刪除的%d節點不存在\n", no);
        }
    }

    public void list() {
        if(head.next == null) {
            System.out.println("鍊表為空");
            return;
        }

        HeroNode temp = head.next;
        while(true) {
            if(temp == null) {
                break;
            }

            System.out.println(temp);
            temp = temp.next;
        }
    }
}



class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}

