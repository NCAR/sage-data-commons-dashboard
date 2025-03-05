package edu.sage.datacommonsdashboard.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemParser {

    public static long parseMemToBytes(String mem) {

        if (mem == null) {
            throw new IllegalArgumentException("Input memory string cannot be null");
        }

        // Regular expression to match numeric value and unit
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*(kb|mb|gb|tb)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(mem);

        if (matcher.matches()) {
            // Extract the numeric part
            String numericValue = matcher.group(1);
            long value;

            if (numericValue.contains(".")) {
                // Handle fractional values by rounding them to the nearest whole number
                value = Math.round(Double.parseDouble(numericValue));
            } else {
                // Parse as a long if the number is already an integer
                value = Long.parseLong(numericValue);
            }

            // Extract the unit (kb, mb, gb, tb)
            String unit = matcher.group(2).toLowerCase();

            long bytes;

            // Convert units to Bytes
            switch (unit) {
                case "tb": // 1 TB = 1024 * 1024 * 1024 * 1024 Bytes
                    bytes = value * 1024L * 1024 * 1024 * 1024;
                    break;
                case "gb": // 1 GB = 1024 * 1024 * 1024 Bytes
                    bytes = value * 1024L * 1024 * 1024;
                    break;
                case "mb": // 1 MB = 1024 * 1024 Bytes
                    bytes = value * 1024L * 1024;
                    break;
                case "kb": // 1 KB = 1024 Bytes
                    bytes = value * 1024L;
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected memory unit: " + unit);
            }

            // Log the value to the console
            //System.out.println("Computed Bytes: " + bytes);

            return bytes;
        } else {
            throw new IllegalArgumentException("Invalid memory format: " + mem);
        }
    }
}