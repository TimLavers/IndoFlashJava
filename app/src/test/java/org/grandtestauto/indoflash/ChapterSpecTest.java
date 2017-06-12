package org.grandtestauto.indoflash;

import android.test.AndroidTestCase;
import org.grandtestauto.indoflash.ChapterSpec;
import org.grandtestauto.indoflash.WordListSpec;

public class ChapterSpecTest extends AndroidTestCase {

    public static ChapterSpec chapterSpec(String title, WordListSpec... wordListSpecs) {
        ChapterSpec result = new ChapterSpec(title);
        for (WordListSpec wls : wordListSpecs) result.wordLists().add(wls);
        return result;
    }
}
