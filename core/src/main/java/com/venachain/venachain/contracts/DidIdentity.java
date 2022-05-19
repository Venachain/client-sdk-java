package com.venachain.venachain.contracts;

import com.venachain.abi.EventEncoder;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Event;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.abi.datatypes.generated.Int32;
import com.venachain.abi.datatypes.generated.Int64;
import com.venachain.crypto.Credentials;
import com.venachain.protocol.Web3j;
import com.venachain.protocol.core.DefaultBlockParameter;
import com.venachain.protocol.core.RemoteCall;
import com.venachain.protocol.core.methods.request.EthFilter;
import com.venachain.protocol.core.methods.response.Log;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.tx.VenachainContract;
import com.venachain.tx.TransactionManager;
import com.venachain.tx.gas.ContractGasProvider;
import com.venachain.utils.VenachainUtil;
import rx.Observable;
import rx.functions.Func1;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the com.venachain.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.4.1.
 */
public class DidIdentity extends VenachainContract {
    private static final String ABI = "[{\"name\":\"ensurePermissionByDid\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int64\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"did_create\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"did_doc\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"get_did\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"did_status_query\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"did_delete\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"main_public_key_update\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"public_key\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"recovery_public_key_update\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"public_key\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"service_update\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"string\"},{\"name\":\"new_service\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"service_add\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"new_service\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"service_delete\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"id\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"proof_update\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"index\",\"type\":\"int32\"},{\"name\":\"new_proof\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"proof_add\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"new_proof\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"proof_delete\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"index\",\"type\":\"int32\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"Notify\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"int32\"}],\"type\":\"event\"}]";

    public static final String FUNC_ENSUREPERMISSIONBYDID = "ensurePermissionByDid";

    public static final String FUNC_DID_CREATE = "did_create";

    public static final String FUNC_GET_DID = "get_did";

    public static final String FUNC_DID_STATUS_QUERY = "did_status_query";

    public static final String FUNC_DID_DELETE = "did_delete";

    public static final String FUNC_MAIN_PUBLIC_KEY_UPDATE = "main_public_key_update";

    public static final String FUNC_RECOVERY_PUBLIC_KEY_UPDATE = "recovery_public_key_update";

    public static final String FUNC_SERVICE_UPDATE = "service_update";

    public static final String FUNC_SERVICE_ADD = "service_add";

    public static final String FUNC_SERVICE_DELETE = "service_delete";

    public static final String FUNC_PROOF_UPDATE = "proof_update";

    public static final String FUNC_PROOF_ADD = "proof_add";

    public static final String FUNC_PROOF_DELETE = "proof_delete";

