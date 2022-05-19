package com.venachain.console;

import org.junit.Before;

import com.venachain.TempFileProvider;

import static org.mockito.Mockito.mock;
import static com.venachain.crypto.SampleKeys.PASSWORD;

public abstract class WalletTester extends TempFileProvider {

    static final char[] WALLET_PASSWORD = PASSWORD.toCharArray();

    IODevice console;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        console = mock(IODevice.class);
    }
}
