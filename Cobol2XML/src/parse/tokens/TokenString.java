/*
 * @(#)TokenString.java	 1.0.0
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
import java.util.*;

public class TokenString {
/**
 * the tokens in this tokenString
 */
	protected Token tokens[];

/**
 * Constructs a tokenString from the supplied tokens.
 *
 * @param   tokens   the tokens to use
 *
 * @return    a tokenString constructed from the supplied 
 *            tokens
 */
public TokenString(Token[] tokens) {
	this.tokens = tokens;
}
/**
 * Constructs a tokenString from the supplied string. 
 *
 * @param   string   the string to tokenize
 *
 * @return    a tokenString constructed from tokens read from 
 *            the supplied string
 */
public TokenString(String s) {
	this(new Tokenizer(s));
}
/**
 * Constructs a tokenString from the supplied reader and 
 * tokenizer. 
 * 
 * @param   Tokenizer   the tokenizer that will produces the 
 *                      tokens
 *
 * @return    a tokenString constructed from the tokenizer's 
 *            tokens
 */
public TokenString(Tokenizer t) {
	Vector<Token> v = new Vector<Token>();
	try {
		while (true) {
			Token tok = t.nextToken();
			if (tok.ttype() == Token.TT_EOF) {
				break;
			}
			v.addElement(tok);
		};
	} catch (IOException e) {
		throw new InternalError(
			"Problem tokenizing string: " + e);
	}
	tokens = new Token[v.size()];
	v.copyInto(tokens);
}
/**
 * Returns the number of tokens in this tokenString.
 *
 * @return   the number of tokens in this tokenString
 */
public int length() {
	return tokens.length;
}
/**
 * Returns the token at the specified index.
 *
 * @param    index   the index of the desired token
 * 
 * @return   token   the token at the specified index
 */
public Token tokenAt(int i) {
	return tokens[i];
}
/**
 * Returns a string representation of this tokenString. 
 *
 * @return   a string representation of this tokenString
 */
public String toString() {
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < tokens.length; i++) {
		if (i > 0) {
			buf.append(" ");
		}	
		buf.append(tokens[i]);
	}
	return buf.toString();
}
}