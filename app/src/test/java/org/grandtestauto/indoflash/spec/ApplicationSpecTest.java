package org.grandtestauto.indoflash.spec;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Tim Lavers
 */
public class ApplicationSpecTest {

    private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<FlashCardsApp><AppName>IndoFlash</AppName>" +
            "    <Chapters>\n" +
            "        <Chapter>\n" +
            "            <Title>Lessons 1 - 10</Title>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 1</Title>\n" +
            "                <File>lesson1</File>\n" +
            "            </WordList>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 2</Title>\n" +
            "                <File>lesson2</File>\n" +
            "            </WordList>\n" +
            "        </Chapter>\n" +
            "        <Chapter>\n" +
            "            <Title>Lessons 11 - 20</Title>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 11</Title>\n" +
            "                <File>lesson11</File>\n" +
            "            </WordList>\n" +
            "            <WordList>\n" +
            "                <Title>Lesson 12</Title>\n" +
            "                <File>lesson12</File>\n" +
            "            </WordList>\n" +
            "        </Chapter>\n" +
            "    </Chapters>\n" +
            "</FlashCardsApp>";

    @Test
    public void constructFromNode() throws Exception {
        ApplicationSpec spec = new ApplicationSpec(WordListSpecTest.getDocument(XML));
        Assert.assertEquals(2, spec.chapterSpecs().size());
        Assert.assertEquals("Lessons 1 - 10", spec.chapterSpecs().get(0).title());
        Assert.assertEquals("Lessons 11 - 20", spec.chapterSpecs().get(1).title());
    }

    @Test
    public void chapterForName() throws Exception {
        ApplicationSpec spec = new ApplicationSpec(WordListSpecTest.getDocument(XML));
        Assert.assertEquals("Lessons 1 - 10", spec.chapterForName("Lessons 1 - 10").title());
        Assert.assertNull(spec.chapterForName("not there"));
    }
}