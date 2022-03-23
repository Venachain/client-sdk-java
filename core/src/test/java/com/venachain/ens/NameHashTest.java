package com.venachain.ens;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class NameHashTest {

    @Test
    public void testNameHash() {
        assertThat(NameHash.nameHash(""),
                is("0x0000000000000000000000000000000000000000000000000000000000000000"));

        assertThat(NameHash.nameHash("eth"),
                is("0x93cdeb708b7545dc668eb9280176169d1c33cfd8ed6f04690a0bcc88a93fc4ae"));

        assertThat(NameHash.nameHash("foo.eth"),
                is("0xde9b09fd7c5f901e23a3f19fecc54828e9c848539801e86591bd9801b019f84f"));
    }

    @Test
    public void testNormalise() {
        assertThat(NameHash.normalise("foo"), is("foo"));
        assertThat(NameHash.normalise("foo.bar.baz.eth"), is("foo.bar.baz.eth"));
        assertThat(NameHash.normalise("fOo.eth"), is("foo.eth"));
        assertThat(NameHash.normalise("foo-bar.eth"), is("foo-bar.eth"));
    }

    @Test
    public void testNormaliseInvalid() {
        testInvalidName("foo..bar");
        testInvalidName("ba\\u007Fr.eth");
        testInvalidName("-baz.eth-");
        testInvalidName("foo_bar.eth");
    }

    private void testInvalidName(String ensName) {
        try {
            NameHash.normalise(ensName);
            fail("Name should not be valid");
        } catch (EnsResolutionException e) {
            // expected
        }
    }
}
