package edu.sage.datacommonsdashboard.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MemParser {

    public static double parseMemToBytes(String mem) {

        if (mem == null) {
            throw new IllegalArgumentException("Input memory string cannot be null");
        }

        // Regular expression to match numeric value and unit
        Pattern pattern = Pattern.compile("(\\d+(?:\\.\\d+)?)\\s*(kb|mb|gb|tb)", Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(mem);

        if (matcher.matches()) {
            // Extract the numeric part
            double value = Double.parseDouble(matcher.group(1));

            // Extract the unit (kb, mb, gb, tb)
            String unit = matcher.group(2).toLowerCase();

            // Convert units to Bytes
            switch (unit) {
                case "tb": // 1 TB = 1024 * 1024 * 1024 * 1024 Bytes
                    return value * 1024 * 1024 * 1024 * 1024;
                case "gb": // 1 GB = 1024 * 1024 * 1024 Bytes
                    return value * 1024 * 1024 * 1024;
                case "mb": // 1 MB = 1024 * 1024 Bytes
                    return value * 1024 * 1024;
                case "kb": // 1 KB = 1024 Bytes
                    return value * 1024;
                default:
                    throw new IllegalArgumentException("Unexpected memory unit: " + unit);
            }
        } else {
            throw new IllegalArgumentException("Invalid memory format: " + mem);
        }
    }

}