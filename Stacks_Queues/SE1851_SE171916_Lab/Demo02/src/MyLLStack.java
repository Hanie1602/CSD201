
import java.util.LinkedList;

public class MyLLStack<E> {

    // che dữ liệu để không cho bên ngoài truy cập private 
    LinkedList<E> list = null; // list các đối tượng

    // Constructors
    public <E> MyLLStack() {
        list = new LinkedList(); // cấp bộ nhớ đủ chúa MAXN phần tử
    }

    // Kiểm tra stack trống
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Đẩy 1 phần tử vào stack -phía đầu ds 
    public E push(E item) {
        list.addFirst(item); // O(1)
        return item;
    }

    // Lấy 1 phần tủ ra ở đầu ds
    public E pop() {
        if (isEmpty()) {
            return null; //không có 
        }
        return list.removeFirst(); // 0(1)
    }

    // Lất tham chiếu đến phần tủ đỉnh stack
    public E topE1() {
        if (isEmpty()) {
            return null;
        }
        return list.peek();
    }

    // Class for a student
    static class Student {

        String id;
        String name;
        int mark;

        public Student(String id, String name, int mark) {
            this.id = id;
            this.name = name;
            this.mark = mark;
        }

        @Override
        public String toString() {
            return id + ", " + name + ", " + mark;
        }
    }

    // Test
    public static void main(String[] args) {
        // Stack of integers
        MyLLStack<Integer> stk = new MyLLStack();
        stk.push(5);
        stk.push(10);
        stk.push(15);
        System.out.print(stk.pop() + ", "); // Xuất 15
        stk.push(20);
        stk.push(25);
        System.out.print(stk.pop() + ", "); // xuất 25 
        System.out.println(stk.pop() + ", "); // xuất 20
        
        // Stack of students
        MyLLStack<Student> stk2 = new MyLLStack();
        stk2.push(new Student("SE01", "Joseline", 7));
        stk2.push(new Student("SE05", "Karen", 2));
        stk2.push(new Student("SE04", "Smith", 4));
        while (!stk2.isEmpty()) {
            System.out.println(stk2.pop());
        }
    }
}
