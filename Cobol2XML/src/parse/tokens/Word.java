/*
 * @(#)Word.java	 1.0.0
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
public class Word extends Terminal {

/**
 * Returns true if an assembly's next element is a word.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if an assembly's next element is a word
 */
protected boolean qualifies(Object o) {
	Token t = (Token) o;
	return t.isWord();
}
/**
 * Create a set with one random word (with 3 to 7 
 * characters).
 */
public ArrayList<String> randomExpansion(int maxDepth, int depth) {
	int n = (int) (5.0 * Math.random()) + 3;
	
	char[] letters = new char[n];
	for (int i = 0; i < n; i++) {
		int c = (int) (26.0 * Math.random()) + 'a';
		letters[i] = (char) c;
	}
	
	ArrayList<String> v = new ArrayList<String>();
	v.add(new String(letters));
	return v;
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
	return "Word";
}
}