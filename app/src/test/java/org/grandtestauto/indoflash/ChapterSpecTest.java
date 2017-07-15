package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;

import static org.grandtestauto.indoflash.WordListSpecTest.parseNode;

public class ChapterSpecTest {

    private static final String XML = "<Chapter>" +
            "<Title>Lessons 1 - 10</Title>" +
            "<WordList><Title>Lesson 2</Title><File>lesson2</File></WordList>" +
            "<WordList><Title>Lesson 3</Title><File>lesson3</File></WordList>" +
            "</Chapter>";

    @Test
    public void constructFromNode() throws Exception {
        Node appNode = parseNode(XML, ChapterSpec.CHAPTER);
        ChapterSpec spec = new ChapterSpec((Element) appNode);
        Assert.assertEquals("Lessons 1 - 10", spec.title());
        //Check that the ChapterSpec contains specs for the word lists
        //in the xml plus one for the favourites.
        List<WordListSpec> wordListSpecs = spec.wordLists();
        Assert.assertEquals(3, wordListSpecs.size());
        Assert.assertEquals("Lesson 2", wordListSpecs.get(0).title());
        Assert.assertEquals("lesson2", wordListSpecs.get(0).fileName());
        Assert.assertEquals("Lesson 3", wordListSpecs.get(1).title());
        Assert.assertEquals("lesson3", wordListSpecs.get(1).fileName());
        Assert.assertEquals(ChapterSpec.FAVOURITES, wordListSpecs.get(2).title());
        Assert.assertEquals(IndoFlash.FAVOURITES_FILE_NAME, wordListSpecs.get(2).fileName());
    }

    @Test
    public void forName() throws Exception {
        Node appNode = parseNode(XML, ChapterSpec.CHAPTER);
        ChapterSpec spec = new ChapterSpec((Element) appNode);
        Assert.assertEquals("lesson2", spec.forName("Lesson 2").fileName());
        Assert.assertEquals("lesson3", spec.forName("Lesson 3").fileName());
        Assert.assertEquals(IndoFlash.FAVOURITES_FILE_NAME, spec.forName(ChapterSpec.FAVOURITES).fileName());
        Assert.assertNull(spec.forName("not there"));
    }
}