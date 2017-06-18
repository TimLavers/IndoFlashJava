package org.grandtestauto.indoflash.testui;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ViewProxy {
    private int id;

    ViewProxy(int id) {
        this.id = id;
    }

    void checkIsShowing() {
        onView(withId(id)).check(matches(isDisplayed()));
    }

    int id() {
        return id;
    }

    public void checkText(String expected) {
        onView(withId(id())).check(matches(withText(expected)));
    }

    public void checkDescription(String expected) {
        onView(withId(id())).check(matches(withContentDescription(expected)));
    }
}
