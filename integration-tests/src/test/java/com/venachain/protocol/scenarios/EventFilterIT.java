package com.venachain.protocol.scenarios;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.venachain.abi.EventEncoder;
import com.venachain.abi.FunctionEncoder;
import com.venachain.abi.FunctionReturnDecoder;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Event;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.generated.Uint256;
import com.venachain.crypto.Credentials;
import com.venachain.protocol.core.DefaultBlockParameterName;
import com.venachain.protocol.core.methods.request.EthFilter;
import com.venachain.protocol.core.methods.request.Transaction;
import com.venachain.protocol.core.methods.response.EthEstimateGas;
import com.venachain.protocol.core.methods.response.EthLog;
import com.venachain.protocol.core.methods.response.Log;
import com.venachain.protocol.core.methods.response.TransactionReceipt;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Filter scenario integration tests.
 */
public class EventFilterIT extends Scenario {

    // Deployed Fibonacci contract instance in testnet
    private static final String CONTRACT_ADDRESS = "0x3c05b2564139fb55820b18b72e94b2178eaace7d";

    @Test
    public void testEventFilter() throws Exception {
        unlockAccount();

        Function function = createFibonacciFunction();
        String encodedFunction = FunctionEncoder.encode(function);

        BigInteger gas = estimateGas(encodedFunction);
        String transactionHash = sendTransaction(ALICE, CONTRACT_ADDRESS, gas, encodedFunction);

        TransactionReceipt transactionReceipt =
                waitForTransactionReceipt(transactionHash);

        assertFalse("Transaction execution ran out of gas",
                gas.equals(transactionReceipt.getGasUsed()));

        List<Log> logs = transactionReceipt.getLogs();
        assertFalse(logs.isEmpty());

        Log log = logs.get(0);

        List<String> topics = log.getTopics();
        assertThat(topics.size(), is(1));

        Event event = new Event("Notify",
                Arrays.asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));

        // check function signature - we only have a single topic our event signature,
        // there are no indexed parameters in this example
        String encodedEventSignature = EventEncoder.encode(event);
        assertThat(topics.get(0),
                is(encodedEventSignature));

        // verify our two event parameters
        List<Type> results = FunctionReturnDecoder.decode(
                log.getData(), event.getNonIndexedParameters());
        assertThat(results, equalTo(Arrays.asList(
                new Uint256(BigInteger.valueOf(7)), new Uint256(BigInteger.valueOf(13)))));

        // finally check it shows up in the event filter
        List<EthLog.LogResult> filterLogs = createFilterForEvent(
                encodedEventSignature, CONTRACT_ADDRESS);
        assertFalse(filterLogs.isEmpty());
    }

    private BigInteger estimateGas(String encodedFunction) throws Exception {
        EthEstimateGas ethEstimateGas = web3j.ethEstimateGas(
                Transaction.createEthCallTransaction(ALICE.getAddress(), null, encodedFunction))
                .sendAsync().get();
        // this was coming back as 50,000,000 which is > the block gas limit of 4,712,388
        // see eth.getBlock("latest")
        return ethEstimateGas.getAmountUsed().divide(BigInteger.valueOf(100));
    }

    private String sendTransaction(
            Credentials credentials, String contractAddress, BigInteger gas,
            String encodedFunction) throws Exception {
        BigInteger nonce = getNonce(credentials.getAddress());
        Transaction transaction = Transaction.createFunctionCallTransaction(
                credentials.getAddress(), nonce, Transaction.DEFAULT_GAS, gas, contractAddress,
                encodedFunction);

        com.venachain.protocol.core.methods.response.EthSendTransaction transactionResponse =
                web3j.ethSendTransaction(transaction).sendAsync().get();

        assertFalse(transactionResponse.hasError());

        return transactionResponse.getTransactionHash();
    }

    private List<EthLog.LogResult> createFilterForEvent(
            String encodedEventSignature, String contractAddress) throws Exception {
        EthFilter ethFilter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );

        ethFilter.addSingleTopic(encodedEventSignature);

        EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
        return ethLog.getLogs();
    }
}
