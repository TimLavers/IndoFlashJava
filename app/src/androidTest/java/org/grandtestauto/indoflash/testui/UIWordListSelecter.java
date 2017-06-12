package org.grandtestauto.indoflash.testui;

import android.app.Instrumentation;
import android.test.InstrumentationTestCase;
import android.test.TouchUtils;
import android.view.View;
import android.widget.ListView;
import org.grandtestauto.indoflash.ChapterSelecter;
import org.grandtestauto.indoflash.R;
import org.grandtestauto.indoflash.UIActivity;
import org.grandtestauto.indoflash.WordListDisplay;
import org.grandtestauto.indoflash.WordListSelecter;

public class UIWordListSelecter extends UIActivity {

    public UIWordListSelecter(InstrumentationTestCase testCase, Instrumentation.ActivityMonitor monitor) {
        super(testCase, monitor);
    }

    public UIWordListDisplay selectList(int index) {
        View itemToClick = listView().getChildAt(index);
        doClick(itemToClick, WordListDisplay.class);
        return new UIWordListDisplay(testCase(), monitor());
    }

    public UIWordListDisplay selectFavourites() {
        TouchUtils.scrollToBottom(testCase(), activity(), listView());
        return selectList(listView().getChildCount() - 1);
    }

    @Override
    public Class activityClass() {
        return WordListSelecter.class;
    }

    public View showChapterListsButton() {
        return activity().findViewById(R.id.show_chapters_button);
    }

//    public UIChapterSelecter activateShowChapterListButton() {
//        doClick(showChapterListsButton(), ChapterSelecter.class);
//        return new UIChapterSelecter(testCase(), monitor());
//    }

    private ListView listView() {
        return (ListView) activity().findViewById(R.id.lists_list);
    }
}
