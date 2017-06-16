package org.grandtestauto.indoflash.functiontest;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.MonitoringInstrumentation;

import org.grandtestauto.indoflash.ChapterSpec;
import org.grandtestauto.indoflash.IndoFlash;
import org.grandtestauto.indoflash.WordListDisplay;
import org.grandtestauto.indoflash.testui.WordListDisplayProxy;
import org.grandtestauto.indoflash.testui.WordListSelectorProxy;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Tim Lavers
 */
public class TestBase {
    @Rule
    public ActivityTestRule<WordListDisplay> activityRule = new ActivityTestRule<>(WordListDisplay.class);

    @After
    public void cleanup() {
        IndoFlash application = (IndoFlash) activityRule.getActivity().getApplication();
        ChapterSpec chapter1Spec = application.applicationSpec().chapterSpecs().get(0);
        application.setCurrentChapter(chapter1Spec);
        application.setWordList(chapter1Spec.wordLists().get(0));

//        ui.finish();
//        activity.finish();
        application.clearFavourites();
        if (application.shuffle()) {
            application.toggleShuffle();
        }
        if (application.showIndonesianFirst()) {
            application.toggleShowIndonesianFirst();
        }

    }

}
