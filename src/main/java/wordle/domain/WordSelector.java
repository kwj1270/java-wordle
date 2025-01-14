package wordle.domain;

import java.util.List;

@FunctionalInterface
public interface WordSelector {
    Word select(final List<? extends Word> wordList);
}
