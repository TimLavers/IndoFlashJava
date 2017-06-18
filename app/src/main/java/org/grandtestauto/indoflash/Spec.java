package org.grandtestauto.indoflash;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Title and filename. Read from XML.
 *
 * @author TimL
 */
class Spec {

    private static final String TITLE_TAG = "Title";
    private String title;

    Spec(String title) {
        this.title = title;
    }

    Spec(Element node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child.getNodeName().equals(TITLE_TAG)) {
                title = child.getTextContent().trim();
            }
        }
    }

    public String title() {
        return title;
    }
}