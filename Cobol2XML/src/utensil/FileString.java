/*
 * @(#)FileString.java	 1.0.0
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

import java.io.*;

public class FileString {

/**
 * Returns a string that represents the contents of a file.
 *
 * @param    fileName    the name of the file to read
 *
 * @return   string    the contents of a file as a String
 *
 * @exception   IOException   if the file is not found, or if there is
 *                            any problem reading the file
 */
public static String stringFromFileNamed(String fileName) 
	throws java.io.IOException {
		
	final int BUFLEN = 1024;
	char buf[] = new char[BUFLEN];
	
	FileReader in = null;
	StringWriter out = null;
	
	try {
		in = new FileReader(fileName);
		try {
			out = new StringWriter();
			while (true) {
				int len = in.read(buf, 0, BUFLEN);
				if (len == -1) {
					break;
				}	
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
	    		out.close(); 
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
    } finally { 	
    	if(in != null) {
    		in.close(); 
		}
	}	
	if (out != null) {
		return out.toString(); 
		}
	else {
		return null;
		}
	}
}