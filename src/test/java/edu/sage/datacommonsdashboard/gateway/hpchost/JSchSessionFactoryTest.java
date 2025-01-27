package edu.sage.datacommonsdashboard.gateway.hpchost;

import com.jcraft.jsch.Session;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class JSchSessionFactoryTest {

    @Test
    public void given_session_attributes__when_create__then_session_returned() {

        // execute
        JSchSessionFactory factory = new JSchSessionFactory();
        Session session = factory.create("username", "host.name", 22);

        // assert
        assertThat(session.getUserName(), is("username"));
        assertThat(session.getHost(), is("host.name"));
        assertThat(session.getPort(), is(22));
    }

}