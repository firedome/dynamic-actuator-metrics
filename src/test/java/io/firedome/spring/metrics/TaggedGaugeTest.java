package io.firedome.spring.metrics;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaggedGaugeTest {

    @Test
    public void taggedGaugeTest(){
        SimpleMeterRegistry registry = new SimpleMeterRegistry();
        TaggedGauge taggedGauge = new TaggedGauge("tide", "city", registry);
        taggedGauge.set("Halifax", 1.75);
        taggedGauge.set("Portland", 1.3);
        taggedGauge.set("Portland", 2);
        taggedGauge.set("Venice", 0.81);
        taggedGauge.set("Halifax", 1.8);
        taggedGauge.set("Halifax", 1.98);
        taggedGauge.set("Venice", 0.43);
        taggedGauge.set("Halifax", 1.86);
        taggedGauge.set("Portland", 2.4);
        taggedGauge.set("Venice", -0.09);

        assertEquals(3, registry.getMeters().size());
    }

}