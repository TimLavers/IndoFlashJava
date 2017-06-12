package org.grandtestauto.indoflash.testui;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.ListView;
import org.grandtestauto.indoflash.ChapterSelecter;
import org.grandtestauto.indoflash.R;
import org.grandtestauto.indoflash.UIActivity;
import org.grandtestauto.indoflash.WordListSelecter;

import java.util.ArrayList;
import java.util.List;

public class UIChapterSelecter extends UIActivity {


    public UIChapterSelecter(InstrumentationTestCase testCase, Instrumentation.ActivityMonitor monitor) {
        super(testCase, monitor);
    }

    @Override
    public Class activityClass() {
        return ChapterSelecter.class;
    }

    public ListView chapterView() {
        return (ListView) activity().findViewById(R.id.chapters_list);
    }

    public List<String> chaptersAsListed() {
        int count = chapterView().getAdapter().getCount();
        List<String> result = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            result.add(chapterView().getAdapter().getItem(i).toString());
        }
        return result;
    }

    public UIWordListSelecter selectChapter(int index) {
        View itemToClick = chapterView().getChildAt(index);
        doClick(itemToClick, WordListSelecter.class);
        return new UIWordListSelecter(testCase(), monitor());

    }
}
