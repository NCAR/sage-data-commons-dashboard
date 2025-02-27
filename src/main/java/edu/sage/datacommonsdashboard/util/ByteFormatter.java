package edu.sage.datacommonsdashboard.util;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class ByteFormatter {

    public static String formatBytes(double bytes) {

        DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Up to 2 decimal places
        if (bytes < 1024) {
            return decimalFormat.format(bytes) + " Bytes";
        } else if (bytes < 1024 * 1024) {
            return decimalFormat.format(bytes / 1024) + " KB";
        } else if (bytes < 1024 * 1024 * 1024) {
            return decimalFormat.format(bytes / (1024 * 1024)) + " MB";
        } else if (bytes < 1024L * 1024 * 1024 * 1024) {
            return decimalFormat.format(bytes / (1024 * 1024 * 1024)) + " GB";
        } else {
            return decimalFormat.format(bytes / (1024L * 1024 * 1024 * 1024)) + " TB";
        }
    }
}