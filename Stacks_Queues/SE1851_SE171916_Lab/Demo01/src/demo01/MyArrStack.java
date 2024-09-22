//Lớp cho một array stack dạng mảng một chiều chứa phần tử
package demo01;

public class MyArrStack<E> {

    //che dữ liệu để không cho bên ngoài truy cập
    private int MAXN = 100;     //số phần tử tối đa, mặc định 100 phần tử
    private int cur = -1;       //Chỉ số phần tử hiện hành
    private Object[] arr = null;  //mảng đối tượng

    //Constructors mặc định cấp bộ nhớ mảng 100 phần tử
    public <E> MyArrStack() {
        arr = new Object[MAXN];     //Cấp bộ nhớ đủ chứa MAXN phần tử
    }

    //Constructors cấp bộ nhớ mảng có số phần tử tùy ý
    public <E> MyArrStack(int maxN) {
        MAXN = maxN;
        arr = new Object[MAXN];
    }

    //Kiểm tra stack trống
    public boolean isEmpty() {
        return cur == -1;
    }

    //Kiểm tra stack đầy
    public boolean isFull() {
        return cur == MAXN - 1;
    }

    //Đẩy 1 phần tử vào stack
    public E push(E item) {
        if (isFull()) {
            return null;       //stack đầy không thêm vào nữa
        }
        cur++;
        arr[cur] = item;    //thêm vào cuối -> 0(1)
        return item;
    }

    //lấy 1 phần tử ra
    public E pop() {
        if (isEmpty()) {
            return null;      //Không có
        }
        E item = (E) arr[cur];      //phần tử được lấy ra. 0(1)
        cur--;          //stack bớt 1 phần tử
        return item;
    }

    //Lấy tham chiếu đến phần tử đỉnh stack
    public E topE1() {
        if (isEmpty()) {
            return null;
        }
        return (E) arr[cur];
    }

    //TESTING: Class for a student
    static class Lamp {

        String id;
        int price;

        public Lamp(String id, int price) {
            this.id = id;
            this.price = price;
        }

        @Override
        public String toString() {
            return id + ", " + price;
        }
    } //class Student

    //Test
    public static void main(String[] args) {
        //Stack of integers
        MyArrStack<Integer> stk = new MyArrStack<>();
        stk.push(5);
        stk.push(10);
        stk.push(15);
        System.out.print(stk.pop() + ", "); // Xuất 15 stk.push(20); stk.push(25);
        System.out.print(stk.pop() + ", "); // xuất 25
        System.out.println(stk.pop() + ", "); // xuất 20
// Stack of students
        MyArrStack<Lamp> stk2 = new MyArrStack();
        stk2.push(new Lamp("L01", 7));
        stk2.push(new Lamp("L05", 2));
        stk2.push(new Lamp("L99", 4));
        while (!stk2.isEmpty()) {
            System.out.println(stk2.pop());
        }
    }
} //Class MyArrStack