    public static final Event NOTIFY_EVENT = new Event("Notify", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int32>() {}));
    ;

    protected DidIdentity(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    protected DidIdentity(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> ensurePermissionByDid(String did) {
        final Function function = new Function(FUNC_ENSUREPERMISSIONBYDID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static String ensurePermissionByDidData(String did) {
        final Function function = new Function(FUNC_ENSUREPERMISSIONBYDID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int64>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function ensurePermissionByDidFunction(String did) {
        final Function function = new Function(FUNC_ENSUREPERMISSIONBYDID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int64>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> didCreate(String did, String did_doc) {
        final Function function = new Function(
                FUNC_DID_CREATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(did_doc)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> didCreateWithNonce(String did, String did_doc, BigInteger nonce) {
        final Function function = new Function(
                FUNC_DID_CREATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(did_doc)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String didCreateData(String did, String did_doc) {
        final Function function = new Function(
                FUNC_DID_CREATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(did_doc)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger didCreateGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String did_doc) throws IOException {
        String ethEstimateGasData = didCreateData(did, did_doc);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<String> getDid(String did) {
        final Function function = new Function(FUNC_GET_DID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getDidData(String did) {
        final Function function = new Function(FUNC_GET_DID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getDidFunction(String did) {
        final Function function = new Function(FUNC_GET_DID, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> didStatusQuery(String did) {
        final Function function = new Function(FUNC_DID_STATUS_QUERY, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String didStatusQueryData(String did) {
        final Function function = new Function(FUNC_DID_STATUS_QUERY, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function didStatusQueryFunction(String did) {
        final Function function = new Function(FUNC_DID_STATUS_QUERY, 
                Arrays.<Type>asList(new Utf8String(did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> didDelete(String did) {
        final Function function = new Function(
                FUNC_DID_DELETE, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> didDeleteWithNonce(String did, BigInteger nonce) {
        final Function function = new Function(
                FUNC_DID_DELETE, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String didDeleteData(String did) {
        final Function function = new Function(
                FUNC_DID_DELETE, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger didDeleteGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did) throws IOException {
        String ethEstimateGasData = didDeleteData(did);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> mainPublicKeyUpdate(String did, String public_key) {
        final Function function = new Function(
                FUNC_MAIN_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mainPublicKeyUpdateWithNonce(String did, String public_key, BigInteger nonce) {
        final Function function = new Function(
                FUNC_MAIN_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String mainPublicKeyUpdateData(String did, String public_key) {
        final Function function = new Function(
                FUNC_MAIN_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger mainPublicKeyUpdateGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String public_key) throws IOException {
        String ethEstimateGasData = mainPublicKeyUpdateData(did, public_key);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> recoveryPublicKeyUpdate(String did, String public_key) {
        final Function function = new Function(
                FUNC_RECOVERY_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> recoveryPublicKeyUpdateWithNonce(String did, String public_key, BigInteger nonce) {
        final Function function = new Function(
                FUNC_RECOVERY_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String recoveryPublicKeyUpdateData(String did, String public_key) {
        final Function function = new Function(
                FUNC_RECOVERY_PUBLIC_KEY_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(public_key)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger recoveryPublicKeyUpdateGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String public_key) throws IOException {
        String ethEstimateGasData = recoveryPublicKeyUpdateData(did, public_key);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> serviceUpdate(String did, String id, String new_service) {
        final Function function = new Function(
                FUNC_SERVICE_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> serviceUpdateWithNonce(String did, String id, String new_service, BigInteger nonce) {
        final Function function = new Function(
                FUNC_SERVICE_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String serviceUpdateData(String did, String id, String new_service) {
        final Function function = new Function(
                FUNC_SERVICE_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger serviceUpdateGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String id, String new_service) throws IOException {
        String ethEstimateGasData = serviceUpdateData(did, id, new_service);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> serviceAdd(String did, String new_service) {
        final Function function = new Function(
                FUNC_SERVICE_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> serviceAddWithNonce(String did, String new_service, BigInteger nonce) {
        final Function function = new Function(
                FUNC_SERVICE_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String serviceAddData(String did, String new_service) {
        final Function function = new Function(
                FUNC_SERVICE_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_service)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger serviceAddGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String new_service) throws IOException {
        String ethEstimateGasData = serviceAddData(did, new_service);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> serviceDelete(String did, String id) {
        final Function function = new Function(
                FUNC_SERVICE_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> serviceDeleteWithNonce(String did, String id, BigInteger nonce) {
        final Function function = new Function(
                FUNC_SERVICE_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String serviceDeleteData(String did, String id) {
        final Function function = new Function(
                FUNC_SERVICE_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(id)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger serviceDeleteGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String id) throws IOException {
        String ethEstimateGasData = serviceDeleteData(did, id);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> proofUpdate(String did, BigInteger index, String new_proof) {
        final Function function = new Function(
                FUNC_PROOF_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> proofUpdateWithNonce(String did, BigInteger index, String new_proof, BigInteger nonce) {
        final Function function = new Function(
                FUNC_PROOF_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String proofUpdateData(String did, BigInteger index, String new_proof) {
        final Function function = new Function(
                FUNC_PROOF_UPDATE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger proofUpdateGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, BigInteger index, String new_proof) throws IOException {
        String ethEstimateGasData = proofUpdateData(did, index, new_proof);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> proofAdd(String did, String new_proof) {
        final Function function = new Function(
                FUNC_PROOF_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> proofAddWithNonce(String did, String new_proof, BigInteger nonce) {
        final Function function = new Function(
                FUNC_PROOF_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String proofAddData(String did, String new_proof) {
        final Function function = new Function(
                FUNC_PROOF_ADD, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(new_proof)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger proofAddGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String new_proof) throws IOException {
        String ethEstimateGasData = proofAddData(did, new_proof);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> proofDelete(String did, BigInteger index) {
        final Function function = new Function(
                FUNC_PROOF_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> proofDeleteWithNonce(String did, BigInteger index, BigInteger nonce) {
        final Function function = new Function(
                FUNC_PROOF_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String proofDeleteData(String did, BigInteger index) {
        final Function function = new Function(
                FUNC_PROOF_DELETE, 
                Arrays.<Type>asList(new Utf8String(did),
                new Int32(index)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger proofDeleteGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, BigInteger index) throws IOException {
        String ethEstimateGasData = proofDeleteData(did, index);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public List<NotifyEventResponse> getNotifyEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(NOTIFY_EVENT, transactionReceipt);
        ArrayList<NotifyEventResponse> responses = new ArrayList<NotifyEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NotifyEventResponse typedResponse = new NotifyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NotifyEventResponse> notifyEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NotifyEventResponse>() {
            @Override
            public NotifyEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(NOTIFY_EVENT, log);
                NotifyEventResponse typedResponse = new NotifyEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NotifyEventResponse> notifyEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NOTIFY_EVENT));
        return notifyEventObservable(filter);
    }

    public static RemoteCall<DidIdentity> deploy(Web3j web3j, Credentials credentials, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DidIdentity.class, web3j, credentials, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<DidIdentity> deploy(Web3j web3j, Credentials credentials, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DidIdentity.class, web3j, credentials, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    public static RemoteCall<DidIdentity> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DidIdentity.class, web3j, transactionManager, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<DidIdentity> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DidIdentity.class, web3j, transactionManager, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    public static DidIdentity load(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DidIdentity(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    public static DidIdentity load(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DidIdentity(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
    }

    public static String getDeployData(String contractBinary) {
        return VenachainUtil.deployEncode(contractBinary, ABI);
    }

    public static BigInteger getDeployGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String contractBinary) throws IOException {
        return VenachainUtil.estimateGasLimit(web3j, estimateGasFrom, estimateGasTo, getDeployData(contractBinary));
    }

    public static class NotifyEventResponse {
        public Log log;

        public String param1;

        public BigInteger param2;
    }
}
