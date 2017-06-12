package org.grandtestauto.indoflash;


/** @author TimL */
public class Word {
    private String word;
    private String definition;

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String word() {
        return word;
    }

    public String definition() {
        return definition;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (!word.equalsIgnoreCase(word1.word)) return false;

        return true;
    }

    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return word + "=" + definition;
    }
}
