package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;

public class SpecTest {

    private static Spec spec(String title) {
        return new Spec(title);
    }

    @Test
    public void testTitle() {
        Assert.assertEquals("The title", spec("The title").title());
    }
}
