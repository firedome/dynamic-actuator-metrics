package io.firedome.spring.metrics;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MultiTaggedTimerTest {

    @Test
    public void multiTaggedTimerTest() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        MultiTaggedTimer multiTaggedTimer = new MultiTaggedTimer("some-timer", registry, "who", "action");
        multiTaggedTimer.getTimer("Eric", "walk-the-dog").record(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        multiTaggedTimer.getTimer("Eric", "make-dinner").record(30, TimeUnit.MINUTES);
        multiTaggedTimer.getTimer("Benz", "make-dinner").record(30, TimeUnit.MINUTES);
        multiTaggedTimer.getTimer("Benz", "homework").record(2, TimeUnit.HOURS);
        multiTaggedTimer.getTimer("Eric", "walk-the-dog").record(10, TimeUnit.MINUTES);
        multiTaggedTimer.getTimer("Benz", "walk-the-dog").record(15, TimeUnit.MINUTES);
        List<Meter> meters = registry.getMeters();
        assertEquals(5, meters.size());
    }


    @Test
    public void multiTaggedTimerIllegalArgsTest() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        MultiTaggedTimer multiTaggedTimer = new MultiTaggedTimer("some-timer", registry, "who", "action");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            multiTaggedTimer.getTimer("walk-the-dog").record(800, TimeUnit.MINUTES);
        });
    }
}