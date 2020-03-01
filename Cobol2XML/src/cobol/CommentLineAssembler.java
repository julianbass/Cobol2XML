package cobol;

import parse.*;
import parse.tokens.*;

public class CommentLineAssembler extends Assembler{

	
	/**
	 * Pop a string and set the target DataDivision to this string.
	 * 
	 * @param  Assembly   the assembly to work on
	 */
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		if(t.sval() != null) {
			c.setCommentLine(t.sval().trim());
			a.setTarget(c);
		}
		
	}


}
