import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class SecctionTests {

	@Test
	public void testSectionStorage() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("working-storage section.");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
	
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getSectionName(), "working-storage");
	}

}
