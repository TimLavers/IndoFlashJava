package org.grandtestauto.indoflash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
//import com.google.analytics.tracking.android.EasyTracker;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * User interface for selecting a word list to work on.
 *
 * @author Tim Lavers
 */
public class WordListSelecter extends Activity {
    public static final String LOG_ID = "IndoFlash:WordListSelecter";
    private IndoFlash application;
    private ListView listsList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (IndoFlash) getApplication();
        setContentView(R.layout.word_list_selecter);
        listsList = (ListView) findViewById(R.id.lists_list);
        final List<WordListSpec> listSpecs = application.currentChapter().wordLists();
        ArrayAdapter<WordListSpec> spinnerModel = new ArrayAdapter<WordListSpec>(this,android.R.layout.simple_spinner_dropdown_item, listSpecs);
        listsList.setAdapter(spinnerModel);
        listsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WordListSpec wordListSpec = listSpecs.get(i);
                application.setWordList(wordListSpec);
                Intent intent = new Intent(WordListSelecter.this, WordListDisplay.class);
                startActivity(intent);
            }
        });

        ImageButton showChaptersButton = (ImageButton)findViewById(R.id.show_chapters_button);
        showChaptersButton.setImageResource(R.drawable.ic_chapters);
        showChaptersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WordListSelecter.this, ChapterSelecter.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Overridden to provide a hook for Google Analytics.
     */
//    @Override
//    public void onStart() {
//        super.onStart();
//        EasyTracker.getInstance(this).activityStart(this);
//    }

    /**
     * Overridden to provide a hook for Google Analytics.
     */
//    @Override
//    public void onStop() {
//        super.onStop();
//        EasyTracker.getInstance(this).activityStop(this);
//    }
}