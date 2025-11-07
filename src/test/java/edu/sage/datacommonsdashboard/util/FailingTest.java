package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FailingTest {

    @Test
    @DisplayName("Test that this test fails")
    void testThatThisTestFails() {
        assertEquals(true, false, "true should be equal to true");
    }
}
