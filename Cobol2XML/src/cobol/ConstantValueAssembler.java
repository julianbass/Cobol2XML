package cobol;

import parse.*;
import parse.tokens.*;
public class ConstantValueAssembler extends Assembler{
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		c.setConstantValue(t.nval()); //nval returns numeric value for token <constant value>
//		System.out.println("Token string[4]: " + c.getConstantValue());
		
		t = (Token) a.pop(); //keyword "value" dont need any action here
		String tokenString = t.sval().trim();
//		System.out.println("Token string[3]: " + tokenString);
		
		t = (Token) a.pop(); // <constant name>
		c.setConstantName(t.sval());
//		System.out.println("Token string[2]: " + c.getConstantName());
		
		t = (Token) a.pop();
		c.setLineNumber((int) Math.round(t.nval())); // <line number>
//		System.out.println("Token string[1]: " + c.getLineNumber());
		
		a.setTarget(c);
	}
}
