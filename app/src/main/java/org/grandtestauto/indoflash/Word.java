package org.grandtestauto.indoflash;


import android.support.annotation.NonNull;

/**
 * A word to be learnt, together with its translation.
 *
 * @author Tim Lavers
 */
class Word {
    private
    @NonNull
    String word;
    private
    @NonNull
    String definition;

    Word(@NonNull String word, @NonNull String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String word() {
        return word;
    }

    String definition() {
        return definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        return word + "=" + definition;
    }
}