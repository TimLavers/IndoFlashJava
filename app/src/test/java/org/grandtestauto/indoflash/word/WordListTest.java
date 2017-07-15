package org.grandtestauto.indoflash.word;

import org.junit.Assert;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tim Lavers
 */
public class WordListTest {

    @Test
    public void words() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        Assert.assertEquals(3, wl123().words().size());
        Assert.assertTrue(wl123().words().contains(new Word("satu", "one")));
        Assert.assertTrue(wl123().words().contains(new Word("dua", "two")));
        Assert.assertTrue(wl123().words().contains(new Word("tiga", "three")));
    }

    @Test
    public void wordsReturnsCopies() {
        WordList list = wl123();
        Assert.assertNotSame(list.words(), list.words());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void wordsIsImmutable() {
        wl123().words().add(new Word("empat", "four"));
    }

    @Test
    public void addWordToEmpty() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        list.add(new Word("empat", "four"));
        Assert.assertEquals(1, list.words().size());
        Assert.assertTrue(list.words().contains(new Word("empat", "four")));
    }

    @Test
    public void addWord() {
        WordList list = wl123();
        list.add(new Word("empat", "four"));
        Assert.assertEquals(4, list.words().size());
        Assert.assertTrue(list.words().contains(new Word("empat", "four")));
    }

    @Test
    public void removeWordFromEmpty() {
        WordList list = new WordList(Collections.<Word>emptyList());
        Assert.assertEquals(0, list.words().size());
        list.remove(new Word("empat", "four"));
        Assert.assertEquals(0, list.words().size());
    }

    @Test
    public void removeWord() {
        WordList list = wl123();
        list.remove(new Word("dua", "two"));
        Assert.assertEquals(2, list.words().size());
        Assert.assertFalse(list.words().contains(new Word("dua", "two")));
        //Check that removing it again does nothing.
        list.remove(new Word("dua", "two"));
        Assert.assertEquals(2, list.words().size());
        Assert.assertFalse(list.words().contains(new Word("dua", "two")));
    }

    @Test
    public void read() throws Exception {
        Reader reader = new StringReader("satu=one\ndua=two\n\ntiga=three");
        Assert.assertEquals(WordList.read(reader).words(), wl123().words());
    }

    @Test
    public void store() throws Exception {
        StringWriter writer = new StringWriter();
        wl123().store(writer, new WordList.ErrorHandler() {
            @Override
            public void showErrorMessageToUser(String errorMessage, Throwable throwable) {
                Assert.fail(throwable.getMessage());
            }
        });
        WordList restored = WordList.read(new StringReader(writer.toString()));
        Assert.assertEquals(restored.words(), wl123().words());
    }

    private WordList wl123() {
        List<Word> w123 = new LinkedList<>();
        w123.add(new Word("satu", "one"));
        w123.add(new Word("dua", "two"));
        w123.add(new Word("tiga", "three"));
        return new WordList(w123);
    }
}