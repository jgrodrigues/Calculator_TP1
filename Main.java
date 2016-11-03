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
        } else if (Character.isDigit(input.charAt(lastParentheses + 1))) {
            System.out.println("ya");
            //parcelaDireita = input.subs

        }

        System.out.println(lastParentheses);
        return 0;
    }

    private static void handleExpression(Scanner in, Calculator c1) {

    }

    /**
     * SHOW HELP MENU
     */
    private static void showOptions() {
        //MENU
    }

    /**
     * VM - Consult Memory Value - WORKING!
     * @param memoryName
     * @param c1
     */
    private static void consultMemoryValue(String memoryName, Calculator c1) {
        if (c1.memoryExists(memoryName)) {
            System.out.printf("%.2f\n", c1.getMemoryValue(memoryName));
        } else {
            System.out.println("Memoria nao existente");
        }
    }

    private static void lm (Calculator c1) {
        if(c1.getHasMemory()) {
            for (int i = 0; i < c1.getMemoryNames().length; i++) {
                if (!c1.getMemoryNames()[i].isEmpty()) {
                    System.out.println(c1.getMemoryNames()[i] + ": " + c1.getMemoryValue(c1.getMemoryNames()[i]));
                }
            }
        } else {
            System.out.println("Calculadora sem memorias.");
        }
    }


    private static void consultMemoryName(Calculator c1) {
        if (c1.getHasMemory()) {

        }
    }

    /**
     * ASSIGN MEMORY NAMES - WORKING!!
     *
     * @param in
     */
    private static void assignMemory(Scanner in) {
        String[] memories = readLine(in).toUpperCase().split(" ");
        Calculator c1 = new Calculator(memories[0], memories[1]);
    }


    private static String readLine(Scanner in) {
        return in.nextLine().toUpperCase();
    }

    private static String readNext(Scanner in) {
        return in.next().toUpperCase();
    }

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        Counter count1 = new Counter();
        Calculator c1;

        System.out.println("Insira nome de cada uma das duas memorias(ex: primeira segunda): ");
        String[] memories = {};
        String start = readLine(in);



        if (!start.isEmpty()) {
            memories = start.split("\\s+");
            if (memories.length == 2) {
                c1 = new Calculator(memories[0], memories[1]);
            } else if(memories.length == 1) {
                c1 = new Calculator(memories[0]);
            } else {
                c1 = new Calculator();
            }
        } else{
            c1 = new Calculator();
        }

        System.out.println(Arrays.toString(memories));


        String option = in.next().toUpperCase();

        while(!option.equals("S")) {

            switch (option) {
                case ("VM"): //consultar valor memoria
                    consultMemoryValue(in.next().toUpperCase(), c1);
                    break;
                case ("LM"): //informa nome das memorias com valor 2 casas
                    lm(c1);
                    break;
                default:
                    //                System.out.println(option);
                    System.out.println("Greg");
                    break;
            }
            in.nextLine();
            option = in.next().toUpperCase();
        }
    }
}





//        handleExpression(in, c1);


//        String memArr[] = input.split(" ");


//        System.out.println(Arrays.toString(memArr));



//        char[] arr = input.toCharArray();




        //checkLastExpression(input);



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






