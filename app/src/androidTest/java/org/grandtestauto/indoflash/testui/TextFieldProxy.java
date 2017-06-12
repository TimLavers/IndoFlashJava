package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class TextFieldProxy extends ViewProxy{
    public TextFieldProxy(int id) {
        super(id);
    }

    public void checkText(String expected) {
        onView(withId(id())).check(matches(withText(expected)));
    }

    public void checkThatTextIsEmpty() {
        checkText("");
    }}
