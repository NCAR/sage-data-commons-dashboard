package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// This test allows us to break the build to test notifications from GitHub
public class FailingTest {

    @Test
    @Disabled("When not in use")
    @DisplayName("Test that this test fails")
    void testThatThisTestFails() {
        assertEquals(true, false, "true should be equal to false");
    }
}
