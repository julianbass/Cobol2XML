/*
 * @(#)Cobol2XML.java	 0.0.1
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

import XMLWriter.*;
import java.io.*;
import parse.*;
import parse.tokens.*;

public class Cobol2XML {
	/**
	 * Recognise some basic constructs in a COBOL source code file.
	 * And then produce a well-formed XML file with the data identified
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("Cobol2XML V0.0.1");
		XMLPayload xmlp = new XMLPayload();
		
		// Poor practice to hard code a file name into the source
		// Better to pass the file name in as a parameter, or ask user for filename input
		// throws FileNotFoundException
		InputStream is = new FileInputStream("C:\\Users\\sgs442\\eclipse-workspace\\CobolParser1\\base.cbl");
		BufferedReader r = 	new BufferedReader(new InputStreamReader(is));

		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		// Look through source code file line by line
		while (true) {
			// throws IOException
			String s = r.readLine();
			if (s == null) {
				break;
			}
			t.setString(s);
			Assembly in = new TokenAssembly(t);
			Assembly out = p.bestMatch(in);
			Cobol c = new Cobol();
			c = (Cobol) out.getTarget();
			
			if(c != null)
				xmlp.addElements(c); 
			
		}
		xmlp.writeFile();
		r.close();
	}

}
