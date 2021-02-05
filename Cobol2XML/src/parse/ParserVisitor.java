/*
 * @(#)ParserVisitor.java	 1.0.0
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
public abstract class ParserVisitor {
/**
 * Visit an alternation.
 *
 * @param   Alternation   the parser to visit
 *
 * @param   ArrayList   a collection of previously visited parsers
 *
 */
public abstract void visitAlternation(
	Alternation a, ArrayList<Assembly> visited);
/**
 * Visit an empty parser.
 *
 * @param   Empty   the parser to visit
 *
 * @param   ArrayList   a collection of previously visited parsers
 *
 */
public abstract void visitEmpty(Empty e, ArrayList<Assembly> visited);
/**
 * Visit a repetition.
 *
 * @param   Repetition   the parser to visit
 *
 * @param   ArrayList   a collection of previously visited parsers
 *
 */
public abstract void visitRepetition(
	Repetition r, ArrayList<Assembly> visited);
/**
 * Visit a sequence.
 *
 * @param   Sequence   the parser to visit
 *
 * @param   ArrayList   a collection of previously visited parsers
 *
 */
public abstract void visitSequence(Sequence s, ArrayList<Assembly> visited);
/**
 * Visit a terminal.
 *
 * @param   Terminal   the parser to visit
 *
 * @param   ArrayList   a collection of previously visited parsers
 *
 */
public abstract void visitTerminal(Terminal t, ArrayList<Assembly> visited);
}