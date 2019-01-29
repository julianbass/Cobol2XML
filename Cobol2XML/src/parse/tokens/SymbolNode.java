/*
 * @(#)SymbolNode.java	 1.0.0
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
import java.io.*;
public class SymbolNode {
	protected char myChar;
	protected Vector<SymbolNode> children = new Vector<SymbolNode>(); // of Node
	protected boolean valid = false;
	protected SymbolNode parent;
/**
 * Constructs a SymbolNode with the given parent, representing 
 * the given character.
 *
 * @param   SymbolNode   this node's parent
 *
 * @param   char   this node's character
 */
public SymbolNode(SymbolNode parent, char myChar) {
	this.parent = parent;
	this.myChar = myChar;
}
/*
 * Add a line of descendants that represent the characters
 * in the given string.
 */
protected void addDescendantLine(String s) {
	if (s.length() > 0) {
		char c = s.charAt(0);
		SymbolNode n = ensureChildWithChar(c);
		n.addDescendantLine(s.substring(1));
	}
}
/**
 * Show the symbol this node represents.
 *
 * @return the symbol this node represents
 */
public String ancestry() {
	return parent.ancestry() + myChar;
}
/*
 * Find the descendant that takes as many characters as
 * possible from the input.
 */
protected SymbolNode deepestRead(PushbackReader r)
	throws IOException {

	char c = (char) r.read();
	SymbolNode n = findChildWithChar(c);
	if (n == null) {
		r.unread(c);
		return this;
	}
	return n.deepestRead(r);
}
/*
 * Find or create a child for the given character. 
 */
protected SymbolNode ensureChildWithChar(char c) {
	SymbolNode n = findChildWithChar(c);
	if (n == null) {
		n = new SymbolNode(this, c);
		children.addElement(n);
	}
	return n;
}
/*
 * Find a child with the given character.
 */
protected SymbolNode findChildWithChar(char c) {
	Enumeration<SymbolNode> e = children.elements();
	while (e.hasMoreElements()) {
		SymbolNode n = (SymbolNode) e.nextElement();
		if (n.myChar == c) {
			return n;
		}
	}
	return null;
}
/*
 * Find a descendant which is down the path the given string
 * indicates. 
 */
protected SymbolNode findDescendant(String s) {
	char c = s.charAt(0);
	SymbolNode n = findChildWithChar(c);
	if (s.length() == 1) {
		return n;
	}
	return n.findDescendant(s.substring(1));
}
/*
 * Mark this node as valid, which means its ancestry is a
 * complete symbol, not just a prefix.
 */
protected void setValid(boolean b) {
	valid = b;
}
/**
 * Give a string representation of this node.
 *
 * @return a string representation of this node
 */
public String toString() {
	return "" + myChar + '(' + valid + ')';
}
/*
 * Unwind to a valid node; this node is "valid" if its
 * ancestry represents a complete symbol. If this node is
 * not valid, put back the character and ask the parent to
 * unwind. 
 */
protected SymbolNode unreadToValid(PushbackReader r)
	throws IOException {
		
	if (valid) {
		return this;
	}
	r.unread(myChar);
	return parent.unreadToValid(r);
}
}