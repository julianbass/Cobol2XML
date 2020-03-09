import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.Token;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;
import parse.tokens.WhitespaceState;

public class TokeinzerTests {

	@Test
	public void testTokenizer() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
//		t.setCharacterState(   0,   ' ', new WhitespaceState());
		
		t.setString("program-id. base_jb12");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
	
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getProgram_ID(), "base_jb12");
		
		
		
		//Testing Token Assembly
		System.out.println("Test");
		TokenAssembly ta = new TokenAssembly("token1 token2");
		assertEquals(ta.remainder("/"), "token1/token2");
		System.out.println(ta.remainder("/"));
	}

}
