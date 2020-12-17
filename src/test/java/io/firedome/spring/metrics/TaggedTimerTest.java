package io.firedome.spring.metrics;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TaggedTimerTest {

    @Test
    public void taggedTimerTest(){
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        TaggedTimer taggedTimer = new TaggedTimer("some-timer", "action", registry);
        taggedTimer.getTimer("walk-the-dog").record(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        taggedTimer.getTimer("make-dinner").record(30, TimeUnit.MINUTES);
        taggedTimer.getTimer("homework").record(2, TimeUnit.HOURS);
        taggedTimer.getTimer("walk-the-dog").record(10, TimeUnit.MINUTES);
        taggedTimer.getTimer("walk-the-dog").record(15, TimeUnit.MINUTES);
        List<Meter> meters = registry.getMeters();
        assertEquals(3, meters.size());
    }

}