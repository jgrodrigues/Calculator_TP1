/**
 * Created by jonas on 29/10/2016.
 */


/***********REMOVER PRINTS CLASSE CALCULADORA****************/
/***Constantes***/
/****precondicoes @pre*******/

public class Calculator {

    /**
     * Instance Variables
     */

    //Error messages
    public static final String NON_EXISTENT_MEMORY = "Memoria nao existente.";
    public static final String NO_MEMORY_MESSAGE = "Calculadora sem memorias.";

    //Operators
    public static final String MINUS = "-";
    public static final String ADD = "+";
    public static final String PRODUCT = "*";
    public static final String DIVISION = "/";
    public static final String MODULE = "%";
    public static final String SIN = "SIN";
    public static final String SEN = "SEN";
    public static final String COS = "COS";
    public static final String LOG = "LOG";
    public static final String EXP = "EXP";
    public static final String ROUND = "ROUND";
    public static final String CEIL = "CEIL";
    public static final String FLOOR = "FLOOR";
    public static final String ABS = "ABS";

    public static final String UNARY_OPERATION = "unary";
    public static final String BINARY_OPERATION = "binary";
    public static final String LITERAL_OPERATION = "literal";
    //public static final String UNARY_OPERATION = "unary";
    public static final String NO_PARENTHESES = "noParentheses";

    //Others

    public static final char OPEN_PARENTHESES = '(';
    public static final char CLOSED_PARENTHESES = ')';

