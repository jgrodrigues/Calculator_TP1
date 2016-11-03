import java.util.DoubleSummaryStatistics;

/**
 * Created by jonas on 29/10/2016.
 */
public class Calculator {

    //Variaveis
    private double memory1;
    private double memory2;
    private String memory1Name;
    private String memory2Name;
    private boolean hasMemory = false;
    private int memoryNumber;

    //Constructor
    public Calculator(String memory1Name, String memory2Name) {
        if (memory1Name.equals(memory2Name)) {
            this.memory1Name = memory1Name;
            memory1 = 0;
            hasMemory = true;
            memoryNumber = 1;

        }else {
            this.memory1Name = memory1Name;
            this.memory2Name = memory2Name;
            memory1 = 0;
            memory2 = 0;
            hasMemory = true;
            memoryNumber = 2;
        }
    }

    public Calculator (String memory1Name) {
        this.memory1Name = memory1Name;
        memory1 = 0;
        memoryNumber = 2;
        hasMemory = true;
    }


    public Calculator () {
        memoryNumber = 0;
    }

    public boolean getHasMemory() {
        return hasMemory;
    }

    public int getMemoryNumber() {
        return memoryNumber;
    }


    /**
     * RETURN VALUE OF NAMED MEMORY
     *
     * @param memoryName
     * @return
     */
    public double getMemoryValue(String memoryName) {
        double value;
        if (memoryName.equals(memory1Name)) {
            value = memory1;
        } else if(memoryName.equals(memory2Name)) {
            value = memory2;
        } else {
            value = 0;
        }
        return value;
    }

    public boolean memoryExists(String memoryName) {
        return (memoryName.equals(memory1Name)  || memoryName.equals(memory2Name));
    }

    public String[] getMemoryNames() {
        String[] arr;
        for(int i = 0; i < 2;i++) {
            arr = new String[]{memory1Name, memory2Name};
            return arr;
        }
        arr = new String[]{memory1Name, memory2Name};
        return arr;

    }

    public double simpleExpression(String expression) {
        String input = expression;
        int i = 0;
        int len = expression.length();
        int parentheses;
        int lastExpressionIndex;

return 0;

    }


    //WORKING

    public double sExpression(String expression) {
        double result = 0;
        System.out.println(expression);
        if (expression.charAt(0) == '+') {
            System.out.println("yes");
            String firstOperand = expression.substring(2, expression.indexOf(')'));
            System.out.println(firstOperand);
            String secondOperand = expression.substring(expression.lastIndexOf('(') + 1, expression.length() - 1);
            System.out.println(secondOperand);

            result = Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
        }
        return result;

    }



    public double calculate(String expression) {

        String input = expression;





//        int i = 0;
//        if(expression.charAt(i) == '(') {
//            calculate(expression.substring(i+1));
//        }
//
//        for (int i = 0; i<expression.length();i++) {
//            if (expression.charAt(i+1) != '+') {
//                String sub = expression.substring(i, expression.lastIndexOf(i, ')'));
//                double d = Double.parseDouble(sub);
//                double[] d = d.split("\\),\\(|\\)|\\(");
//                String result = arr[0] + arr[1];
//                expression = expression + expression.replace(sub, result);
//            }

        // "+(3)(/(9)(3))" = 6


       return 0;

    }

//    public double simpleExpression () {
//
//    }

//    public double complexExpression() {
//
//    }

}
