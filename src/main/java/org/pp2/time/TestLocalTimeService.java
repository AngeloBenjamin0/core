package org.pp2.time;

import java.time.LocalTime;

public class TestLocalTimeService implements LocalTimeService {
    @Override
    public LocalTime now() {
        return LocalTime.of(10, 0, 0);
    }
}