    private double lastResult;
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
            if (expression.charAt(i) == OPEN_PARENTHESES) {
                parentheses += 1;
            } else if(expression.charAt(i) == CLOSED_PARENTHESES) {
                parentheses -= 1;
            }
            i++;
        }while(i<expression.length());
        return parentheses == 0;
    }

    public boolean hasOpenParentheses(String expression) {
        return expression.indexOf(OPEN_PARENTHESES) != -1;
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
            operator = expression.substring(0, expression.indexOf(OPEN_PARENTHESES)).trim();
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
            if (expression.charAt(i) == OPEN_PARENTHESES) {
                parentheses++;
            } else if (expression.charAt(i) == CLOSED_PARENTHESES) {
                parentheses--;
            }
            i++;
        } while (parentheses != 0);
        return i;
    }


    public int openParCount(String expression) {
        int i = 0, parentheses = 0;
        do {
            if (expression.charAt(i) == OPEN_PARENTHESES) {
                parentheses++;
            }
            i++;
        } while (i<expression.length());
        return parentheses;
    }

    /**
     * CE - Calculate Expression
     *
     * @param expression
     * @return
     */
    public double calculateExpression(String expression) {
        Double expressionResult = Double.NaN;
        String operator = "";
        String firstParcel;
        String secondParcel;
        String parcels = "";

        if (hasEqualParentheses(expression)) {
            if (hasOpenParentheses(expression)) {
                if (hasEqualParentheses(expression)) {
                    operator = expression.substring(0, expression.indexOf(OPEN_PARENTHESES)).trim();
                    parcels = expression.substring(expression.indexOf(OPEN_PARENTHESES), expression.length()).trim();
                }
            }

            if (!hasOpenParentheses(expression)) {
                if (validateExpression(expression, NO_PARENTHESES)) {
                    if (isMemoryName(expression)) {
                        expressionResult = getExpressionValue(expression);
                    } else if (Utilities.isDoubleValue(expression)) {
                        expressionResult = Double.parseDouble(expression);
                    }
                }
            } else {
                int lastIndexParcel = getLastIndexExpression(parcels);
                firstParcel = parcels.substring(1, lastIndexParcel - 1);
                if (isBinaryOperator(operator)) {
                    if (validateExpression(parcels, BINARY_OPERATION)) {
                        secondParcel = parcels.substring(lastIndexParcel, parcels.length() - 1).trim();
                        secondParcel = secondParcel.substring(1, secondParcel.length());
                        expressionResult = binaryExpression(getExpressionValue(firstParcel), getExpressionValue(secondParcel), operator);
                    }
                } else if (isUnaryOperator(operator)) {
                    if (validateExpression(firstParcel, UNARY_OPERATION)) {
                        if (Utilities.isDoubleValue(firstParcel)) {
                            expressionResult = unaryExpression(getExpressionValue(firstParcel), operator);
                        } else {
                            secondParcel = Double.toString(getExpressionValue(firstParcel));
                            expressionResult = unaryExpression(getExpressionValue(secondParcel), operator);
                        }
                    }
                } else if (validateExpression(expression, LITERAL_OPERATION)) {
                    expressionResult = literalExpression(expression);
                }
            }
        }

        return expressionResult;
    }

    public void setLastValue(Double expressionResult) {
        if (!Double.isNaN(expressionResult)) {
            lastResult = expressionResult;
        }
    }

    /**
     * Calculate Literal Expression
     *
     * @param expression
     * @return
     */
    public double literalExpression(String expression) {
        double value;
        if (isMemoryName(expression)) {
            value =  getExpressionValue(expression);
        } else if (Utilities.isDoubleValue(expression)) {
            value = Double.parseDouble(expression);
        } else {
            value = Double.NaN;
        }
        return value;
    }

    public boolean isValidNoParentheses(String expression) {
        boolean isValid = true;
        if (!isMemoryName(expression) && !Utilities.isDoubleValue(expression)) {
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidBinaryOperation(String expression) {
        boolean isValid = true;
        if (openParCount(expression) == 1) { //parcels
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidUnaryOperation(String expression) {
        boolean isValid = true;
        if (!Utilities.isDoubleValue(expression) &&
                Utilities.isDoubleValue(expression.replaceAll("\\s", ""))){ //firstParcel
            isValid = false;
        }
        return isValid;
    }

    public boolean isValidLiteralOperation(String expression) {
        boolean isValid = true;
        if (!isMemoryName(expression) || !Utilities.isDoubleValue(expression)) {
            isValid = false;
        }
        return isValid;
    }


    public boolean validateExpression(String expression, String type) {
        boolean isValid = true;

        switch (type) {
            case (NO_PARENTHESES):
                isValid = isValidNoParentheses(expression);
                break;
            case (UNARY_OPERATION):
                isValid = isValidUnaryOperation(expression);
                break;
            case (BINARY_OPERATION):
                isValid = isValidBinaryOperation(expression);
                break;
            case (LITERAL_OPERATION):
                isValid = isValidLiteralOperation(expression);
                break;
        }

        return isValid;
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
            case (SEN):
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

    public boolean hasMemories() {
        return mem1 != null || mem2 != null;
    }

    /**
     * VM - Get memory value
     *
     * @param memoryName
     * @return
     */
    public double getMemoryValue(String memoryName) {
        double value = Double.NaN;
        if (hasMemories()) {
            if (mem1 != null && memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
                value = mem1.getMemoryValue();
            } else if (mem2 != null && memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
                value = mem2.getMemoryValue();
            }
        }
        return value;
    }

//    /**
//     * AVM - Assign last value to memory
//     *
//     * @param memoryName
//     */
//    public void assignLastValue(String memoryName) {
//        double lastResult = getLastResult();
//        if (isMemoryName(memoryName)) {
//            if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
//                    mem1.setValue(lastResult);
//            } else if (memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
//                    mem2.setValue(lastResult);
//            }
//        } else {
//            System.out.println(NON_EXISTENT_MEMORY);
//        }
//    }

    /**
     * Get memory value - REMOVE
     *
     * @param memoryName
     */
//    public void getValue(String memoryName) {
//        double memoryValue = getMemoryValue(memoryName);
//        if (!(Double.isNaN(memoryValue))) {
//            System.out.printf("%s: ", memoryName);
//            System.out.printf("%.2f\n", memoryValue);
//        } else {
//            System.out.println(NON_EXISTENT_MEMORY);
//        }
//    }

    /**
     * LM - Gets memories names and values;
     */
//    public void getMemoriesInfo() {
//        if (hasMemories()) {
//            if (mem1 != null) {
//                System.out.printf("%s: ", mem1.getMemoryName());
//                System.out.printf("%.2f\n", mem1.getMemoryValue());
//            }
//            if (mem2 != null) {
//                System.out.printf("%s: ", mem2.getMemoryName());
//                System.out.printf("%.2f\n", mem2.getMemoryValue());
//            }
//        }
//    }

    public boolean hasMemory1() {
        return mem1 != null;
    }

    public boolean hasMemory2() {
        return mem2 != null;
    }

    public String getMemory1Name() {
        return mem1.getMemoryName();
    }

    public String getMemory2Name() {
        return mem2.getMemoryName();
    }

    public void setMemory1Value(double value) {
        mem1.setValue(value);
    }

    public void setMemory2Value(double value) {
        mem2.setValue(value);
    }


    /**
     * Check if is a binary operator
     *
     * @param operator
     * @return
     */
    public boolean isBinaryOperator(String operator) {
        return operator.equals(ADD) || operator.equals(MINUS) || operator.equals(DIVISION)
                || operator.equals(MODULE) || operator.equals(PRODUCT);
    }

    /**
     * Check if is a unary operator
     *
     * @param operator
     * @return
     */
    public boolean isUnaryOperator(String operator) {
        return operator.equals(SIN) || operator.equals(SEN) || operator.equals(COS) || operator.equals(EXP)
                || operator.equals(LOG) || operator.equals(ROUND) || operator.equals(CEIL) || operator.equals(FLOOR) || operator.equals(ABS);
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
