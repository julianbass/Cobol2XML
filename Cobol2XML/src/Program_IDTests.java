import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class Program_IDTests {

	@Test
	public void testProgramId() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("program-id. base_jb12");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
	
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getProgram_ID(), "base_jb12");
		
		t.setString("program-id. base_jb11");
		
		Assembly in2 = new TokenAssembly(t);
		Assembly out2 = p.bestMatch(in);
		
		Cobol c2 = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertNotEquals(c2.getProgram_ID(), "base_jb12");
	}

}
