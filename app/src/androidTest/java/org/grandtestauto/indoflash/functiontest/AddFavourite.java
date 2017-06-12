package org.grandtestauto.indoflash.functiontest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.FunctionTest;
import org.grandtestauto.indoflash.WordListDisplay;
import org.grandtestauto.indoflash.testui.WordListDisplayProxy;
import org.grandtestauto.indoflash.testui.WordListSelectorProxy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Check that the "Add to favourites" button works.
 *
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class AddFavourite {

    @Rule
    public ActivityTestRule<WordListDisplay> activityRule = new ActivityTestRule<>(WordListDisplay.class);

    @Test
    public void doIt() {
        WordListDisplayProxy ui = new WordListDisplayProxy();
        ui.checkCurrentWordIs("you");//Sanity check.

        ui.addCurrentWordAsFavourite();

        ui.showDefinitionOfCurrentWord();

        ui.checkTranslationIs("anda");

        ui.activateNextButton();

        ui.checkCurrentWordIs("what");

        ui.activateNextButton();

        WordListSelectorProxy wordListSelectorProxy = ui.activateWordListSelectorButton();
        wordListSelectorProxy.selectFavourites();

        ui.checkCurrentWordIs("you");//It was previously showing "what".

        ui.checkTranslationIsEmpty();//It was previously showing "anda".

        ui.showDefinitionOfCurrentWord();

        ui.checkTranslationIs("anda");//Check that the definition is carried along with a word.
    }
}
