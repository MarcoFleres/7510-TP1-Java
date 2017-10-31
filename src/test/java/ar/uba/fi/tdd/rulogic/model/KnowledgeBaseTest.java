package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.io.IOException;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase = new KnowledgeBase();

	public KnowledgeBaseTest() throws IOException {
	}

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test() {

		Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
		Assert.assertTrue(this.knowledgeBase.answer("varon (pepe)."));
		Assert.assertFalse(this.knowledgeBase.answer("varon (maria)."));
		Assert.assertTrue(this.knowledgeBase.answer("mujer (maria)."));

	}

}
