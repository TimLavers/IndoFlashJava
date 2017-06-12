package org.grandtestauto.indoflash.testui;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.Assert;
import org.grandtestauto.indoflash.R;
import org.grandtestauto.indoflash.UIActivity;
import org.grandtestauto.indoflash.WordListDisplay;
import org.grandtestauto.indoflash.WordListSelecter;
//import org.jetbrains.annotations.NotNull;

public class UIWordListDisplay extends UIActivity {
    public static final String LOG_ID = "IndoFlashTest:UIWordListDisplay";


    public UIWordListDisplay(InstrumentationTestCase testCase, Instrumentation.ActivityMonitor monitor) {
        super(testCase);
        setMonitor(monitor);
        setActivity(instrumentation().waitForMonitorWithTimeout(monitor(), 5));
    }

    public UIWordListDisplay(InstrumentationTestCase testCase) {
        super(testCase);
    }

    public void launch() {
        setMonitor(instrumentation().addMonitor(WordListDisplay.class.getName(), null, false));
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(instrumentation().getTargetContext(), WordListDisplay.class.getName());
        instrumentation().startActivitySync(intent);
        setActivity(instrumentation().waitForMonitorWithTimeout(monitor(), 5));
    }

    @Override
    public Class activityClass() {
        return WordListDisplay.class;
    }

    public TextView wordView() {
        return (TextView) activity().findViewById(R.id.word_view);
    }

    public TextView definitionView() {
        return (TextView) activity().findViewById(R.id.definition_view);
    }

    public TextView titleView() {
        return (TextView) activity().findViewById(R.id.word_list_title_view);
    }

    public CharSequence title() {
        return titleView().getText();
    }

    public View addRemoveFavouriteButton() {
        return activity().findViewById(R.id.add_to_favourites_button);
    }

    public void activateRemoveFavouriteButton() {
        clickView(addRemoveFavouriteButton());
    }

    public View indonesianOrEnglishFirstButton() {
        return activity().findViewById(R.id.indonesian_first_button);
    }

    public void activateToggleIndonesianButton() {
        clickView(indonesianOrEnglishFirstButton());
    }

    public View shuffleOrUnshuffleButton() {
        return activity().findViewById(R.id.shuffle_button);
    }

    public Button nextButton() {
        return (Button) activity().findViewById(R.id.next_button);
    }

    public View showWordListsButton() {
        return activity().findViewById(R.id.show_word_lists_button);
    }

    public CharSequence currentWord() {
        return wordView().getText();
    }

    public CharSequence currentTranslation() {
        return definitionView().getText();
    }

    public void checkThatCurrentWordIs(CharSequence expected) {
        Assert.assertEquals(expected, currentWord());
    }

    public void checkThatCurrentTranslationIs(CharSequence expected) {
        Assert.assertEquals(expected, currentTranslation());
    }

    public void checkThatCurrentTranslationIsEmpty() {
        Assert.assertEquals("", currentTranslation());
    }

    public void addCurrentWordAsFavourite() {
        clickView(addRemoveFavouriteButton());
    }

    public void showDefinitionOfCurrentWord() {
        checkThatCurrentTranslationIs("");
        activateNextButton();
    }

    public void showNextWord() {
        Assert.assertTrue(currentWord().length() > 0);
        Assert.assertTrue(currentTranslation().length() > 0);
        activateNextButton();
    }

    public void repeatList() {
        Assert.assertTrue(currentWord().length() > 0);
        Assert.assertTrue(currentTranslation().length() > 0);
        Assert.assertEquals("Repeat list", nextButton().getText());
        activateNextButton();
    }

    public UIWordListSelecter showWordLists() {
        doClick(showWordListsButton(), WordListSelecter.class);
        return new UIWordListSelecter(testCase(), monitor());
    }

    public void toggleShuffle() {
        clickView(shuffleOrUnshuffleButton());
    }

    private void activateNextButton() {
        clickView(nextButton());
    }
}
