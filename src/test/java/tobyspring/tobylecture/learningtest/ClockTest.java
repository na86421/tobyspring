package tobyspring.tobylecture.learningtest;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;

public class ClockTest {
    // Clock을 이용해서 LocalDateTIme.now?
    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime now2 = LocalDateTime.now(clock);

        assertThat(now2).isAfter(now);
    }

    // Clock을 Test에서 사용할 때 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
    @Test
    void fixedClock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime now2 = LocalDateTime.now(clock);

        assertThat(now2).isEqualTo(now);
    }
}
