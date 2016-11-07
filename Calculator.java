/**
 * Created by jonas on 29/10/2016.
 */
public class Calculator {

    //Variaveis

    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";

    private static final String MINUS = "-";
    private static final String ADD = "+";
    private static final String PRODUCT = "*";
    private static final String DIVISION = "/";
    private static final String MODULE = "%";
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
        mem2 = null;
    }

    public Calculator(String name1, String name2) {
        lastResult = 0;

        if(name1.equals(name2)) {
            mem1 = new Memory(name1);
            mem2 = null;
        } else {
            mem1 = new Memory(name1);
            mem2 = new Memory(name2);
        }
    }

    public boolean isExpression(String expression){
        int parentheses = 0;
        int i = 0;
        do{
            if (expression.charAt(i) == '(') {
                parentheses += 1;
            } else if(expression.charAt(i) == ')') {
                parentheses -= 1;
            }

            i++;
        }while(i<expression.length());

        return parentheses == 0;
    }

    double getExpNumber(String expression) {
        String operator = "";
        double number;
        if (expression.indexOf('(') != -1) {
            operator = expression.substring(0, expression.indexOf('(')).trim();
        }

        if (isBinaryOperator(operator) || isUnaryOperator(operator)) {
            number = calculateExpression(expression);
        } else if (Utilities.isDoubleValue(expression)) {
            number = Double.parseDouble(expression);
        } else {
            number = getMemoryValue(expression);
        }

        return number;
    }

    public double calculateExpression(String expression) {

        String operator = "";
        String firstParcel;
        String secondParcel;
        String parcels = "";
        int i=0;
        int parentheses = 0;

        if (expression.indexOf('(') != -1) {
            operator = expression.substring(0, expression.indexOf('(')).trim();
            parcels = expression.substring(expression.indexOf('('), expression.length()).trim();
        }


        do {
            if(parcels.charAt(i) == '(') {
                parentheses++;
            } else if(parcels.charAt(i) == ')') {
                parentheses--;
            }

            i++;
        } while (parentheses != 0);

        firstParcel = parcels.substring(1, i - 1);

        if (isBinaryOperator(operator)) {
            secondParcel = parcels.substring(i, parcels.length() - 1).trim();
            secondParcel = secondParcel.substring(1, secondParcel.length());


            lastResult = binaryExpression(getExpNumber(firstParcel), getExpNumber(secondParcel), operator);

        } else if (isUnaryOperator(operator)) {
                if (parcels.indexOf('(') != -1) {
                    operator = parcels.substring(0, expression.indexOf('(')).trim();
                }

            lastResult = unaryExpression(getExpNumber(firstParcel), operator);

        } else {
                lastResult = literalExpression(expression);
        }

        return lastResult;

    }


    public boolean isBinaryOperator(String operator) {
        return operator.equals(ADD) || operator.equals(MINUS) || operator.equals(DIVISION) || operator.equals(MODULE) || operator.equals(PRODUCT);
    }

    public boolean isUnaryOperator(String operator) {
        return operator.equals(SIN) || operator.equals(COS) || operator.equals(EXP) || operator.equals(LOG) ||
                operator.equals(ROUND) || operator.equals(CEIL) || operator.equals(FLOOR);
    }

    /**
     * Solve Literal Expression
     *
     * @param expression
     * @return
     */
    public double literalExpression(String expression) {
        double value;
        if (expression.equalsIgnoreCase(mem1.getMemoryName())) {
            value =  mem1.getMemoryValue();
        } else if (expression.equalsIgnoreCase(mem2.getMemoryName())) {
            value = mem2.getMemoryValue();
        } else {
            value = Double.parseDouble(expression);
        }
        return value;
    }

    /**
     * Solve Unary Expression
     *
     * @param parcel
     * @param operand
     * @return
     */
    public double unaryExpression(double parcel, String operand) {
        double result = Double.NaN;
        switch (operand) {
            case (SIN):
                result = sin(parcel);
                break;
            case (COS):
                result = cos(parcel);
                break;
            case (LOG):
                result = Math.log(parcel);
                break;
            case (EXP):
                result = Math.exp(parcel);
                break;
            case (ROUND):
                result = Math.round(parcel);
                break;
            case (CEIL):
                result = Math.ceil(parcel);
                break;
            case (FLOOR):
                result = Math.floor(parcel); //Missing abs
        }
        return result;
    }

    public double binaryExpression(double first, double second, String operand) {
        double result = Double.NaN;
        switch (operand) {
            case (ADD):
                result = first + second;
                break;
            case (MINUS):
                result = first - second;
                break;
            case (PRODUCT):
                result = first * second;
                break;
            case (DIVISION):
                result = first / second;
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

    public void assignLastValue(String memoryName) {
        double lastResult = getLastResult();
        if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
            mem1.setValue(lastResult);
            System.out.printf("%s: %.2f\n", memoryName, lastResult);
        } else if(memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
            mem2.setValue(lastResult);
            System.out.printf("%s: %.2f\n", memoryName, lastResult);
        }
    }


    public void getValue(String memoryName) {
        double memoryValue = getMemoryValue(memoryName);
        if (!(Double.isNaN(memoryValue))) {
            System.out.printf("%.2f\n", memoryValue);
        } else {
            System.out.println(NON_EXISTENT_MEMORY);
        }
    }

    //FIX EMPTY VALUE
    public void getMemoriesInfo() {
//        boolean mem1NotEmpty = !mem1.getMemoryName().isEmpty();
//        boolean mem2NotEmpty = !mem2.getMemoryName().isEmpty();

        boolean mem1NotEmpty = !(mem1 == null);
        boolean mem2NotEmpty = !(mem2 == null);

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

}
