package numbers;

import java.util.Stack;
import java.util.StringTokenizer;

public class EquationParser {
	private String[] tokens;
	private double result;
	public EquationParser(String equation)
	{
		StringTokenizer strTkn = new StringTokenizer(equation, "*+-/^()", true);
		String[] tokens = new String[strTkn.countTokens()];
		int total = 0;
		for(int i = 0; i < tokens.length; i++) {
			tokens[i] = strTkn.nextToken().trim();
			if(tokens[i].length() > 0) total++;
		}
		this.tokens = new String[total];
		int i = 0;
		for(String str : tokens) {
			if(str.length() > 0) {
				this.tokens[i] = str;
				i++;
			}
		}
		calculate();
		printResult();
	}
	private void calculate()
	{
		Stack<String> equationStack = new Stack<String>();
		int n = 0;
		boolean adding = true;
		boolean lastWasSign = false;
		for(int i = 0; i < tokens.length; i++) {
			if(tokens[i] == "-") {
				adding = false;
				lastWasSign = true;
			} else if(tokens[i] == "+") {
				adding = true;
				lastWasSign = true;
			} if(isNumber(tokens[i])) {
				n += getNumber(tokens[i]);
			}
		}
	}
	private int getNumber(String string) {
		try {
			int in = Integer.parseInt(string);
			return in;
		} catch(NumberFormatException nfe) {
			return 0;
		}
	}
	private boolean isNumber(String string) {
		try {
			int in = Integer.parseInt(string);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
	private void printResult()
	{
		System.out.println(result);
	}
	public static void main(final String[] args)
	{
		new EquationParser("1 ^ 2 - ( 3 +4)*5/6");
	}
}
