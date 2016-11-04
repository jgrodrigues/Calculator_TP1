import com.sun.org.apache.xpath.internal.operations.Mod;

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
    private static final char MINUS = '-';
    private static final char ADD = '+';
    private static final char PRODUCT = '*';
    private static final char DIVISION = '/';
    private static final char MODULE = '%';
    private static final String SIN = "SIN";
    private static final String COS = "COS";
    private static final String LOG = "LOG";
    private static final String EXP = "EXP";
    private static final String ROUND = "ROUND";
    private static final String CEIL = "CEIL";
    private static final String FLOOR = "FLOOR";
    public double lastResult = 0;

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

//    public int getMemoryNumber() {
//        return memoryNumber;
//    }


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


    //WORKING NOT FINISHED

    public double sExpression(String expression) {
        double result = 0;
//        System.out.println(expression);
        String firstParcel = null;
        String secondParcel = null;


        if (expression.indexOf('(') == 1) { //SIMPLE EXPRESSIONS

//            System.out.println("teste1");

            char operand = expression.charAt(0);

            firstParcel = expression.substring(2, expression.indexOf(')'));
//            System.out.println(firstParcel);
            secondParcel = expression.substring(expression.lastIndexOf('(') + 1, expression.length() - 1);
//            System.out.println(secondParcel);
            switch (operand) {
                case (ADD):
//                    System.out.println(ADD);
                    result = Double.parseDouble(firstParcel) + Double.parseDouble(secondParcel);
                    break;
                case (MINUS):
//                    System.out.println(MINUS);
                    result = Double.parseDouble(firstParcel) - Double.parseDouble(secondParcel);
                    break;
                case (PRODUCT):
//                    System.out.println(PRODUCT);
                    result = Double.parseDouble(firstParcel) * Double.parseDouble(secondParcel);
                    break;
                case (DIVISION):
//                    System.out.println(DIVISION);
                    result = Double.parseDouble(firstParcel) / Double.parseDouble(secondParcel);
                    break;
                case (MODULE):
//                    System.out.println(MODULE);
                    result = Double.parseDouble(firstParcel) % Double.parseDouble(secondParcel);
                    break;
            }
        }else { //COMPLEX EXPRESSIONS

//            System.out.println("teste2");

            String operand = expression.substring(0,3).toUpperCase();
//            System.out.println(operand);

            firstParcel = expression.substring(4, expression.indexOf(')'));
//            System.out.println(firstParcel);



            switch (operand) {
                case (SIN):
//                    System.out.println(SIN);
                    result = sin(Double.parseDouble(firstParcel));
                    break;
                case (COS):
//                    System.out.println(COS);
                    result = cos(Double.parseDouble(firstParcel));
                    break;
                case (LOG):
//                    System.out.println(LOG);
                    result = Math.log(Double.parseDouble(firstParcel));
                    break;
                case (EXP):
//                    System.out.println(EXP);
                    result = Math.exp(Double.parseDouble(firstParcel));
                    break;
                case (ROUND):
//                    System.out.println(ROUND);
                    result = Math.round(Double.parseDouble(firstParcel));
                    break;
                //POR ACABAR
            }
        }
        lastResult = result;
        return result;

    }



    public double calculate(String expression) {

        String input = expression;




        // "+(3)(/(9)(3))" = 6


       return 0;

    }


    public double recursion(String expression) {
        if (Utilities.isDoubleValue(expression)) {
            return Double.parseDouble(expression);
        }
//        if (expression.charAt(0) ==)
        return 0;
    }

    public void avm(String memoryName) {
        if (memoryName.equals(memory1Name)){
            memory1 = lastResult;
        } else if(memoryName.equals(memory2Name)){
            memory2 = lastResult;
        }
    }


    public double add (double value1, double value2) {
        return value1 + value2;
    }

    public double minus (double value1, double value2) {
        return value1 - value2;
    }

    public double product (double value1, double value2) {
        return value1 * value2;
    }

    public double division (double value1, double value2) {
        return value1 / value2;
    }

    public double module (double value1, double value2) {
        return value1 % value2;
    }

    public double log (double value) {
        return Math.log(value);
    }

    public double exp (double value) {
        return Math.exp(value);
    }

    public double round(double value) {
        return Math.round(value);
    }

    public double ceil (double value) {
        return Math.ceil(value);
    }

    public double floor (double value) {
        return Math.floor(value);
    }

    public double cos (double value) {
        return Math.cos(value);
    }

    public double sin (double value) {
        return Math.sin(value);
    }




//    public double complexExpression() {
//
//    }

}
