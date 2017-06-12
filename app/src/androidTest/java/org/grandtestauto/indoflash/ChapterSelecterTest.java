package org.grandtestauto.indoflash;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.testui.ListViewProxy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;


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

        new ListViewProxy(R.id.chapters_list).checkElements(expectedChapters);
    }
}