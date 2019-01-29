/*
 * @(#)CaselessLiteral.java	 1.0.0
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
public class CaselessLiteral extends Literal {
/**
 * Constructs a literal that will match the specified string,
 * given mellowness about case.
 *
 * @param   string   the string to match as a token
 *
 * @return   a literal that will match the specified string,
 *           disregarding case
 */
public CaselessLiteral(String literal) {
	super(literal);
}
/**
 * Returns true if the literal this object equals an
 * assembly's next element, disregarding case.
 *
 * @param   object   an element from an assembly
 *
 * @return   true, if the specified literal equals the next 
 *           token from an assembly, disregarding case
 */
protected boolean qualifies(Object o) {
	return literal.equalsIgnoreCase((Token) o);
}
}