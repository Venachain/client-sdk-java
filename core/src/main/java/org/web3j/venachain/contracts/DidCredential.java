package org.web3j.venachain.contracts;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.VenachainContract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.VenachainUtil;
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
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 0.4.1.
 */
public class DidCredential extends VenachainContract {
    private static final String ABI = "[{\"name\":\"request_to_be_issuer\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"},{\"name\":\"issuerJson\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"approve_issuer\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"delete_issuer\",\"inputs\":[{\"name\":\"did\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"request_credential\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"},{\"name\":\"self_did\",\"type\":\"string\"},{\"name\":\"serviceType\",\"type\":\"int32\"},{\"name\":\"path\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"approve_credential\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"},{\"name\":\"self_did\",\"type\":\"string\"},{\"name\":\"serviceType\",\"type\":\"int32\"},{\"name\":\"path\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"burn_credential\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"},{\"name\":\"self_did\",\"type\":\"string\"},{\"name\":\"serviceType\",\"type\":\"int32\"},{\"name\":\"path\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"start_specific_service\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"},{\"name\":\"serviceType\",\"type\":\"int32\"},{\"name\":\"service_info\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"stop_specific_service\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"},{\"name\":\"serviceType\",\"type\":\"int32\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"get_credential_list_by_did\",\"inputs\":[{\"name\":\"user_did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"get_request_credential_list_by_issuerid\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"get_servicelist_by_did\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"get_issuer_list\",\"inputs\":[],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"get_issuer_by_did\",\"inputs\":[{\"name\":\"issuer_did\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"set_credential\",\"inputs\":[{\"name\":\"path\",\"type\":\"string\"},{\"name\":\"credentialContent\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"update_credential\",\"inputs\":[{\"name\":\"path\",\"type\":\"string\"},{\"name\":\"credentialContent\",\"type\":\"string\"}],\"outputs\":[],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"get_credential\",\"inputs\":[{\"name\":\"path\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"Notify\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"int32\"}],\"type\":\"event\"}]";

    public static final String FUNC_REQUEST_TO_BE_ISSUER = "request_to_be_issuer";

    public static final String FUNC_APPROVE_ISSUER = "approve_issuer";

    public static final String FUNC_DELETE_ISSUER = "delete_issuer";

    public static final String FUNC_REQUEST_CREDENTIAL = "request_credential";

    public static final String FUNC_APPROVE_CREDENTIAL = "approve_credential";

    public static final String FUNC_BURN_CREDENTIAL = "burn_credential";

    public static final String FUNC_START_SPECIFIC_SERVICE = "start_specific_service";

    public static final String FUNC_STOP_SPECIFIC_SERVICE = "stop_specific_service";

    public static final String FUNC_GET_CREDENTIAL_LIST_BY_DID = "get_credential_list_by_did";

    public static final String FUNC_GET_REQUEST_CREDENTIAL_LIST_BY_ISSUERID = "get_request_credential_list_by_issuerid";

    public static final String FUNC_GET_SERVICELIST_BY_DID = "get_servicelist_by_did";

    public static final String FUNC_GET_ISSUER_LIST = "get_issuer_list";

    public static final String FUNC_GET_ISSUER_BY_DID = "get_issuer_by_did";

    public static final String FUNC_SET_CREDENTIAL = "set_credential";

    public static final String FUNC_UPDATE_CREDENTIAL = "update_credential";

    public static final String FUNC_GET_CREDENTIAL = "get_credential";

    public static final Event NOTIFY_EVENT = new Event("Notify", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Int32>() {}));
    ;

    protected DidCredential(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    protected DidCredential(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> requestToBeIssuer(String did, String issuerJson) {
        final Function function = new Function(
                FUNC_REQUEST_TO_BE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(issuerJson)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestToBeIssuerWithNonce(String did, String issuerJson, BigInteger nonce) {
        final Function function = new Function(
                FUNC_REQUEST_TO_BE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(issuerJson)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String requestToBeIssuerData(String did, String issuerJson) {
        final Function function = new Function(
                FUNC_REQUEST_TO_BE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did),
                new Utf8String(issuerJson)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger request_to_be_issuerGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did, String issuerJson) throws IOException {
        String ethEstimateGasData = requestToBeIssuerData(did, issuerJson);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> approveIssuer(String did) {
        final Function function = new Function(
                FUNC_APPROVE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approveIssuerWithNonce(String did, BigInteger nonce) {
        final Function function = new Function(
                FUNC_APPROVE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String approveIssuerData(String did) {
        final Function function = new Function(
                FUNC_APPROVE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger approve_issuerGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did) throws IOException {
        String ethEstimateGasData = approveIssuerData(did);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> deleteIssuer(String did) {
        final Function function = new Function(
                FUNC_DELETE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deleteIssuerWithNonce(String did, BigInteger nonce) {
        final Function function = new Function(
                FUNC_DELETE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String deleteIssuerData(String did) {
        final Function function = new Function(
                FUNC_DELETE_ISSUER, 
                Arrays.<Type>asList(new Utf8String(did)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger delete_issuerGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String did) throws IOException {
        String ethEstimateGasData = deleteIssuerData(did);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> requestCredential(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_REQUEST_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> requestCredentialWithNonce(String issuer_did, String self_did, BigInteger serviceType, String path, BigInteger nonce) {
        final Function function = new Function(
                FUNC_REQUEST_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String requestCredentialData(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_REQUEST_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger requestCredentialGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String issuer_did, String self_did, BigInteger serviceType, String path) throws IOException {
        String ethEstimateGasData = requestCredentialData(issuer_did, self_did, serviceType, path);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> approveCredential(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_APPROVE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approveCredentialWithNonce(String issuer_did, String self_did, BigInteger serviceType, String path, BigInteger nonce) {
        final Function function = new Function(
                FUNC_APPROVE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String approveCredentialData(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_APPROVE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger approveCredentialGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String issuer_did, String self_did, BigInteger serviceType, String path) throws IOException {
        String ethEstimateGasData = approveCredentialData(issuer_did, self_did, serviceType, path);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> burnCredential(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_BURN_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> burnCredentialWithNonce(String issuer_did, String self_did, BigInteger serviceType, String path, BigInteger nonce) {
        final Function function = new Function(
                FUNC_BURN_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String burnCredentialData(String issuer_did, String self_did, BigInteger serviceType, String path) {
        final Function function = new Function(
                FUNC_BURN_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Utf8String(self_did),
                new Int32(serviceType),
                new Utf8String(path)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger burnCredentialGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String issuer_did, String self_did, BigInteger serviceType, String path) throws IOException {
        String ethEstimateGasData = burnCredentialData(issuer_did, self_did, serviceType, path);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> startSpecificService(String issuer_did, BigInteger serviceType, String service_info) {
        final Function function = new Function(
                FUNC_START_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType),
                new Utf8String(service_info)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> startSpecificServiceWithNonce(String issuer_did, BigInteger serviceType, String service_info, BigInteger nonce) {
        final Function function = new Function(
                FUNC_START_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType),
                new Utf8String(service_info)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String startSpecificServiceData(String issuer_did, BigInteger serviceType, String service_info) {
        final Function function = new Function(
                FUNC_START_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType),
                new Utf8String(service_info)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger start_specific_serviceGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String issuer_did, BigInteger serviceType, String service_info) throws IOException {
        String ethEstimateGasData = startSpecificServiceData(issuer_did, serviceType, service_info);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> stopSpecificService(String issuer_did, BigInteger serviceType) {
        final Function function = new Function(
                FUNC_STOP_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> stopSpecificServiceWithNonce(String issuer_did, BigInteger serviceType, BigInteger nonce) {
        final Function function = new Function(
                FUNC_STOP_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String stopSpecificServiceData(String issuer_did, BigInteger serviceType) {
        final Function function = new Function(
                FUNC_STOP_SPECIFIC_SERVICE, 
                Arrays.<Type>asList(new Utf8String(issuer_did),
                new Int32(serviceType)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger stopSpecificServiceGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String issuer_did, BigInteger serviceType) throws IOException {
        String ethEstimateGasData = stopSpecificServiceData(issuer_did, serviceType);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<String> getCredentialListByDid(String user_did) {
        final Function function = new Function(FUNC_GET_CREDENTIAL_LIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(user_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getCredentialListByDidData(String user_did) {
        final Function function = new Function(FUNC_GET_CREDENTIAL_LIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(user_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getCredentialListByDidFunction(String user_did) {
        final Function function = new Function(FUNC_GET_CREDENTIAL_LIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(user_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getRequestCredentialListByIssuerid(String issuer_did) {
        final Function function = new Function(FUNC_GET_REQUEST_CREDENTIAL_LIST_BY_ISSUERID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getRequestCredentialListByIssuerIdData(String issuer_did) {
        final Function function = new Function(FUNC_GET_REQUEST_CREDENTIAL_LIST_BY_ISSUERID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getRequestCredentialListByIssuerIdFunction(String issuer_did) {
        final Function function = new Function(FUNC_GET_REQUEST_CREDENTIAL_LIST_BY_ISSUERID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getServiceListByDid(String issuer_did) {
        final Function function = new Function(FUNC_GET_SERVICELIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getServiceListByDidData(String issuer_did) {
        final Function function = new Function(FUNC_GET_SERVICELIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getServiceListByDidFunction(String issuer_did) {
        final Function function = new Function(FUNC_GET_SERVICELIST_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getIssuerList() {
        final Function function = new Function(FUNC_GET_ISSUER_LIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getIssuerListData() {
        final Function function = new Function(FUNC_GET_ISSUER_LIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getIssuerListFunction() {
        final Function function = new Function(FUNC_GET_ISSUER_LIST, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getIssuerByDid(String issuer_did) {
        final Function function = new Function(FUNC_GET_ISSUER_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getIssuerByDidData(String issuer_did) {
        final Function function = new Function(FUNC_GET_ISSUER_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getIssuerByDidFunction(String issuer_did) {
        final Function function = new Function(FUNC_GET_ISSUER_BY_DID, 
                Arrays.<Type>asList(new Utf8String(issuer_did)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> setCredential(String path, String credentialContent) {
        final Function function = new Function(
                FUNC_SET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setCredentialWithNonce(String path, String credentialContent, BigInteger nonce) {
        final Function function = new Function(
                FUNC_SET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String setCredentialData(String path, String credentialContent) {
        final Function function = new Function(
                FUNC_SET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger setCredentialGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String path, String credentialContent) throws IOException {
        String ethEstimateGasData = setCredentialData(path, credentialContent);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> updateCredential(String path, String credentialContent) {
        final Function function = new Function(
                FUNC_UPDATE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateCredentialWithNonce(String path, String credentialContent, BigInteger nonce) {
        final Function function = new Function(
                FUNC_UPDATE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String updateCredentialData(String path, String credentialContent) {
        final Function function = new Function(
                FUNC_UPDATE_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path),
                new Utf8String(credentialContent)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger updateCredentialGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String path, String credentialContent) throws IOException {
        String ethEstimateGasData = updateCredentialData(path, credentialContent);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<String> getCredential(String path) {
        final Function function = new Function(FUNC_GET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getCredentialData(String path) {
        final Function function = new Function(FUNC_GET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getCredentialFunction(String path) {
        final Function function = new Function(FUNC_GET_CREDENTIAL, 
                Arrays.<Type>asList(new Utf8String(path)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
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

    public static RemoteCall<DidCredential> deploy(Web3j web3j, Credentials credentials, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DidCredential.class, web3j, credentials, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<DidCredential> deploy(Web3j web3j, Credentials credentials, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DidCredential.class, web3j, credentials, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    public static RemoteCall<DidCredential> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DidCredential.class, web3j, transactionManager, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<DidCredential> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DidCredential.class, web3j, transactionManager, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    public static DidCredential load(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DidCredential(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    public static DidCredential load(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DidCredential(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
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
