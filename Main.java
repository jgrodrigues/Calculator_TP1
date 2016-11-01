import java.util.Scanner;
import java.util.Arrays;


public class Main {

    public static final char[] OPERAND = {'+', '-', '*', '/', '%'};

    private static int checkLastExpression(String input) {
        int lastParentheses;
        String exp;
        lastParentheses = input.lastIndexOf("(");
        if (input.charAt(lastParentheses + 1) == '+') {
            exp = input.substring((lastParentheses + 1), (input.indexOf(')', lastParentheses + 1)));
            System.out.println(exp);
        } else if(Character.isDigit(input.charAt(lastParentheses + 1))) {
            System.out.println("ya");
            //parcelaDireita = input.subs

        }

        System.out.println(lastParentheses);
        return 0;
    }

    private static



    public static void main(String[] args) {
        Calculator calc1 = new Calculator();
        Scanner in = new Scanner(System.in);
        Counter count1 = new Counter();

        String input = in.next();
        char[] arr = input.toCharArray();

        checkLastExpression(input);

        in.nextLine();


//
//
//        int openParentheses = 0;
//        int closeParentheses = 0;
//
//        char[] operandArr = new char[5];
//
//        for (int i = 0; i < input.length(); i++) {
//            System.out.println(arr);
//            switch (arr[i]) {
//                case ('('):
//                    openParentheses++;
//                    System.out.println("Open parentheses " + openParentheses);
//                    break;
//                case (')'):
//                    closeParentheses++;
//                    System.out.println("Closed parentheses " + openParentheses);
//                    break;
//                case ('+'):
//                    operandArr[i] = '+';
//                    break;
//                case ('-'):
//                    operandArr[i] = '-';
//                    break;
//                case ('*'):
//                    operandArr[i] = '*';
//                    break;
//                case ('/'):
//                    operandArr[i] = '/';
//                    break;
//                case ('%'):
//                    operandArr[i] = '%';
//                    break;
////              default:
////                  System.out.println("Operand invalid. Quitting...");
////                  System.exit(1);
//            }
//        }
//
//        if (openParentheses == closeParentheses) {
//            System.out.println("Total expressions " + openParentheses);
//
//            System.out.println(arr);
//            System.out.println(Arrays.toString(operandArr));
//
//
//        } else {
//            System.out.println("Expression Invalid. Quitting...");
//            System.exit(1);
//        }
//
//
//        for (int z = 0; z < arr.length; z++) {
//            System.out.println(arr[z]);
//        }
//
//        int firstParentheses = input.lastIndexOf('(');
//        System.out.println("X" + firstParentheses);
//
//        int lastParentheses = 0;
//
//            lastParentheses = input.lastIndexOf(')', firstParentheses) + firstParentheses;
//            System.out.println("L"+ lastParentheses);
//
//        String expression = input.substring(firstParentheses + 1, lastParentheses);
//
//        System.out.println(expression);




    }
}


