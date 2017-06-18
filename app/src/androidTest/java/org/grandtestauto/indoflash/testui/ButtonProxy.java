package org.grandtestauto.indoflash.testui;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class ButtonProxy extends ViewProxy {
    ButtonProxy(int id) {
        super(id);
    }

    public void activate() {
        onView(withId(id())).perform(click());
    }

    public void checkIsInactive() {
        onView(allOf(withId(id()), not(isClickable()))).check(matches(isDisplayed()));
    }
}