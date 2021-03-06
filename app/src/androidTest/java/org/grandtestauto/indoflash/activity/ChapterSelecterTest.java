package org.grandtestauto.indoflash.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.testui.ChapterListSelecterProxy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class ChapterSelecterTest {

    @Rule
    public ActivityTestRule<ChapterSelecter> activityRule = new ActivityTestRule<>(ChapterSelecter.class);

    @Test
    public void ensureChaptersShow() throws Exception {
        List<String> expectedChapters = new ArrayList<>(6);
        expectedChapters.add("Lessons 1 - 10");
        expectedChapters.add("Lessons 11 - 20");
        expectedChapters.add("Lessons 21 - 30");
        expectedChapters.add("Lessons 31 - 40");
        expectedChapters.add("Lessons 41 - 50");
        expectedChapters.add("Lessons 51 - 57");

        new ChapterListSelecterProxy().checkElements(expectedChapters);
    }
}