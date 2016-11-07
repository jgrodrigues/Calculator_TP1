import java.util.Scanner;


public class Main {

    public static final String NON_EXISTENT_OPTION = "Opcao inexistente.";
    public static final String EXIT_MESSAGE = "Aplicacao terminada. Ate a proxima";

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
        System.out.printf("VM - Consultar o valor da memoria\n" +
                "LM - Indicar o nome das memorias\n" +
                "CE - Calcular o valor duma expressao\n" +
                "AVM - Atribuir ultimo valor calculado a memoria\n" +
                "A - Ajuda\n" +
                "S - Sair\n");
    }


//    private static void lm(Memory m1) {
//        String memory1 = m1.getMemory1Name();
//        String memory2 = m1.getMemory2Name();
//
//        if (m1.hasMemories()) {
//            if (!memory1.isEmpty()) {
//                System.out.printf("%s: ", memory1);
//                System.out.printf("%.2f\n", m1.getMemoryValue(memory1));
//            }
//            if (!memory2.isEmpty()) {
//                System.out.printf("%s: ", memory2);
//                System.out.printf("%.2f\n", m1.getMemoryValue(memory2));
//            }
//        } else {
//            System.out.println(NO_MEMORY_MESSAGE);
//        }
//    }


    /**
     * ASSIGN MEMORY NAMES
     *
     * @param memory1
     * @param memory2
     * @param m1
     */
//    private static void assignMemory(String memory1, String memory2, Memory m1) {
//
//        if(!memory1.isEmpty() && !memory2.isEmpty() && !memory1.equalsIgnoreCase(memory2)) {
//            m1 = new Memory(memory1, memory2);
//            if (memory1.equals(memory2)) {
//                m1 = new Memory(memory1);
//            }
//        } else {
//            m1 = new Memory();
//        }
//
//    }

//    private static void vm(String memoryName, Calculator c1) {
//        double memoryValue = c1(memoryName);
//        if (!(Double.isNaN(memoryValue))) {
//            System.out.println(memoryValue);
//
//        } else {
//            System.out.println(NON_EXISTENT_MEMORY);
//        }
//    }




    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        Calculator c1;

        String memories = in.nextLine().trim();
        String memory1 = "";
        String memory2 = "";

        if (memories.isEmpty()) {
            c1 = new Calculator();
            System.out.println("1");
        } else if (memories.indexOf(' ') == -1) {
            c1 = new Calculator(memories);
            System.out.println("2");
        } else {
            memory1 = memories.substring(0, memories.indexOf(' '));
            memory2 = memories.substring(memories.indexOf(' '), memories.length()).trim();
            c1 = new Calculator(memory1, memory2);
            System.out.println("3");
        }





//        showOptions();
        String option = in.next().toUpperCase();

        while(!option.equals("S")) {



            switch (option) {
                case ("VM"): //consultar valor memoria
                    c1.vm(in.next());
                    break;
                case ("LM"): //informa nome das memorias com valor 2 casas
                    c1.lm();
                    break;
                case("CE"):
                    System.out.println(c1.ce(in.nextLine().toUpperCase().trim()));
                    break;
                case("AVM"):
                    c1.avm(in.next());
                    break;
                case ("A"):
                    showOptions();
                    break;
                default:
                    //DEBUG
                    System.out.println(option);
                    System.out.println(NON_EXISTENT_OPTION);
                    break;
            }
            in.nextLine();
            option = in.next().toUpperCase();
        }
        System.out.println(EXIT_MESSAGE);
    }
}










