/*
 * @(#)Literal.java	 1.0.0
 *
 * Copyright (c) 1999 Steven J. Metsker
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
 
package parse.tokens;

import java.util.*;
import parse.*;

public class Literal extends Terminal {
/**
 * the literal to match
 */
	protected Token literal; 

/**
 * Constructs a literal that will match the specified string.
 *
 * @param   string   the string to match as a token
 *
 * @return   a literal that will match the specified string
 */
public Literal(String s) {
	literal = new Token(s);
}
/**
 * Returns true if the literal this object equals an
 * assembly's next element.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if the specified literal equals the next 
 *           token from an assembly
 */
protected boolean qualifies(Object o) {
	//System.out.println("literally" + o.toString() + "is literally" + literal.sval + " " + literal.equalsIgnoreCase((Token) o));
	return literal.equals((Token) o);
}
/**
 * Returns a textual description of this parser.
 *
 * @param   vector   a list of parsers already printed in 
 *                   this description
 * 
 * @return   string   a textual description of this parser
 *
 * @see Parser#toString()
 */
public String unvisitedString(ArrayList<Parser> visited) {
	return literal.toString();
}
}