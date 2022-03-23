package com.venachain.utils;

import java.io.IOException;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VersionTest {

    @Test
    public void testGetVersion() throws IOException {
        assertThat(Version.getVersion(), is(Version.DEFAULT));
    }

    @Test
    public void testGetTimestamp() throws IOException {
        assertThat(Version.getTimestamp(), is("2017-01-31 01:21:09.843 UTC"));
    }
}
