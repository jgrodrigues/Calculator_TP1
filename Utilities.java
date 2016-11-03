/**
 * This class is provided to be reused in the first project of 
 * Introduction to Programming.
 * You may reuse it freely, but may not alter it. As this class
 * is provided by the lecturing team, it will not be identified as 
 * plagiarism, thus not violating the Ethics Code.
 */

/**
 * @author Miguel Goulao
 * The class Utilities contains a utility method to be called
 * as class methods (just like what you do with the Math class, for example.
 * To use this in your project, simply import the file Utilities.java to your 
 * source files folder. The class is assumed to be put in the default package 
 * in your Eclipse project. When building a zip file with your submission, please
 * include this file, as well.
 */
public class Utilities {
	
	/**
	 * This operation checks whether a <code>String</code> can be safely 
	 * transformed to a value of type <code>double</code>. It returns 
	 * <code>true</code> if the <code>String</code> can be transformed 
	 * into <code>double</code>, or <code>false</code>, otherwise.
	 * 
	 * <pre><strong>Example:</strong></pre>
	 * Suppose you need to check if a <code>String</code> called 
	 * <code>expression</code> can be transformed into a double safely. 
	 * Here is a code snippet on how you could do it:
	 * 
	 * <pre>
	 * <code>
	 * double value;
	 * if (Utilities.isDoubleValue(expression)) {
	 *     value = Double.parseDouble(expression);
	 *     // use value (double) in whatever way you need
	 * } else {
	 *     // do whatever error treatment you need to in this situation.
	 * }
	 * </code>
	 * </pre>    
	 * @param expression the <code>String</code> which may contain, or not, a <code>double</code> value
	 * @return true if <code>expression</code> returns a <code>double</code>, or <code>false</code>, otherwise.  
	 */
	public static boolean isDoubleValue(String expression) {
		boolean result;
		try {
			Double.parseDouble(expression.trim());
			result = true;
		} catch (NumberFormatException e) {
			result = false;
		}
		
		return result;
	}
}
