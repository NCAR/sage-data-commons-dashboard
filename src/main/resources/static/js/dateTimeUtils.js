function getCurrentTimeZoneAbbreviation() {

    const formatter = new Intl.DateTimeFormat('en-US', {
        timeZoneName: 'short' // Display short time zone name (e.g., "PST")
    });

    // Extract the time zone abbreviation from a formatted date
    const parts = formatter.formatToParts(new Date());
    const timeZoneAbbr = parts.find(part => part.type === 'timeZoneName').value;

    return timeZoneAbbr;
}
