package cca.com;

import java.util.Stack;

public class Calculate
{
	private Basic calc = new Basic();
	private String resultBuffer;
	private double resultNum = 0;
	private int size;
	private Object[] tokens;
	public Calculate(Stack <Object> e)
	{
		size = e.size();
		tokens = new String[256];
		for(int i = 0; i < size; i++) {
			tokens[i] = e.pop();
		}
		calculate();
	}
	
	private void calculate()
	{
		resultNum = 0;
		double a = 0;
		double b = 0;
		double c = 0;
		String e = null;
		int i = 0;
		int j = 0;
		int k = 0;
		int y = 0;
		String x = "";
		Boolean z = true;
		while(z == true){
			Boolean again = false;
			for(i = 0; i < size; i++) {
				if(isOperator(tokens[i]) & "^".equals(tokens[i])) {
					for(j = i; j > -1; j--) {
						if((tokens[j].getClass() == String)) {
							break;
						}
					}
					for(k = i; k < size; k++) {
						if(isNumeric(tokens[k])) {
							break;
						}
					}
					b = Double.parseDouble(tokens[k]);
					c = Double.parseDouble(tokens[j]);
					e = tokens[i];
					a = doIt(b, c, e);
					tokens[j] = tokens[k] = null;
					tokens[i] = "" + a;
					a = 0;
					y = 0;
					x = "";
					again = true;
					break;
				}
			}
			if(again){
				z = true;
			} else {
				z = false;
			}
		}
		b = c = 0;
		a = 0;
		e = null;
		z = true;
		while(z == true){
			Boolean again = false;
			for(i = 0; i < size; i++) {
				if(isOperator(tokens[i]) & ("/".equals(tokens[i]) | "*".equals(tokens[i]))) {
					for(j = i; j > -1; j--) {
						if(isNumeric(tokens[j])) {
							break;
						}
					}
					for(k = i; k < size; k++) {
						if(isNumeric(tokens[k])) {
							break;
						}
					}
					b = Double.parseDouble(tokens[k]);
					c = Double.parseDouble(tokens[j]);
					e = tokens[i];
					a = doIt(b, c, e);
					tokens[j] = tokens[k] = null;
					tokens[i] = "" + a;
					a = 0;
					y = 0;
					x = "";
					again = true;
					break;
				}
			}
			if(again){
				z = true;
			} else {
				z = false;
			}
		}
		b = c = 0;
		a = 0;
		e = null;
		z = true;
		while(z == true){
			Boolean again = false;
			for(i = 0; i < size; i++) {
				if(isOperator(tokens[i]) & ("-".equals(tokens[i]) | "+".equals(tokens[i]))) {
					for(j = i; j > -1; j--) {
						if(isNumeric(tokens[j])) {
							break;
						}
					}
					for(k = i; k < size; k++) {
						if(isNumeric(tokens[k])) {
							break;
						}
					}
					b = Double.parseDouble(tokens[j]);
					c = Double.parseDouble(tokens[k]);
					e = tokens[i];
					a = doIt(b, c, e);
					tokens[j] = tokens[k] = null;
					tokens[i] = "" + a;
					a = 0;
					y = 0;
					x = "";
					again = true;
					break;
				}
			}
			if(again){
				z = true;
			} else {
				z = false;
			}
		}
		for(String w : tokens) {
			if(isNumeric(w) || isOperator(w)) {
				x = w;
				y++;
			}
		}
		if(y == 1) {
			resultNum = Double.parseDouble(x);
		}
		resultBuffer = "" + resultNum;
	}
	
	private double doIt(double a, double b, String c)
	{
		double d = 0;
		if("*".equals(c)) {
			calc.mult(a, b);
			d = calc.getProd();
		} else if("-".equals(c)) {
			calc.sub(a, b);
			d = calc.getDiff();
		} else if("+".equals(c)) {
			calc.add(a, b);
			d = calc.getSum();
		} else if("^".equals(c)) {
			calc.pow(a, b);
			d = calc.getProd();
		} else if("/".equals(c)) {
			calc.div(a, b);
			d = calc.getProd();
		}
		return d;
	}
	
	String getResult()
	{
		return resultBuffer;
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
}
