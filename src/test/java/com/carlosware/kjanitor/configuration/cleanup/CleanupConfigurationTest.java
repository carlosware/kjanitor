package com.carlosware.kjanitor.configuration.cleanup;

import static org.junit.Assert.assertEquals;

import com.carlosware.kjanitor.configuration.BaseConfigurationDataTest;
import java.util.List;
import org.junit.Test;

public class CleanupConfigurationTest extends BaseConfigurationDataTest {
    @Test
    public void cleanupConfigurationTest() {
        List<String> topicsToPreserve = generateStringList(generateRandomInteger());
        CleanupConfiguration cleanupConfiguration = createCleanupConfiguration(topicsToPreserve);

        assertEquals(topicsToPreserve.size(), cleanupConfiguration.getToPreserve().size());
        assertEquals(topicsToPreserve, cleanupConfiguration.getToPreserve());
    }

    @Test(expected = NullPointerException.class)
    public void cleanupConfigurationCreationExceptionTest() {
        CleanupConfiguration.from(null);
    }
}
