import java.util.Scanner;
import java.util.Arrays;


public class Main {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
//    public static final char

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
        String input = in.next();
        int count = 0, len = input.length();
        String ret = null;
        String s = "teste 1";

        while(count < len) {


        if (input.charAt(count) == '+') {
            ret = s.substring(0, count);
        } else if (input.indexOf(0) == '-') {
            ret = ret + s.substring(count+1, len);
        } else if (input.indexOf(0) == '+') {

        } else if (input.indexOf(0) == '+') {

        } else if (input.indexOf(0) == '+') {

        }

        }

        //ou

//        count=0;
//        int start=0;
//
//                while(count < len) {
//                    if (s.charAt(count) == 'b' && !done) {
//                        start = count;
//                    } else if(s.charAt(count) == 's') {
//                        ret = s.substring(start, count+1);
//                        done = true;
//                    }
//                }


        count++;
        System.out.println(ret);
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


//    private static void consultMemoryName(Calculator c1) {
//        if (c1.getHasMemory()) {
//
//        }
//    }

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
        return in.nextLine().toUpperCase().trim();
    }

    private static String readNext(Scanner in) {
        return in.next().toUpperCase().trim();
    }



    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        Calculator c1;

        System.out.println("Insira nome de cada uma das duas memorias(ex: primeira segunda): ");
        String memory1 = readNext(in);
        String memory2 = readNext(in);
        in.nextLine();


        if(!memory1.isEmpty() && !memory2.isEmpty()) {
            c1 = new Calculator(memory1, memory2);
        } else if (!memory1.isEmpty() && memory2.isEmpty()) {
            c1 = new Calculator(memory1);
        } else {
            c1 = new Calculator();
        }




        System.out.println(memory1);
        System.out.println(memory2);


        String option = in.next().toUpperCase();

        while(!option.equals("S")) {

            switch (option) {
                case ("VM"): //consultar valor memoria
                    consultMemoryValue(in.next().toUpperCase(), c1);
                    break;
                case ("LM"): //informa nome das memorias com valor 2 casas
                    lm(c1);
                    break;
                case("CE"):
                    System.out.println(c1.sExpression(in.next()));
                    break;
                case("AVM"):
                    System.out.println(c1.avm());
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










