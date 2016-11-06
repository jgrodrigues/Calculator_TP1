import java.util.Scanner;


public class Main {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final String NON_EXISTENT_OPTION = "Opcao inexistente.";
    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";
    public static final String EXIT_MESSAGE = "Aplicacao terminada. Ate a proxima";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";


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


    private static void lm(Memory m1) {
        String memory1 = m1.getMemory1Name();
        String memory2 = m1.getMemory2Name();

        if (m1.hasMemories()) {
            if (!memory1.isEmpty()) {
                System.out.printf("%s: ", memory1);
                System.out.printf("%.2f\n", m1.getMemoryValue(memory1));
            }
            if (!memory2.isEmpty()) {
                System.out.printf("%s: ", memory2);
                System.out.printf("%.2f\n", m1.getMemoryValue(memory2));
            }
        } else {
            System.out.println(NO_MEMORY_MESSAGE);
        }
    }



    /**
     * ASSIGN MEMORY NAMES - WORKING!!
     *
     * @param in
     */
    private static void assignMemory(String memory1, String memory2, Memory m1) {

        if(!memory1.isEmpty() && !memory2.isEmpty() && !memory1.equalsIgnoreCase(memory2)) {
            m1 = new Memory(memory1, memory2);
            if (memory1.equals(memory2)) {
                m1 = new Memory(memory1);
            }
        } else {
            m1 = new Memory();
        }

    }


    private static void avm(String memoryName, Calculator c1, Memory m1) {
        double lastResult = c1.getLastResult();
        if (memoryName.equalsIgnoreCase(m1.getMemory1Name())) {
            m1.setMemoryValue(memoryName, lastResult);
            System.out.println(memoryName + ": " + lastResult);
        } else if(memoryName.equalsIgnoreCase(m1.getMemory2Name())) {
            m1.setMemoryValue(memoryName, lastResult);
            System.out.println(memoryName + ": " + lastResult);
        }
    }

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        Calculator c1 = new Calculator();
        Memory m1 = null;
        System.out.println("Insira nome de cada uma das duas memorias(ex: primeira segunda): ");

        String memory1 = in.next().trim();
        String memory2 = in.next().trim();
        in.nextLine();

//        assignMemory(memory1, memory2, m1);

        if(!memory1.isEmpty() && !memory2.isEmpty() && !memory1.equalsIgnoreCase(memory2)) {
            m1 = new Memory(memory1, memory2);
            if (memory1.equals(memory2)) {
                m1 = new Memory(memory1);
            }
        } else {
            m1 = new Memory();
        }


        //DEBUG
        System.out.println(memory1);
        System.out.println(memory2);


        //Options
        String option = in.next().toUpperCase();

        while(!option.equals("S")) {

            switch (option) {
                case ("VM"): //consultar valor memoria
                    System.out.println(m1.getMemoryValue(in.next()));
                    break;
                case ("LM"): //informa nome das memorias com valor 2 casas
                    lm(m1);
                    break;
                case("CE"):
                    System.out.println(c1.sExpression(in.next().toUpperCase()));
                    break;
                case("AVM"):
//                    c1.avm(in.next().toUpperCase());
                    avm(in.next(), c1, m1);
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










