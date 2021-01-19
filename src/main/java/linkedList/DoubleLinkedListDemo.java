package linkedList;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        System.out.println("Double Linked List");

        HeroNode2 hero1 = new HeroNode2(1, "Tom", "Tome");
        HeroNode2 hero2 = new HeroNode2(2, "John", "Joao");
        HeroNode2 hero3 = new HeroNode2(3, "Andre", "Alexadre");
        HeroNode2 hero4 = new HeroNode2(4, "Peter", "Pedro");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
//
//        doubleLinkedList.list();

        System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);

        System.out.println("Order list~~");
        doubleLinkedList.list();

        System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        HeroNode2 newHeroNode = new HeroNode2(4, "Gustavo", "GoGO--");
        doubleLinkedList.update(newHeroNode);
        System.out.println("Update list~~");
        doubleLinkedList.list();

        System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
        doubleLinkedList.del(3);
        System.out.println("deleted elements~~");
        doubleLinkedList.list();

    }
}
class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void list() {
        if (head.next == null) {
            System.out.println("鏈表為空");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }

            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void addByOrder(HeroNode2 heroNode) {

        HeroNode2 temp = head;
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
            heroNode.pre = temp;

        }
    }

    public void add(HeroNode2 heroNode) {

        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 newHeroNode) {

        if (head.next == null) {
            System.out.println("鏈表為空~~");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {

                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("沒找到%d節點\n", newHeroNode.no);
        }
    }

    public void del(int no) {

        if (head.next == null) {
            System.out.println("鏈表為空~~");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {

                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {

            temp.pre.next = temp.next;

            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要刪除的%d節點不存在\n", no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // Ϊ����ʾ��������������toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}
