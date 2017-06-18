package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;

public class WordListSelecterProxy extends ListViewProxy {

    WordListSelecterProxy() {
        super(R.id.lists_list);
    }

    public void selectFavourites() {
        onData(new ContainsMatcher("Favourites")).perform(click());
    }

    public WordListDisplayProxy selectList(String name) {
        select(name);
        return new WordListDisplayProxy();
    }

    public ChapterListSelecterProxy activateChapterListButton() {
        new ButtonProxy(R.id.show_chapters_button).activate();
        return new ChapterListSelecterProxy();
    }
}

class ContainsMatcher extends BaseMatcher<String> {

    private String toMatch;

    ContainsMatcher(String toMatch) {
        this.toMatch = toMatch;
    }

    @Override
    public boolean matches(Object item) {
        return item.toString().contains(toMatch);
    }

    @Override
    public void describeTo(Description description) {

    }
}