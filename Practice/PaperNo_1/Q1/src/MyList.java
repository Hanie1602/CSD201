/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList {

    Node head, tail;

    MyList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = head;
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void loadData(int k) { //do not edit this function
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//==================================================================
/* 
   Khong su dung tieng Viet co dau de viet ghi chu.
   Neu dung khi chay truc tiep se bao loi va nhan 0 diem
     */
    //---------------------------------------------------
    void addLast(String xName, int xSalary, int xAbility) {
        //You should write here appropriate statements to complete this function.
        //..........................
        //..........................
        if (xName.charAt(0) == 'A') {
            return;
        }
        Worker b = new Worker(xName, xSalary, xAbility);
        Node newNode = new Node(b);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    //===================================================================
    //You do not need to edit this function. Your task is to complete the addLast function above only.
    void f1() throws Exception {
        clear();
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        f.close();
    }

//==================================================================
    void addFirst(Worker b) {
        Node newNode = new Node(b);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    void addPos(Worker b, int index) {
        Node newNode = new Node(b);
        if (isEmpty()) {
            if (index == 0) {
                head = tail = newNode;
                return;
            }
        }
        Node temp = head;
        if (index == 0) {
            addFirst(b);
            return;
        }

        int count = 0;
        while (temp != null) {
            if (index - count == 1) {
                newNode.next = temp.next;
                temp.next = newNode;
                if (temp == tail) {
                    tail = newNode;
                }
                return;
            }
            count++;
            temp = temp.next;
        }
    }

    void f2() throws Exception {
        clear();
        loadData(5);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        Worker x, y;
        x = new Worker("X", 1, 2);
        y = new Worker("Y", 3, 4);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //..........................
        //..........................
        addPos(x, 0);
        addPos(y, 4);
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f3() throws Exception {
        clear();
        loadData(9);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //..........................
        //..........................

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

//==================================================================
    void f4() throws Exception {
        clear();
        loadData(13);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //..........................
        //..........................
        sortByS();

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void sortByS() {
        Node pi, pj;
        Worker x;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pj.info.salary > pi.info.salary) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }
//==================================================================

    void f5() throws Exception {
        clear();
        loadData(17);
        String fname = "f5.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //..........................
        //..........................
        sortBySalary();
        delPosition(1);
        delPosition(1);

        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
    void sortBySalary() {
        Node pi, pj;
        Worker x;
        pi = head;
        while (pi != null) {
            pj = pi.next;
            while (pj != null) {
                if (pj.info.salary > pi.info.salary) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
            }
            pi = pi.next;
        }
    }

    void delNodeHaveValueX(int x) {
        Node p = head;
        int i = 0;
        while (p != null) {
            if (p.info.salary == x) {
                break;
            }
            i++;
            p = p.next;
        }
        delPosition(i);
    }

    void delPosition(int pos) {    
        Node p = head;
        while (pos-- > 1) {
            p = p.next;
        }
        p.next = p.next.next;
    }

//==================================================================
    void delete(Node p) {
        Node temp = this.head;
        if (isEmpty()) {
            return;
        }
        while (temp != null) {
            if (temp == p) {
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
    }

    void f6() throws Exception {
        clear();
        loadData(21);
        String fname = "f6.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int deleteSalary = 11;  //find and delete the node having salary=10
        //------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        //..........................
        //..........................
        if (head == null || head.next == null) {
            return;
        }

        Node prev = head;
        Node curr = head.next;
        Node min = head;

        while (curr != null) {
            if (curr.info.salary < deleteSalary) {
                min = curr;
            }
            prev = curr;
            curr = curr.next;
        }
        if (min == null) {
            return;
        }
        prev.next = min.next;
        //------------------------------------------------------------------------------------
        ftraverse(f);
        f.close();
    }
}
