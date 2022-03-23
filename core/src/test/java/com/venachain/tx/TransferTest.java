package com.venachain.tx;

import java.math.BigDecimal;

import com.venachain.crypto.Credentials;
import com.venachain.crypto.SampleKeys;
import com.venachain.utils.Convert;
import org.junit.Before;
import org.junit.Test;

import com.venachain.protocol.core.methods.response.TransactionReceipt;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class TransferTest extends ManagedTransactionTester {

    protected TransactionReceipt transactionReceipt;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        transactionReceipt = prepareTransfer();
    }

    @Test
    public void testSendFunds() throws Exception {
        assertThat(sendFunds(SampleKeys.CREDENTIALS, ADDRESS,
                BigDecimal.TEN, Convert.Unit.ETHER),
                is(transactionReceipt));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTransferInvalidValue() throws Exception {
        sendFunds(SampleKeys.CREDENTIALS, ADDRESS,
                new BigDecimal(0.1), Convert.Unit.WEI);
    }

    protected TransactionReceipt sendFunds(Credentials credentials, String toAddress,
                                           BigDecimal value, Convert.Unit unit) throws Exception {
        return new Transfer(web3j, getVerifiedTransactionManager(credentials))
                .sendFunds(toAddress, value, unit).send();
    }
}
