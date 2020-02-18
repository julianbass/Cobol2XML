import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class ConstantTests {

	@Test
	public void constantTestValue() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("88 test value 112");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getConstantValue(), 112, 0.000001);
		
		t.setString("88 test value 113");
		Assembly in2 = new TokenAssembly(t);
		Assembly out2 = p.bestMatch(in2);
		
		Cobol c2 = new Cobol();
		c2 = (Cobol) out2.getTarget();
		
		assertNotEquals(c2.getConstantValue(), 112, 0.000001);
	}
	
	@Test
	public void constantTestName() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("88 test value 112");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getConstantName(),"test");
		
		t.setString("88 test2 value 113");
		Assembly in2 = new TokenAssembly(t);
		Assembly out2 = p.bestMatch(in2);
		
		Cobol c2 = new Cobol();
		c2 = (Cobol) out2.getTarget();
		
		assertNotEquals(c2.getConstantName(),"test");
	}

	
	@Test
	public void constantTestLineNum() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		
		t.setString("88 test value 112");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();
		
		assertEquals(c.getLineNumber(), 88);
		
		t.setString("87 test2 value 113");
		Assembly in2 = new TokenAssembly(t);
		Assembly out2 = p.bestMatch(in2);
		
		Cobol c2 = new Cobol();
		c2 = (Cobol) out2.getTarget();
		
		assertNotEquals(c2.getLineNumber(), 88);
	}
}
