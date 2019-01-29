package parse.tokens;

import parse.Assembly;

public class TokenAssembly extends Assembly{
	/**
	 * the "string" of tokens this assembly will consume
	 */
		protected TokenString tokenString;

	/**
	 * Constructs a TokenAssembly on a TokenString constructed 
	 * from the given String.
	 *
	 * @param   string   the string to consume
	 *
	 * @return   a TokenAssembly that will consume a tokenized 
	 *           version of the supplied String
	 */
	public TokenAssembly(String s) {
		this(new TokenString(s));
	}
	/**
	 * Constructs a TokenAssembly on a TokenString constructed 
	 * from the given Tokenizer.
	 *
	 * @param   Tokenizer   the tokenizer to consume tokens 
	 *                      from
	 *
	 * @return   a TokenAssembly that will consume a tokenized 
	 *           version of the supplied Tokenizer
	 */
	public TokenAssembly(Tokenizer t) {
		this(new TokenString(t));
	}
	/**
	 * Constructs a TokenAssembly from the given TokenString.
	 *
	 * @param   tokenString   the tokenString to consume
	 *
	 * @return   a TokenAssembly that will consume the supplied 
	 *           TokenString
	 */
	public TokenAssembly(TokenString tokenString) {
		this.tokenString = tokenString;
	}
	/**
	 * Returns a textual representation of the amount of this 
	 * tokenAssembly that has been consumed.
	 *
	 * @param   delimiter   the mark to show between consumed 
	 *                      elements
	 *
	 * @return   a textual description of the amount of this 
	 *           assembly that has been consumed
	 */
	public String consumed(String delimiter) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < elementsConsumed(); i++) {
			if (i > 0) {
				buf.append(delimiter);
			}	
			buf.append(tokenString.tokenAt(i));
		}
		return buf.toString();
	}
	/**
	 * Returns the default string to show between elements 
	 * consumed or remaining.
	 *
	 * @return   the default string to show between elements 
	 *           consumed or remaining
	 */
	public String defaultDelimiter() {
		return "/";
	}
	/**
	 * Returns the number of elements in this assembly.
	 *
	 * @return   the number of elements in this assembly
	 */
	public int length() {
		return tokenString.length();
	}
	/**
	 * Returns the next token.
	 *
	 * @return   the next token from the associated token string.
	 *
	 * @exception  ArrayIndexOutOfBoundsException  if there are no 
	 *             more tokens in this tokenizer's string.
	 */		
	public Object nextElement() {
		return tokenString.tokenAt(index++);
	}
	/**
	 * Shows the next object in the assembly, without removing it
	 *
	 * @return   the next object
	 *
	 */
	public Object peek() {
		if (index < length()) {
			return tokenString.tokenAt(index);
		} else {
			return null;
		}
	}
	/**
	 * Returns a textual representation of the amount of this 
	 * tokenAssembly that remains to be consumed.
	 *
	 * @param   delimiter   the mark to show between consumed 
	 *                      elements
	 *
	 * @return   a textual description of the amount of this 
	 *           assembly that remains to be consumed
	 */
	public String remainder(String delimiter) {
		StringBuffer buf = new StringBuffer();
		for (int i = elementsConsumed();
			 i < tokenString.length();
			 i++) {
				 
			if (i > elementsConsumed()) {
				buf.append(delimiter);
			}
			buf.append(tokenString.tokenAt(i));
		}
		return buf.toString();
	}
}
