package cca.com;

class SyntaxError extends Exception
{
	private static final long serialVersionUID = 1L;
	private final String errorMsg = "Syntax Error.";
	private String num1;
	private String num2;
	private String operator;
	SyntaxError(String a, String b, String c)
	{
		num1 = a;
		num2 = c;
		operator = b;
		if(isNumeric() && isOperator());
	}
	
	private Boolean isNumeric()
	{
		double a = 0;
		try {
			a = Double.parseDouble(num1);
			a = Double.parseDouble(num2);
			if(isOperator() != true) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if(a != 0);
		return true;
	}
	private Boolean isOperator()
	{
		if(operator == "/" | operator == "-" | operator == "*" | operator == "+" | operator == "^") {
			return true;
		}
		return false;
	}
	@Override
	public String toString()
	{
		return errorMsg;
	}
}
