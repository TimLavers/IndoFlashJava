package org.grandtestauto.indoflash;

import android.test.AndroidTestCase;
import org.grandtestauto.indoflash.WordListSpec;
import org.junit.Assert;
import org.junit.Test;

public class WordListSpecTest {

    private static WordListSpec wordListSpec(String title, String fileName) {
        return new WordListSpec(title, fileName);
    }

    @Test
    public void testTitle() {
        String title = "The title";
        Assert.assertEquals(title, wordListSpec(title, "The file").title());
    }

    @Test
    public void testFileName() {
        String title = "The title";
        String fileName = "The file";
        Assert.assertEquals(fileName, wordListSpec(title, fileName).fileName());
    }
}
