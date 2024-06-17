package wordle.domain;

public class Attempt {
    private final Round current;
    private final Round last;

    public Attempt(final int current, final int last) {
        this(new Round(current), new Round(last));
    }

    public Attempt(final Round current, final Round last) {
        validate(current, last);
        this.current = current;
        this.last = last;
    }

    private static void validate(final Round current, final Round last) {
        if(current.isGreaterThan(last)) {
            throw new IllegalArgumentException("current should be less than or equal to last");
        }
    }

    public boolean isNotFinished() {
        return current.isLessThan(last);
    }

    public Attempt next() {
        return new Attempt(current.next(), last);
    }

    public int current() {
        return current.round();
    }

    public int last() {
        return last.round();
    }
}
