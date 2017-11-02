package ar.uba.fi.tdd.rulogic.model;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KnowledgeBaseTest {

	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {

		// LLemos la base de datos y la convertimos en un string
		InputStream inputStream = KnowledgeBaseTest.class.getClassLoader().getResourceAsStream("./rules.db");

		String db = new BufferedReader(new InputStreamReader(inputStream))
				.lines()
				.collect(Collectors.joining("\n"));

		knowledgeBase = new KnowledgeBase(db);

		System.out.println(knowledgeBase);

	}

	@Test
	public void test() {

		assertFalse(this.knowledgeBase.answer("varon (javier)."));
		assertTrue(this.knowledgeBase.answer("varon (pepe)."));
		assertFalse(this.knowledgeBase.answer("varon (maria)."));
		assertTrue(this.knowledgeBase.answer("mujer (maria)."));

	}

	@Test
	public void testFacts() {

		assertTrue(this.knowledgeBase.answer("varon(juan)"));
		assertTrue(this.knowledgeBase.answer("varon(pepe)"));
		assertTrue(this.knowledgeBase.answer("varon(hector)"));
		assertTrue(this.knowledgeBase.answer("varon(roberto)"));
		assertTrue(this.knowledgeBase.answer("varon(alejandro)"));
		assertTrue(this.knowledgeBase.answer("varon(nicolas)"));
		assertTrue(this.knowledgeBase.answer("mujer(maria)"));
		assertTrue(this.knowledgeBase.answer("mujer(cecilia)"));
		assertTrue(this.knowledgeBase.answer("padre(juan, pepe)"));
		assertTrue(this.knowledgeBase.answer("padre(juan, pepa)"));
		assertTrue(this.knowledgeBase.answer("padre(hector, maria)"));
		assertTrue(this.knowledgeBase.answer("padre(roberto, alejandro)"));
		assertTrue(this.knowledgeBase.answer("padre(roberto, cecilia)"));
		assertTrue(this.knowledgeBase.answer("hermano(nicolas, roberto)"));
		assertTrue(this.knowledgeBase.answer("hermano(roberto, nicolas)"));

		assertFalse(this.knowledgeBase.answer("mujer(juan)"));
		assertFalse(this.knowledgeBase.answer("mujer(pepe)"));
		assertFalse(this.knowledgeBase.answer("mujer(hector)"));
		assertFalse(this.knowledgeBase.answer("mujer(roberto)"));
		assertFalse(this.knowledgeBase.answer("mujer(alejandro)"));
		assertFalse(this.knowledgeBase.answer("varon(maria)"));
		assertFalse(this.knowledgeBase.answer("varon(cecilia)"));
		assertFalse(this.knowledgeBase.answer("padre(pepe, juan)"));
		assertFalse(this.knowledgeBase.answer("padre(pepa, juan)"));
		assertFalse(this.knowledgeBase.answer("padre(maria, hector)"));

		assertFalse(this.knowledgeBase.answer("mujer(jgobnejaogb)"));
		assertFalse(this.knowledgeBase.answer("varon(jgobnejaogb)"));
		assertFalse(this.knowledgeBase.answer("padre(roberto)"));

	}


}
