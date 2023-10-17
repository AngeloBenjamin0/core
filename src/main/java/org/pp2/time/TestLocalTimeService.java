package org.pp2.time;

import java.time.LocalDateTime;

public class TestLocalTimeService implements LocalTimeService {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.of(2020, 1, 1, 10, 0);
    }
}
