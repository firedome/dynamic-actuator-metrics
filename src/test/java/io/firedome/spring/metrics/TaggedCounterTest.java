package io.firedome.spring.metrics;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TaggedCounterTest {
    @Test
    public void testTaggedTimer() {
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        TaggedCounter taggedCounter = new TaggedCounter("sheep", "color", registry);
        taggedCounter.increment("black");
        taggedCounter.increment("white");
        taggedCounter.increment("white");
        taggedCounter.increment("black");
        taggedCounter.increment("brown");
        taggedCounter.increment("black");
        taggedCounter.increment("black");
        taggedCounter.increment("black");
        assertEquals(3, registry.getMeters().size());
    }

}