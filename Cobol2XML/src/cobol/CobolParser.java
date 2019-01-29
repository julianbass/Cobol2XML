/*
 * @(#)CobolParser.java	 0.0.1
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

import parse.Alternation;
import parse.Empty;
import parse.Parser;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Num;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.Word;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object
	 * as an assembly's target.
	 *
	 * @return a parser that will recognize and build a 
	 *         <code>COBOL</object> from a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();
		
		Symbol fullstop = new Symbol('.');
		fullstop.discard();
		
		a.add(IdentificationDivision() );
		
		a.add(ProgramID() );
		
		a.add(ProcedureDivision() );
		
		a.add(DataDivision() );
		
		a.add( Section() );
		
		a.add(new Empty());
		return a;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    identification division;
	 *
	 */
	protected Parser IdentificationDivision() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("identification division") );
		s.add(new Symbol('.').discard());
		s.setAssembler(new IdentificationDivisionAssembler());
		return s;
	}

	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word;
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}


	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    country = Word;
	 *
	 * Use a CountryAssembler to update the target coffee 
	 * object.
	 */
	//protected Parser program_id() {
//		return new Word();
	//}

	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    procedure division;
	 *
	 */
	protected Parser ProcedureDivision() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("procedure division") );
		s.add(new Symbol('.').discard());
		s.setAssembler(new ProcedureDivisionAssembler());
		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    data division;
	 *
	 */
	protected Parser DataDivision() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("data division") );
		s.add(new Symbol('.').discard());
		s.setAssembler(new DataDivisionAssembler());
		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    working-storage section;
	 *
	 */
	protected Parser Section() {
		Sequence s = new Sequence();
		//s.add(new Word());
		s.add(new CaselessLiteral("working-storage section") );
		//s.add(new CaselessLiteral("working-storage section") );
		s.add(new Symbol('.').discard());
		s.setAssembler(new SectionAssembler());
		System.out.println("Parsed Section");
		return s;
	}


	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return new CobolParser().cobol();
	}

	/**
	 * Returns a tokenizer that allows spaces to appear inside
	 * the "words" that identify cobol's grammar.
	 *
	 * @return a tokenizer that allows spaces to appear inside
	 * the "words" that identify a coffee's name.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', true);
		return t;
	}

}
