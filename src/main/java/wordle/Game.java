package wordle;

import wordle.domain.*;
import wordle.view.InputView;
import wordle.view.OutputView;

public class Game {
    private static final int START_ATTEMPT = 0;
    private static final int MAX_ATTEMPT = 6;

    private final InputView inputView;
    private final OutputView outputView;
    private final WordListReader wordListReader;

    public Game(final InputView inputView,
                final OutputView outputView,
                final WordListReader wordListReader) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.wordListReader = wordListReader;
    }

    public void start(final WordSelector wordSelector) {
        final WordList wordList = wordListReader.read();
        final Answer answer = new Answer(wordList.select(wordSelector));
        outputView.welcome(MAX_ATTEMPT);
        play(wordList, answer, new Attempt(START_ATTEMPT, MAX_ATTEMPT), new Results());
    }

    private void play(final WordList wordList, final Answer answer, final Attempt attempt, final Results results) {
        if (attempt.isNotFinished() && !results.hasAnswer()) {
            final Guess guess = inputWord(wordList);
            final Result result = answer.examineResult(guess);
            final Results newResults = results.add(result);
            final Attempt newAttempt = attempt.next();
            outputView.showResults(newResults, newAttempt);
            play(wordList, answer, newAttempt, newResults);
        }
    }

    private Guess inputWord(final WordList wordList) {
        try {
            outputView.insertWord();
            final Word word = inputView.inputWord();
            return new Guess(wordList.find(word));
        } catch (final Exception e) {
            outputView.wrongWord();
            return inputWord(wordList);
        }
    }
}
