/**
 * Created by Jonas Rodrigues, ID 49806 on 29/10/2016.
 */

import java.util.Scanner;

public class Main {

    /* Constants */
    public static final String NON_EXISTENT_OPTION = "Opcao inexistente.";
    public static final String EXIT_MESSAGE = "Aplicacao terminada. Ate a proxima.";
    public static final String INVALID_EXPRESSION = "Expressao mal definida.";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";
    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";

    /* Show help menu */
    private static void showOptions() {
        System.out.printf("VM - Consultar o valor da memoria\n" +
                "LM - Indicar o nome das memorias\n" +
                "CE - Calcular o valor duma expressao\n" +
                "AVM - Atribuir ultimo valor calculado a memoria\n" +
                "A - Ajuda\n" +
                "S - Sair\n");
    }

    /**
     * Calculate an expression given by the user
     *
     * @param input the String expression given by the user
     * @param c1    Calculator object
     */
    private static void calcExp(String input, Calculator c1) {
        if (c1.hasEqualParentheses(input)) { //Only proceed if expression has the same number of opened and closed parentheses(validation)
            double result = c1.calcComplexExpression(input);
            if (!Double.isNaN(result)) {
                System.out.printf("resultado: %.2f\n", result);
                c1.setLastValue(result);
            } else {
                System.out.println(INVALID_EXPRESSION);
            }
        } else {
            System.out.println(INVALID_EXPRESSION);
        }
    }

    /**
     * Assign last result from a valid expression if memory name provided is equal to mem1 name or mem2 name
     *
     * @param memoryName the String referencing the memory we want to assign the value
     * @param c1         Calculator object
     */
    private static void assignMemoryValue(String memoryName, Calculator c1) {
        double lastResult = c1.getLastResult();
        if (c1.isMemoryName(memoryName)) {
            if (memoryName.equalsIgnoreCase(c1.getMemory1Name())) {
                c1.setMemory1Value(lastResult);
            } else if (memoryName.equalsIgnoreCase(c1.getMemory2Name())) {
                c1.setMemory2Value(lastResult);
            }
        } else {
            System.out.println(NON_EXISTENT_MEMORY);
        }
    }

    /**
     * Get the value of a certain memory
     *
     * @param memoryName the String indicating the memory which we want to consult the value
     * @param c1         Calculator object
     */
    private static void getMemoryValue(String memoryName, Calculator c1) {
        if (c1.isMemoryName(memoryName)) {
            if (memoryName.equalsIgnoreCase(c1.getMemory1Name())) {
                String memory1Name = c1.getMemory1Name();
                double memory1Value = c1.getMemoryValue(memoryName);
                System.out.printf("%s: ", memory1Name);
                System.out.printf("%.2f\n", memory1Value);
            } else if (memoryName.equalsIgnoreCase(c1.getMemory2Name())) {
                String memory2Name = c1.getMemory2Name();
                double memory2Value = c1.getMemoryValue(memoryName);
                System.out.printf("%s: ", memory2Name);
                System.out.printf("%.2f\n", memory2Value);
            }
        } else {
            System.out.println(NON_EXISTENT_MEMORY);
        }
    }


    /**
     * Shows all existing memories and corresponding values
     *
     * @param c1 Calculator object
     */
    private static void getMemoriesInfo(Calculator c1) {
        if (c1.hasMemories()) {
            if (c1.hasMemory1()) {
                String mem1Name = c1.getMemory1Name();
                double mem1Value = c1.getMemoryValue(mem1Name);
                System.out.printf("%s: ", mem1Name);
                System.out.printf("%.2f\n", mem1Value);
            }
            if (c1.hasMemory2()) {
                String mem2Name = c1.getMemory2Name();
                double mem2Value = c1.getMemoryValue(mem2Name);
                System.out.printf("%s: ", mem2Name);
                System.out.printf("%.2f\n", mem2Value);
            }
        } else {
            System.out.println(NO_MEMORY_MESSAGE);
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
        while (!option.equals("S")) {
            switch (option) {
                case ("VM"): //Return value of a certain memory
                    getMemoryValue(in.next(), c1);
                    break;
                case ("LM"): //Return value and name of all memories
                    getMemoriesInfo(c1);
                    break;
                case ("CE"): //Calculate Expression
                    String input = in.nextLine().toUpperCase().trim();
                    calcExp(input, c1);
                    break;
                case ("AVM"): //Set last result calculated to a certain memory
                    assignMemoryValue(in.next(), c1);
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
        in.close();
        System.out.println(EXIT_MESSAGE);
    }
}










