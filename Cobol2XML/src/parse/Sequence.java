/*
 * @(#)Sequence.java	 1.0.0
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
 
package parse;

import java.util.*;
import java.util.logging.Logger;

public class Sequence extends CollectionParser {
	 private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
/**
 * Constructs a nameless sequence.
 */
public Sequence () {
}
/**
 * Constructs a sequence with the given name.
 *
 * @param    name    a name to be known by
 */
public Sequence(String name) {
	super(name);
}
/**
 * A convenient way to construct a CollectionParser with the
 * given parser.
 */
public Sequence(Parser p) {
	super(p);
}
/**
 * A convenient way to construct a CollectionParser with the
 * given parsers.
 */
public Sequence(Parser p1, Parser p2) {
	super(p1, p2);
}
/**
 * A convenient way to construct a CollectionParser with the
 * given parsers.
 */
public Sequence(Parser p1, Parser p2, Parser p3) {
	super(p1, p2, p3);
}
/**
 * A convenient way to construct a CollectionParser with the
 * given parsers.
 */
public Sequence(
	Parser p1, 
	Parser p2, 
	Parser p3, 
	Parser p4) {
	super(p1, p2, p3, p4);
}
/**
 * Accept a "visitor" and a collection of previously visited
 * parsers.
 *
 * @param   ParserVisitor   the visitor to accept
 *
 * @param   Vector   a collection of previously visited parsers
 */
public void accept(ParserVisitor pv, ArrayList<Assembly> visited) {
	pv.visitSequence(this, visited);
}
/**
 * Given a set of assemblies, this method matches this
 * sequence against all of them, and returns a new set 
 * of the assemblies that result from the matches.
 *
 * @return   a Vector of assemblies that result from 
 *           matching against a beginning set of assemblies
 *
 * @param   Vector   a vector of assemblies to match against
 *
 */
public ArrayList<Assembly> match(ArrayList<Assembly> in) {
	ArrayList<Assembly> out = in;
	// Creating object of type Enumeration<Parser> 
	// Enumeration<Parser> e = subparsers.elements()
    Enumeration<Parser> e = Collections.enumeration(subparsers); 

	while (e.hasMoreElements()) {
		Parser p = (Parser) e.nextElement();
		out = p.matchAndAssemble(out);
		if (out.isEmpty()) {
			return out;
		}
	}
	return out;
}
/*
 * Create a random expansion for each parser in this 
 * sequence and return a collection of all these expansions.
 */
protected ArrayList<Assembly> randomExpansion(int maxDepth, int depth) {
	LOGGER.info("random expansion");
	ArrayList<Assembly> v = new ArrayList<Assembly>();
	// Creating object of type Enumeration<Parser> 
	// Enumeration<Parser> e = subparsers.elements()
    Enumeration<Parser> e = Collections.enumeration(subparsers); 

	while (e.hasMoreElements()) {
		Parser p = (Parser) e.nextElement();
		ArrayList<Parser> w = (ArrayList<Parser>) p.randomExpansion(maxDepth, depth++);
		// Creating object of type Enumeration<Parser>
		// Enumeration<?> f = w.elements()
	    Enumeration<Parser> f = Collections.enumeration(w); 
		while (f.hasMoreElements()) {
			Object ass = f.nextElement();
			LOGGER.info("more elements" + f.hasMoreElements());
			LOGGER.info("next element" + ass);
			v.add((Assembly) ass);
		}
		
	}
	return v;
}
/*
 * Returns the string to show between the parsers this
 * parser is a sequence of. This is an empty string,
 * since convention indicates sequence quietly. For
 * example, note that in the regular expression 
 * <code>(a|b)c</code>, the lack of a delimiter between
 * the expression in parentheses and the 'c' indicates a 
 * sequence of these expressions.
 */
protected String toStringSeparator() {
	return " ";
}
}