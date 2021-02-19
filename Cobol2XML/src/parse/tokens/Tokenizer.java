/*
 * @(#)Tokenizer.java	 1.0.0
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
public class Tokenizer { 
	/*
	 * The reader to read characters from
	 */
	protected PushbackReader reader;

	/*
	 * The number of characters that might be in a symbol
	 */
	protected static final int DEFAULT_SYMBOL_MAX = 4;
	
	/*
	 * The state lookup table
	 */
	protected TokenizerState[] characterState = 
		new TokenizerState[256];
	/*
	 * The default states that actually consume text and 
	 * produce a token
	 */
	protected NumberState numberState = new NumberState();
	protected QuoteState quoteState = new QuoteState();
	protected SlashState slashState = new SlashState();
	protected SymbolState symbolState = new SymbolState();
	protected WhitespaceState whitespaceState = 
		new WhitespaceState();
	protected WordState wordState = new WordState();
/**
 * Constructs a tokenizer with a default state table (as 
 * described in the class comment). 
 *
 * @return   a tokenizer
 */
public Tokenizer() {

	setCharacterState(0, 255, symbolState()); // the default

	setCharacterState(   0,   ' ', whitespaceState());
	setCharacterState( 'a',   'z', wordState());
	setCharacterState( 'A',   'Z', wordState());
	setCharacterState(0xc0,  0xff, wordState());
	setCharacterState( '0',   '9', numberState());
	setCharacterState( '-',   '-', numberState());
	setCharacterState( '.',   '.', numberState());
	setCharacterState( '"',   '"', quoteState());
	setCharacterState( '\'', '\'', quoteState());
	setCharacterState( '/',   '/', slashState());
}
/**
 * Constructs a tokenizer to read from the supplied string.
 *
 * @param   String   the string to read from
 */
public Tokenizer(String s) {
	this();
	setString(s);
}
/**
 * Return the reader this tokenizer will read from.
 *
 * @return the reader this tokenizer will read from
 */
public PushbackReader getReader() {
	return reader;
}
/**
 * Return the next token.
 *
 * @return the next token.
 *
 * @exception   IOException   if there is any problem reading
 */
public Token nextToken() throws IOException { 
	int c = reader.read();
	
	/* There was a defect here, that resulted from the fact 
	 * that unreading a -1 results in the next read having a 
	 * value of (int)(char)-1, which is 65535. This may be
	 * a defect in PushbackReader. */
	 
	if (c >= 0 && c < characterState.length) {
		return characterState[c].nextToken(reader, c, this);
	}
	return Token.EOF;
}
/**
 * Return the state this tokenizer uses to build numbers.
 *
 * @return  the state this tokenizer uses to build numbers
 */
public NumberState numberState() {
	return numberState;
}
/**
 * Return the state this tokenizer uses to build quoted 
 * strings.
 *
 * @return  the state this tokenizer uses to build quoted 
 *          strings
 */
public QuoteState quoteState() {
	return quoteState;
}
/**
 * Change the state the tokenizer will enter upon reading 
 * any character between "from" and "to".
 *
 * @param   from   the "from" character
 *
 * @param   to   the "to" character
 *
 * @param   TokenizerState   the state to enter upon reading a
 *                           character between "from" and "to"
 */
public void setCharacterState(
	int from, int to, TokenizerState state) {
	
	for (int i = from; i <= to; i++) {
		if (i >= 0 && i < characterState.length) {
			characterState[i] = state;
		}
	}
}
/**
 * Set the reader to read from.
 * 
 * @param   PushbackReader   the reader to read from
 */
public void setReader(PushbackReader r) {
	this.reader = r;
}
/**
 * Set the string to read from.
 * 
 * @param   String   the string to read from
 */
public void setString(String s) {
	setString(s, DEFAULT_SYMBOL_MAX);
}
/**
 * Set the string to read from.
 * 
 * @param   String   the string to read from
 *
 * @param   int   the maximum length of a symbol, which
 *                establishes the size of pushback buffer
 *                we need
 */
public void setString(String s, int symbolMax) {
	setReader(
		new PushbackReader(new StringReader(s), symbolMax));
}
/**
 * Return the state this tokenizer uses to recognize
 * (and ignore) comments.
 *
 * @return  the state this tokenizer uses to recognize
 *          (and ignore) comments
 *
 */
public SlashState slashState() {
	return slashState;
}
/**
 * Return the state this tokenizer uses to recognize 
 * symbols.
 *
 * @return  the state this tokenizer uses to recognize 
 *          symbols
 */
public SymbolState symbolState() {
	return symbolState;
}
/**
 * Return the state this tokenizer uses to recognize (and
 * ignore) whitespace.
 *
 * @return  the state this tokenizer uses to recognize
 *          whitespace
 */
public WhitespaceState whitespaceState() {
	return whitespaceState;
}
/**
 * Return the state this tokenizer uses to build words.
 *
 * @return  the state this tokenizer uses to build words
 */
public WordState wordState() {
	return wordState;
}
}