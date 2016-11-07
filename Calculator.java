/**
 * Created by jonas on 29/10/2016.
 */
public class Calculator {

    //Variaveis

    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";

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
    private double lastResult;

    private Memory mem1;
    private Memory mem2;


    public Calculator() {
        lastResult = 0;

        mem1 = null;
        mem2 = null;
    }

    public Calculator(String name) {
        lastResult = 0;
        mem1 = new Memory(name);
    }

    public Calculator(String name1, String name2) {
        lastResult = 0;

        if(name1.equals(name2)) {
            mem1 = new Memory(name1);
        } else {
            mem1 = new Memory(name1);
            mem2 = new Memory(name2);
        }
    }


    public double ce(String expression) {
        System.out.println(expression);

        double result;
        String firstParcel;
        String secondParcel;


        if (expression.indexOf('(') == 1) { //SIMPLE EXPRESSIONS


            char operand = expression.charAt(0);

//            firstParcel = expression.substring(2, expression.indexOf(')'));
//            secondParcel = expression.substring(expression.lastIndexOf('(') + 1, expression.length() - 1);

            int secondParentheses = expression.indexOf('(', expression.indexOf(')'));
            int length = expression.length();


            firstParcel = expression.substring(2, expression.indexOf(')'));
            System.out.println(firstParcel);
            secondParcel = expression.substring(secondParentheses + 1, length - 1);
            System.out.println(secondParcel);


            result = binaryExpression(firstParcel, secondParcel, operand);

        }else { //COMPLEX EXPRESSIONS


            String operand = expression.substring(0,3).toUpperCase();

            firstParcel = expression.substring(4, expression.indexOf(')'));

            result = unaryExpression(firstParcel, operand);

        }
        lastResult = result;
        return result;

    }


    public double unaryExpression(String parcel, String operand) {
        double result = Double.NaN;
        switch (operand) {
            case (SIN):
                result = sin(Double.parseDouble(parcel));
                break;
            case (COS):
                result = cos(Double.parseDouble(parcel));
                break;
            case (LOG):
                result = Math.log(Double.parseDouble(parcel));
                break;
            case (EXP):
                result = Math.exp(Double.parseDouble(parcel));
                break;
            case (ROUND):
                result = Math.round(Double.parseDouble(parcel));
                break;
            case (CEIL):
                result = Math.ceil(Double.parseDouble(parcel));
                break;
            case (FLOOR):
                result = Math.floor(Double.parseDouble(parcel));
        }
        return result;
    }

    public double binaryExpression(String firstParcel, String secondParcel, char operand) {
        double result = Double.NaN;
        switch (operand) {
            case (ADD):
                result = Double.parseDouble(firstParcel) + Double.parseDouble(secondParcel);
                break;
            case (MINUS):
                result = Double.parseDouble(firstParcel) - Double.parseDouble(secondParcel);
                break;
            case (PRODUCT):
                result = Double.parseDouble(firstParcel) * Double.parseDouble(secondParcel);
                break;
            case (DIVISION):
                result = Double.parseDouble(firstParcel) / Double.parseDouble(secondParcel);
                break;
            case (MODULE):
                result = Double.parseDouble(firstParcel) % Double.parseDouble(secondParcel);
                break;
        }
        return result;
    }


    public double getMemoryValue (String memoryName) {
        double value = 0;
        if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
            value = mem1.getMemoryValue();
        } else if (memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
            value = mem2.getMemoryValue();
        } else {
            value = Double.NaN;
        }
        return value;
    }

    public void avm(String memoryName) {
        double lastResult = getLastResult();
        if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
            mem1.setValue(lastResult);
            System.out.println(memoryName + ": " + lastResult);
        } else if(memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
            mem2.setValue(lastResult);
            System.out.println(memoryName + ": " + lastResult);
        }
    }


    public void vm(String memoryName) {
        double memoryValue = getMemoryValue(memoryName);
        if (!(Double.isNaN(memoryValue))) {
            System.out.println(memoryValue);

        } else {
            System.out.println(NON_EXISTENT_MEMORY);
        }
    }

    //FIX EMPTY VALUE
    public void lm() {
//        boolean mem1NotEmpty = !mem1.getMemoryName().isEmpty();
//        boolean mem2NotEmpty = !mem2.getMemoryName().isEmpty();

        boolean mem1NotEmpty = !mem1.getMemoryName().equals(null);
        boolean mem2NotEmpty = !mem2.getMemoryName().equals(null);

        if (mem1NotEmpty || mem2NotEmpty) {
            if (mem1NotEmpty) {
                System.out.printf("%s: ", mem1.getMemoryName());
                System.out.printf("%.2f\n", mem1.getMemoryValue());
            }
            if (mem2NotEmpty) {
                System.out.printf("%s: ", mem2.getMemoryName());
                System.out.printf("%.2f\n", mem2.getMemoryValue());
            }
        } else {
            System.out.println(NO_MEMORY_MESSAGE);
        }
    }


    public double getLastResult() {
        return lastResult;
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
