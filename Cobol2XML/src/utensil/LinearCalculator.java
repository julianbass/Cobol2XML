/*
 * @(#)LinearCalculator.java	 1.0.0
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
 
package utensil;
public class LinearCalculator {
	double xFrom;
	double xTo;
	double yFrom;
	double yTo;
/**
 * Create a LinearCalculator from known points on two scales.
 *
 * @param double xFrom
 * @param double xTo
 * @param double yFrom
 * @param double yTo
 */
public LinearCalculator(double xFrom, double xTo, double yFrom, double yTo) {
	this.xFrom = xFrom;
	this.xTo = xTo;
	this.yFrom = yFrom;
	this.yTo = yTo;
}
/**
 * Return the value on the first scale, corresponding to the given
 * value on the second scale.
 *
 * @return the value on the first scale, corresponding to the given
 *         value on the second scale
 */
public double calculateXforGivenY(double y) {
	if (yTo == yFrom) {
		return (xFrom + xTo) / 2;
	}
	return (y - yFrom) / (yTo - yFrom) * (xTo - xFrom) + xFrom;
}
/**
 * Return the value on the second scale, corresponding to the given
 * value on the first scale.
 *
 * @return the value on the second scale, corresponding to the given
 *         value on the first scale
 */
public double calculateYforGivenX(double x) {
	if (xTo == xFrom) {
		return (yFrom + yTo) / 2;
	}
	return (x - xFrom) / (xTo - xFrom) * (yTo - yFrom) + yFrom;
}
/**
 * Show the example in the class comment.
 * 
 * @param  args  ignored.
 */
public static void main(String args[]) {
	LinearCalculator lc = new LinearCalculator(32, 212, 0, 100);
	System.out.println(lc.calculateYforGivenX(68));
}
/**
 * Return a textual description of this object.
 * 
 * @return a textual description of this object
 */
public String toString() {
	return   "" + xFrom + ":" + xTo +
	       "::" + yFrom + ":" + yTo;
}
}