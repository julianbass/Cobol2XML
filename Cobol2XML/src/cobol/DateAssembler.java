/*
 * @(#)DateAssembler.java	 0.0.1
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
 
package cobol;

import parse.*;
import parse.tokens.*;
public class DateAssembler extends Assembler {
/**
 * Pop a string, and set the target SectionName to this
 * string.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	Cobol c = new Cobol();
	Token t = (Token) a.pop();
	String tokenString = t.sval().trim();
	
	// deconstruct token string into month and year components
	String monthString = "" + tokenString.charAt(0) + tokenString.charAt(1) + tokenString.charAt(2);
	c.setMonthDateWritten(monthString);
	
	String yearString = "" + tokenString.charAt(4) + tokenString.charAt(5) + tokenString.charAt(6) + tokenString.charAt(7);
	c.setYearDateWritten(Integer.parseInt(yearString));
	
	t = (Token) a.pop();
	c.setDayDateWritten( (int) t.nval() );
	a.setTarget(c);
}
}