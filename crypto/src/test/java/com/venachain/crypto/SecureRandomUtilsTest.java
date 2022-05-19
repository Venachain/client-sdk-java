package com.venachain.crypto;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static com.venachain.crypto.SecureRandomUtils.isAndroidRuntime;
import static com.venachain.crypto.SecureRandomUtils.secureRandom;

public class SecureRandomUtilsTest {

    @Test
    public void testSecureRandom() {
        secureRandom().nextInt();
    }

    @Test
    public void testIsNotAndroidRuntime() {
        assertFalse(isAndroidRuntime());
    }
}
