package org.pp2.time;

import java.time.LocalTime;

public class ProdLocalTimeService implements LocalTimeService{
    @Override
    public LocalTime now() {
        return LocalTime.now();
    }
}
