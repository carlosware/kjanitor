package com.carlosware.kjanitor.configuration.removal;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

import com.carlosware.kjanitor.configuration.BaseConfigurationDataTest;

import java.util.List;
import org.junit.Test;

public class RemovalConfigurationTest extends BaseConfigurationDataTest {
    @Test
    public void removalConfigurationTest() {
        List<String> topicsToRemove = generateStringList(generateRandomInteger());
        RemovalConfiguration removalConfiguration = createRemovalConfiguration(topicsToRemove);

        assertEquals(topicsToRemove.size(), removalConfiguration.getTopics().size());
        assertEquals(topicsToRemove, removalConfiguration.getTopics());
    }

    @Test(expected = NullPointerException.class)
    public void removalConfigurationNullTopicsTest() {
        RemovalConfiguration.from(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removalConfigurationNoTopicsTest() {
        RemovalConfiguration.from(emptyList());
    }
}
