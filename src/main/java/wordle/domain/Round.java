package wordle.domain;

public class Round {
    private static final int INCREMENT_UNIT = 1;
    private static final int MINIMUM_ROUND = 0;

    private final int round;

    public Round(final int round) {
        validate(round);
        this.round = round;
    }

    private static void validate(final int round) {
        if (round < MINIMUM_ROUND) {
            throw new IllegalArgumentException("current should be less than or equal to last");
        }
    }

    public boolean isGreaterThan(final Round last) {
        return this.round > last.round;
    }

    public boolean isLessThan(final Round last) {
        return this.round < last.round;
    }

    public Round next() {
        return new Round(Math.addExact(round, INCREMENT_UNIT));
    }

    public int round() {
        return round;
    }
}
