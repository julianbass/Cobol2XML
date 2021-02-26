/*
 * @(#)Cobol2XML.java	 0.1.0
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

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import logger.*;
import parse.*;
import parse.tokens.*;
import xmlwriter.*;

public class Cobol2XML {
	/**
	 * Recognise some basic constructs in a COBOL source code file.
	 * And then produce a well-formed XML file with the data identified
	 * 
	 * First command line parameter must be path to cobol source file, such as
	 * "C:\\Users\\<your user name>\\git\\cobol2xml\\base.cbl"
	 * 
	 * Or, when you know exactly where the repository is located and have the file in the right place, simply
	 * "base.cbl"
	 * 
	 * The quotation marks are required
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	// use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    
    
    public static void main(String[] args) throws Exception {
		/* The first command line parameter is used to get the cobol source file namee
		 * In case you are not sure if you are pointing toward the right file, print out the filename
		 * like this...
		 *
		 * System.out.println("arg[0]" + args[0])
		 * 
		 * A rather crude approach is to hard code the filename for the cobol source file, like this
		 * InputStream is = new FileInputStream("C:\\Users\\sgs442\\eclipse-workspace\\CobolParser1\\base.cbl")
		 */

        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        LOGGER.setLevel(Level.INFO);
        LOGGER.info("Cobol2XML V0.1.0");

        XMLPayload xmlp = new XMLPayload();
		InputStream is = null;
		BufferedReader r = null;
		try {
			is = new FileInputStream(args[0]);
			try {
				r = new BufferedReader(new InputStreamReader(is));

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
					}// while
				} catch (Exception e) {
					e.printStackTrace(); // BufferedReader
				} finally {
					if(r != null) {
						r.close(); 
					}
				} // finally BufferedReader
		  } catch (Exception e) {
		    e.printStackTrace(); // FileInputStream
		  } finally {
			xmlp.writeFile(args[1]);
			if(is != null) {
				is.close(); 
			}		
		  } // finally FilelInputStream

	} // main()
}


