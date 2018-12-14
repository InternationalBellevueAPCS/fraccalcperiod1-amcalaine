import java.util.*;
public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
    	Scanner console = new Scanner(System.in);
    	console.useDelimiter("\n");
        // Checkpoint 2: Accept user input multiple times.
        System.out.println("Please input a calculation: (type quit to exit)");
        String userInput = console.nextLine();
        while(!(userInput.equals("quit"))) {
        	System.out.println(produceAnswer(userInput));
        	System.out.println("Please input a calculation: (type quit to exit)");
            userInput = console.nextLine();
        }	
        		
    	
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param userInput - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String userInput)
    { 
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
    	//Separate First Operand:
    	String operand1 = ""; 
    	int counter = 0;
    	for (int i = 0; i < userInput.length(); i++) {
    		String s = "";
    		s += userInput.charAt(i);
    		if (s.equals(" ")) {
    			counter += 1;
    			break;
    		}
    		else {
    			operand1 += s;
    			counter += 1;
    		}
    	}
    	//Separate Operator:
    	String operator = "";
    	for (int i = counter; i < userInput.length(); i++) {
    		String s = "";
    		s += userInput.charAt(i);
    		if (s.equals(" ")) {
    			counter += 1;
    			break;
    		}
    		else {
    			operator += s;
        		counter += 1;
    		}
    	}
    	//Separate Second Operand:
    	String operand2 = "";
    	for (int i = counter; i < userInput.length(); i++) {
    		String s = "";
    		s += userInput.charAt(i);
    		if (s.equals(" ")) {
    			counter += 1;
    			break;
    		}
    		else {
    			operand2 += s;
        		counter += 1;
    		}
    	}
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    	//OBTAIN EACH PART OF OPERAND 2
    	String whole2 = opWhole(operand2);
    	String numerator2 = opNumerator(operand2);
    	String denominator2 = opDenominator(operand2);
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
    	//PARSE EACH PART OF OPERAND 2
    	int intFormWhole2 = Integer.parseInt(whole2);
    	int intFormNumerator2 = Integer.parseInt(numerator2);
    	int intFormDenominator2 = Integer.parseInt(denominator2);
    	if (intFormWhole2 < 0) {
    		intFormNumerator2 *= -1;
    	}
    	
    	//OBTAIN EACH PART OF OPERAND 1
    	String whole1 = opWhole(operand1);
    	String numerator1 = opNumerator(operand1);
    	String denominator1 = opDenominator(operand1);
    	//PARSE EACH PART OF OPERAND 1
    	int intFormWhole1 = Integer.parseInt(whole1);
    	int intFormNumerator1 = Integer.parseInt(numerator1);
    	int intFormDenominator1 = Integer.parseInt(denominator1);
    	if (intFormWhole1 < 0) {
    		intFormNumerator1 *= -1;
    	}
    	//CALCULATION
    	String answer = "";
    	//IF ADDITION:
    	if (operator.equals("+")) {
    		int whole = intFormWhole1 + intFormWhole2;
    		if (intFormDenominator1 != intFormDenominator2) {
    			//need temporary variable
    			int temp = intFormDenominator1;
    			intFormDenominator1 *= intFormDenominator2;
    			intFormNumerator1 *= intFormDenominator2;
    			intFormDenominator2 *= temp;
    			intFormNumerator2 *= temp;
    		}
    		int numerator = 0;
    		numerator = intFormNumerator1 + intFormNumerator2;
    		int denominator = intFormDenominator1;
    		if (denominator < 0) {
    			denominator *= -1;
    			numerator *= -1;
    		}
    		int divisor = greatestCommonDivisor(numerator, denominator);
    		numerator /= divisor;
    		denominator /= divisor;
    		if (numerator < 0) {
    			numerator = Math.abs(numerator);
    			int newWhole = numerator / denominator;
    			newWhole *= -1;
    			whole += newWhole;
    			numerator %= denominator;
    			if (whole == 0) {
    				return Integer.toString(0);
    			}
    			answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			return answer;
    		}
    		else if (whole == 0 && (numerator / denominator == 0)) {
    			return (Integer.toString(numerator) + "/" + Integer.toString(denominator));
    		}
    		
    		else if (numerator == 0) {
    			answer += (Integer.toString(whole));
    			return answer;
    		}
    		else {
    			whole = whole + (numerator / denominator);
    			numerator = numerator % denominator;
    			if (numerator == 0) {
    				answer = Integer.toString(whole);
    			}
    			else {
    				answer = (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			}
    			return answer;
    		}	
    	}
    	//IF SUBTRACTION
    	else if (operator.equals("-")) {
    		int whole = intFormWhole1 - intFormWhole2;
    		if (intFormDenominator1 != intFormDenominator2) {
    			//need temporary variable
    			int temp = intFormDenominator1;
    			intFormDenominator1 *= intFormDenominator2;
    			intFormNumerator1 *= intFormDenominator2;
    			intFormDenominator2 *= temp;
    			intFormNumerator2 *= temp;
    		}
    		int numerator = intFormNumerator1 - intFormNumerator2;
    		if (whole < 0) {
    			if (numerator < 0) {
    				numerator *= -1;
    			}
    		}
    		int denominator = intFormDenominator1;
    		int divisor = greatestCommonDivisor(numerator, denominator);
    		numerator /= divisor;
    		denominator /= divisor;
    		if (numerator < 0 && denominator < 0) {
    			numerator = Math.abs(numerator);
    			int newWhole = numerator / denominator;
    			newWhole *= -1;
    			whole += newWhole;
    		}
    		if (whole < 0 && (numerator > denominator)) {
    			whole = whole += (-1*(numerator /denominator));
    			numerator = numerator % denominator;
    			answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			return answer;
    		}
    		else if (numerator > denominator) {
    			whole = whole + (numerator / denominator);
    			numerator = numerator % denominator;
    			answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			return answer;
    		}
    		else if (numerator == 0) {
    			answer += (Integer.toString(whole));
    			return answer;
    		}
    		else if (whole == 0 || numerator < 0) {
    			return (Integer.toString(numerator) + "/" + Integer.toString(denominator));
    		}
    		else {
    			answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			return answer;
    		}
    	}
    	//IF MULTIPLICATION
    	else if (operator.equals("*")) {
    		int improper1 = intFormWhole1 * intFormDenominator1 + intFormNumerator1;
    		int improper2 = intFormWhole2 * intFormDenominator2+ intFormNumerator2;
    		int numerator = improper1 * improper2;
    		int denominator = intFormDenominator1 * intFormDenominator2;
    		int divisor = greatestCommonDivisor(numerator, denominator);
    		numerator /= divisor;
    		denominator /= divisor;
    		int whole = 0;
    		if ((numerator / denominator > 0) && (numerator % denominator == 0)) {
    			return Integer.toString(whole + (numerator / denominator));
    		}
    		if (numerator == 0 && whole == 0) {
    			return Integer.toString(0);
    		}
    		else {
    			whole = whole + (numerator / denominator);
    			if (whole == 0) {
    				return (Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			}
    			numerator = numerator % denominator;
    			if (numerator < 0) {
    				numerator *= -1;
    			}
    			if (numerator == 0) {
    				answer += Integer.toString(whole);
    			}
    			else {
    				answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			}
    			return answer;
    		}
    	}
    	//IF DIVISION
    	else {
    		int improper1 = intFormWhole1 * intFormDenominator1 + intFormNumerator1;
    		int improper2 = intFormWhole2 * intFormDenominator2 + intFormNumerator2;
    		int numerator = improper1 * intFormDenominator2;
    		int denominator = intFormDenominator1 * improper2;
    		int divisor = greatestCommonDivisor(numerator, denominator);
    		numerator /= divisor;
    		denominator /= divisor;
    		int whole = numerator / denominator;
    		numerator = numerator % denominator;
    		if (numerator < 0 && denominator < 0) {
    			numerator *= -1;
    			denominator *= -1;
    		}
    		if ((numerator / denominator ) > 1) {
    			whole = whole + (numerator / denominator);
    			numerator = numerator % denominator;
    		}
    		numerator = Math.abs(numerator);
    		denominator = Math.abs(denominator);
    		if (numerator == 0) {
    			answer += (Integer.toString(whole));
    		}
    		else {
    			if (whole == 0) {
    				answer += Integer.toString(numerator) + "/" + Integer.toString(denominator);
    			}
    			else {
    				answer += (Integer.toString(whole) + "_" + Integer.toString(numerator) + "/" + Integer.toString(denominator));
    			}
    		}
    	return answer;
    	}
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	
    }   
    //RETRIEVE WHOLE NUMBER FROM INPUT
    public static String opWhole(String str) {
    	//mixed number
		if (str.contains("_")) {
			return str.substring(0, str.indexOf('_'));
		}
		//fraction
		else if (str.contains("/")) {
			return "0";
		}
		//whole number
		else {
			return str;
		}
    }
    //RETRIEVE NUMERATOR FROM INPUT
    public static String opNumerator(String str) {
    	//mixed number
    	if (str.contains("_")) {
    		return str.substring(str.indexOf('_')+1, str.indexOf('/'));
    	}
    	//fraction
    	else if (str.contains("/")) {
    		return str.substring(0, str.indexOf('/'));
    	}
    	//whole number
    	else {
    		return "0";
    	}
    }
    //RETRIEVE DENOMINATOR FROM INPUT
    public static String opDenominator(String str) {
    	//if slash
    	if (str.contains("/")) {
    		return str.substring(str.indexOf("/")+1);
    	}
    	//no slash
    	else {
    		return "1";
    	}
    }
    // TODO: Fill in the space below with helper methods
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
