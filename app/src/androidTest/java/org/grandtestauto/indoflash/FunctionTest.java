package org.grandtestauto.indoflash;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.grandtestauto.indoflash.testui.UIWordListDisplay;
import org.grandtestauto.indoflash.testui.UIWordListSelecter;
import org.junit.Before;

import java.util.concurrent.TimeUnit;

/**
 * Base class for multi-activity functional tests.
 *
 * @author Tim Lavers
 */
public abstract class FunctionTest extends ActivityInstrumentationTestCase2<WordListDisplay> {

    UIWordListDisplay ui;

    public FunctionTest() {
        super(WordListDisplay.class);
    }

    public void test() {
        setup();


        //Run the actual test.
        doIt();

        //Get the application back to a known state.
        IndoFlash application = (IndoFlash) ui.activity().getApplication();
        ChapterSpec chapter1Spec = application.applicationSpec().chapterSpecs().get(0);
        application.setCurrentChapter(chapter1Spec);
        application.setWordList(chapter1Spec.wordLists().get(0));

        ui.finish();
        application.clearFavourites();
        if (application.shuffle()) {
            application.toggleShuffle();
        }
        if (application.showIndonesianFirst()) {
            application.toggleShowIndonesianFirst();
        }

        //Cleanup.
        setActivity(null);
        pause(5);
    }

    @Before
     void setup() {
        //Launch the main activity, which is WordListDisplay.
        ui = new UIWordListDisplay(this);

        ui.launch();
    }

    abstract void doIt();

    public void debug(String message) {
        Log.d("IndoFlashTest", message);
    }

    public static void pause(int seconds) {
        Log.d("IndoFlashTest", "Sleeping for " + seconds + " seconds...");
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("IndoFlashTest", "Resuming after pause...");
    }

    protected void openFavouritesList() {
        UIWordListSelecter uiWordListSelecter = ui.showWordLists();
        ui = uiWordListSelecter.selectFavourites();
    }
}
