package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;

public class ChapterListSelecterProxy extends ListViewProxy {

    ChapterListSelecterProxy() {
        super(R.id.chapters_list);
    }

    public WordListSelecterProxy selectChapter(String title) {
        select(title);
        return new WordListSelecterProxy();
    }
}
