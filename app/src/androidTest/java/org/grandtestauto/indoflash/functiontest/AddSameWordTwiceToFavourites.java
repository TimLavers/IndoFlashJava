package org.grandtestauto.indoflash.functiontest;

import android.support.test.runner.AndroidJUnit4;

import org.grandtestauto.indoflash.testui.WordListDisplayProxy;
import org.grandtestauto.indoflash.testui.WordListSelectorProxy;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * See Issue #2.
 *
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class AddSameWordTwiceToFavourites extends TestBase {

    @Test
    public void doIt() {
        WordListDisplayProxy ui = new WordListDisplayProxy();
        ui.checkCurrentWordIs("you");//Sanity check.

        ui.addCurrentWordAsFavourite();

        ui.checkCurrentWordIs("you");//Sanity check that we are about to add the same word again to the favourites.

        ui.addCurrentWordAsFavourite();

        ui.showDefinitionOfCurrentWord();
        ui.activateNextButton();

        ui.checkCurrentWordIs("what");//Sanity check that we added "you/anda" twice as a favourite.

        ui.addCurrentWordAsFavourite();//So that two distinct words have been added as favourites.

        WordListSelectorProxy wordListSelectorProxy = ui.activateWordListSelectorButton();
        wordListSelectorProxy.selectFavourites();

        ui.checkCurrentWordIs("you");

        ui.checkTranslationIsEmpty();

        ui.showDefinitionOfCurrentWord();

        ui.checkTranslationIs("anda");

        ui.activateNextButton();

        ui.checkCurrentWordIs("what");//Not "you/anda" even though it was added twice.
    }
}