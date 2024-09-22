/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {
   Node root;
   BSTree() {root=null;}
   boolean isEmpty() {
       return(root==null);
      }
   void clear() {
       root=null;
      }
   void visit(Node p) {
      System.out.print("p.info: ");
      if(p != null) System.out.println(p.info + " ");
     }
   void fvisit(Node p, RandomAccessFile f) throws Exception {
      if(p != null) f.writeBytes(p.info + " ");
     }
   void breadth(Node p, RandomAccessFile f) throws Exception {
     if(p==null) return;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     while(!q.isEmpty()) {
        r = q.dequeue();
        fvisit(r,f);
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
   void preOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      fvisit(p,f);
      preOrder(p.left,f);
      preOrder(p.right,f);
     }
   void inOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      inOrder(p.left,f);
      fvisit(p,f);
      inOrder(p.right,f);
     }
   void postOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      postOrder(p.left,f);
      postOrder(p.right,f);
      fvisit(p,f);
     }

   void loadData(int k) { //do not edit this function
      String [] a = Lib.readLineToStrArray("data.txt", k);
      int [] b = Lib.readLineToIntArray("data.txt", k+1);
      int [] c = Lib.readLineToIntArray("data.txt", k+2);
      int n = a.length;
      for(int i=0;i<n;i++) insert(a[i],b[i],c[i]);
     }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
  void insert(String xBrand, int xColor, int xWeight) {
    //You should insert here statements to complete this function
    if(xBrand.charAt(0) == 'A'){
        return;
    }
    Node curr = root;
    Node parent = null;
    Bike b = new Bike(xBrand, xColor, xWeight);
    Node newNode = new Node(b);
    if(isEmpty()){
            root = newNode; 
            return;
        }else{
            curr = root;
            parent = null;
            while (true) {            
                parent = curr;
                if(curr.info.weight > b.weight){
                    curr = curr.left;
                    if(curr == null){
                        parent.left = newNode;
                         return;
                    }
                }else{
                    curr = curr.right;
                    if(curr == null){
                        parent.right = newNode;
                        return;
                    }
                }
                if(curr.info.weight== b.weight){  
                    return;
                }
            }
        }
   }

