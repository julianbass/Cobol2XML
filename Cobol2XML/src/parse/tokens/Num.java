/*
 * @(#)Num.java	 1.0.0
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

public class Num extends Terminal {

/**
 * Returns true if an assembly's next element is a number.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if an assembly's next element is a number as
 *           recognized the tokenizer
 */
protected boolean qualifies(Object o) {
	Token t = (Token) o;
	return t.isNumber();
}
/**
 * Create a set with one random number (between 0 and 
 * 100).
 */
public ArrayList<String> randomExpansion(int maxDepth, int depth) {
	double d = Math.floor(1000.0 * Math.random()) / 10;
	ArrayList<String> v = new ArrayList<String>();
	v.add(Double.toString(d));
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
	return "Num";
}
}