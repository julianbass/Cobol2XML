/*
 * @(#)QuoteState.java	 1.0.0
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
public class QuoteState extends TokenizerState {
	protected char charbuf[] = new char[16];
/*
 * Fatten up charbuf as necessary.
 */
protected void checkBufLength(int i) {
	if (i >= charbuf.length) {
		char nb[] = new char[charbuf.length * 2];
		System.arraycopy(charbuf, 0, nb, 0, charbuf.length);
		charbuf = nb;
	}
}
/**
 * Return a quoted string token from a reader. This method 
 * will collect characters until it sees a match to the
 * character that the tokenizer used to switch to this 
 * state.
 *
 * @return a quoted string token from a reader
 */
public Token nextToken(
	PushbackReader r, int cin, Tokenizer t)
	throws IOException {
		
	int i = 0;
	charbuf[i++] = (char) cin;
	int c;
	do {
		c = r.read();
		if (c < 0) {
			c = cin;
		}
		checkBufLength(i);
		charbuf[i++] = (char) c;
	} while (c != cin);
	 
	String sval = String.copyValueOf(charbuf, 0, i);
	return new Token(Token.TT_QUOTED, sval, 0);
}
}