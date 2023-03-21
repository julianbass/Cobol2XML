/*
 * @(#)SlashSlashState.java	 1.0.0
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
public class SlashSlashState extends TokenizerState {
/**
 * Ignore everything up to an end-of-line and return the 
 * tokenizer's next token.
 *
 * @return the tokenizer's next token
 */
public Token nextToken(
	PushbackReader r, int theSlash, Tokenizer t)
	throws IOException {
		
	int c;
	while ((c = r.read()) != '\n' && c != '\r' && c >= 0) {
		// Empty to consume newline or carriage returns
	}
	return t.nextToken();
}
}