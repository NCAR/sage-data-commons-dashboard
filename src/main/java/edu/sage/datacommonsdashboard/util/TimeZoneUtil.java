package edu.sage.datacommonsdashboard.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeZoneUtil {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";

    public String convertTimestampToFormattedDate(Integer timestampSeconds, ZoneId zoneId) {

        // Timestamp is in seconds, needs converting to millis
        Instant instant = Instant.ofEpochMilli(timestampSeconds * 1000L);

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        return formatter.format(zonedDateTime);
    }
}
