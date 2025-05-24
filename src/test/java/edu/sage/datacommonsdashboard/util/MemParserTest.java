package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MemParserTest {

    @Test
    @DisplayName("Test valid memory formats")
    void testValidMemoryConversions() {
        // Test terabytes (TB)
        assertEquals(1_099_511_627_776L, MemParser.parseMemToBytes("1 TB"), "1 TB should equal 1,099,511,627,776 bytes");

        // Test gigabytes (GB)
        assertEquals(107_374_182_400L, MemParser.parseMemToBytes("100 GB"), "100 GB should equal 107,374,182,400 bytes");

        // Test megabytes (MB)
        assertEquals(134_217_728L, MemParser.parseMemToBytes("128 MB"), "128 MB should equal 134,217,728 bytes");

        // Test kilobytes (KB)
        assertEquals(1024L, MemParser.parseMemToBytes("1 KB"), "1 KB should equal 1,024 bytes");
    }

    @Test
    @DisplayName("Test invalid inputs")
    void testInvalidInputs() {
        // Invalid cases
        assertThrows(IllegalArgumentException.class, () -> MemParser.parseMemToBytes("50 XB"), "Unexpected memory unit should throw exception");
        assertThrows(IllegalArgumentException.class, () -> MemParser.parseMemToBytes("Memory"), "Non-memory format should throw exception");
        assertThrows(IllegalArgumentException.class, () -> MemParser.parseMemToBytes(" "), "Empty string should throw exception");
    }

    @Test
    @DisplayName("Test null")
    void testNullInput() {

        assertThrows(IllegalArgumentException.class, () -> MemParser.parseMemToBytes(null), "Null input should throw exception");
    }

    @Test
    @DisplayName("Test byte values")
    void testByteValues() {
        // Test explicit byte values
        assertEquals(120L, MemParser.parseMemToBytes("120b"), "120b should equal 120 bytes");
        assertEquals(120L, MemParser.parseMemToBytes("120B"), "120B should equal 120 bytes (case insensitive)");
        assertEquals(1000L, MemParser.parseMemToBytes("1000b"), "1000b should equal 1000 bytes");

        // Test with spaces
        assertEquals(500L, MemParser.parseMemToBytes("500 b"), "500 b should equal 500 bytes");
        assertEquals(250L, MemParser.parseMemToBytes("250 B"), "250 B should equal 250 bytes");

        // Test decimal values
        assertEquals(125L, MemParser.parseMemToBytes("124.6b"), "124.6b should round to 125 bytes");
    }

}
