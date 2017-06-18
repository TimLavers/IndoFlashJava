package org.grandtestauto.indoflash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * User interface for selecting a chapter of word lists to work on.
 *
 * @author Tim Lavers
 */
public class ChapterSelecter extends Activity {
    private IndoFlash handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_selecter);
        ListView chapterList = (ListView) findViewById(R.id.chapters_list);
        handler = (IndoFlash) getApplication();
        final List<ChapterSpec> chapterSpecs = handler.chapterSpecs();
        ArrayAdapter<ChapterSpec> spinnerModel = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, chapterSpecs);
        chapterList.setAdapter(spinnerModel);
        chapterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChapterSpec chapterSpec = chapterSpecs.get(i);
                handler.setCurrentChapter(chapterSpec);
                Intent intent = new Intent(ChapterSelecter.this, WordListSelecter.class);
                startActivity(intent);
            }
        });
    }
}