package org.grandtestauto.indoflash.testui;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import org.grandtestauto.indoflash.R;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class WordListDisplayProxy {
    private TextFieldProxy currentWordProxy = new TextFieldProxy(R.id.word_view);
    private TextFieldProxy translatedWordProxy = new TextFieldProxy(R.id.definition_view);
    private ButtonProxy nextButtonProxy = new ButtonProxy(R.id.next_button);
    private ButtonProxy favouritesProxy = new ButtonProxy(R.id.add_to_favourites_button);
    private ButtonProxy wordListsButtonProxy = new ButtonProxy(R.id.show_word_lists_button);

    public void checkCurrentWordIs(String expected) {
        currentWordProxy.checkText(expected);
    }

    public void checkShowsEmptyFavouritesMessage() {
        onView(withId(R.id.word_view)).check(matches(withText(R.string.favourites_is_empty)));
    }

    public void checkWordViewIsShowing() {
        currentWordProxy.checkIsShowing();
    }

    public void checkTranslationIsEmpty() {
        translatedWordProxy.checkThatTextIsEmpty();
    }

    public void checkTranslationIs(String expected) {
        translatedWordProxy.checkText(expected);
    }

    public void checkFavouritesButtonIsShowing() {
        favouritesProxy.checkIsShowing();
    }

    public void checkNextButtonIsShowing() {
        nextButtonProxy.checkIsShowing();
    }

    public void checkWordListsButtonIsShowing() {
        wordListsButtonProxy.checkIsShowing();
    }

    public void activateNextButton() {
        nextButtonProxy.activate();
    }

    public void addToOrRemoveFromFavourites() {
        favouritesProxy.activate();
    }

    public void showDefinitionOfCurrentWord() {
        checkTranslationIsEmpty();
        activateNextButton();
    }

    public void checkTitle(String expected) {
        new ViewProxy(R.id.word_list_title_view).checkText(expected);
    }

    public WordListSelecterProxy activateWordListSelectorButton() {
        wordListsButtonProxy.activate();
        return new WordListSelecterProxy();
    }

    public void toggleLanguage() {
        toggleIndonesianButton().activate();
    }

    public ButtonProxy nextButton() {
        return nextButtonProxy;
    }

    public ButtonProxy addOrRemoveFavouriteButton() {
        return new ButtonProxy(R.id.add_to_favourites_button);
    }

    public ButtonProxy toggleIndonesianButton() {
        return new ButtonProxy(R.id.indonesian_first_button);
    }

    public ButtonProxy shuffleUnshuffleButton() {
        return new ButtonProxy(R.id.shuffle_button);
    }


    /*
    From StackOverflow haffax
     */
    public CharSequence currentWord() {
        final CharSequence[] result = new CharSequence[1];
        onView(withId(R.id.word_view)).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public void perform(UiController uiController, View view) {
                result[0] = ((TextView) view).getText();
            }
        });
        return result[0];
    }

}