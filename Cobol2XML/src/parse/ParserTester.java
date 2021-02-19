/*
 * @(#)ParserTester.java	 1.0.0
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

public abstract class ParserTester {
	// use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected Parser p;
	protected boolean logTestStrings = true;
/**
 * Constructs a tester for the given parser.
 */
protected ParserTester(Parser p) {
	this.p = p;
}
/*
 * Subclasses must override this, to produce an assembly
 * from the given (random) string.
 */
protected abstract Assembly assembly(String s);

/*
 * Generate a random language element, and return true if
 * the parser cannot unambiguously parse it.
 */
protected boolean canGenerateProblem(int depth) {
	String s = p.randomInput(depth, separator());
	logTestString(s);
	Assembly a = assembly(s);
	a.setTarget(freshTarget());
	ArrayList<Assembly> in = new ArrayList<Assembly>();
	in.add(a);
	ArrayList<?> out = completeMatches(p.match(in));
	if (out.size() != 1) {
		logProblemFound(s, out.size());
		return true;
	}
	return false;
}
/**
 * Return a subset of the supplied vector of assemblies,
 * filtering for assemblies that have been completely
 * matched.
 *
 * @param   Vector   a collection of partially or completely
 *                   matched assemblies
 *
 * @return   a collection of completely matched assemblies
 */
public static ArrayList<Assembly> completeMatches(ArrayList<?> in) {
	ArrayList<Assembly> out = new ArrayList<Assembly>();
	// Creating object of type Enumeration<Parser> 
	// Enumeration<?> e = in.elements()
    Enumeration<?> e = Collections.enumeration(in);
	while (e.hasMoreElements()) {
		Assembly a = (Assembly) e.nextElement();
		if (!a.hasMoreElements()) {
			out.add(a);
		}
	}
	return out;
}
/*
 * Give subclasses a chance to provide fresh target at
 * the beginning of a parse.
 */
protected utensil.PubliclyCloneable freshTarget() {
	return null;
}
/*
 * This method is broken out to allow subclasses to create
 * less verbose tester, or to direct logging to somewhere
 * other than System.out.
 */
protected void logDepthChange(int depth) {
	//System.out.println("Testing depth " + depth + "...")
	LOGGER.info("Testing depth " + depth + "...");
}
/*
 * This method is broken out to allow subclasses to create
 * less verbose tester, or to direct logging to somewhere
 * other than System.out.
 */
protected void logPassed() {
	//System.out.println("No problems found.")
	LOGGER.info("No problems found.");
}
/*
 * This method is broken out to allow subclasses to create
 * less verbose tester, or to direct logging to somewhere
 * other than System.out.
 */
protected void logProblemFound(String s, int matchSize) {
	// System.out.println("Problem found for string:")
	// System.out.println(s)
	LOGGER.info("Problem found for string:");
	LOGGER.info(s);
	if (matchSize == 0) {
		//System.out.println(
		//	"Parser cannot match this apparently " +
		//	"valid string.")
		LOGGER.info("Parser cannot match this apparently " +
				"valid string.");
	} else {
		//System.out.println(
		//	"The parser found " + matchSize + 
		//	" ways to parse this string.")
		LOGGER.info("The parser found " + matchSize +
				" ways to parse this string.");
	}
}
/*
 * This method is broken out to allow subclasses to create
 * less verbose tester, or to direct logging to somewhere
 * other than System.out.
 */
protected void logTestString(String s) {
	if (logTestStrings) {
		//System.out.println("    Testing string " + s)
		LOGGER.info("    Testing string " + s);
	}
}
/*
 * By default, place a blank between randomly generated
 * "words" of a language.
 */
protected String separator() {
	return " ";
}
/**
 * Set the boolean which determines if this class displays
 * every test string.
 *
 * @param   boolean   true, if the user wants to see
 *                    every test string
 */
public void setLogTestStrings(boolean logTestStrings) {
	this.logTestStrings = logTestStrings;
}
/**
 * Create a series of random language elements, and test
 * that the parser can unambiguously parse each one.
 */
public void test() {
	for (int depth = 2; depth < 8; depth++) {
		logDepthChange(depth);
		for (int k = 0; k < 100; k++) {
			if (canGenerateProblem(depth)) {
				return;
			}
		}
	}
	logPassed();
}
}