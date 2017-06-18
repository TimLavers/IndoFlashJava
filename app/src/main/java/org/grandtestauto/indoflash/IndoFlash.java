package org.grandtestauto.indoflash;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * The main application. Holds the state such as the list of words
 * currently being studied, whether or not to shuffle the list,
 * and so on.
 *
 * @author Tim Lavers
 */
public class IndoFlash extends Application {
    public static final String FAVOURITES_FILE_NAME = "favourites";
    public static final String LOG_ID = "IndoFlash:IndoFlash";
    public static final String PREFERENCES_NAME = "IndoFlash";
    public static final String INDONESIAN_TO_ENGLISH_PREFERENCES_KEY = "IndonesianToEnglish";
    public static final String CHAPTER_PREFERENCES_KEY = "Chapter";
    public static final String WORD_LIST_PREFERENCES_KEY = "WordList";

    private WordListSpec wordListSpec;
    private WordList wordList;
    private ChapterSpec currentChapter;
    private ApplicationSpec applicationSpec;
    private boolean showIndonesianFirst = false;
    private boolean showingFavourites = false;
    private boolean shuffle = false;

    @Override
    public void onCreate() {
        super.onCreate();
        parseSetupFileToApplicationSpec();
        showIndonesianFirst = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(INDONESIAN_TO_ENGLISH_PREFERENCES_KEY, false);
        setInitialChapterAndWordList();
    }

    private void setInitialChapterAndWordList() {
        String storedChapter = getSetting(CHAPTER_PREFERENCES_KEY);
        currentChapter = applicationSpec.chapterForName(storedChapter);
        if (currentChapter == null) currentChapter = applicationSpec.chapterSpecs().get(0);

        String storedWordList = getSetting(WORD_LIST_PREFERENCES_KEY);
        wordListSpec = currentChapter.forName(storedWordList);
        if (wordListSpec == null) {
            wordListSpec = currentChapter.wordLists().get(0);
        }
        setWordList(wordListSpec);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public ApplicationSpec applicationSpec() {
        return applicationSpec;
    }

    public void addRemoveFavourite(Word word) {
        //If translation is showing first, the word passed in here is the reverse of its stored version in the data files.
        Word wordToAddOrRemove = showIndonesianFirst() ? word : new Word(word.definition(), word.word());

        WordList words = readFromFavourites();
        if (showingFavourites) {
            words.remove(wordToAddOrRemove);
        } else {
            words.add(wordToAddOrRemove);
        }
        writeToFavourites(words);
    }

    public void clearFavourites() {
        writeToFavourites(new WordList(Collections.<Word>emptyList()));
    }

    public WordList storedFavourites() {
        return readFromFavourites();
    }

    public WordList wordList() {
        return wordList;
    }

    public void toggleShowIndonesianFirst() {
        showIndonesianFirst = !showIndonesianFirst;
        storeSetting(INDONESIAN_TO_ENGLISH_PREFERENCES_KEY, showIndonesianFirst);
    }

    private void storeSetting(String key, boolean value) {
        getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    private void storeSetting(String key, String value) {
        getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    private String getSetting(String key) {
        return getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getString(key, "");
    }

    public boolean showIndonesianFirst() {
        return showIndonesianFirst;
    }

    public void setWordList(WordListSpec wordListSpec) {
        this.wordListSpec = wordListSpec;
        String fileName = wordListSpec.fileName();
        if (fileName.equalsIgnoreCase(FAVOURITES_FILE_NAME)) {
            wordList = readFromFavourites();
            showingFavourites = true;
        } else {
            wordList = readWordList(fileName);
            showingFavourites = false;
        }
        storeSetting(WORD_LIST_PREFERENCES_KEY, wordListSpec.title());
    }

    public void setCurrentChapter(ChapterSpec chapterSpec) {
        this.currentChapter = chapterSpec;
        storeSetting(CHAPTER_PREFERENCES_KEY, chapterSpec.title());
    }

    public ChapterSpec currentChapter() {
        return currentChapter;
    }

    public WordListSpec currentWordList() {
        return wordListSpec;
    }

    public List<ChapterSpec> chapterSpecs() {
        return applicationSpec.chapterSpecs();
    }

    public boolean shuffle() {
        return shuffle;
    }

    public void toggleShuffle() {
        shuffle = !shuffle;
    }

    public boolean showingFavourites() {
        return showingFavourites;
    }

    private void parseSetupFileToApplicationSpec() {
        InputStream inputStream = getResources().openRawResource(R.raw.setup);
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            handleError("Could not create document builder", e);
        }
        org.w3c.dom.Document document = null;
        try {
            document = builder.parse(inputStream);
        } catch (SAXException e) {
            handleError("Could not parse setup file", e);
        } catch (IOException e) {
            handleError("Error reading setup file", e);
        }
        applicationSpec = new ApplicationSpec(document);
    }

    private WordList readWordList(String fileName) {
        Class raw = R.raw.class;
        try {
            Field fileNameIntField = raw.getField(fileName);
            int fileNameInt = fileNameIntField.getInt(null);
            InputStream inputStream = getResources().openRawResource(fileNameInt);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            return WordList.readFromStream(reader);
        } catch (Throwable e) {
            Log.e(LOG_ID, "Problem loading file", e);
            return new WordList(Collections.<Word>emptyList());
        }
    }

    private WordList readFromFavourites() {
        FileInputStream fin;
        try {
            fin = openFileInput(FAVOURITES_FILE_NAME);
        } catch (FileNotFoundException e) {
            Log.d(LOG_ID, "Could not find file " + FAVOURITES_FILE_NAME + " when reading from favourites.", e);
            return new WordList(Collections.<Word>emptyList());
        }
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(fin, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.d(LOG_ID, "Bad coding when reading from favourites.", e);
            return new WordList(Collections.<Word>emptyList());
        }
        try {
            return WordList.readFromStream(reader);
        } catch (IOException e) {
            Log.d(LOG_ID, "Problem when reading from favourites.", e);
            return new WordList(Collections.<Word>emptyList());
        }
    }

    private void writeToFavourites(WordList toStore) {
        FileOutputStream fout = null;
        try {
            fout = openFileOutput(FAVOURITES_FILE_NAME, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            Log.d(LOG_ID, "Could not find file " + FAVOURITES_FILE_NAME + " when writing to favourites.", e);
        }
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(fout, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.d(LOG_ID, "Bad coding when writing to favourites.", e);
        }
        toStore.storeIn(writer, new WordList.ErrorHandler() {
            @Override
            public void showErrorMessageToUser(String errorMessage, Throwable throwable) {
                Log.d("IndoFlash.Favourites", "Could not store favourites.", throwable);
            }
        });

    }

    private void handleError(String msg, Exception problem) {
        Log.e(LOG_ID, msg, problem);
    }
}
