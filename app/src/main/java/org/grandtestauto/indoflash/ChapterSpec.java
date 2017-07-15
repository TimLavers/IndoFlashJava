package org.grandtestauto.indoflash;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

/**
 * Sub-topic structure for the word lists that make up
 * a chapter in the application. Read from XML.
 *
 * @author Tim Lavers
 */
public class ChapterSpec extends Spec {
    static final String CHAPTER = "Chapter";
    static final String FAVOURITES = "Favourites";
    private List<WordListSpec> wordLists = new LinkedList<>();

    ChapterSpec(Element node) {
        super(node);
        NodeList childNodes = node.getElementsByTagName(WordListSpec.TAG);
        for (int i = 0; i < childNodes.getLength(); i++) {
            wordLists.add(new WordListSpec((Element) childNodes.item(i)));
        }
        wordLists.add(new WordListSpec(FAVOURITES, IndoFlash.FAVOURITES_FILE_NAME));
    }

    @Override
    public String toString() {
        return title();
    }

    public List<WordListSpec> wordLists() {
        return wordLists;
    }

    WordListSpec forName(String name) {
        for (WordListSpec list : wordLists) {
            if (list.title().equals(name)) {
                return list;
            }
        }
        return null;
    }
}