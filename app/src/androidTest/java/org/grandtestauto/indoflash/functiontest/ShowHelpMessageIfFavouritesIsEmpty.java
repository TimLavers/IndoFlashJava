package org.grandtestauto.indoflash.functiontest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * See Issue #5.
 *
 * @author Tim Lavers
 */
@RunWith(AndroidJUnit4.class)
public class ShowHelpMessageIfFavouritesIsEmpty extends TestBase {

    @Test
    public void doIt() {
        showFavourites();

        ui.checkShowsEmptyFavouritesMessage();
        ui.addOrRemoveFavouriteButton().checkIsInactive();
        ui.nextButton().checkText("");
        ui.nextButton().checkIsInactive();
    }
}