/*
 * @(#)WordState.java	 1.0.0
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
public class WordState extends TokenizerState {
	protected char charbuf[] = new char[16];
	protected boolean wordChar[] = new boolean[256];
/**
 * Constructs a word state with a default idea of what 
 * characters are admissible inside a word (as described in 
 * the class comment). 
 *
 * @return   a state for recognizing a word
 */
public WordState() {
	setWordChars('a', 'z', true);
	setWordChars('A', 'Z', true);
	setWordChars('0', '9', true);
	setWordChars('-', '-', true);
	setWordChars('_', '_', true);
	setWordChars('\'', '\'', true);
	setWordChars(0xc0, 0xff, true);
}
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
 * Return a word token from a reader.
 *
 * @return a word token from a reader
 */
public Token nextToken(PushbackReader r, int c, Tokenizer t) 
	throws IOException {
		
	int i = 0;
	do {
		checkBufLength(i);
		charbuf[i++] = (char) c;
		c = r.read();
	} while (wordChar(c));
	
	if (c >= 0) {
		r.unread(c);
	}
	String sval = String.copyValueOf(charbuf, 0, i);
	return new Token(Token.TT_WORD, sval, 0);
}
/**
 * Establish characters in the given range as valid 
 * characters for part of a word after the first character. 
 * Note that the tokenizer must determine which characters
 * are valid as the beginning character of a word.
 *
 * @param   from   char
 *
 * @param   to   char
 *
 * @param   boolean   true, if this state should allow
 *                    characters in the given range as part
 *                    of a word
 */
public void setWordChars(int from, int to, boolean b) {
	for (int i = from; i <= to; i++) {
		if (i >= 0 && i < wordChar.length) {
			wordChar[i] = b;
		}
	}
}
/*
 * Just a test of the wordChar array.
 */
protected boolean wordChar(int c) {
	if (c >= 0 && c < wordChar.length) {
		return wordChar[c];
	}
	return false;
}
}