package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;

public class WordTest {

    @SuppressWarnings({"EqualsBetweenInconvertibleTypes", "ObjectEqualsNull"})
    @Test
    public void equalsTest() {
        Word w1 = new Word("satu", "one");
        Word w2 = new Word("satu", "1");
        Word w3 = new Word("Satu", "one");
        Assert.assertTrue(w1.equals(w2));
        Assert.assertFalse(w1.equals(w3));
        Assert.assertFalse(w1.equals("satu"));
        Assert.assertFalse(w1.equals(null));
    }

    @Test
    public void hashCodeTest() {
        Word w1 = new Word("satu", "one");
        Word w2 = new Word("satu", "1");
        Assert.assertEquals(w1.hashCode(), w2.hashCode());
    }

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

    @Test
    public void toStringTest() {
        Word word = new Word("satu", "one");
        Assert.assertEquals("satu=one", word.toString());
    }
}