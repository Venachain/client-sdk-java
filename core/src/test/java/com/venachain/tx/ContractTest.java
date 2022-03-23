package com.venachain.tx;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;

import com.venachain.abi.EventEncoder;
import com.venachain.abi.EventValues;
import com.venachain.abi.FunctionEncoder;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.generated.Uint256;
import com.venachain.crypto.Credentials;
import com.venachain.crypto.SampleKeys;
import com.venachain.protocol.Web3j;
import com.venachain.utils.Async;
import com.venachain.utils.Numeric;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.ExpectedException;

import com.venachain.abi.datatypes.Address;
import com.venachain.abi.datatypes.Event;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.protocol.core.DefaultBlockParameterName;
import com.venachain.protocol.core.RemoteCall;
import com.venachain.protocol.core.Request;
import com.venachain.protocol.core.Response;
import com.venachain.protocol.core.methods.request.Transaction;
import com.venachain.protocol.core.methods.response.EthCall;
import com.venachain.protocol.core.methods.response.EthGetCode;
import com.venachain.protocol.core.methods.response.EthGetTransactionReceipt;
import com.venachain.protocol.core.methods.response.EthSendTransaction;
import com.venachain.protocol.core.methods.response.Log;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.protocol.exceptions.TransactionException;
import com.venachain.tx.gas.ContractGasProvider;
import com.venachain.tx.gas.DefaultGasProvider;
import com.venachain.tx.gas.StaticGasProvider;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContractTest extends ManagedTransactionTester {

    private static final String TEST_CONTRACT_BINARY = "12345";

    private TestContract contract;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        super.setUp();

        contract = new TestContract(
                ADDRESS, web3j, getVerifiedTransactionManager(SampleKeys.CREDENTIALS),
                new DefaultGasProvider());
    }

    @Test
    public void testGetContractAddress() {
        assertThat(contract.getContractAddress(), is(ADDRESS));
    }

    @Test
    public void testGetContractTransactionReceipt() {
        assertFalse(contract.getTransactionReceipt().isPresent());
    }

    @Test
    public void testDeploy() throws Exception {
        TransactionReceipt transactionReceipt = createTransactionReceipt();
        Contract deployedContract = deployContract(transactionReceipt);

        assertThat(deployedContract.getContractAddress(), is(ADDRESS));
        assertTrue(deployedContract.getTransactionReceipt().isPresent());
        assertThat(deployedContract.getTransactionReceipt().get(), equalTo(transactionReceipt));
    }

    @Test
    public void testContractDeployFails() throws Exception {
        thrown.expect(TransactionException.class);
        thrown.expectMessage(
                "Transaction has failed with status: 0x0. Gas used: 1. (not-enough gas?)");
        TransactionReceipt transactionReceipt = createFailedTransactionReceipt();
        deployContract(transactionReceipt);
    }

    @Test
    public void testContractDeployWithNullStatusSucceeds() throws Exception {
        TransactionReceipt transactionReceipt = createTransactionReceiptWithStatus(null);
        Contract deployedContract = deployContract(transactionReceipt);

        assertThat(deployedContract.getContractAddress(), is(ADDRESS));
        assertTrue(deployedContract.getTransactionReceipt().isPresent());
        assertThat(deployedContract.getTransactionReceipt().get(), equalTo(transactionReceipt));
    }

    @Test
    public void testIsValid() throws Exception {
        prepareEthGetCode(TEST_CONTRACT_BINARY);

        Contract contract = deployContract(createTransactionReceipt());
        assertTrue(contract.isValid());
    }

    @Test
    public void testIsValidDifferentCode() throws Exception {
        prepareEthGetCode(TEST_CONTRACT_BINARY + "0");

        Contract contract = deployContract(createTransactionReceipt());
        assertFalse(contract.isValid());
    }

    @Test
    public void testIsValidEmptyCode() throws Exception {
        prepareEthGetCode("");

        Contract contract = deployContract(createTransactionReceipt());
        assertFalse(contract.isValid());
    }

    @Test(expected = RuntimeException.class)
    public void testDeployInvalidContractAddress() throws Throwable {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);

        prepareTransaction(transactionReceipt);

        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Uint256(BigInteger.TEN)));

        try {
            TestContract.deployRemoteCall(
                    TestContract.class, web3j, SampleKeys.CREDENTIALS,
                    ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
                    "0xcafed00d", encodedConstructor, BigInteger.ZERO).send();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    @Test
    public void testCallSingleValue() throws Exception {
        // Example taken from FunctionReturnDecoderTest

        EthCall ethCall = new EthCall();
        ethCall.setResult("0x0000000000000000000000000000000000000000000000000000000000000020"
                + "0000000000000000000000000000000000000000000000000000000000000000");
        prepareCall(ethCall);

        assertThat(contract.callSingleValue().send(), equalTo(new Utf8String("")));
    }

    @Test
    public void testCallSingleValueEmpty() throws Exception {
        // Example taken from FunctionReturnDecoderTest

        EthCall ethCall = new EthCall();
        ethCall.setResult("0x");
        prepareCall(ethCall);

        assertNull(contract.callSingleValue().send());
    }

    @Test
    public void testCallMultipleValue() throws Exception {
        EthCall ethCall = new EthCall();
        ethCall.setResult("0x0000000000000000000000000000000000000000000000000000000000000037"
                + "0000000000000000000000000000000000000000000000000000000000000007");
        prepareCall(ethCall);

        assertThat(contract.callMultipleValue().send(),
                equalTo(Arrays.asList(
                        new Uint256(BigInteger.valueOf(55)),
                        new Uint256(BigInteger.valueOf(7)))));
    }

    @Test
    public void testCallMultipleValueEmpty() throws Exception {
        EthCall ethCall = new EthCall();
        ethCall.setResult("0x");
        prepareCall(ethCall);

        assertThat(contract.callMultipleValue().send(),
                equalTo(emptyList()));
    }

    @SuppressWarnings("unchecked")
    private void prepareCall(EthCall ethCall) throws IOException {
        Request<?, EthCall> request = mock(Request.class);
        when(request.send()).thenReturn(ethCall);

        when(web3j.ethCall(any(Transaction.class), eq(DefaultBlockParameterName.LATEST)))
                .thenReturn((Request) request);
    }

    @Test
    public void testTransaction() throws Exception {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);
        transactionReceipt.setStatus("0x1");

        prepareTransaction(transactionReceipt);

        assertThat(contract.performTransaction(
                new Address(BigInteger.TEN), new Uint256(BigInteger.ONE)).send(),
                is(transactionReceipt));
    }

    @Test
    public void testTransactionFailed() throws Exception {
        thrown.expect(TransactionException.class);
        thrown.expectMessage(
                "Transaction has failed with status: 0x0. Gas used: 1. (not-enough gas?)");

        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);
        transactionReceipt.setStatus("0x0");
        transactionReceipt.setGasUsed("0x1");

        prepareTransaction(transactionReceipt);
        contract.performTransaction(
                new Address(BigInteger.TEN), new Uint256(BigInteger.ONE)).send();
    }

    @Test
    public void testProcessEvent() {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        Log log = new Log();
        log.setTopics(Arrays.asList(
                // encoded function
                "0xfceb437c298f40d64702ac26411b2316e79f3c28ffa60edfc891ad4fc8ab82ca",
                // indexed value
                "0000000000000000000000003d6cb163f7c72d20b0fcd6baae5889329d138a4a"));
        // non-indexed value
        log.setData("0000000000000000000000000000000000000000000000000000000000000001");

        transactionReceipt.setLogs(Arrays.asList(log));

        EventValues eventValues = contract.processEvent(transactionReceipt).get(0);

        assertThat(eventValues.getIndexedValues(),
                equalTo(singletonList(
                        new Address("0x3d6cb163f7c72d20b0fcd6baae5889329d138a4a"))));
        assertThat(eventValues.getNonIndexedValues(),
                equalTo(singletonList(new Uint256(BigInteger.ONE))));
    }

    @Test(expected = TransactionException.class)
    public void testTimeout() throws Throwable {
        prepareTransaction(null);

        TransactionManager transactionManager =
                getVerifiedTransactionManager(SampleKeys.CREDENTIALS, 1, 1);

        contract = new TestContract(
                ADDRESS, web3j, transactionManager,
                new DefaultGasProvider());

        testErrorScenario();
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void testInvalidTransactionResponse() throws Throwable {
        prepareNonceRequest();

        EthSendTransaction ethSendTransaction = new EthSendTransaction();
        ethSendTransaction.setError(new Response.Error(1, "Invalid transaction"));

        Request<?, EthSendTransaction> rawTransactionRequest = mock(Request.class);
        when(rawTransactionRequest.sendAsync()).thenReturn(Async.run(() -> ethSendTransaction));
        when(web3j.ethSendRawTransaction(any(String.class)))
                .thenReturn((Request) rawTransactionRequest);

        testErrorScenario();
    }

    @Test
    public void testSetGetAddresses() throws Exception {
        assertNull(contract.getDeployedAddress("1"));
        contract.setDeployedAddress("1", "0x000000000000add0e00000000000");
        assertNotNull(contract.getDeployedAddress("1"));
        contract.setDeployedAddress("2", "0x000000000000add0e00000000000");
        assertNotNull(contract.getDeployedAddress("2"));
    }

    @Test
    public void testSetGetGasPrice() {
        assertEquals(ManagedTransaction.GAS_PRICE, contract.getGasPrice());
        BigInteger newPrice = ManagedTransaction.GAS_PRICE.multiply(BigInteger.valueOf(2));
        contract.setGasPrice(newPrice);
        assertEquals(newPrice, contract.getGasPrice());
    }

    @Test
    public void testStaticGasProvider() throws IOException, TransactionException {
        StaticGasProvider gasProvider = new StaticGasProvider(BigInteger.TEN, BigInteger.ONE);
        TransactionManager txManager = mock(TransactionManager.class);
        when(txManager.executeTransaction(any(), any(), any(), any(), any()))
                .thenReturn(new TransactionReceipt());

        contract = new TestContract(ADDRESS, web3j, txManager, gasProvider);

        Function func = new Function("test",
                Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        contract.executeTransaction(func);

        verify(txManager).executeTransaction(eq(BigInteger.TEN),
                eq(BigInteger.ONE), any(), any(), any());
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void testInvalidTransactionReceipt() throws Throwable {
        prepareNonceRequest();
        prepareTransactionRequest();

        EthGetTransactionReceipt ethGetTransactionReceipt = new EthGetTransactionReceipt();
        ethGetTransactionReceipt.setError(new Response.Error(1, "Invalid transaction receipt"));

        Request<?, EthGetTransactionReceipt> getTransactionReceiptRequest = mock(Request.class);
        when(getTransactionReceiptRequest.sendAsync())
                .thenReturn(Async.run(() -> ethGetTransactionReceipt));
        when(web3j.ethGetTransactionReceipt(TRANSACTION_HASH))
                .thenReturn((Request) getTransactionReceiptRequest);

        testErrorScenario();
    }

    @Test
    public void testExtractEventParametersWithLogGivenATransactionReceipt() {

        final java.util.function.Function<String, Event> eventFactory = name ->
                new Event(name, emptyList());

        final BiFunction<Integer, Event, Log> logFactory = (logIndex, event) ->
                new Log(false, "" + logIndex, "0", "0x0", "0x0", "0", "0x" + logIndex, "", "",
                        singletonList(EventEncoder.encode(event)));

        final Event testEvent1 = eventFactory.apply("TestEvent1");
        final Event testEvent2 = eventFactory.apply("TestEvent2");

        final List<Log> logs = Arrays.asList(
                logFactory.apply(0, testEvent1),
                logFactory.apply(1, testEvent2)
        );

        final TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setLogs(logs);

        final List<Contract.EventValuesWithLog> eventValuesWithLogs1 =
                contract.extractEventParametersWithLog(testEvent1, transactionReceipt);

        assertEquals(eventValuesWithLogs1.size(), 1);
        assertEquals(eventValuesWithLogs1.get(0).getLog(), logs.get(0));

        final List<Contract.EventValuesWithLog> eventValuesWithLogs2 =
                contract.extractEventParametersWithLog(testEvent2, transactionReceipt);

        assertEquals(eventValuesWithLogs2.size(), 1);
        assertEquals(eventValuesWithLogs2.get(0).getLog(), logs.get(1));
    }

    void testErrorScenario() throws Throwable {
        try {
            contract.performTransaction(
                    new Address(BigInteger.TEN), new Uint256(BigInteger.ONE)).send();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    private TransactionReceipt createTransactionReceipt() {
        return createTransactionReceiptWithStatus("0x1");
    }

    private TransactionReceipt createFailedTransactionReceipt() {
        return createTransactionReceiptWithStatus("0x0");
    }

    private TransactionReceipt createTransactionReceiptWithStatus(String status) {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setTransactionHash(TRANSACTION_HASH);
        transactionReceipt.setContractAddress(ADDRESS);
        transactionReceipt.setStatus(status);
        transactionReceipt.setGasUsed("0x1");
        return transactionReceipt;
    }

    private Contract deployContract(TransactionReceipt transactionReceipt)
            throws Exception {

        prepareTransaction(transactionReceipt);

        String encodedConstructor = FunctionEncoder.encodeConstructor(
                Arrays.<Type>asList(new Uint256(BigInteger.TEN)));

        return TestContract.deployRemoteCall(
                TestContract.class, web3j, getVerifiedTransactionManager(SampleKeys.CREDENTIALS),
                ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT,
                "0xcafed00d", encodedConstructor, BigInteger.ZERO).send();
    }

    @SuppressWarnings("unchecked")
    private void prepareEthGetCode(String binary) throws IOException {
        EthGetCode ethGetCode = new EthGetCode();
        ethGetCode.setResult(Numeric.prependHexPrefix(binary));

        Request<?, EthGetCode> ethGetCodeRequest = mock(Request.class);
        when(ethGetCodeRequest.send())
                .thenReturn(ethGetCode);
        when(web3j.ethGetCode(ADDRESS, DefaultBlockParameterName.LATEST))
                .thenReturn((Request) ethGetCodeRequest);
    }

    private static class TestContract extends Contract {
        public TestContract(
                String contractAddress, Web3j web3j, Credentials credentials,
                BigInteger gasPrice, BigInteger gasLimit) {
            super(TEST_CONTRACT_BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
        }

        public TestContract(
                String contractAddress,
                Web3j web3j, TransactionManager transactionManager,
                ContractGasProvider gasProvider) {
            super(TEST_CONTRACT_BINARY, contractAddress, web3j, transactionManager, gasProvider);
        }

        public RemoteCall<Utf8String> callSingleValue() {
            Function function = new Function("call",
                    Arrays.<Type>asList(),
                    Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                    }));
            return executeRemoteCallSingleValueReturn(function);
        }

        public RemoteCall<List<Type>> callMultipleValue()
                throws ExecutionException, InterruptedException {
            Function function = new Function("call",
                    Arrays.<Type>asList(),
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Uint256>() { },
                            new TypeReference<Uint256>() { }));
            return executeRemoteCallMultipleValueReturn(function);
        }

        public RemoteCall<TransactionReceipt> performTransaction(
                Address address, Uint256 amount) {
            Function function = new Function("approve",
                    Arrays.<Type>asList(address, amount),
                    Collections.<TypeReference<?>>emptyList());
            return executeRemoteCallTransaction(function);
        }

        public List<EventValues> processEvent(TransactionReceipt transactionReceipt) {
            Event event = new Event("Event",
                    Arrays.<TypeReference<?>>asList(
                            new TypeReference<Address>(true) { },
                            new TypeReference<Uint256>() { }));
            return extractEventParameters(event, transactionReceipt);
        }
    }
}
