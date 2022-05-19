package com.venachain.protocol.scenarios;

import java.math.BigInteger;

import org.junit.Test;

import com.venachain.crypto.RawTransaction;
import com.venachain.crypto.TransactionEncoder;
import com.venachain.protocol.core.DefaultBlockParameterName;
import com.venachain.protocol.core.methods.response.EthGetTransactionCount;
import com.venachain.protocol.core.methods.response.EthSendTransaction;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.utils.Convert;
import com.venachain.utils.Numeric;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Create, sign and send a raw transaction.
 */
public class CreateRawTransactionIT extends Scenario {

    @Test
    public void testTransferEther() throws Exception {
        BigInteger nonce = getNonce(ALICE.getAddress());
        RawTransaction rawTransaction = createEtherTransaction(
                nonce, BOB.getAddress());

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, ALICE);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction =
                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();

        assertFalse(transactionHash.isEmpty());

        TransactionReceipt transactionReceipt =
                waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));
    }

    @Test
    public void testDeploySmartContract() throws Exception {
        BigInteger nonce = getNonce(ALICE.getAddress());
        RawTransaction rawTransaction = createSmartContractTransaction(nonce);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, ALICE);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction =
                web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();

        assertFalse(transactionHash.isEmpty());

        TransactionReceipt transactionReceipt =
                waitForTransactionReceipt(transactionHash);

        assertThat(transactionReceipt.getTransactionHash(), is(transactionHash));

        assertFalse("Contract execution ran out of gas",
                rawTransaction.getGasLimit().equals(transactionReceipt.getGasUsed()));
    }

    private static RawTransaction createEtherTransaction(BigInteger nonce, String toAddress) {
        BigInteger value = Convert.toWei("0.5", Convert.Unit.ETHER).toBigInteger();

        return RawTransaction.createEtherTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, toAddress, value);
    }

    private static RawTransaction createSmartContractTransaction(BigInteger nonce)
            throws Exception {
        return RawTransaction.createContractTransaction(
                nonce, GAS_PRICE, GAS_LIMIT, BigInteger.ZERO, getFibonacciSolidityBinary());
    }

    BigInteger getNonce(String address) throws Exception {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                address, DefaultBlockParameterName.LATEST).sendAsync().get();

        return ethGetTransactionCount.getTransactionCount();
    }
}
