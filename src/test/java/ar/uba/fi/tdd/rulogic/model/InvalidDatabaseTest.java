package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

/**
 * Created by marco on 04/11/17.
 */
public class InvalidDatabaseTest {

    @Test
    public void partialDatabase() {

        try {
            KnowledgeBase kb = new KnowledgeBase(
                    "varon(juan).\n" +
                    "varon.");

            Assert.fail("Exception was not thrown");
        } catch(IllegalArgumentException ignore) {}

    }

}
