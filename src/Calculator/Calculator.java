package Calculator;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    public static void main(String[] args)
    {
        running();
    }


    public static String evaluate(String Str)
    {
        char[] tokens = Str.toCharArray();

        // Stack for numbers: 'values'
        Stack<String> values = new Stack<>();

        // Stack for Operators: 'operations'
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ') continue;

            if (tokens[i] >= '0' && tokens[i] <= '9')
            {
                StringBuilder sb = new StringBuilder();

                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
                    sb.append(tokens[i++]);

                values.push(String.valueOf(sb));


                i--;
            }


            else if (tokens[i] == '(')
                ops.push(tokens[i]);


            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // this if condition handle all one arg  operator
            else if (tokens[i]=='!' ||tokens[i]=='~' ||tokens[i]=='R' ||tokens[i]=='#' || tokens[i]=='T' || tokens[i]=='S'||tokens[i]=='C'||tokens[i]=='L'||tokens[i]=='B') {

                while(!ops.empty() && hasPrecedence(tokens[i],ops.peek()))
                {
                    values.push(applyoperationsingle(ops.pop(),values.pop()));
                }
                ops.push(tokens[i]);
            }

            // Current token is an operator. and operator take two agrs
            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i]== '%' || tokens[i] =='^')
            {

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(),values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }





        }


        while (!ops.empty()) {
            String s=null;
            Character abc = ops.peek();

            if (abc== '+' ||abc== '-' ||abc== '*' ||abc== '/' ||abc=='^' ||abc== '%' ) {
                s= applyOp(ops.pop(),values.pop(),values.pop());
            }
            if (abc=='!' ||abc=='~' ||abc=='R' ||abc=='#' || abc=='T' || abc=='S'||abc=='C' || abc== 'L' || abc=='B') {
                s= applyoperationsingle(ops.pop(),values.pop());
            }
            values.push(s);

        }
        // Top of 'values' contains
        // result, return it
        return values.pop();
    }

    private static String applyoperationsingle(Character op, String A) {
        int a=Integer.parseInt(A);
        switch (op)
        {
            case '!' : return String.valueOf(fact(a));      // need to solve fact function
            case '~' : return String.valueOf(Math.negateExact(a));  // this thing only work for positive numbers
            case 'R' : return String.valueOf(Math.toRadians(a));
            case '#' : return String.valueOf(Math.sqrt(a));
            case 'T' : return String.valueOf(Math.tan(a));
            case 'S' : return String.valueOf(Math.sin(a));
            case 'C' : return String.valueOf(Math.cos(a));
            case 'L' : return String.valueOf(Math.log10(a));
            case 'B' : {
                String i= Integer.toBinaryString(a);
                return i;
            }

        }



        return null;
    }


    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if (( op1 == '^' || op1 == '*' || op1 == '/'|| op1 =='%') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }


    public static String applyOp(char op, String B, String A)
    {
        int a=Integer.parseInt(A);
        int b=Integer.parseInt(B);

        switch (op)
        {
            case '+': return String.valueOf((a+b));
            case '-': return String.valueOf((a-b));
            case '*': return String.valueOf((a*b));

            case '%': return String.valueOf((a%b));
            case '^': return String.valueOf((Math.pow(a,b)));

            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return String.valueOf((a/b));

        }
        return null;
    }




    private static void running() {
        Scanner sc = new Scanner(System.in);
        boolean checkit=true;

        while(checkit)
        {
            System.out.print("> ");
            String str = sc.nextLine();

            if(str.equals("--help")){
                printhelp();
                System.out.print("> ");
                str=sc.nextLine();
            }
            if(str.equals("exit")) break;

            System.out.println(evaluate(str));
        }
    }

    private static void printhelp() {
        System.out.println("+ Addition");                        //Done
        System.out.println("- Subtraction");                     //Done
        System.out.println("/ Division");                        //Done
        System.out.println("* Multiplication");                    //Done
        System.out.println("^ Exponent");                        // Done
        System.out.println("% Modulus");                         // Done
        System.out.println("! Factorial");                          // Works
        System.out.println("~ Negate (work for positive value not Vise-versa");  // done but when you enter -negative can't handle it
        System.out.println("R Convert Degree to Radian");            // Works
        System.out.println("# Square Root");                        // Works
        System.out.println("S Sin Function");                        // Works
        System.out.println("T Tan Function");                           //Works
        System.out.println("C Cos Function");                            // Done
        System.out.println("L Logarithm Base10 Function");                  //Done
        System.out.println("B Decimal to Binary");                        // Done
    }

    static int fact(int n)
    {
        if (n==1 || n==0) return 1;

        return n*fact(n-1);
    }
}