//Do not edit this function. Your task is to complete insert function above only.
  void f1() throws Exception {
    clear();
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    inOrder(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  
  
//=============================================================
  void postOrder2(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      postOrder2(p.left,f);
      postOrder2(p.right,f);
      if(p.info.color < 7){
          fvisit(p,f);
      }
     }
  void f2() throws Exception {
    clear();
    loadData(5);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    postOrder(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
      postOrder2(root, f);


    //------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }  

//=============================================================
  boolean hasChild(Node p){
      if(p.left == null && p.right == null){
          return false;
      }
      return true;
      
  }
  Node search(Node p, int x){
      if(p == null){
          return null;
      }
      if(x == 2){
          return p;
      }
      if(p.left!=null && hasChild(p.left)){
          x++;
          return search(p.left, x);         
      }
      if(p.right!=null  && hasChild(p.right)){
            x++;
          return search(p.right, x);
      }
      return null;
  }
  void delete(Node x) {
        if (isEmpty()) 
            return;
        Node p = root, f = null;
        while (p != null) {
            if (p.info == x.info) break;
            f = p;
            if (p.info.weight > x.info.weight)
                p = p.left;
            else
                p = p.right;
        }
        if (p == null) return;
        
        // No child
        if (p.left == null && p.right == null) {
            if (f == null) {
                root = null;
                return;
            }
            if (f.left == p)
                f.left = null;
            else
                f.right = null;
        }
        
        // 1 child
        if (p.left != null && p.right == null) {
            if (f == null) {
                root = p.left;
                return;
            }
            if (f.left == p)
                f.left = p.left;
            else
                f.right = p.left;
        } 
        else if (p.left == null && p.right != null) {
            if (f == null) {
                root = p.right;
                return;
            }
            if (f.left == p) 
                f.left = p.right;
            else 
                f.right = p.right;
        }

        // 2 children
        if (p.left != null && p.right != null)
            deleteByCopying(p);
    }
    
    void deleteByCopying(Node p) {
        Node rightMost = p.left, f = null;
        while (rightMost.right != null) {
            f = rightMost;
            rightMost = rightMost.right;
        }
        p.info = rightMost.info;
        if (f == null)
            p.left = rightMost.left;
        else 
            f.right = rightMost.left;
    }

  void f3() throws Exception {
    clear();
    loadData(9);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    Node p = search(root, 1);
    Node q = p.right;
      delete(q);

    //------------------------------------------------------------------------------------
    breadth(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  

//=============================================================
  Node findMaxNode(Node p){
        if(p.right == null){
            return p.right;
        }
        return findMaxNode(p.right);
    }
  Node searchParent(Node a) {
        if (a == null)
            return null;
        Node p = root, f = null;
        while (p != null && p != a) {
            f = p;
            if (p.info.weight > a.info.weight)
                p = p.left;
            else 
                p = p.right;
        }
        return f;
    }
  Node rotateLeft(Node p){
      if (p == null || p.left == null) {
            return (p);
        }
        Node q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
  }
 void f4() throws Exception {
    clear();
    loadData(13);;
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    Node p = root;
    Node max = findMaxNode(p);
    Node parent = rotateLeft(searchParent(max));
     
    //------------------------------------------------------------------------------------
    breadth(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  

 }

/*
    The class Bike with 3 data members: brand, color and weight is given and you do not need to edit it. The BSTree class is a binary search tree of Bike objects. 
    The variable weight is the key of the tree, thus it must be unique. The following methods should be completed:
    ===================================================================

    void insert(String xBrand, int xColor, int xWeight) - check if xBrand.charAt(0) == 'A' then do nothing, 
    otherwise insert new Bike object with brand=xBrand, color=xColor, weight-xWeight to the tree (color and weight can get arbitrary, even negative values).

    void f1() - Do not edit this method. Your task is to complete the insert(...) method above only. Output in the file f1.txt must be the following:
     (B,9,4) (C,4,3) (D,8,6) (Y,6,-7) (E,2,5) (F,-6,7) 
     (Y,6,-7) (C,4,3) (B,9,4) (E,2,5) (D,8,6) (F,-6,7) 

    void f2()-Perform post-order traversal from the root but display to file f2.txt nodes with color<7 only. 
    Hint: Copy the function postOrder(...) to function postOrder2(...) and modify it. Output in the file f2.txt must be the following:
     (F,2,-1) (D,6,1) (G,7,3) (M,4,6) (K,-1,5) (I,1,7) (L,5,10) (J,3,9) (H,10,8) (E,9,4) (C,8,2) 
     (F,2,-1) (D,6,1) (M,4,6) (K,-1,5) (I,1,7) (L,5,10) (J,3,9) 
     
    Câu 3: SAI
    void f3() Perfom breadth-first traversal from the root and find the second node p having 2 children. 
    If such node does not exist then do nothing, otherwise delete the node q = p.right by copying. 
    Output in the file f3.txt must be the following:
    (C,8,2) (D,6,1) (E,9,4) (F,2,-1) (G,7,3) (H,10,8) (1,1,7) (J,3,9) (K,-1,5) (L,5,10) (M,4,6)
    (C,8,2) (D,6,1) (E,9,4) (F,2,-1) (G,7,3) (1,1,7) (K,-1,5) (J,3,9) (M,4,6) (L,5,10)

    void f4 ()-Find the node p having maximum weight. Suppose f is the father of p. Check if f is not null then rotate f about p. 
    Output in the file f4.txt must be the following:
    (C,8,2) (D,6,1) (E,9,4) (F,2,-1) (G,7,3) (H,10,8) (1,1,7) (1,3,9) (K,-1,5) (L,5,10) (M,4,6)
    (C,8,2) (D,6,1) (E,9,4) (F,2,-1) (G,7,3) (H,10,8) (1,1,7) (L,5,10) (K,-1,5) (1,3,9) (M,4,6)
*/
