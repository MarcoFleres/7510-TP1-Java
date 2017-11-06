package ar.uba.fi.tdd.rulogic.model;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ModelTests {

    @org.junit.Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void factWithNoParameters() {
        expected.expect(IllegalArgumentException.class);
        new Fact("hola().");
    }

    @Test
    public void factWithEmptyParameters() {
        expected.expect(IllegalArgumentException.class);
        new Fact("hola(a,b,).");
    }

    @Test
    public void factMissesPoint() {
        expected.expect(IllegalArgumentException.class);
        new Fact("hola(chau)");
    }

    @Test
    public void factMissesVerb() {
        expected.expect(IllegalArgumentException.class);
        new Fact("(chau).");
    }

    @Test
    public void factEmpty() {
        expected.expect(IllegalArgumentException.class);
        new Fact("");
    }

    @Test
    public void queryMissesParameters() {
        expected.expect(IllegalArgumentException.class);
        new Query("hola");
    }

    @Test
    public void queryEmpty() {
        expected.expect(IllegalArgumentException.class);
        new Query("");
    }

    @Test
    public void ruleMissingTargets() {
        expected.expect(IllegalArgumentException.class);
        new Rule("padre(juan, pedro) :-");
    }


}
