package wordle.domain;

import java.util.List;
import java.util.Objects;

public class Word {
    private static final int MINIMUM_INDEX = 0;
    private static final int WORD_SIZE = 5;
    private final List<Alphabet> alphabets;

    private static List<Alphabet> alphabets(final String word) {
        return word.toLowerCase().chars()
                .mapToObj(it -> (char) it)
                .map(Alphabet::of)
                .toList();
    }


    public Word(final String word) {
        this(alphabets(word));
    }

    public Word(final List<Alphabet> alphabets) {
        if (alphabets.size() != WORD_SIZE) {
            throw new IllegalArgumentException("단어는 5글자의 소문자 알파벳으로 이루어져야 합니다");
        }
        this.alphabets = alphabets;
    }

    public int size() {
        return alphabets.size();
    }

    public Alphabet find(final int index) {
        if (index < MINIMUM_INDEX || index >= alphabets.size()) {
            throw new IllegalArgumentException("단어의 길이보다 작거나 같은 인덱스만 들어올 수 있습니다");
        }
        return alphabets.get(index);
    }

    public long countAlphabets(final Alphabet alphabet, final int toIndex) {
        if (toIndex > alphabets.size()) {
            throw new IllegalArgumentException("단어의 길이보다 작거나 같은 인덱스만 들어올 수 있습니다");
        }
        return alphabets.subList(MINIMUM_INDEX, toIndex)
                .stream()
                .filter(it -> it == alphabet)
                .count();
    }

    public boolean isSameAs(final String word) {
        return alphabets.equals(alphabets(word));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(alphabets, word.alphabets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alphabets);
    }
}
