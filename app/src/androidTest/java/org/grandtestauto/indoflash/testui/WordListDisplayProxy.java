package org.grandtestauto.indoflash.testui;

import org.grandtestauto.indoflash.R;

import static android.support.test.espresso.action.ViewActions.click;
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

    public void addCurrentWordAsFavourite() {
        favouritesProxy.activate();
    }

    public void showDefinitionOfCurrentWord() {
        checkTranslationIsEmpty();
        activateNextButton();
    }

    public WordListSelectorProxy activateWordListSelectorButton() {
        wordListsButtonProxy.activate();
        return new WordListSelectorProxy(R.id.lists_list);
    }
}