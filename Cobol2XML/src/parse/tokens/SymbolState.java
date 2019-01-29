/*
 * @(#)SymbolState.java	 1.0.0
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

import java.io.*;
public class SymbolState extends TokenizerState {
	SymbolRootNode symbols = new SymbolRootNode();
/**
 * Constructs a symbol state with a default idea of what 
 * multi-character symbols to accept (as described in the 
 * class comment).
 *
 * @return   a state for recognizing a symbol
 */
public SymbolState() {
	add("!=");
	add(":-");
	add("<=");
	add(">=");
}
/**
 * Add a multi-character symbol.
 *
 * @param   String   the symbol to add, such as "=:="
 */
public void add(String s) {
	symbols.add(s);
}
/**
 * Return a symbol token from a reader.
 *
 * @return a symbol token from a reader
 */
public Token nextToken(
	PushbackReader r, int first, Tokenizer t) 
	throws IOException {

	String s = symbols.nextSymbol(r, first); 
	return new Token(Token.TT_SYMBOL, s, 0);
}
}