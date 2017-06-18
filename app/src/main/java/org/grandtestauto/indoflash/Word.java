package org.grandtestauto.indoflash;


/**
 * @author TimL
 */
class Word {
    private String word;
    private String definition;

    Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public String word() {
        return word;
    }

    String definition() {
        return definition;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equalsIgnoreCase(word1.word);

    }

    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return word + "=" + definition;
    }
}
