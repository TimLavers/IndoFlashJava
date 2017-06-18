package org.grandtestauto.indoflash.functiontest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import org.grandtestauto.indoflash.ChapterSpec;
import org.grandtestauto.indoflash.IndoFlash;
import org.grandtestauto.indoflash.WordListDisplay;
import org.grandtestauto.indoflash.testui.WordListDisplayProxy;
import org.grandtestauto.indoflash.testui.WordListSelecterProxy;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Tim Lavers
 */
public class TestBase {
    @Rule
    public ActivityTestRule<WordListDisplay> activityRule = new ActivityTestRule<>(WordListDisplay.class);
    WordListDisplayProxy ui;

    @Before
    public void setup() {
        ui = new WordListDisplayProxy();
    }

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

    void closeApplication() {
        activityRule.getActivity().finish();
    }

    void restartApplication() {
        activityRule.launchActivity(new Intent());
        ui = new WordListDisplayProxy();
    }

    void showFavourites() {
        WordListSelecterProxy wordListSelectorProxy = ui.activateWordListSelectorButton();
        wordListSelectorProxy.selectFavourites();
    }

    @NonNull
    List<CharSequence> firstFiveWordsShowing() {
        List<CharSequence> wordsShown = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            wordsShown.add(ui.currentWord());
            ui.showDefinitionOfCurrentWord();
            ui.activateNextButton();
        }
        return wordsShown;
    }

    @NonNull
    List<CharSequence> firstFiveWordsUnshuffled() {
        List<CharSequence> wordsInOrder = new LinkedList<>();
        wordsInOrder.add("you");
        wordsInOrder.add("what");
        wordsInOrder.add("How are you?");
        wordsInOrder.add("good");
        wordsInOrder.add("fine");
        return wordsInOrder;
    }
}