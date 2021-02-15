/*
 * @(#)QuotedString.java	 1.0.0
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
public class QuotedString extends Terminal {
/**
 * Returns true if an assembly's next element is a quoted 
 * string.
 *
 * @param   object   an element from a assembly
 *
 * @return   true, if a assembly's next element is a quoted 
 *           string, like "chubby cherubim".
 */
protected boolean qualifies(Object o) {
	Token t = (Token) o;
	return t.isQuotedString();
}
/**
 * Create a set with one random quoted string (with 2 to
 * 6 characters).
 */
public ArrayList<String> randomExpansion(int maxDepth, int depth) {
	int n = (int) (5.0 * Math.random());
	
	char[] letters = new char[n + 2];
	letters[0] = '"';
	letters[n + 1] = '"';
	
	for (int i = 0; i < n; i++) {
		int c = (int) (26.0 * Math.random()) + 'a';
		letters[i + 1] = (char) c;
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
	return "QuotedString";
}
}