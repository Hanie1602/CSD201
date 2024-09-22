
import java.util.Stack;
import java.util.StringTokenizer;

public class PostfixEvaluator {

    public static boolean isoperator(String S) {
        return (S.equals("+") || S.equals("-") || S.equals("*") || S.equals("/"));
    }

    // Evaluate a simple expression + - *
    public static double evaluate(String op, double nl, double n2) {
        if (op.equals("+")) {
            return nl + n2;
        }
        if (op.equals("-")) {
            return nl - n2;
        }
        if (op.equals("*")) {
            return nl * n2;
        }
        if (op.equals("/")) {
            if (n2 == 0) {
                throw new RuntimeException("Divide by 0!");
            }
            return nl / n2;
        }
        throw new RuntimeException("Operator is not supported!");
    }

    public static double evaluate(String exp) {
        double result = 0;
        // Split expression to sub-strings
        StringTokenizer stk = new StringTokenizer(exp, "() ");
        Stack<Double> stack = new Stack();
        double nl, n2;
        while (stk.hasMoreElements()) {
            String part = stk.nextToken(); // get a part of exp.
            // Checking S with operators
            if (!isoperator(part)) {
                stack.push(Double.parseDouble(part));
            } else { // part is an operator
                // pop 2 values from stack
                n2 = stack.pop(); // LIFO --> n2 must be taken out first 
                nl= stack.pop();
                // evaluate sub-expression: nl part n2
                result = evaluate(part, nl, n2);
                stack.push(result); // push temporary result to stack
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String exp = " ((3)(4)*(5) (6)* + (3)*";
        System.out.println(evaluate(exp));
    }
}
