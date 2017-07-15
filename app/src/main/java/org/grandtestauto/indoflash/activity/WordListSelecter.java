package org.grandtestauto.indoflash.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import org.grandtestauto.indoflash.IndoFlash;
import org.grandtestauto.indoflash.R;
import org.grandtestauto.indoflash.spec.WordListSpec;

import java.util.List;

/**
 * User interface for selecting a word list to work on.
 *
 * @author Tim Lavers
 */
public class WordListSelecter extends Activity {
    private IndoFlash application;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (IndoFlash) getApplication();
        setContentView(R.layout.word_list_selecter);
        ListView listsList = (ListView) findViewById(R.id.lists_list);
        final List<WordListSpec> listSpecs = application.currentChapter().wordLists();
        ArrayAdapter<WordListSpec> spinnerModel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listSpecs);
        listsList.setAdapter(spinnerModel);
        listsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                WordListSpec wordListSpec = listSpecs.get(i);
                application.setWordList(wordListSpec);
                Intent intent = new Intent(WordListSelecter.this, WordListDisplay.class);
                startActivity(intent);
            }
        });

        ImageButton showChaptersButton = (ImageButton) findViewById(R.id.show_chapters_button);
        showChaptersButton.setImageResource(R.drawable.ic_chapters);
        showChaptersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(WordListSelecter.this, ChapterSelecter.class);
                startActivity(intent);
            }
        });
    }
}