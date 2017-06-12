package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WordListTest {

    @Test
    public void testWords() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        Assert.assertEquals(3, wl123().words().size());
        Assert.assertTrue(wl123().words().contains(new Word("satu", "one")));
        Assert.assertTrue(wl123().words().contains(new Word("dua", "two")));
        Assert.assertTrue(wl123().words().contains(new Word("tiga", "three")));
    }

    @Test
    public void testAddWord() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        list.add(new Word("empat", "four"));
        Assert.assertEquals(1, list.words().size());
        Assert.assertTrue(list.words().contains(new Word("empat", "four")));

        list = wl123();
        list.add(new Word("empat", "four"));
        Assert.assertEquals(4, list.words().size());
        Assert.assertTrue(list.words().contains(new Word("empat", "four")));
    }

    @Test
    public void testRemoveWord() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        list.remove(new Word("empat", "four"));
        Assert.assertEquals(0, list.words().size());

        list = wl123();
        list.remove(new Word("dua", "two"));
        Assert.assertEquals(2, list.words().size());
        Assert.assertFalse(list.words().contains(new Word("dua", "two")));
    }

    private WordList wl123() {
        List<Word> w123 = new LinkedList<>();
        w123.add(new Word("satu", "one"));
        w123.add(new Word("dua", "two"));
        w123.add(new Word("tiga", "three"));
        return new WordList(w123);
    }
}