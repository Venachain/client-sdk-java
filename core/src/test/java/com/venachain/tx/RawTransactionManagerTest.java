package com.venachain.tx;

import java.math.BigDecimal;

import com.venachain.crypto.SampleKeys;
import com.venachain.utils.Convert;
import org.junit.Test;

import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.tx.exceptions.TxHashMismatchException;

public class RawTransactionManagerTest extends ManagedTransactionTester {

    @Test(expected = TxHashMismatchException.class)
    public void testTxHashMismatch() throws Exception {
        TransactionReceipt transactionReceipt = prepareTransfer();
        prepareTransaction(transactionReceipt);

        TransactionManager transactionManager =
                new RawTransactionManager(web3j, SampleKeys.CREDENTIALS);
        Transfer transfer = new Transfer(web3j, transactionManager);
        transfer.sendFunds(ADDRESS, BigDecimal.ONE, Convert.Unit.ETHER).send();
    }
}
