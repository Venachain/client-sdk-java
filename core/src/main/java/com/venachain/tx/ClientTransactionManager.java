package com.venachain.tx;

import java.io.IOException;
import java.math.BigInteger;

import com.venachain.protocol.Web3j;
import com.venachain.protocol.core.methods.request.Transaction;
import com.venachain.protocol.core.methods.response.EthSendTransaction;
import com.venachain.tx.response.TransactionReceiptProcessor;

/**
 * TransactionManager implementation for using an Ethereum node to transact.
 *
 * <p><b>Note</b>: accounts must be unlocked on the node for transactions to be successful.
 */
public class ClientTransactionManager extends TransactionManager {

    private final Web3j web3j;

    public ClientTransactionManager(
            Web3j web3j, String fromAddress) {
        super(web3j, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3j web3j, String fromAddress, int attempts, int sleepDuration) {
        super(web3j, attempts, sleepDuration, fromAddress);
        this.web3j = web3j;
    }

    public ClientTransactionManager(
            Web3j web3j, String fromAddress,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, fromAddress);
        this.web3j = web3j;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException {

        Transaction transaction = new Transaction(
                getFromAddress(), null, gasPrice, gasLimit, to, value, data);

        return web3j.ethSendTransaction(transaction)
                .send();
    }

    @Override
    public EthSendTransaction sendTransactionWithNonce(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value, BigInteger nonce)
            throws IOException {

        Transaction transaction = new Transaction(
                getFromAddress(), nonce, gasPrice, gasLimit, to, value, data);

        return web3j.ethSendTransaction(transaction)
                .send();
    }

}
