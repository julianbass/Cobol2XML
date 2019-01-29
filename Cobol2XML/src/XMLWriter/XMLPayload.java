/*
 * @(#)XMLPayload.java	 0.1.0
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
 */package XMLWriter;

import cobol.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLPayload {
	Document doc;
	Element rootElement;
	
	public XMLPayload() {
		try {
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = 
		            dbFactory.newDocumentBuilder();
		doc = dBuilder.newDocument();
		
		 // root element
        rootElement = doc.createElement("cobol");
        doc.appendChild(rootElement);
		
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
		
	}
	
	
	public void addElements(Cobol c) {
		
		String identificationDivision = c.getIdentificationDivision();
		if (identificationDivision != null) {
			this.addIdentificationDivisionElement( identificationDivision );
			// Add contents of identification division
			this.addProgram_IDElement( c.getProgram_ID() );
		} else {
			// Add contents of identification division
			this.addProgram_IDElement( c.getProgram_ID() );
		}
		
		
		String procedureDivision = c.getProcedureDivision();
		if (procedureDivision != null) {
			this.addProcedureDivisionElement( procedureDivision );
			// Add contents of procedure division
		} else {
			// Add contents of procedure division
		}
		
		String dataDivision = c.getDataDivision();
		if (dataDivision != null) {
			this.addDataDivisionElement( dataDivision );
			// Add contents of data division
		} else {
			// Add contents of data division
		}
		
		String sectionName = c.getSectionName();
		if (sectionName != null) {
			this.addSectionElement( sectionName );
			//System.out.println("Got Section");
			// Add contents of procedure division
		} else {
			//System.out.println("Section Name null");
		}
	}
	
	

 	void addIdentificationDivisionElement(String stringElement) {
		//  ID element
		
		if(stringElement != null) {
			Element idDivision = doc.createElement("identification-division");
			idDivision.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(idDivision);
		}
	}

 	void addProgram_IDElement(String stringElement) {
		//  Program ID element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("Program-ID");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
 	
 	void addSectionElement(String stringElement) {
		//  Section element
		
		if(stringElement != null) {
			Element cobolname = doc.createElement("section");
			cobolname.appendChild(doc.createTextNode(stringElement));
			rootElement.appendChild(cobolname);
		}
	}
	
	
	void addProcedureDivisionElement(String stringElement) {
		//  Procedure division element
		
		if(stringElement != null) {
			Element procedureDivision = doc.createElement("procedure-division");
			procedureDivision.appendChild(doc.createTextNode(stringElement));
			// Add content of procedure division
			rootElement.appendChild(procedureDivision);
		}
	}
	
	void addDataDivisionElement(String stringElement) {
		//  Data Division element
		
		if(stringElement != null) {
			Element dataDivision = doc.createElement("data-division");
			dataDivision.appendChild(doc.createTextNode(stringElement));
			// Add content of data division
			rootElement.appendChild(dataDivision);
		}
	}
	
	public void writeFile(String filename) {
		try {
		// write the content into xml file
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer =
        transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        
        StreamResult result =
                new StreamResult(new File(filename));
        transformer.transform(source, result);
        
        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
	}

}
