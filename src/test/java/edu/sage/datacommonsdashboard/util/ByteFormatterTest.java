package edu.sage.datacommonsdashboard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteFormatterTest {

    @Test
    @DisplayName("Test formatting for KB")
    void testFormattingKB() {
        assertEquals("1 KB", ByteFormatter.formatBytes(1024), "Expected '1 KB' for input 1024");
        assertEquals("10 KB", ByteFormatter.formatBytes(10 * 1024), "Expected '10 KB' for input 10 * 1024");
        assertEquals("512 KB", ByteFormatter.formatBytes(1024 * 512), "Expected '512 KB' for input 512 * 1024");
    }

    @Test
    @DisplayName("Test formatting for MB")
    void testFormattingMB() {
        assertEquals("1 MB", ByteFormatter.formatBytes(1024 * 1024), "Expected '1 MB' for input 1024 * 1024");
        assertEquals("128 MB", ByteFormatter.formatBytes(134_217_728), "Expected '128 MB' for input 134,217,728");
        assertEquals("1 GB", ByteFormatter.formatBytes(1024L * 1024 * 1024), "Expected '1024 MB' for input 1024 * 1024 * 1024");
    }

    @Test
    @DisplayName("Test formatting for GB")
    void testFormattingGB() {
        assertEquals("1 GB", ByteFormatter.formatBytes(1024L * 1024 * 1024), "Expected '1 GB' for input 1024^3 bytes");
        assertEquals("1 TB", ByteFormatter.formatBytes(1_099_511_627_776L), "Expected '1024 GB' for input 1024^4 bytes");
        assertEquals("10 GB", ByteFormatter.formatBytes(10L * 1024 * 1024 * 1024), "Expected '10 GB' for input 10 * 1024^3 bytes");
    }

    @Test
    @DisplayName("Test formatting for TB")
    void testFormattingTB() {
        assertEquals("1 TB", ByteFormatter.formatBytes(1024L * 1024 * 1024 * 1024), "Expected '1 TB' for input 1024^4 bytes");
        assertEquals("2 TB", ByteFormatter.formatBytes(2L * 1024 * 1024 * 1024 * 1024), "Expected '2 TB' for input 2 * 1024^4 bytes");
    }

    @Test
    @DisplayName("Test edge cases")
    void testEdgeCases() {
        assertEquals("1 KB", ByteFormatter.formatBytes(1024), "Edge case where bytes equal exactly 1024 should return '1 KB'");
        assertEquals("1 MB", ByteFormatter.formatBytes(1024 * 1024), "Edge case where bytes equal exactly 1024 * 1024 should return '1 MB'");
        assertEquals("1 GB", ByteFormatter.formatBytes(1024L * 1024 * 1024), "Edge case where bytes equal exactly 1024^3 should return '1 GB'");
        assertEquals("1 TB", ByteFormatter.formatBytes(1024L * 1024 * 1024 * 1024), "Edge case where bytes equal exactly 1024^4 should return '1 TB'");
    }
}

