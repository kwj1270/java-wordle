package wordle.domain;

import java.time.*;
import java.util.List;

public class TimeBaseAnswerSelector implements Selector {

    private static final LocalDate BASE_LOCAL_DATE = LocalDate.of(2021, 6, 19);
    private final LocalDate localDate;

    public TimeBaseAnswerSelector(final LocalDate localDate) {
        this.localDate = localDate;
    }

    public Word select(List<Word> wordList) {
        if (wordList.isEmpty()) {
            throw new RuntimeException();
        }
        final long epochSecond = localDate.toEpochSecond(LocalTime.of(0, 0), ZoneOffset.UTC);
        final long baseEpochSecond = BASE_LOCAL_DATE.toEpochSecond(LocalTime.of(0, 0), ZoneOffset.UTC);
        final long timeDifference = Math.subtractExact(epochSecond, baseEpochSecond);
        if (timeDifference < 0) {
            throw new RuntimeException();
        }

        final int index = (int) timeDifference % wordList.size();
        return wordList.get(index);
    }
}