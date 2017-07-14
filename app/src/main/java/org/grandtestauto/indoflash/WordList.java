package org.grandtestauto.indoflash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * List of words for the user to learn.
 *
 * @author Tim Lavers
 */
class WordList {
    private static final String LOG_ID = "IndoFlash.WordList";
    private List<Word> words;

    WordList(List<Word> words) {
        this.words = new LinkedList<>(words);
    }

    static WordList read(Reader reader) throws IOException {
        List<Word> words = new LinkedList<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String nextLine = bufferedReader.readLine();
        while (nextLine != null) {
            if (nextLine.contains("=")) {
                String[] pair = nextLine.split("=");
                if (pair.length != 2) continue;
                words.add(new Word(pair[0].trim(), pair[1].trim()));
            }
            nextLine = bufferedReader.readLine();
        }
        return new WordList(words);
    }

    public List<Word> words() {
        return Collections.unmodifiableList(words);
    }

    public void add(Word word) {
        if (words.contains(word)) return;
        words.add(word);
    }

    void remove(Word word) {
        words.remove(word);
    }

    void store(Writer writer, ErrorHandler eh) {
        BufferedWriter bw = new BufferedWriter(writer);
        for (Word word : words) {
            try {
                bw.append(word.word());
                bw.append("=");
                bw.append(word.definition());
                bw.newLine();
            } catch (Throwable e) {
                eh.showErrorMessageToUser("Problem saving word list....", e);
            }
        }
        try {
            bw.close();
        } catch (Throwable e) {
            eh.showErrorMessageToUser("Problem saving word list....", e);
        }
    }

    interface ErrorHandler {
        void showErrorMessageToUser(String errorMessage, Throwable throwable);
    }
}