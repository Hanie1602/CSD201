/*
    Lambda khái niệm khái quát hóa các hàm toán học
    ví dụ: Lambda x, f : x -> 6x^2 -5
    Cú pháp Lambda: (x) -> biểu thức tính toán
    Cú pháp Lambda còn đdược gọi là lập trình hàm, functional programming
 */
package lambda;

import java.util.ArrayList;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.List;

//Khai báo các hàm cùng tham số -> Dùng interface
//Trong interface buộc phải có duy nhất 1 hàm trừu tượng (chắc chắn phải có 1 hàm trừu tượng)
public interface MyFunc<T, U, V> {

    public abstract V eval(T x, U y);

    //TEST
    public static void main(String[] args) {
        //f2: (x,y) -> 5x^2 - 6y + 5
        MyFunc<Double, Double, Double> f = new MyFunc<Double, Double, Double>() {
            @Override
            public Double eval(Double x, Double y) {
                return 5 * x * x - 6 * y + 5;
            }
        };          //; là kết thúc khai biến f
        System.out.println(f.eval(1.0, -1.0));

        MyFunc<Double, Double, Double> f2 = (x, y) -> {
            return 5 * x * x - 6 * y + 5;
        };
        System.out.println(f2.eval(2.0, -2.0));

        //Test BiPredicate: t <= u
        BiPredicate<Integer, Integer> bf = (t, u) -> {
            return t <= u;
        };
        System.out.println(bf.test(5, 7));

        //Hàm tìm trị max của 1 list
        Function<List<Double>, Double> f3 = (t) -> {
            double result = Double.MIN_VALUE;
            for (Double x : t) {
                if (result < x) {
                    result = x;
                }
            }
            return result;
        };
        ArrayList<Double> L = new ArrayList();
        L.add(1.5);
        L.add(8.0);
        L.add(7.5);
        L.add(-1.5);
        L.add(10.5);
        double max = f3.apply(L);
        System.out.println(max);
    }
} //Interface MyFunc

/* Note:
    Có cần tạo các hàm không: --> Không, vì các ngôn ngữ lập trình đều có (mình chỉ cần biết cách làm, hiểu là được)
    Có cần biết lập trình hàm không: --> Không, bởi vì không cần dùng lập trình hàm (Lập trình hàm chỉ là cú pháp

    Predicate: là một hàm boolean nhận tham số đầu vào, trả trị đúng sai (trả trị boolean)
*/
