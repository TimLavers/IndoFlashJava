package org.grandtestauto.indoflash;

import android.util.Log;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/** @author TimL */
public class WordList {
    private static final String LOG_ID = "IndoFlash.WordList";
    private List<Word> words;

    public static interface ErrorHandler {
        void showErrorMessageToUser(String errorMessage, Throwable throwable);
    }

    public static WordList readFromStream(Reader reader) throws IOException {
        List<Word> words = new LinkedList<Word>();
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

    public WordList(List<Word> words) {
        this.words = new LinkedList<Word>(words);
    }

    public List<Word> words() {
        return Collections.unmodifiableList(words);
    }

    public void add(Word word) {
        if (words.contains(word)) return;
        words.add(word);
    }

    public void remove(Word word) {
        words.remove(word);
    }

    @Override
    public String toString() {
        return "WordList" + words;
    }

    public void storeIn(Writer writer, ErrorHandler eh) {
        Log.d(LOG_ID, "storeIn: " );
        BufferedWriter bw = new BufferedWriter(writer);
        for (Word word : words) {
            try {
                Log.d(LOG_ID, "storeIn, adding word: " + word );
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
}
