package com.venachain.utils;

import org.junit.Test;

import static com.venachain.utils.Assertions.verifyPrecondition;

public class AssertionsTest {

    @Test
    public void testVerifyPrecondition() {
        verifyPrecondition(true, "");
    }

    @Test(expected = RuntimeException.class)
    public void testVerifyPreconditionFailure() {
        verifyPrecondition(false, "");
    }
}
