
import java.util.Stack;

public class NumStringAdder {

    public static int add(int carry, char d1, char d2, Stack resultStk) {
        int newCarry = 0;
        int num1 = Character.getNumericValue(d1); // '6'--> 6
        int num2 = Character.getNumericValue(d2); // '7' --> 7
        int sum = carry + num1 + num2;
        if (sum >= 10) { // sum = 13
            newCarry = 1; // newCarry =1
            sum -= 10; // sum = 3 --> '3' is result
        }
        resultStk.push(Character.forDigit(sum, 10));//push '3' to result stack
        return newCarry; // for next addition
    }

    public static String add(String numl, String num2) {
        Stack<Character> stk1 = new Stack();
        Stack<Character> stk2 = new Stack();
        int i;
        // push digits in numl, num2 to their stacks 
        for (i = 0; i < numl.length(); i++) {
            stk1.push(numl.charAt(i));
        }
        for (i = 0; i < num2.length(); i++) {
            stk2.push(num2.charAt(i));
        }
        // Add characters to stack stk3 
        Stack<Character> stk3 = new Stack();
        char d1, d2;
        int carry = 0;
        while (!stk1.empty() || !stk2.empty()) {
            d1 = (!stk1.empty()) ? stk1.pop() : '0';
            d2 = (!stk2.empty()) ? stk2.pop() : '0';
            carry = add(carry, d1, d2, stk3);
        }
        // Pop stk3 to result string
        String result = "";
        while (!stk3.empty()) {
            result += stk3.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        String num1 = "592"; 
        String num2 = "3784";
        String sumStr = add(num1, num2);
        System.out.println(sumStr);
    }
}
