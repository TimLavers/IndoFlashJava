package org.grandtestauto.indoflash.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.testui.WordListDisplayProxy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class WordListDisplayTest {
    @Rule
    public ActivityTestRule<WordListDisplay> activityRule = new ActivityTestRule<>(WordListDisplay.class);

    @Test
    public void testOnCreate() {
        WordListDisplayProxy wld = new WordListDisplayProxy();
        wld.checkWordViewIsShowing();
        wld.checkCurrentWordIs("you");
        wld.checkTranslationIsEmpty();
        wld.checkFavouritesButtonIsShowing();
        wld.checkNextButtonIsShowing();
        wld.checkWordListsButtonIsShowing();
    }

    @Test
    public void testActivateNext() {
        WordListDisplayProxy wld = new WordListDisplayProxy();
        wld.checkCurrentWordIs("you");
        wld.checkTranslationIsEmpty();

        wld.activateNextButton();
        wld.checkCurrentWordIs("you");
        wld.checkTranslationIs("anda");

        wld.activateNextButton();
        wld.checkCurrentWordIs("what");
        wld.checkTranslationIsEmpty();

        wld.activateNextButton();
        wld.checkCurrentWordIs("what");
        wld.checkTranslationIs("apa");
    }
}