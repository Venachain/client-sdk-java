package com.venachain.protocol.scenarios;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.venachain.abi.FunctionEncoder;
import com.venachain.abi.FunctionReturnDecoder;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.protocol.core.DefaultBlockParameterName;
import com.venachain.protocol.core.methods.request.Transaction;
import com.venachain.protocol.core.methods.response.TransactionReceipt;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration test demonstrating integration with Greeter contract taken from the
 * <a href="https://github.com/ethereum/go-ethereum/wiki/Contract-Tutorial">Contract Tutorial</a>
 * on the Go Ethereum Wiki.
 */
public class GreeterContractIT extends Scenario {

    private static final String VALUE = "Greetings!";

    @Test
    public void testGreeterContract() throws Exception {
        boolean accountUnlocked = unlockAccount();
        assertTrue(accountUnlocked);

        // create our smart contract
        String createTransactionHash = sendCreateContractTransaction();
        assertFalse(createTransactionHash.isEmpty());

        TransactionReceipt createTransactionReceipt =
                waitForTransactionReceipt(createTransactionHash);

        assertThat(createTransactionReceipt.getTransactionHash(), is(createTransactionHash));

        assertFalse("Contract execution ran out of gas",
                createTransactionReceipt.getGasUsed().equals(GAS_LIMIT));

        String contractAddress = createTransactionReceipt.getContractAddress();

        assertNotNull(contractAddress);

        // call our getter
        Function getFunction = createGreetFunction();
        String responseValue = callSmartContractFunction(getFunction, contractAddress);
        assertFalse(responseValue.isEmpty());

        List<Type> response = FunctionReturnDecoder.decode(
                responseValue, getFunction.getOutputParameters());
        assertThat(response.size(), is(1));
        assertThat(response.get(0).getValue(), is(VALUE));
    }

    private String sendCreateContractTransaction() throws Exception {
        BigInteger nonce = getNonce(ALICE.getAddress());

        String encodedConstructor =
                FunctionEncoder.encodeConstructor(Collections.singletonList(new Utf8String(VALUE)));

        Transaction transaction = Transaction.createContractTransaction(
                ALICE.getAddress(),
                nonce,
                GAS_PRICE,
                GAS_LIMIT,
                BigInteger.ZERO,
                getGreeterSolidityBinary() + encodedConstructor);

        com.venachain.protocol.core.methods.response.EthSendTransaction
                transactionResponse = web3j.ethSendTransaction(transaction)
                .sendAsync().get();

        return transactionResponse.getTransactionHash();
    }

    private String callSmartContractFunction(
            Function function, String contractAddress) throws Exception {

        String encodedFunction = FunctionEncoder.encode(function);

        com.venachain.protocol.core.methods.response.EthCall response = web3j.ethCall(
                Transaction.createEthCallTransaction(
                        ALICE.getAddress(), contractAddress, encodedFunction),
                DefaultBlockParameterName.LATEST)
                .sendAsync().get();

        return response.getValue();
    }

    private static String getGreeterSolidityBinary() throws Exception {
        return load("/solidity/greeter/build/Greeter.bin");
    }

    Function createGreetFunction() {
        return new Function(
                "greet",
                Collections.emptyList(),
                Collections.singletonList(new TypeReference<Utf8String>() {}));
    }
}
