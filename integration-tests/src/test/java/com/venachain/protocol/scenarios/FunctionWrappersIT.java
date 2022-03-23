package com.venachain.protocol.scenarios;

import java.math.BigInteger;

import com.venachain.abi.datatypes.generated.Uint256;
import com.venachain.protocol.Web3j;
import org.junit.Test;

import com.venachain.generated.Fibonacci;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.protocol.http.HttpService;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Test Fibonacci contract generated wrappers.
 *
 * <p>Generated via running com.venachain.codegen.SolidityFunctionWrapperGenerator with params:
 * <em>project-home</em>/src/test/resources/solidity/fibonacci.abi -o
 * <em>project-home</em>/src/integration-test/java -p com.venachain.generated
 */
public class FunctionWrappersIT extends Scenario {

    @Test
    public void testFibonacci() throws Exception {
        Fibonacci fibonacci = Fibonacci.load(
                "0x3c05b2564139fb55820b18b72e94b2178eaace7d", Web3j.build(new HttpService()),
                ALICE, STATIC_GAS_PROVIDER);

        BigInteger result = fibonacci.fibonacci(BigInteger.valueOf(10)).send();
        assertThat(result, equalTo(BigInteger.valueOf(55)));
    }

    @Test
    public void testFibonacciNotify() throws Exception {
        Fibonacci fibonacci = Fibonacci.load(
                "0x3c05b2564139fb55820b18b72e94b2178eaace7d", Web3j.build(new HttpService()),
                ALICE, STATIC_GAS_PROVIDER);

        TransactionReceipt transactionReceipt = fibonacci.fibonacciNotify(
                BigInteger.valueOf(15)).send();

        Fibonacci.NotifyEventResponse result = fibonacci.getNotifyEvents(transactionReceipt).get(0);

        assertThat(result.input,
                equalTo(new Uint256(BigInteger.valueOf(15))));

        assertThat(result.result,
                equalTo(new Uint256(BigInteger.valueOf(610))));
    }
}
