package cca.com;

import java.util.Stack;

public class DataAccess
{
	public static void main(String[] args)
	{
		DataAccess alego = null;
		if(alego == null) {
			alego = new DataAccess();
		}
	}
	final String add = "+";
	final char addition = '+';
	final String cbt = ")";
	final char closeBracket = ')';
	final char divide = '/';
	final String dvd = "/";
	final String exp = "^";
	final char exponent = '^';
	final String mlt = "*";
	final char multiply = '*';
	final char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	final String obt = "(";
	final char openBracket = '(';
	String operation = "1/24-27+1";
	Stack<Object> operationStack = new Stack<Object>();
	String[] operationStrings;
	String[] operationTokens;
	Stack<Object> stack = new Stack<Object>();
	final String sub = "-";
	
	final char subtract = '-';
	public DataAccess()
	{
		tokenizeIt();
		for(String a : operationTokens) {
			System.out.print(a + " ");
		}
		bedmas();
	}
	private void bedmas()
	{
		int a = 0;
		for(String s : operationTokens) {
			if(s != "" && s != null) {
				a++;
			}
		}
		for(int b = 0; b < a; b++) {
			String c = operationTokens[b].toString();
			if(c != "" && c != null) {
				if(isNumeric(c) == false) {
					operationStack.push(c);
					if(cbt.equals(operationStack.peek())) {
						getCalculation();
					}
				} else {
					operationStack.push(Double.parseDouble(c));
				}
			}
		}
		finalCalculation();
	}
	
	private void finalCalculation()
	{
			Calculate calc = new Calculate(operationStack);
			String responseBuffer = calc.getResult();
			operationStack = new Stack<String>();
			System.out.print("= " + responseBuffer);
	}
	
	private void getCalculation()
	{
		for(Boolean continuous = true; continuous == true;) {
			stack.push(operationStack.pop());
			if(obt.equals(stack.peek())) {
				Calculate calc = new Calculate(stack);
				operationStack.push(calc.getResult());
				stack = new Stack<String>();
				continuous = false;
			}
		}
	}
	
	private Boolean isBracket(String a)
	{
		if("(".equals(a) | ")".equals(a)) {
			return true;
		}
		return false;
	}
	private Boolean isNumeric(String a) {
		double b = 0;
		try {
			b = Double.parseDouble(a);
		} catch (Exception e) {
			return false;
		}
		if(b != 0);
		return true;
	}
	private Boolean isOperator(String a)
	{
		if("/".equals(a) | "-".equals(a) | "*".equals(a) | "+".equals(a) | "^".equals(a)) {
			return true;
		}
		return false;
	}
	private void tokenizeIt()
	{
		String[] tempArray = new String[256];
		for(int c = 0; c < operation.length(); c++) {
			tempArray[c] = new String ();
			tempArray[c] = "";
		}
		int b = 0;
		for(int i = 0; i < operation.length(); i++) {
			char a = operation.charAt(i);
			if(isNumeric("" + a)) {
				tempArray[b] += a;
			} else {
				if(tempArray[b] != "") {
					b++;
				}
				if(isOperator("" + a)) {
					tempArray[b] += a;
					b++;
				} else if(isBracket("" + a)) {
					tempArray[b] += a;
					b++;
				}
			}
		}
		int d = 0;
		for(String s : tempArray) {
			if(isOperator(s) || isBracket(s) || isNumeric(s)) {
				d++;
			}
		}
		operationTokens = new String[d];
		d = 0;
		for(String s : tempArray) {
			if(isOperator(s) || isBracket(s) || isNumeric(s)) {
				operationTokens[d] = new String();
				operationTokens[d] = s;
				d++;
			}
		}
	}
}
