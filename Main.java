import java.util.Scanner;


public class Main {

    /**
     * Constants
     */
    public static final String NON_EXISTENT_OPTION = "Opcao inexistente.";
    public static final String EXIT_MESSAGE = "Aplicacao terminada. Ate a proxima.";
    public static final String INVALID_EXPRESSION = "Expressao mal definida.";

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

    private static void calcExp(String input, Calculator c1) {
        if (c1.hasEqualParentheses(input)) {
            System.out.printf("resultado: %.2f\n", c1.calculateExpression(input));
        } else {
            System.out.println(INVALID_EXPRESSION);
        }
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Calculator c1;
        String memories = in.nextLine().trim();
        String memory1;
        String memory2;
        if (memories.isEmpty()) {
            c1 = new Calculator();
        } else if (memories.indexOf(' ') == -1) {
            c1 = new Calculator(memories);
        } else {
            memory1 = memories.substring(0, memories.indexOf(' '));
            memory2 = memories.substring(memories.indexOf(' '), memories.length()).trim();
            c1 = new Calculator(memory1, memory2);
        }

        String option = in.next().toUpperCase();
        while(!option.equals("S")) {
            switch (option) {
                case ("VM"): //Return value of memory
                    c1.getValue(in.next());
                    break;
                case ("LM"): //Return value and name of memories
                    c1.getMemoriesInfo();
                    break;
                case("CE"): //Calculate Expression
                    String input = in.nextLine().toUpperCase().trim();
                    calcExp(input, c1);
                    break;
                case("AVM"): //Set last result value to a memory
                    c1.assignLastValue(in.next());
                    break;
                case ("A"): //Help Menu
                    showOptions();
                    break;
                default:
                    System.out.println(NON_EXISTENT_OPTION);
                    break;
            }
            option = in.next().toUpperCase();
        }
        System.out.println(EXIT_MESSAGE);
    }
}










