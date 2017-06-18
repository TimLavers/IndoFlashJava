package org.grandtestauto.indoflash.testui;

import android.view.View;
import android.widget.ListView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

public class ListViewProxy extends ViewProxy {

    public ListViewProxy(int id) {
        super(id);
    }

    /*
     * From https://stackoverflow.com/questions/30361068/assert-proper-number-of-items-in-list-with-espresso
     */
    private static Matcher<View> withListSize(final int size) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(final View view) {
                return ((ListView) view).getCount() == size;
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("ListView should have " + size + " items");
            }
        };
    }

    public void checkElements(List<String> expected) {
        for (int i = 0; i < expected.size(); i++) {
            onData(anything())
                    .inAdapterView(withId(id()))
                    .atPosition(i)
                    .check(matches(withText(expected.get(i))));
        }
        onView(withId(id())).check(matches(withListSize(expected.size())));
    }

    public void select(String itemText) {
        onData(new ContainsMatcher(itemText)).perform(click());
    }
}