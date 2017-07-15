package org.grandtestauto.indoflash.spec;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Title and filename for a word list. Read from XML.
 *
 * @author TimL
 */
public class WordListSpec extends Spec {
    static final String TAG = "WordList";

    private static final String FILE_TAG = "File";

    private String fileName;

    WordListSpec(String title, String fileName) {
        super(title);
        this.fileName = fileName;
    }

    WordListSpec(Element node) {
        super(node);
        NodeList childNodes = node.getElementsByTagName(FILE_TAG);
        Node child = childNodes.item(0);
        fileName = child.getTextContent().trim();
    }

    @Override
    public String toString() {
        return title();
    }

    public String fileName() {
        return fileName;
    }
}