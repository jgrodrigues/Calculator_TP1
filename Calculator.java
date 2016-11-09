/**
 * Created by jonas on 29/10/2016.
 */
public class Calculator {

    /**
     * Instance Variables
     */

    //Error messages
    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";

    //Operators
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
    private static final String ABS = "ABS";

    //Others
    private double lastResult;
    private static final char openParentheses = '(';
    private static final char closedParentheses = ')';

    //Memories
    private Memory mem1;
    private Memory mem2;

    /**
     * Constructor
     */
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

    /**
     * Check if input has equal number of open and closed parentheses
     *
     * @param expression
     * @return
     */
    public boolean hasEqualParentheses(String expression){
        int parentheses = 0;
        int i = 0;
        do{
            if (expression.charAt(i) == openParentheses) {
                parentheses += 1;
            } else if(expression.charAt(i) == closedParentheses) {
                parentheses -= 1;
            }

            i++;
        }while(i<expression.length());

        return parentheses == 0;
    }

    public boolean hasOpenParentheses(String expression) {
        return expression.indexOf(openParentheses) != -1;
    }

    public boolean isMemoryName(String expression) {
        boolean isMemory = false;
        if (mem1 != null || mem2 != null) {
            if (mem1 != null ) {
                if (expression.equalsIgnoreCase(mem1.getMemoryName())) {
                    isMemory = true;
                }
            }
            if (mem2 != null) {
                if (expression.equalsIgnoreCase(mem2.getMemoryName())) {
                    isMemory = true;
                }
            }
        }
        return isMemory;
    }

    /**
     * Calculate expression value
     *
     * @param expression
     * @return
     */
    public double getExpressionValue(String expression) {
        String operator = "";
        double number;
        if (hasOpenParentheses(expression)) {
            operator = expression.substring(0, expression.indexOf(openParentheses)).trim();
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

    public int getLastIndexExpression(String expression) {
        int i = 0, parentheses = 0;
        do {
            if (expression.charAt(i) == openParentheses) {
                parentheses++;
            } else if (expression.charAt(i) == closedParentheses) {
                parentheses--;
            }
            i++;
        } while (parentheses != 0);
        return i;
    }

    /**
     * CE - Calculate Expression
     *
     * @param expression
     * @return
     */
    public double calculateExpression(String expression) {
        //System.out.println("Expression -> " + expression);
        String operator = "";
        String firstParcel;
        String secondParcel;
        String parcels = "";

        if (hasOpenParentheses(expression)) {
            operator = expression.substring(0, expression.indexOf(openParentheses)).trim();
            parcels = expression.substring(expression.indexOf(openParentheses), expression.length()).trim();
        }
        if(!hasOpenParentheses(expression)) {
//            System.out.println("Expression -> " + expression);
            if (isMemoryName(expression)){
//                System.out.println("1");
                lastResult = getExpressionValue(expression);
            } else if (Utilities.isDoubleValue(expression)) {
//                System.out.println("2");
                lastResult = Double.parseDouble(expression);
            } else {
                lastResult = Double.NaN;
            }
        } else {
            int lastIndexParcel = getLastIndexExpression(parcels);
            firstParcel = parcels.substring(1, lastIndexParcel - 1);
            if (isBinaryOperator(operator)) {
                secondParcel = parcels.substring(lastIndexParcel, parcels.length() - 1).trim();
                secondParcel = secondParcel.substring(1, secondParcel.length());
                lastResult = binaryExpression(getExpressionValue(firstParcel), getExpressionValue(secondParcel), operator);
            } else if (isUnaryOperator(operator)) {
                if (hasOpenParentheses(parcels)) {
                }
                if (Utilities.isDoubleValue(firstParcel)) {
                    lastResult = unaryExpression(getExpressionValue(firstParcel), operator);
                } else {
                    secondParcel = Double.toString(getExpressionValue(firstParcel));
                    String operator2 = operator;
                    lastResult = unaryExpression(getExpressionValue(secondParcel), operator2);
                }
            } else {
                System.out.println(operator);
                lastResult = literalExpression(expression);
            }
        }
        return lastResult;
    }



    /**
     * Calculate Literal Expression
     *
     * @param expression
     * @return
     */
    public double literalExpression(String expression) {
        System.out.println(expression);
        double value;
        if (isMemoryName(expression)) {
            value =  getExpressionValue(expression);
        } else {
            value = Double.parseDouble(expression);
        }
        return value;
    }

    /**
     * Calculate Unary Expression
     *
     * @param parcel
     * @param operator
     * @return
     */
    public double unaryExpression(double parcel, String operator) {
        double result = Double.NaN;
        switch (operator) {
            case ("sen"):
                result = Math.sin(parcel);
                break;
            case (SIN):
                result = Math.sin(parcel);
                break;
            case (COS):
                result = Math.cos(parcel);
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
                result = Math.floor(parcel);
                break;
            case (ABS):
                result = Math.abs(parcel);
                break;
        }
        return result;
    }

    /**
     * Calculate binary expression
     *
     * @param firstParcel
     * @param secondParcel
     * @param operator
     * @return
     */
    public double binaryExpression(double firstParcel, double secondParcel, String operator) {
        double result = Double.NaN;
        switch (operator) {
            case (ADD):
                result = firstParcel + secondParcel;
                break;
            case (MINUS):
                result = firstParcel - secondParcel;
                break;
            case (PRODUCT):
                result = firstParcel * secondParcel;
                break;
            case (DIVISION):
                result = firstParcel / secondParcel;
                break;
        }
        return result;
    }

    /**
     * VM - Get memory value
     *
     * @param memoryName
     * @return
     */
    public double getMemoryValue (String memoryName) {
        double value;
        if (mem1 != null && memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
            value = mem1.getMemoryValue();
        } else if (mem2 != null && memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
            value = mem2.getMemoryValue();
        } else {
            value = Double.NaN;
        }
        return value;
    }

    /**
     * AVM - Assign last value to memory
     *
     * @param memoryName
     */
    public void assignLastValue(String memoryName) {
        double lastResult = getLastResult();
        if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
            mem1.setValue(lastResult);
        } else if(memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
            mem2.setValue(lastResult);
        }
    }


    /**
     * Get memory value - REMOVE
     *
     * @param memoryName
     */
    public void getValue(String memoryName) {
        double memoryValue = getMemoryValue(memoryName);
        if (!(Double.isNaN(memoryValue))) {
            System.out.printf("%s: ", memoryName);
            System.out.printf("%.2f\n", memoryValue);
        } else {
            System.out.println(NON_EXISTENT_MEMORY);
        }
    }

    /**
     * LM - Gets memories names and values;
     */
    public void getMemoriesInfo() {

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

    /**
     * Check if is a binary operator
     *
     * @param operator
     * @return
     */
    public boolean isBinaryOperator(String operator) {
        return operator.equals(ADD) || operator.equals(MINUS) || operator.equals(DIVISION) || operator.equals(MODULE) || operator.equals(PRODUCT);
    }

    /**
     * Check if is a unary operator
     *
     * @param operator
     * @return
     */
    public boolean isUnaryOperator(String operator) {
        return operator.equals(SIN) || operator.equals(COS) || operator.equals(EXP) || operator.equals(LOG) ||
                operator.equals(ROUND) || operator.equals(CEIL) || operator.equals(FLOOR) || operator.equals(ABS);
    }

    /**
     * Get Last Value calculated
     *
     * @return
     */
    public double getLastResult() {
        return lastResult;
    }
}
