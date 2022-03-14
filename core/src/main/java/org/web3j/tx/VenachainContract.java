package org.web3j.tx;


import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.VenachainEventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.*;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;
import org.web3j.utils.VenachainUtil;
import rx.Observable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Solidity contract type abstraction for interacting with smart contracts via native Java types.
 */
@SuppressWarnings("WeakerAccess")
public abstract class VenachainContract extends ManagedTransaction {

    //https://www.reddit.com/r/ethereum/comments/5g8ia6/attention_miners_we_recommend_raising_gas_limit/
    /**
     * @deprecated ...
     * @see org.web3j.tx.gas.DefaultGasProvider
     */
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000);

    public static final String FUNC_DEPLOY = "deploy";

    protected final String contractBinary;
    protected String contractAddress;
    protected String cnsName;
    protected BigInteger isCns;
    protected ContractGasProvider gasProvider;
    protected TransactionReceipt transactionReceipt;
    protected Map<String, String> deployedAddresses;
    protected DefaultBlockParameter defaultBlockParameter = DefaultBlockParameterName.LATEST;

    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, TransactionManager transactionManager,
                                ContractGasProvider gasProvider) {
        super(web3j, transactionManager);


        if (contractAddress != null) {
            if (WalletUtils.isValidAddress(contractAddress)){
                this.contractAddress = contractAddress;
                this.isCns = BigInteger.ZERO;
            }else{
                this.contractAddress = "0x0000000000000000000000000000000000000000";
                this.cnsName = contractAddress;
                this.isCns = BigInteger.ONE;
            }
        } else {
            this.contractAddress = contractAddress;
            this.isCns = BigInteger.ZERO;
        }


        this.contractBinary = contractBinary;
        this.gasProvider = gasProvider;
    }

    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, Credentials credentials,
                                ContractGasProvider gasProvider) {

        this(contractBinary, contractAddress, web3j,
                new RawTransactionManager(web3j, credentials),
                gasProvider);
    }
    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, Credentials credentials) {
        this(contractBinary,contractAddress, web3j, new RawTransactionManager(web3j, credentials),
                new StaticGasProvider(BigInteger.ZERO, BigInteger.ZERO));
    }
    protected VenachainContract(String contractAddress,
                                Web3j web3j, Credentials credentials) {
        this("",contractAddress, web3j, new RawTransactionManager(web3j, credentials),
                new StaticGasProvider(BigInteger.ZERO, BigInteger.ZERO));
    }

    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, TransactionManager transactionManager) {
        this(contractBinary, contractAddress, web3j, transactionManager,
                new StaticGasProvider(BigInteger.ZERO, BigInteger.ZERO));
    }
    protected VenachainContract(String contractAddress,
                                Web3j web3j, TransactionManager transactionManager) {
        this("", contractAddress, web3j, transactionManager,
                new StaticGasProvider(BigInteger.ZERO, BigInteger.ZERO));
    }

    @Deprecated
    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, TransactionManager transactionManager,
                                BigInteger gasPrice, BigInteger gasLimit) {
        this(contractBinary, contractAddress, web3j, transactionManager,
                new StaticGasProvider(gasPrice, gasLimit));
    }

    @Deprecated
    protected VenachainContract(String contractBinary, String contractAddress,
                                Web3j web3j, Credentials credentials,
                                BigInteger gasPrice, BigInteger gasLimit) {
        this(contractBinary, contractAddress, web3j, new RawTransactionManager(web3j, credentials),
                gasPrice, gasLimit);
    }

    @Deprecated
    protected VenachainContract(String contractAddress,
                                Web3j web3j, TransactionManager transactionManager,
                                BigInteger gasPrice, BigInteger gasLimit) {
        this("", contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    @Deprecated
    protected VenachainContract(String contractAddress,
                                Web3j web3j, Credentials credentials,
                                BigInteger gasPrice, BigInteger gasLimit) {
        this("", contractAddress, web3j, new RawTransactionManager(web3j, credentials),
                gasPrice, gasLimit);
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setTransactionReceipt(TransactionReceipt transactionReceipt) {
        this.transactionReceipt = transactionReceipt;
    }

    public String getContractBinary() {
        return contractBinary;
    }

    public void setGasProvider(ContractGasProvider gasProvider) {
        this.gasProvider = gasProvider;
    }

    /**
     * Allow {@code gasPrice} to be set.
     * @param newPrice gas price to use for subsequent transactions
     * @deprecated use ContractGasProvider
     */
    public void setGasPrice(BigInteger newPrice) {
        this.gasProvider = new StaticGasProvider(newPrice, gasProvider.getGasLimit());
    }

    /**
     * Get the current {@code gasPrice} value this contract uses when executing transactions.
     * @return the gas price set on this contract
     * @deprecated use ContractGasProvider
     */
    public BigInteger getGasPrice() {
        return gasProvider.getGasPrice();
    }

    /**
     * Check that the contract deployed at the address associated with this smart contract wrapper
     * is in fact the contract you believe it is.
     *
     * <p>This method uses the
     * <a href="https://github.com/ethereum/wiki/wiki/JSON-RPC#eth_getcode">eth_getCode</a> method
     * to get the contract byte code and validates it against the byte code stored in this smart
     * contract wrapper.
     *
     * @return true if the contract is valid
     * @throws IOException if unable to connect to web3j node
     */
    public boolean isValid() throws IOException {
        if (contractAddress.equals("")) {
            throw new UnsupportedOperationException(
                    "Contract binary not present, you will need to regenerate your smart "
                            + "contract wrapper with web3j v2.2.0+");
        }

        EthGetCode ethGetCode = web3j
                .ethGetCode(contractAddress, DefaultBlockParameterName.LATEST)
                .send();
        if (ethGetCode.hasError()) {
            return false;
        }

        String code = Numeric.cleanHexPrefix(ethGetCode.getCode());
        // There may be multiple contracts in the Solidity bytecode, hence we only check for a
        // match with a subset
        return !code.isEmpty() && contractBinary.contains(code);
    }

    /**
     * If this Contract instance was created at deployment, the TransactionReceipt associated
     * with the initial creation will be provided, e.g. via a <em>deploy</em> method. This will
     * not persist for Contracts instances constructed via a <em>load</em> method.
     *
     * @return the TransactionReceipt generated at contract deployment
     */
    public Optional<TransactionReceipt> getTransactionReceipt() {
        return Optional.ofNullable(transactionReceipt);
    }

    /**
     * Sets the default block parameter. This use useful if one wants to query
     * historical state of a contract.
     *
     * @param defaultBlockParameter the default block parameter
     */
    public void setDefaultBlockParameter(DefaultBlockParameter defaultBlockParameter) {
        this.defaultBlockParameter = defaultBlockParameter;
    }

    /**
     * Execute constant function call - i.e. a call that does not change state of the contract
     *
     * @param function to call
     * @return {@link List} of values returned by function call
     */
    private List<Type> executeCall(Function function) throws IOException {
        String encodedFunction;
        Boolean isCNS = this.isCns == BigInteger.ONE;
        String value;

        if (isCNS){
            encodedFunction = VenachainUtil.invokeEncode(this.cnsName, function);
            org.web3j.protocol.core.methods.response.EthCall ethCall = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    defaultBlockParameter)
                    .send();

            value = ethCall.getValue();
        }else{
            encodedFunction = VenachainUtil.invokeEncode(function);
            org.web3j.protocol.core.methods.response.EthCall ethCall = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    defaultBlockParameter)
                    .send();
            value = ethCall.getValue();
        }
        return FunctionReturnDecoder.decode(value, function.getOutputParameters());
    }

    /**
     * Execute constant function call - i.e. a call that does not change state of the contract
     *
     * @param function to call
     * @return {@link List} of values returned by function call
     */
    private List<Type> executeCall(Function function,BigInteger number) throws IOException {
        String encodedFunction;
        Boolean isCNS = this.isCns == BigInteger.ONE;
        String value;

        if (isCNS){
            encodedFunction = VenachainUtil.invokeEncode(this.cnsName, function);
            org.web3j.protocol.core.methods.response.EthCall ethCall = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    DefaultBlockParameter.valueOf(number))
                    .send();

            value = ethCall.getValue();
        }else{
            encodedFunction = VenachainUtil.invokeEncode(function);

            org.web3j.protocol.core.methods.response.EthCall ethCall = web3j.ethCall(
                    Transaction.createEthCallTransaction(
                            transactionManager.getFromAddress(), contractAddress, encodedFunction),
                    DefaultBlockParameter.valueOf(number))
                    .send();

            value = ethCall.getValue();
        }

        return FunctionReturnDecoder.decode(value, function.getOutputParameters());
    }



    @SuppressWarnings("unchecked")
    protected <T extends Type> T executeCallSingleValueReturn(
            Function function) throws IOException {
        List<Type> values = executeCall(function);
        if (!values.isEmpty()) {
            return (T) values.get(0);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends Type> T executeCallSingleValueReturn(
            Function function,  BigInteger number) throws IOException {
        List<Type> values = executeCall(function,number);
        if (!values.isEmpty()) {
            return (T) values.get(0);
        } else {
            return null;
        }
    }



    @SuppressWarnings("unchecked")
    protected <T extends Type, R> R executeCallSingleValueReturn(
            Function function, Class<R> returnType) throws IOException {
        T result = executeCallSingleValueReturn(function);
        if (result == null) {
            System.out.println("Empty value returned from contract, check input and re-try.");
            return null;
        }
        Object value = result.getValue();
        if (returnType.isAssignableFrom(value.getClass())) {
            return (R) value;
        } else if (result.getClass().equals(Address.class) && returnType.equals(String.class)) {
            return (R) result.toString();  // cast isn't necessary
        } else {
            throw new ContractCallException(
                    "Unable to convert response: " + value
                            + " to expected type: " + returnType.getSimpleName());
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends Type, R> R executeCallSingleValueReturn(
            Function function, Class<R> returnType,  BigInteger number) throws IOException {
        T result = executeCallSingleValueReturn(function,number);
        if (result == null) {
            throw new ContractCallException("Empty value (0x) returned from contract");
        }

        Object value = result.getValue();
        if (returnType.isAssignableFrom(value.getClass())) {
            return (R) value;
        } else if (result.getClass().equals(Address.class) && returnType.equals(String.class)) {
            return (R) result.toString();  // cast isn't necessary
        } else {
            throw new ContractCallException(
                    "Unable to convert response: " + value
                            + " to expected type: " + returnType.getSimpleName());
        }
    }





    protected List<Type> executeCallMultipleValueReturn(
            Function function) throws IOException {
        return executeCall(function);
    }

    protected TransactionReceipt executeTransaction(
            Function function)
            throws IOException, TransactionException {
        return executeTransaction(function, BigInteger.ZERO);
    }

    private TransactionReceipt executeTransaction(Function function, BigInteger weiValue) throws IOException, TransactionException {

        Boolean isCNS = this.isCns == BigInteger.ONE;
        String invokeData;

        if (isCNS){
            invokeData = VenachainUtil.invokeEncode(this.cnsName,function);
            return executeTransaction(invokeData , weiValue, function.getName());
        }else{
            invokeData = VenachainUtil.invokeEncode(function);
        }
        return executeTransaction(invokeData , weiValue, function.getName());
    }

    private TransactionReceipt executeTransactionWithNonce(Function function, BigInteger nonce) throws IOException, TransactionException {

        Boolean isCNS = this.isCns == BigInteger.ONE;
        String invokeData;

        if (isCNS){
            invokeData = VenachainUtil.invokeEncode(this.cnsName,function);
            return executeTransactionWithNonce(invokeData , nonce, function.getName());
        }else{
            invokeData = VenachainUtil.invokeEncode(function);
        }

        return executeTransactionWithNonce(invokeData , nonce, function.getName());
    }

    /**
     * Given the duration required to execute a transaction.
     *
     * @param data  to send in transaction
     * @param weiValue in Wei to send in transaction
     * @return {@link Optional} containing our transaction receipt
     * @throws IOException                 if the call to the node fails
     * @throws TransactionException if the transaction was not mined while waiting
     */
    TransactionReceipt executeTransaction(
            String data, BigInteger weiValue, String funcName)
            throws TransactionException, IOException {

        TransactionReceipt receipt = send(contractAddress, data, weiValue,
                gasProvider.getGasPrice(funcName),
                gasProvider.getGasLimit(funcName));

        return receipt;
    }

    TransactionReceipt executeTransactionWithNonce(
            String data, BigInteger nonce, String funcName)
            throws TransactionException, IOException {

        TransactionReceipt receipt = sendWithNonce(contractAddress, data, BigInteger.ZERO,
                gasProvider.getGasPrice(funcName),
                gasProvider.getGasLimit(funcName),
                nonce);

        return receipt;
    }

    protected <T extends Type> RemoteCall<T> executeRemoteCallSingleValueReturn(Function function) {
        return new RemoteCall<>(() -> executeCallSingleValueReturn(function));
    }

    protected <T> RemoteCall<T> executeRemoteCallSingleValueReturn(
            Function function, Class<T> returnType) {
        return new RemoteCall<>(() -> executeCallSingleValueReturn(function, returnType));
    }

    protected <T> RemoteCall<T> executeRemoteCallSingleValueReturn(
            Function function, Class<T> returnType, BigInteger number) {
        return new RemoteCall<>(() -> executeCallSingleValueReturn(function, returnType, number));
    }


    protected RemoteCall<List<Type>> executeRemoteCallMultipleValueReturn(Function function) {
        return new RemoteCall<>(() -> executeCallMultipleValueReturn(function));
    }

    protected RemoteCall<TransactionReceipt> executeRemoteCallTransaction(Function function) {
        return new RemoteCall<>(() -> executeTransaction(function));
    }

    protected RemoteCall<TransactionReceipt> executeRemoteCallTransactionWithNonce(Function function, BigInteger nonce) {
        return new RemoteCall<>(() -> executeTransactionWithNonce(function, nonce));
    }

    protected RemoteCall<TransactionReceipt> executeRemoteCallTransaction(
            Function function, BigInteger weiValue) {
        return new RemoteCall<>(() -> executeTransaction(function, weiValue));
    }

    private static <T extends VenachainContract> T create(
            T contract, String binary, String abi, String encodedConstructor, BigInteger value)
            throws IOException, TransactionException {

        String data = VenachainUtil.deployEncode(binary, abi);

        TransactionReceipt transactionReceipt =
                contract.executeTransaction(data, value, FUNC_DEPLOY);

        String contractAddress = transactionReceipt.getContractAddress();
        if (contractAddress == null) {
            throw new RuntimeException("Empty contract address returned");
        }
        contract.setContractAddress(contractAddress);
        contract.setTransactionReceipt(transactionReceipt);

        return contract;
    }

    protected static <T extends VenachainContract> T deploy(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor, BigInteger value)
            throws RuntimeException, TransactionException {

        try {
            Constructor<T> constructor = type.getDeclaredConstructor(
                    String.class,
                    String.class,
                    Web3j.class, Credentials.class,
                    ContractGasProvider.class);
            constructor.setAccessible(true);

            // we want to use null here to ensure that "to" parameter on message is not populated
            T contract = constructor.newInstance(binary, null, web3j, credentials, contractGasProvider);

            return create(contract, binary, abi, encodedConstructor, value);
        } catch (TransactionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static <T extends VenachainContract> T deploy(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor, BigInteger value)
            throws RuntimeException, TransactionException {

        try {
            Constructor<T> constructor = type.getDeclaredConstructor(
                    String.class,
                    String.class,
                    Web3j.class, TransactionManager.class,
                    ContractGasProvider.class);
            constructor.setAccessible(true);

            // we want to use null here to ensure that "to" parameter on message is not populated
            T contract = constructor.newInstance(binary,null, web3j, transactionManager, contractGasProvider);
            return create(contract, binary, abi, encodedConstructor, value);
        } catch (TransactionException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    protected static <T extends VenachainContract> T deploy(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor, BigInteger value)
            throws RuntimeException, TransactionException {

        return deploy(type, web3j, credentials,
                new StaticGasProvider(gasPrice, gasLimit),
                binary, abi, encodedConstructor, value);
    }

    @Deprecated
    protected static <T extends VenachainContract> T deploy(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor, BigInteger value)
            throws RuntimeException, TransactionException {

        return deploy(type, web3j, transactionManager,
                new StaticGasProvider(gasPrice, gasLimit),
                binary, abi, encodedConstructor, value);
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor, BigInteger value) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, credentials, gasPrice, gasLimit, binary, abi,
                encodedConstructor, value));
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor) {
        return deployRemoteCall(
                type, web3j, credentials, gasPrice, gasLimit,
                binary, abi, encodedConstructor, BigInteger.ZERO);
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor, BigInteger value) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, credentials, contractGasProvider, binary, abi,
                encodedConstructor, value));
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, credentials, contractGasProvider, binary, abi,
                encodedConstructor, BigInteger.ZERO));
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor, BigInteger value) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, transactionManager, gasPrice, gasLimit, binary, abi,
                encodedConstructor, value));
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit,
            String binary, String abi, String encodedConstructor) {
        return deployRemoteCall(
                type, web3j, transactionManager, gasPrice, gasLimit, binary, abi,
                encodedConstructor, BigInteger.ZERO);
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor, BigInteger value) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, transactionManager, contractGasProvider, binary, abi,
                encodedConstructor, value));
    }

    public static <T extends VenachainContract> RemoteCall<T> deployRemoteCall(
            Class<T> type,
            Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider,
            String binary, String abi, String encodedConstructor) {
        return new RemoteCall<>(() -> deploy(
                type, web3j, transactionManager, contractGasProvider, binary, abi,
                encodedConstructor, BigInteger.ZERO));
    }

    public static EventValues staticExtractEventParameters(
            Event event, Log log) {

        List<String> topics = log.getTopics();
        String encodedEventSignature = VenachainEventEncoder.encode(event);
        if (!topics.get(0).equals(encodedEventSignature)) {
            return null;
        }

        List<Type> indexedValues = new ArrayList<>();
        List<Type> nonIndexedValues = VenachainUtil.eventDecode(log.getData(), event.getNonIndexedParameters());

        List<TypeReference<Type>> indexedParameters = event.getIndexedParameters();
        for (int i = 0; i < indexedParameters.size(); i++) {
            Type value = FunctionReturnDecoder.decodeIndexedValue(
                    topics.get(i + 1), indexedParameters.get(i));
            indexedValues.add(value);
        }
        return new EventValues(indexedValues, nonIndexedValues);
    }

    public static EventValues staticExtractEventParameters(
            List<Event> events, Log log) {

        List<String> topics = log.getTopics();
        for (Event event : events) {
            String encodedEventSignature = VenachainEventEncoder.encode(event);
            if (topics.get(0).equals(encodedEventSignature)) {
                List<Type> indexedValues = new ArrayList<>();
                List<Type> nonIndexedValues = VenachainUtil.eventDecode(log.getData(), event.getNonIndexedParameters());

                List<TypeReference<Type>> indexedParameters = event.getIndexedParameters();
                for (int i = 0; i < indexedParameters.size(); i++) {
                    Type value = FunctionReturnDecoder.decodeIndexedValue(
                            topics.get(i + 1), indexedParameters.get(i));
                    indexedValues.add(value);
                }
                return new EventValues(indexedValues, nonIndexedValues);
            }
        }
        return new EventValues(new ArrayList<>(), new ArrayList<>());
    }

    protected EventValues extractEventParameters(Event event, Log log) {
        return staticExtractEventParameters(event, log);
    }

    protected List<EventValues> extractEventParameters(
            Event event, TransactionReceipt transactionReceipt) {
        return transactionReceipt.getLogs().stream()
                .map(log -> extractEventParameters(event, log))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected EventValuesWithLog extractEventParametersWithLog(Event event, Log log) {
        final EventValues eventValues = staticExtractEventParameters(event, log);
        return (eventValues == null) ? null : new EventValuesWithLog(eventValues, log);
    }

    protected EventValuesWithLog extractEventParametersWithLog(List<Event> events, Log log) {
        final EventValues eventValues = staticExtractEventParameters(events, log);
        return new EventValuesWithLog(eventValues, log);
    }

    protected List<EventValuesWithLog> extractEventParametersWithLog(
            Event event, TransactionReceipt transactionReceipt) {
        return transactionReceipt.getLogs().stream()
                .map(log -> extractEventParametersWithLog(event, log))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    protected List<EventValuesWithLog> extractEventParametersWithLog(
            List<Event> events, TransactionReceipt transactionReceipt) {
        return transactionReceipt.getLogs().stream()
                .map(log -> extractEventParametersWithLog(events, log))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 订阅事件，选取特定的block进行监听。
     *
     * @param filter 选择具体监听的block
     * @param funcName 接口名称
     * @return {@link Optional} 节点的区块数据推送
     */
    protected Observable<EventResponse> eventObservable(EthFilter filter, String funcName) {
        return web3j.ethLogObservable(filter).map(log -> {
            Event event = new Event(funcName, Arrays.asList(new TypeReference<Int32>() {}, new TypeReference<Utf8String>() {}));
            EventValuesWithLog eventValues = extractEventParametersWithLog(event, log);

            assert eventValues.getNonIndexedValues().size() == 2;
            EventResponse typedResponse = new EventResponse();
            typedResponse.setLog(log);
            typedResponse.setCode((BigInteger) eventValues.getNonIndexedValues().get(0).getValue());
            typedResponse.setMsg((String) eventValues.getNonIndexedValues().get(1).getValue());
            return typedResponse;
        });
    }

    /**
     * 订阅事件，选取特定的block进行监听。
     *
     * @param filter 选择具体监听的block
     * @param funcNames 接口名称列表
     * @return {@link Optional} 节点的区块数据推送
     */
    protected Observable<EventResponse> eventObservable(EthFilter filter, String[] funcNames) {
        return web3j.ethLogObservable(filter).map(log -> {
            List<Event> events = new ArrayList<>();
            for (String funcName : funcNames) {
                Event event = new Event(funcName, Arrays.asList(new TypeReference<Int32>() {}, new TypeReference<Utf8String>() {}));
                events.add(event);
            }

            EventValuesWithLog eventValues = extractEventParametersWithLog(events, log);

            assert eventValues.getNonIndexedValues().size() == 2;
            EventResponse typedResponse = new EventResponse();
            typedResponse.setLog(log);
            typedResponse.setCode((BigInteger) eventValues.getNonIndexedValues().get(0).getValue());
            typedResponse.setMsg((String) eventValues.getNonIndexedValues().get(1).getValue());
            return typedResponse;
        });
    }

    /**
     * 根据交易回执获取事件内容。
     *
     * @param transactionReceipt 交易回执
     * @param funcName 接口名称
     * @return {@link Optional} 事件信息
     */
    protected List<EventResponse> eventResponses(TransactionReceipt transactionReceipt, String funcName) {
        Event event = new Event(funcName, Arrays.asList(new TypeReference<Int32>() {}, new TypeReference<Utf8String>() {}));
        return getEventResponses(extractEventParametersWithLog(event, transactionReceipt));
    }

    /**
     * 根据交易回执获取事件内容。
     *
     * @param transactionReceipt 交易回执
     * @param funcNames 接口名称列表
     * @return {@link Optional} 事件信息
     */
    protected List<EventResponse> eventResponses(TransactionReceipt transactionReceipt, String[] funcNames) {
        List<Event> events = new ArrayList<>();
        for (String funcName : funcNames) {
            Event event = new Event(funcName, Arrays.asList(new TypeReference<Int32>() {}, new TypeReference<Utf8String>() {}));
            events.add(event);
        }
        return getEventResponses(extractEventParametersWithLog(events, transactionReceipt));
    }

    private ArrayList<EventResponse> getEventResponses(List<VenachainContract.EventValuesWithLog> valueList) {
        ArrayList<EventResponse> responses = new ArrayList<>(valueList.size());
        for (VenachainContract.EventValuesWithLog eventValues : valueList) {
            assert eventValues.getNonIndexedValues().size() == 2;

            EventResponse typedResponse = new EventResponse();
            typedResponse.setLog(eventValues.getLog());
            typedResponse.setCode((BigInteger) eventValues.getNonIndexedValues().get(0).getValue());
            typedResponse.setMsg((String) eventValues.getNonIndexedValues().get(1).getValue());
            responses.add(typedResponse);
        }
        return responses;
    }

    /**
     * Subclasses should implement this method to return pre-existing addresses for deployed
     * contracts.
     *
     * @param networkId the network id, for example "1" for the main-net, "3" for ropsten, etc.
     * @return the deployed address of the contract, if known, and null otherwise.
     */
    protected String getStaticDeployedAddress(String networkId) {
        return null;
    }

    public final void setDeployedAddress(String networkId, String address) {
        if (deployedAddresses == null) {
            deployedAddresses = new HashMap<>();
        }
        deployedAddresses.put(networkId, address);
    }

    public final String getDeployedAddress(String networkId) {
        String addr = null;
        if (deployedAddresses != null) {
            addr = deployedAddresses.get(networkId);
        }
        return addr == null ? getStaticDeployedAddress(networkId) : addr;
    }

    /**
     * Adds a log field to {@link EventValues}.
     */
    public static class EventValuesWithLog {
        private final EventValues eventValues;
        private final Log log;

        private EventValuesWithLog(EventValues eventValues, Log log) {
            this.eventValues = eventValues;
            this.log = log;
        }

        public List<Type> getIndexedValues() {
            return eventValues.getIndexedValues();
        }

        public List<Type> getNonIndexedValues() {
            return eventValues.getNonIndexedValues();
        }

        public Log getLog() {
            return log;
        }
    }

    @SuppressWarnings("unchecked")
    protected static <S extends Type, T>
    List<T> convertToNative(List<S> arr) {
        List<T> out = new ArrayList<T>();
        for (Iterator<S> it = arr.iterator(); it.hasNext(); ) {
            out.add((T)it.next().getValue());
        }
        return out;
    }
}
