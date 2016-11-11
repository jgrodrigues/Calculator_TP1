/**
 * Created by jonas on 29/10/2016.
 */


public class Calculator {

    /* Constants */
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
    public static final String NO_PARENTHESES = "noParentheses";
    public static final char OPEN_PARENTHESES = '(';
    public static final char CLOSED_PARENTHESES = ')';

    /* Instance Variables */
    private double lastResult;
    private Memory mem1;
    private Memory mem2;

    /* Constructors */

    /**
     * Create a calculator with no memories
     */
    public Calculator() {
        lastResult = 0;
        mem1 = null;
        mem2 = null;
    }

    /**
     * Create a calculator with 1 memory
     *
     * @param name the String corresponding to the mem1 name
     */
    public Calculator(String name) {
        lastResult = 0;
        mem1 = new Memory(name);
        mem2 = null;
    }

    /**
     * Create a calculator with 2 memories
     *
     * @param name1 the String corresponding to the mem1 name
     * @param name2 the String corresponding to the mem2 name
     */
    public Calculator(String name1, String name2) {
        lastResult = 0;
        if (name1.equals(name2)) {
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
     * @param expression the String that may have or not equal open and closed parentheses
     * @return true if the expression has an equal number of open and closed parentheses, or false if otherwise
     */
    public boolean hasEqualParentheses(String expression) {
        int parentheses = 0;
        int i = 0;
        do {
            if (expression.charAt(i) == OPEN_PARENTHESES) {
                parentheses += 1;
            } else if (expression.charAt(i) == CLOSED_PARENTHESES) {
                parentheses -= 1;
            }
            i++;
        } while (i < expression.length());
        return parentheses == 0;
    }

    /**
     * Checks if an expression as any open parentheses
     *
     * @param expression the String which might contain open parentheses
     * @return true if expressions has any open parentheses
     */
    public boolean hasOpenParentheses(String expression) {
        return expression.indexOf(OPEN_PARENTHESES) != -1;
    }

    /**
     * Gets Last index of an expression
     *
     * @param expression the String which we intend to get the last index of
     * @return the last index of the expression
     */
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

    /**
     * Count number of parentheses
     *
     * @param expression the String which we intend to know exactly how many open parentheses it has
     * @return an integer with the exact number of open parentheses the String expression has
     */
    public int parCount(String expression) {
        int i = 0, parentheses = 0;
        do {
            if (expression.charAt(i) == OPEN_PARENTHESES) {
                parentheses++;
            }
            i++;
        } while (i < expression.length());
        return parentheses;
    }

    public boolean isMemoryName(String expression) {
        boolean isMemory = false;
        if (mem1 != null || mem2 != null) {
            if (mem1 != null) {
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
        double number = Double.NaN;
        if (hasOpenParentheses(expression)) {
            operator = expression.substring(0, expression.indexOf(OPEN_PARENTHESES)).trim();
        }
        if (isBinaryOperator(operator) || isUnaryOperator(operator)) {
            number = calculateExpression(expression);
        } else if (Utilities.isDoubleValue(expression)) {
            number = Double.parseDouble(expression);
        } else if (hasMemories()) {
            number = getMemoryValue(expression);
        }
        return number;
    }

    /**
     * Calculate Literal Expression, it is either a memory or a double value since literal validation was a success
     *
     * @param expression the String which is a literal to be calculated
     * @return the value of the String calculated
     */
    public double calcLiteralExpression(String expression) {
        double value;
        if (Utilities.isDoubleValue(expression)) {
            value = Double.parseDouble(expression);
        } else {
            value = getExpressionValue(expression);
        }
        return value;
    }

    /**
     * Checks if an expression without parentheses if valid if it is either a memory name or a number
     *
     * @param expression the String which may be or not a memory name or a number
     * @return true if String expression is a literal(memory name or a number)
     */
    public boolean isValidNoParentheses(String expression) {
        boolean isValid = true;
        if (!isMemoryName(expression) && !Utilities.isDoubleValue(expression)) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if the binary operation parcels are valid and doesn't contain 1 single parentheses
     *
     * @param expression the String which has or not valid binary operation parcels
     * @return true if String expression contains valid binary operation parcels
     */
    public boolean isValidBinaryOperation(String expression) {
        boolean isValid = true;
        if (parCount(expression) == 1) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if a unary operation parcel is valid
     *
     * @param expression the String which may or not be a valid unary operation parcel
     * @return true if the expression is a valid unary operation parcel
     */
    public boolean isValidUnaryOperation(String expression) {
        boolean isValid = true;
        if (!Utilities.isDoubleValue(expression) &&
                Utilities.isDoubleValue(expression.replaceAll("\\s", ""))) { //firstParcel
            isValid = false;
        }
        return isValid;
    }

    /**
     * Checks if a literal is valid(if it is a memory name or a double)
     *
     * @param expression the String that may be or not a valid literal
     * @return true if expression is a valid literal
     */
    public boolean isValidLiteralOperation(String expression) {
        boolean isValid = true;
        if (!isMemoryName(expression) || !Utilities.isDoubleValue(expression)) {
            isValid = false;
        }
        return isValid;
    }


    /**
     * Checks if an expression if valid
     *
     * @param expression the String that may be a parcel, two parcels or a single literal
     * @param type       type of operation the expression belongs at
     * @return true if the expression is valid
     */
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
     * @param parcel   the double inside the unary parcel we want to calculate
     * @param operator the operator defining which unary operation to proceed
     * @return the result of the unary operation between the parcel and the operator
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
     * @param firstParcel  the double inside the first parcel of the binary operation
     * @param secondParcel the double inside the second parcel of the binary operation
     * @param operator     the operator used to calculate the result between the first and second parcel
     * @return the result of the binary operation
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
     * Check if is a binary operator
     *
     * @param operator the String which determines the type of operation
     * @return true if operator is a binary operator
     */
    public boolean isBinaryOperator(String operator) {
        return operator.equals(ADD) || operator.equals(MINUS) || operator.equals(DIVISION)
                || operator.equals(MODULE) || operator.equals(PRODUCT);
    }

    /**
     * Check if is a unary operator
     *
     * @param operator the String which determines the type of operation
     * @return true if operator is a unary operator
     */
    public boolean isUnaryOperator(String operator) {
        return operator.equals(SIN) || operator.equals(SEN) || operator.equals(COS) || operator.equals(EXP)
                || operator.equals(LOG) || operator.equals(ROUND) || operator.equals(CEIL) || operator.equals(FLOOR) || operator.equals(ABS);
    }

    /**
     * Calculate an Expression, calling several validation methods to avoid calculating an invalid expression
     *
     * @param expression the String that may contain or not a valid expression to be calculated
     * @return a double if the expression is valid, or NaN if the expression is invalid
     */
    public double calculateExpression(String expression) {
        Double expressionResult = Double.NaN; //Result if expression eventually doesn't succeed in validation(if isn't valid)
        String operator = "";
        String firstParcel;
        String secondParcel;
        String parcels = "";

        if (hasEqualParentheses(expression)) { //Only proceed if expression has the same number of opened and closed parentheses(validation)
            if (hasOpenParentheses(expression)) {
                if (hasEqualParentheses(expression)) {
                    operator = expression.substring(0, expression.indexOf(OPEN_PARENTHESES)).trim();
                    parcels = expression.substring(expression.indexOf(OPEN_PARENTHESES), expression.length()).trim();
                }
            }

            if (!hasOpenParentheses(expression)) {
                if (validateExpression(expression, NO_PARENTHESES)) { //Only proceed if expression passes validation of non parentheses expressions
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
                    if (validateExpression(parcels, BINARY_OPERATION)) { //Only proceed if expression passes binary operations validation
                        secondParcel = parcels.substring(lastIndexParcel, parcels.length() - 1).trim();
                        secondParcel = secondParcel.substring(1, secondParcel.length());
                        expressionResult = binaryExpression(getExpressionValue(firstParcel), getExpressionValue(secondParcel), operator);
                    }
                } else if (isUnaryOperator(operator)) {
                    if (validateExpression(firstParcel, UNARY_OPERATION)) { //Only proceed if expression passes unary operation validation
                        if (Utilities.isDoubleValue(firstParcel)) {
                            expressionResult = unaryExpression(getExpressionValue(firstParcel), operator);
                        } else {
                            secondParcel = Double.toString(getExpressionValue(firstParcel));
                            expressionResult = unaryExpression(getExpressionValue(secondParcel), operator);
                        }
                    }
                } else if (validateExpression(expression, LITERAL_OPERATION)) { //Only proceed if expression passes literal operation validation
                    expressionResult = calcLiteralExpression(expression);
                }
            }
        }
        return expressionResult;
    }

    /**
     * Sets the last value calculated only if the last value
     * calculated was valid(from a valid expression)
     *
     * @param expressionResult the double from the last expression result which may be a number, or a NaN(if expression was invalid)
     */
    public void setLastValue(Double expressionResult) {
        if (!Double.isNaN(expressionResult)) {
            lastResult = expressionResult;
        }
    }

    /**
     * Checks if there are any memories
     *
     * @return true if it has at least one memory object created
     */
    public boolean hasMemories() {
        return mem1 != null || mem2 != null;
    }

    /**
     * Get value of a certain memory
     *
     * @param memoryName the name of the corresponding memory which we intend to get the value
     * @return the value of the memory we want to know
     */
    public double getMemoryValue(String memoryName) {
        double value = Double.NaN;
        if (hasMemories()) {
            if (memoryName.equalsIgnoreCase(mem1.getMemoryName())) {
                value = mem1.getMemoryValue();
            } else if (memoryName.equalsIgnoreCase(mem2.getMemoryName())) {
                value = mem2.getMemoryValue();
            }
        }
        return value;
    }

    /**
     * @return true if mem1 object exists, or false otherwise
     */
    public boolean hasMemory1() {
        return mem1 != null;
    }

    /**
     * @return true if mem2 object exists, or false otherwise
     */
    public boolean hasMemory2() {
        return mem2 != null;
    }

    /**
     * @return the mem1 name if mem1 exists
     */
    public String getMemory1Name() {
        return mem1.getMemoryName();
    }

    /**
     * @return the mem2 name if mem2 exists
     */
    public String getMemory2Name() {
        return mem2.getMemoryName();
    }

    /**
     * @param value sets mem1 value
     */
    public void setMemory1Value(double value) {
        mem1.setValue(value);
    }

    /**
     * @param value sets mem2 value
     */
    public void setMemory2Value(double value) {
        mem2.setValue(value);
    }

    /**
     * Get Last Value calculated
     *
     * @return the double of the last result calculated from a valid expression
     */
    public double getLastResult() {
        return lastResult;
    }
}
