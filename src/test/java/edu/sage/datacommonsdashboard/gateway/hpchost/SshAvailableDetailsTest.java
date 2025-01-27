package edu.sage.datacommonsdashboard.gateway.hpchost;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SshAvailableDetailsTest {

    @Test
    public void given_all_details__when_build__then_return_full_details() {

        // setup and execute
        SshAvailableDetails details = SshAvailableDetails.of(b -> b.setHostname("hostname")
                .setUsername("username")
                .setPort(100)
                .setHostKey("hostkey")
                .setExpectedPrompt("expectedprompt"));

        assertThat(details.getHostname(), is("hostname"));
        assertThat(details.getUsername(), is("username"));
        assertThat(details.getPort(), is(100));
        assertThat(details.getHostKey(), is("hostkey"));
        assertThat(details.getExpectedPrompt(), is("expectedprompt"));
    }

    @Test
    public void given_default_port__when_build__then_port_equals_22() {

        // setup and execute
        SshAvailableDetails details = SshAvailableDetails.of(b -> b.setHostname("hostname")
                .setUsername("username")
                .setHostKey("hostkey"));

        // assert
        assertThat(details.getPort(), is(22));
    }

    @Test
    public void given_missing_hostname__when_build__then_expect_exception() {

        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> SshAvailableDetails.of(b -> b)
        );

        assertThat(e.getMessage(), is("hostname cannot be null."));
    }

    @Test
    public void given_missing_username__when_build__then_expect_exception() {

        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> SshAvailableDetails.of(b -> b.setHostname("hostname"))
        );

        assertThat(e.getMessage(), is("username cannot be null."));
    }

    @Test
    public void given_missing_hostkey__when_build__then_expect_exception() {

        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> SshAvailableDetails.of(b -> b.setHostname("hostname")
                        .setUsername("username"))
        );

        assertThat(e.getMessage(), is("hostKey cannot be null."));
    }
}