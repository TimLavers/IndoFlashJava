package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ViewProxy {
    private int id;

    public ViewProxy(int id) {
        this.id = id;
    }

    public void checkIsShowing() {
        onView(withId(id)).check(matches(isDisplayed()));
    }

    int id() {
        return id;
    }
}