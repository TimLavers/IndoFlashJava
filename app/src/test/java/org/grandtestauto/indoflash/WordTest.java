package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;

public class WordTest {

    @Test
    public void testWord() {
        Word word = new Word("satu", "one");
        Assert.assertEquals("satu", word.word());
    }

    @Test
    public void testDefinition() {
        Word word = new Word("satu", "one");
        Assert.assertEquals("one", word.definition());
    }
}