package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ButtonProxy extends ViewProxy{
    public ButtonProxy(int id) {
        super(id);
    }

    public void activate() {
        onView(withId(id())).perform(click());
    }
}
