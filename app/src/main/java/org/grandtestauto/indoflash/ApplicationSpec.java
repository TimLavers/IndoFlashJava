package org.grandtestauto.indoflash;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

/**
 * Topic and sub-topic structure for the word lists that make up
 * a Flash Cards application. Read from XML.
 *
 * @author TimL
 */
public class ApplicationSpec {
    private static final String FLASH_CARDS_APP_TAG = "FlashCardsApp";
    private static final String CHAPTERS_TAG = "Chapters";
    private static final String APP_NAME_TAG = "AppName";

    private List<ChapterSpec> chapterSpecs = new LinkedList<>();

    ApplicationSpec(Document document) {
        Node appNode = document.getElementsByTagName(FLASH_CARDS_APP_TAG).item(0);
        NodeList children = appNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeName().equals(CHAPTERS_TAG)) {
                NodeList chapterNodes = ((Element) child).getElementsByTagName(ChapterSpec.CHAPTER);
                for (int c = 0; c < chapterNodes.getLength(); c++) {
                    chapterSpecs.add(new ChapterSpec((Element) chapterNodes.item(c)));
                }
            }
        }
    }

    public List<ChapterSpec> chapterSpecs() {
        return chapterSpecs;
    }

    ChapterSpec chapterForName(String name) {
        for (ChapterSpec spec : chapterSpecs) {
            if (spec.title().equals(name)) {
                return spec;
            }
        }
        return null;
    }
}