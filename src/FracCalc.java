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
    	//First Operand:
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
    	//Operator:
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
    	//Second Operand:
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
    	//Return Second Operand:
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
    	String whole = op2Whole(operand2);
    	String numerator = op2Numerator(operand2);
    	String denominator = op2Denominator(operand2);
    	String strAnswer = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator;
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        
        return strAnswer;
    }
    
    public static String op2Whole(String str) {
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
    public static String op2Numerator(String str) {
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
    public static String op2Denominator(String str) {
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
