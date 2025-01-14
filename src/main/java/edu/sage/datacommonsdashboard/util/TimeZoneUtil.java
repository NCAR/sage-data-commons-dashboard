package edu.sage.datacommonsdashboard.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneUtil {

    public String getNowInDenverTimeZoneAbbreviation() {

        // Get the current date and time in Denver's time zone
        ZonedDateTime nowInDenver = ZonedDateTime.now(ZoneId.of("America/Denver"));

        /// Use the DateTimeFormatter with the z pattern to get the abbreviation
        // String timeZoneName = nowInDenver.getZone().getId();  // Always "America/Denver"
        String abbreviation = nowInDenver.format(java.time.format.DateTimeFormatter.ofPattern("z"));

        return abbreviation;
    }

    public String getTimeZoneAbbreviation(ZonedDateTime dateTime, ZoneId zoneId) {

        // Create a ZonedDateTime in the specified ZoneId
        ZonedDateTime zonedDateTime = dateTime.withZoneSameInstant(zoneId);

        // Use the DateTimeFormatter to get the abbreviation
        String abbreviation = zonedDateTime.format(java.time.format.DateTimeFormatter.ofPattern("z"));

        return abbreviation;
    }

    public String convertTimestampToDateString(Integer timestamp) {

        // Timestamp is in seconds, needs convering to millis
        Instant instant = Instant.ofEpochMilli(timestamp * 1000L);

        // Convert the Instant to a ZonedDateTime in the Mountain Time Zone
        ZonedDateTime mountainTime = ZonedDateTime.ofInstant(instant, ZoneId.of("America/Denver"));

        // Format the ZonedDateTime for readability
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        return formatter.format(mountainTime);
    }
}
