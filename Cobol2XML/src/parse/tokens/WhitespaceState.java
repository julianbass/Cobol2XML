/*
 * @(#)WhitespaceState.java	 1.0.0
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
public class WhitespaceState extends TokenizerState {
	protected boolean whitespaceChar[] = new boolean[256];
/**
 * Constructs a whitespace state with a default idea of what
 * characters are, in fact, whitespace.
 *
 * @return   a state for ignoring whitespace
 */
public WhitespaceState() {
	setWhitespaceChars(0, ' ', true);
}
/**
 * Ignore whitespace (such as blanks and tabs), and return 
 * the tokenizer's next token.
 *
 * @return the tokenizer's next token
 */
public Token nextToken(
	PushbackReader r, int aWhitespaceChar, Tokenizer t) 
	throws IOException {
		
	int c;
	do {
		c = r.read();
	} while (
		c >= 0 && 
		c < whitespaceChar.length && 
		whitespaceChar[c]);
	
	if (c >= 0) {
		r.unread(c);
	}
	return t.nextToken();
}
/**
 * Establish the given characters as whitespace to ignore.
 *
 * @param   first   char
 *
 * @param   second   char
 *
 * @param   boolean   true, if this state should ignore
 *                    characters in the given range
 */
public void setWhitespaceChars(int from, int to, boolean b) {
	for (int i = from; i <= to; i++) {
		if (i >= 0 && i < whitespaceChar.length) {
			whitespaceChar[i] = b;
		}
	}
}
}