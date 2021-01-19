package hashtab;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {

        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add");
            System.out.println("list");
            System.out.println("find");
            System.out.println("delete");
            System.out.println("exit");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("type id");
                    int id = scanner.nextInt();
                    System.out.println("type name");
                    String name = scanner.next();

                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("type id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;//鏈表的數量


    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];

        //分別初始化鏈表
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    public void add(Emp emp) {
        //根據id得到應當相對的鏈表
        int empLinkedListNO = hashFun(emp.id);
        //將emp添加到對應的鏈表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    public void list() {
        for(int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

//    public void delete(int id) {
//        int empLinkedListNO = hashFun(id);
//        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
//        if(emp != null) {
//            System.out.printf("第　%d 鏈　找到　id = %d\n", (empLinkedListNO + 1), id);
//            empLinkedListArray[empLinkedListNO].delete(emp);
//        }else{
//            System.out.println("沒找到~");
//        }
//    }
    public void findEmpById(int id) {
        //使用散列函數確定到哪條鏈表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if(emp != null) {
            System.out.printf("第　%d 鏈　找到　id = %d\n", (empLinkedListNO + 1), id);
        }else{
            System.out.println("沒找到~");
        }
    }
    //使用簡單的取模法來補助散列函數
    public int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    //執行第一個Emp，鏈表的head是直接指向第一個Emp
    private Emp head;

    public void add(Emp emp) {
       //第一個
        if(head == null) {
            head = emp;
            return;
        }
        //非第一個
        Emp curEmp = head;
        while(true) {
            if(curEmp.next == null) {//鏈表最後
                break;
            }
            curEmp = curEmp.next; //後移
        }
        //退出時直接將emp加入鏈表
        curEmp.next = emp;
    }

    public void list(int no) {
        if(head == null) { //鏈表為空
            System.out.println("第 "+(no+1)+" 鍊表為空");
            return;
        }
        System.out.print("第 "+(no+1)+" 訊息為-->");
        Emp curEmp = head; //輔助指針
        while(true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if(curEmp.next == null) {//curEmp為最後節點
                break;
            }
            curEmp = curEmp.next; //後移
        }
        System.out.println();
    }

//    public void delete(Emp emp) {
//        if(head == null) {
//            System.out.println("鏈表為空");
//        }
//        //輔助指針
//        Emp curEmp = head;
//        while(true) {
//            if(curEmp == emp) {//找到
//
//                break;
//            }
//            //退出
//            if(curEmp.next == null) {//沒找到
//                curEmp = null;
//                break;
//            }
//            curEmp = curEmp.next;//後移
//        }
//    }

    public Emp findEmpById(int id) {
        //判斷是否為空
        if(head == null) {
            System.out.println("鏈表為空");
            return null;
        }
        //輔助指針
        Emp curEmp = head;
        while(true) {
            if(curEmp.id == id) {//找到
                break;
            }
            //退出
            if(curEmp.next == null) {//沒找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//後移
        }

        return curEmp;
    }
}
