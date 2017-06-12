package org.grandtestauto.indoflash;

import android.util.Log;
import android.widget.Toast;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Title and filename for a word list. Read from XML.
 *
 * @author TimL
 */
public class WordListSpec extends Spec {
    public static final String TAG = "WordList";

    public static final String FILE_TAG = "File";

    private String fileName;

    public WordListSpec(String title, String fileName) {
        super(title);
        this.fileName = fileName;
    }

    public WordListSpec(Element node) {
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