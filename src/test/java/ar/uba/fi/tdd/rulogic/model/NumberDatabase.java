package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by marco on 04/11/17.
 */
public class NumberDatabase {

    private KnowledgeBase kb;

    private static final String db =
            "add(zero, zero, zero).\n" +
            "add(zero, one, one).\n" +
            "add(zero, two, two).\n" +
            "add(one, zero, one).\n" +
            "add(one, one, two).\n" +
            "add(one, two, zero).\n" +
            "add(two, zero, two).\n" +
            "add(two, one, zero).\n" +
            "add(two, two, one).\n" +
            "subtract(X, Y, Z) :- add(Y, Z, X).";

    @Before
    public void setup() {

        kb = new KnowledgeBase(db);

    }

    @Test
    public void testFacts() {
        Assert.assertTrue(kb.answer("add(one, one, two) "));
        Assert.assertFalse(kb.answer("add(two, one, one)"));

        try {
            kb.answer("add(, one, two)");
            Assert.fail("Should have failed");
        } catch (IllegalArgumentException ignore) {}
    }

    @Test
    public void testRules() {
        Assert.assertFalse(kb.answer("subtract(one, one, two)"));
        Assert.assertTrue(kb.answer("subtract(two, one, one)"));

        try {
            kb.answer("subtract()");
            Assert.fail("Should have failed");
        } catch (IllegalArgumentException ignore) {}
    }
}
