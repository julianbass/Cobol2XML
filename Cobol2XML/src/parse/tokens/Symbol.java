/*
 * @(#)Symbol.java	 1.0.0
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

public class Symbol extends Terminal {
/**
 * the literal to match
 */
	protected Token symbol; 

/**
 * Constructs a symbol that will match the specified char.
 *
 * @param   char   the character to match. The char must be 
 *                 one that the tokenizer will return as a 
 *                 symbol token. This typically includes most 
 *                 characters except letters and digits. 
 *
 * @return   a symbol that will match the specified char
 */
public Symbol(char c) {
	this(String.valueOf(c));
}
/**
 * Constructs a symbol that will match the specified sequence
 * of characters.
 *
 * @param   String   the characters to match. The characters
 *                   must be a sequence that the tokenizer will 
 *                   return as a symbol token, such as
 *                   <code><=</code>.
 *
 * @return   a Symbol that will match the specified sequence
 *           of characters
 */
public Symbol(String s) {
	symbol = new Token(Token.TT_SYMBOL, s, 0);
}
/**
 * Returns true if the symbol this object represents equals an
 * assembly's next element.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if the specified symbol equals the next 
 *           token from an assembly
 */
protected boolean qualifies(Object o) {
	return symbol.equals((Token) o);
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
	return symbol.toString();
}
}