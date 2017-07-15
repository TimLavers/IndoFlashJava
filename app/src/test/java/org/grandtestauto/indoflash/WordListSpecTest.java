package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WordListSpecTest {

    private static WordListSpec wordListSpec(String title, String fileName) {
        return new WordListSpec(title, fileName);
    }

    static Node parseNode(String xml, String tag) throws Exception {
        return getDocument(xml).getElementsByTagName(tag).item(0);
    }

    static Document getDocument(String xml) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(xml.getBytes("utf-8"));
        return builder.parse(stream);
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

    @Test
    public void toStringTest() {
        String title = "The title";
        Assert.assertEquals(title, wordListSpec(title, "The file").toString());
    }

    @Test
    public void constructFromNode() throws Exception {
        String xml = "<WordList><Title>Lesson 1</Title><File>lesson1</File></WordList>";
        Node appNode = parseNode(xml, WordListSpec.TAG);
        WordListSpec list = new WordListSpec((Element) appNode);
        Assert.assertEquals("Lesson 1", list.title());
        Assert.assertEquals("lesson1", list.fileName());
    }
}
