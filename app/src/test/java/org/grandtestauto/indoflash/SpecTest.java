package org.grandtestauto.indoflash;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import static org.grandtestauto.indoflash.WordListSpecTest.parseNode;

public class SpecTest {

    private static Spec spec(String title) {
        return new Spec(title);
    }

    @Test
    public void testTitle() {
        Assert.assertEquals("The title", spec("The title").title());
    }

    @Test
    public void constructFromNode() throws Exception {
        String xml = "<WordList><Title>Lesson 1</Title><File>lesson1</File></WordList>";
        Node appNode = parseNode(xml, WordListSpec.TAG);
        Spec list = new Spec((Element) appNode);
        Assert.assertEquals("Lesson 1", list.title());
    }
}
