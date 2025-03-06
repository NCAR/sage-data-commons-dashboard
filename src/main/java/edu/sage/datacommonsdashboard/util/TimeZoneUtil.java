package edu.sage.datacommonsdashboard.util;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimeZoneUtil {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z";

    public String convertTimestampToDateString(Integer timestamp, ZoneId zoneId) {

        // Timestamp is in seconds, needs converting to millis
        Instant instant = Instant.ofEpochMilli(timestamp * 1000L);

        // Convert the Instant to a ZonedDateTime in the Time Zone
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

        return formatter.format(zonedDateTime);
    }
}
