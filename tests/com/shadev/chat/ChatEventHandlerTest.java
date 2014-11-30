package com.shadev.chat;

import com.shadev.net.Connection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatEventHandlerTest {

    private ChatEventHandler ceh;
    private Connection c;

    @Before
    public void setUp() throws Exception {
        ceh = new ChatEventHandler();
        c = new Connection(ceh);
    }

    @Test
    public void testGetNumOfConnections() throws Exception {
        Assert.assertEquals(1, ceh.getNumOfConnections());
    }

    @Test
    public void testRegisterConnection() throws Exception {
        Assert.assertFalse(ceh.registerConnection(c));
    }

    @Test
    public void testConnected() throws Exception {
        Assert.assertFalse(ceh.connected());
    }
}