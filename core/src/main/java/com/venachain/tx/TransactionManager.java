package com.venachain.tx;

import java.io.IOException;
import java.math.BigInteger;

import com.venachain.protocol.Web3j;
import com.venachain.protocol.core.methods.response.EthSendTransaction;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.protocol.exceptions.TransactionException;
import com.venachain.tx.response.PollingTransactionReceiptProcessor;
import com.venachain.tx.response.TransactionReceiptProcessor;

import static com.venachain.protocol.core.JsonRpc2_0Web3j.DEFAULT_BLOCK_TIME;

/**
 * Transaction manager abstraction for executing transactions with Ethereum client via
 * various mechanisms.
 */
public abstract class TransactionManager {

    public static final int DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH = 40;
    public static final long DEFAULT_POLLING_FREQUENCY = DEFAULT_BLOCK_TIME;

    private final TransactionReceiptProcessor transactionReceiptProcessor;
    private final String fromAddress;

    protected TransactionManager(
            TransactionReceiptProcessor transactionReceiptProcessor, String fromAddress) {
        this.transactionReceiptProcessor = transactionReceiptProcessor;
        this.fromAddress = fromAddress;
    }

    protected TransactionManager(Web3j web3j, String fromAddress) {
        this(new PollingTransactionReceiptProcessor(
                        web3j, DEFAULT_POLLING_FREQUENCY, DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH),
                fromAddress);
    }

    protected TransactionManager(
            Web3j web3j, int attempts, long sleepDuration, String fromAddress) {
        this(new PollingTransactionReceiptProcessor(web3j, sleepDuration, attempts), fromAddress);
    }

    protected TransactionReceipt executeTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException, TransactionException {

        EthSendTransaction ethSendTransaction = sendTransaction(
                gasPrice, gasLimit, to, data, value);
        return processResponse(ethSendTransaction);
    }

    protected TransactionReceipt executeTransactionWithNonce(
        BigInteger gasPrice, BigInteger gasLimit, String to,
        String data, BigInteger value,BigInteger nonce)
        throws IOException, TransactionException {

    EthSendTransaction ethSendTransaction = sendTransactionWithNonce(
            gasPrice, gasLimit, to, data, value,nonce);
        return processResponse(ethSendTransaction);
    }

    public abstract EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value)
            throws IOException;
    
    public abstract EthSendTransaction sendTransactionWithNonce(
        BigInteger gasPrice, BigInteger gasLimit, String to,
        String data, BigInteger value, BigInteger nonce)
        throws IOException;

    public String getFromAddress() {
        return fromAddress;
    }

    private TransactionReceipt processResponse(EthSendTransaction transactionResponse)
            throws IOException, TransactionException {
        if (transactionResponse.hasError()) {
            throw new RuntimeException("Error processing transaction request: "
                    + transactionResponse.getError().getMessage());
        }

        String transactionHash = transactionResponse.getTransactionHash();

        return transactionReceiptProcessor.waitForTransactionReceipt(transactionHash);
    }


}
