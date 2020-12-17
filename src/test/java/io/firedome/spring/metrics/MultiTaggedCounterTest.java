package io.firedome.spring.metrics;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MultiTaggedCounterTest {

    @Test
    public void multiTaggedCounterTest() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        MultiTaggedCounter multiTaggedCounter = new MultiTaggedCounter("sheep", registry, "color", "age-group");
        multiTaggedCounter.increment("black", "young");
        multiTaggedCounter.increment("black", "young");
        multiTaggedCounter.increment("white", "young");
        multiTaggedCounter.increment("black", "old");
        multiTaggedCounter.increment("black", "toddler");
        multiTaggedCounter.increment("black", "old");
        multiTaggedCounter.increment("white", "toddler");
        multiTaggedCounter.increment("brown", "adult");

        assertEquals(6, registry.getMeters().size());
    }

    @Test
    public void multiTaggedCounterIllegalArgTest() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        MultiTaggedCounter multiTaggedCounter = new MultiTaggedCounter("sheep", registry, "color", "age-group");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            multiTaggedCounter.increment("black");
        });
    }

}