import java.util.Scanner;


public class Main {

    /**
     * Constants
     */
    public static final String NON_EXISTENT_OPTION = "Opcao inexistente.";
    public static final String EXIT_MESSAGE = "Aplicacao terminada. Ate a proxima";

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
                case ("VM"): //Return value of memory
                    c1.getValue(in.next());
                    break;
                case ("LM"): //Return value and name of memories
                    c1.getMemoriesInfo();
                    break;
                case("CE"): //Calculate Expression
                    System.out.printf("%.2f\n", c1.calculateExpression(in.nextLine().toUpperCase().trim()));
                    break;
                case("AVM"): //Set last result value to a memory
                    c1.assignLastValue(in.next());
                    break;
                case ("A"): //Help Menu
                    showOptions();
                    break;
                default:
                    //DEBUG
                    System.out.println(option);
                    System.out.println(NON_EXISTENT_OPTION);
                    break;
            }
//            in.nextLine();
            option = in.next().toUpperCase();
        }
        System.out.println(EXIT_MESSAGE);
    }
}










