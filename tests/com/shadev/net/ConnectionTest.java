package com.shadev.net;

import com.shadev.chat.ChatEventHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.BindException;
import java.rmi.server.ExportException;

import static org.junit.Assert.*;

public class ConnectionTest {

    private Connection c;
    private ChatEventHandler ceh;

    @Before
    public void setUp() throws Exception {
        ceh = new ChatEventHandler();
        c = new Connection(ceh);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalConnect() throws Exception {
        c.connect("localhost", 99999999);
    }

    @Test
    public void testRole() throws Exception {
        Assert.assertEquals("Client", c.getRole());
    }
}