package com.venachain.utils;

import java.io.IOException;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.venachain.utils.Version.DEFAULT;
import static com.venachain.utils.Version.getTimestamp;
import static com.venachain.utils.Version.getVersion;

public class VersionTest {

    @Test
    public void testGetVersion() throws IOException {
        assertThat(getVersion(), is(DEFAULT));
    }

    @Test
    public void testGetTimestamp() throws IOException {
        assertThat(getTimestamp(), is("2017-01-31 01:21:09.843 UTC"));
    }
}
