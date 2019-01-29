/*
 * @(#)Cobol.java	 0.0.1
 *
 * Copyright (c) 2019 Julian M. Bass
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

package cobol;

import utensil.*;

public class Cobol implements PubliclyCloneable {
	protected String identificationDivision;
	protected String procedureDivision;
	protected String program_id;
	protected String sectionName;
	protected String dataDivision;

	
	/**
	 * Return a copy of this object.
	 *
	 * @return a copy of this object
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError();
		}
	}
	/**
	 * Compares two objects for equality, treating nulls carefullly,
	 * and relying on the first object's implementation of <code>
	 * equals()</code>.
	 *
	 * @param   o1   one object
	 *
	 * @param   o2   the other
	 *
	 * @return  <code>true</code> if the objects are equal and
	 *          <code>false</code> otherwise.
	 */
	public static boolean equal(Object o1, Object o2) {
		if (o1 == null || o2 == null) {
			return o1 == null && o2 == null;
		}
		return o1.equals(o2);
	}
	/**
	 * Compares this object against the specified object. The 
	 * result is <code>true</code> if and only if the argument is 
	 * not <code>null</code> and is a <code>Coffee</code> object 
	 * whose attributes all equal this object's attributes.
	 *
	 * @param   o   the object to compare with.
	 *
	 * @return  <code>true</code> if the objects are equal and
	 *          <code>false</code> otherwise.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Cobol)) {
			return false;
		}
		Cobol c = (Cobol) o;
		
		if (!equal(identificationDivision, c.identificationDivision)) {
			return false;
		}
		
		if (!equal(program_id, c.program_id)) {
			return false;
		}
		
		if (!equal(procedureDivision, c.procedureDivision)) {
			return false;
		}

		if (!equal(dataDivision, c.dataDivision)) {
			return false;
		}
		
		if (!equal(sectionName, c.sectionName)) {
			return false;
		}
		

		return true;
	}

	/**
	 * Return the identification division.
	 *
	 * @return the identification division.
	 */
	public String getIdentificationDivision() {
		return identificationDivision;
	}

	/**
	 * Return the name of this COBOL program.
	 *
	 * @return the name of this COBOL program
	 */
	public String getProgram_ID() {
		return program_id;
	}

	/**
	 * Return a procedure division for this COBOL program.
	 *
	 * @return a procedure division for this COBOL program
	 */
	public String getProcedureDivision() {
		return procedureDivision;
	}

	/**
	 * Return the data division.
	 *
	 * @return the data division.
	 */
	public String getDataDivision() {
		return dataDivision;
	}

	/**
	 * Return the section name.
	 *
	 * @return the section name
	 */
	public String getSectionName() {
		return sectionName;
	}



	/**
	 * Set the identification division.
	 *
	 * @param   String   the name
	 */
	public void setIdentificationDivision(String identificationDivisionString) {
		this.identificationDivision = identificationDivisionString;
	}

	/**
	 * Set the name of this cobol file.
	 *
	 * @param   String   the name
	 */
	public void setProgram_ID(String program_idString) {
		this.program_id = program_idString;
	}

	/**
	 * Set the identification division.
	 *
	 * @param   String   the name
	 */
	public void setDataDivision(String dataDivisionString) {
		this.dataDivision = dataDivisionString;
	}

	/**
	 * Set a former name of this coffee.
	 *
	 * @param   String   the name
	 */
	public void setProcedureDivision(String procedureDivision) {
		this.procedureDivision = procedureDivision;
	}


	/**
	 * Set the day date written for this program. 
	 *
	 * @param   int the day date
	 */

	/**
	 * Set the section name 
	 *
	 * @param  String sectionName
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * Return a textual description of this coffee type.
	 * 
	 * @return a textual description of this coffee type
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(identificationDivision);
		buf.append(", ");
		buf.append(program_id);
		
		/*if (formerName != null) {
			buf.append('(');
			buf.append(formerName);
			buf.append(')');
		}
		buf.append(", ");
		buf.append(roast);
		if (alsoOfferFrench) {
			buf.append("/French");
		}
		
		buf.append(", ");
		buf.append(country);
		buf.append(", ");
		buf.append(price); */
		
		return buf.toString();
	}
}
