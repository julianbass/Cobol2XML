/*
 * @(#)SymbolRootNode.java	 1.0.0
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
public class SymbolRootNode extends SymbolNode {
	protected SymbolNode[] children = new SymbolNode[256];
/**
 * Create and initialize a root node.
 */
public SymbolRootNode() {
	super(null, (char) 0);
	init();
}
/**
 * Add the given string as a symbol.
 *
 * @param   String   the character sequence to add
 */
public void add(String s) {
	char c = s.charAt(0);
	SymbolNode n = ensureChildWithChar(c);
	n.addDescendantLine(s.substring(1));
	findDescendant(s).setValid(true);
}
/**
 * A root node has no parent and no character of its own, so 
 * its ancestry is "".
 *
 * @return an empty string
 */
public String ancestry() {
	return "";
}
/*
 * A root node maintains its children in an array instead of
 * a Vector, to be faster.
 */
protected SymbolNode findChildWithChar(char c) {
	return children[c];
}
/*
 * Set all possible symbols to be valid children. This means
 * that the decision of which characters are valid one-
 * character symbols lies outside this tree. If a tokenizer
 * asks this tree to produce a symbol, this tree assumes that
 * the first available character is a valid symbol.
 */
protected void init() {
	int len = children.length;
	for (char i = 0; i < len; i++) {
		children[i] = new SymbolNode(this, i);
		children[i].setValid(true);
	}
}
/**
 * Return a symbol string from a reader.
 *
 * @param   PushbackReader   a reader to read from
 *
 * @param   int   the first character of this symbol, already
 *                read from the reader
 *
 * @return a symbol string from a reader
 */
public String nextSymbol(PushbackReader r, int first) 
	throws IOException {
		
	SymbolNode n1 = findChildWithChar((char) first);
	SymbolNode n2 = n1.deepestRead(r);
	SymbolNode n3 = n2.unreadToValid(r);
	return n3.ancestry();
}
}