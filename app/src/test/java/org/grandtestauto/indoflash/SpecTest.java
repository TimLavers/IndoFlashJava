package org.grandtestauto.indoflash;

import android.test.AndroidTestCase;
import org.grandtestauto.indoflash.Spec;
import org.junit.Assert;
import org.junit.Test;

public class SpecTest {

    public static Spec spec(String title) {
        return new Spec(title);
    }

    @Test
    public void testTitle() {
        Assert.assertEquals("The title", spec("The title").title());
    }
}
