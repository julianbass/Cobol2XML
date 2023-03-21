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

/**
 * Recognise constructs in a COBOL source code file.
 * And then produce a well-formed XML file with the data identified
 */
public class Cobol2XML {


	// use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


	/**
	 * @param args command line arguments
	 * @throws Exception e
	 */
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

		// Creates an object which stores each line of the cobol program as an object representing a xml tag
        XMLPayload xmlp = new XMLPayload();
		// Reads the input file as a series of bytes
		// Used to read each line in the input file's byte stream
		// Try block because the next method call may throw FileNotFoundException
		BufferedReader r = null;
		try (InputStream is = new FileInputStream(args[0])) {
			// Initialise with object that can read bytes from input file
			try {
				// Uses the InputStreamReader to convert the bytes into characters, then passes this into the BufferedReader that can read lines
				r = new BufferedReader(new InputStreamReader(is));
				// Gets a new Tokenizer object that uses spaces as a delimiter for the cobol words
				Tokenizer t = CobolParser.tokenizer();
				// Gets a new Alternation object that builds a cobol object from parsed word data
				Parser p = CobolParser.start();
				// Look through source code file line by line
				while (true) {
					// Reads the first/next line of the input file, may throw IOException
					String s = r.readLine();
					// Breaks the while loop if the end of the file is reached
					if (s == null) {
						break;
					}
					// Inputs the current line into the tokenizer, which will split it into cobol words
					t.setString(s);
					// Creates a TokenAssembly containing the tokens returned from the Tokenizer object
					Assembly in = new TokenAssembly(t);
					// Gets an Assembly containing as many matched elements consumed by the cobol parser as possible
					Assembly out = p.bestMatch(in);
					// Gets a cobol object, containing the cobol data retrieved from this line
					Cobol c = (Cobol) out.getTarget();
					// If the cobol object contains data, appends it as a xml element to the payload object
					if (c != null)
						xmlp.addElements(c);
				}// while
			} catch (Exception e) {
				e.printStackTrace(); // BufferedReader
			} finally {
				if (r != null) {
					// Closes the BufferedReader to allow the InputStream to be closed by automatic resource management
					r.close();
				}
			} // finally BufferedReader
		} catch (Exception e) {
			e.printStackTrace(); // FileInputStream
		} finally {
			xmlp.writeFile(args[1]);
		}

	} // main()
}


