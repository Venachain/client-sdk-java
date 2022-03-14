package org.web3j.precompiled;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Int32;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.EventResponse;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.VenachainContract;
import org.web3j.tx.gas.ContractGasProvider;
import rx.Observable;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParamManagement extends VenachainContract {

    public static final String CONTRACT_ADDRESS = "0x1000000000000000000000000000000000000004";

    public static final String FUNC_SETGASCONTRACTNAME = "setGasContractName";
    public static final String FUNC_GETGASCONTRACTNAME = "getGasContractName";
    public static final String GasContractNameKey = "GasContractName";

    public static final String FUNC_SETISPRODUCEEMPTYBLOCK = "setIsProduceEmptyBlock";
    public static final String FUNC_GETISPRODUCEEMPTYBLOCK = "getIsProduceEmptyBlock";
    public static final String IsProduceEmptyBlockKey = "IsProduceEmptyBlock";

    public static final String FUNC_SETTXGASLIMIT = "setTxGasLimit";
    public static final String FUNC_GETTXGASLIMIT = "getTxGasLimit";
    public static final String TxGasLimitKey = "TxGasLimit";

    public static final String FUNC_SETBLOCKGASLIMIT = "setBlockGasLimit";
    public static final String FUNC_GETBLOCKGASLIMIT = "getBlockGasLimit";
    public static final String BlockGasLimitKey = "BlockGasLimit";

    public static final String FUNC_SETCHECKCONTRACTDEPLOYPERMISSION = "setCheckContractDeployPermission";
    public static final String FUNC_GETCHECKCONTRACTDEPLOYPERMISSION = "getCheckContractDeployPermission";
    public static final String IsCheckContractDeployPermissionKey = "IsCheckContractDeployPermission";

    public static final String FUNC_SETISAPPROVEDEPLOYEEDCONTRACT = "setIsApproveDeployedContract";
    public static final String FUNC_GETISAPPROVEDEPLOYEEDCONTRACT = "getIsApproveDeployedContract";
    public static final String IsApproveDeployedContractKey = "IsApproveDeployedContract";

    public static final String FUNC_SETISTXUSEGAS = "setIsTxUseGas";
    public static final String FUNC_GETISTXUSEGAS = "getIsTxUseGas";
    public static final String IsTxUseGasKey = "IsTxUseGas";

    public static final String FUNC_SETVRFPARAMS = "setVRFParams";
    public static final String FUNC_GETVRFPARAMS = "getVRFParams";
    public static final String VrfParamsKey = "VRFParams";


    protected ParamManagement(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ParamManagement load(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ParamManagement("", CONTRACT_ADDRESS, web3j, credentials, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setGasContractName(String name) {
        final Function function = new Function(FUNC_SETGASCONTRACTNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setGasContractNameObservable(EthFilter filter) {
        return eventObservable(filter, GasContractNameKey);
    }

    public List<EventResponse> getSetGasContractNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, GasContractNameKey);
    }

    public RemoteCall<String> getGasContractName() {
        final Function function = new Function(FUNC_GETGASCONTRACTNAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> setIsProduceEmptyBlock(BigInteger value) {
        final Function function = new Function(FUNC_SETISPRODUCEEMPTYBLOCK,
                Arrays.<Type>asList(new Uint32(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setIsProduceEmptyBlockObservable(EthFilter filter) {
        return eventObservable(filter, IsProduceEmptyBlockKey);
    }

    public List<EventResponse> getSetIsProduceEmptyBlockEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, IsProduceEmptyBlockKey);
    }

    public RemoteCall<Boolean> getIsProduceEmptyBlock() {
        final Function function = new Function(FUNC_GETISPRODUCEEMPTYBLOCK,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setTxGasLimit(BigInteger value) {
        final Function function = new Function(FUNC_SETTXGASLIMIT,
                Arrays.<Type>asList(new Uint64(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setTxGasLimitObservable(EthFilter filter) {
        return eventObservable(filter, TxGasLimitKey);
    }

    public List<EventResponse> getSetTxGasLimitEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, TxGasLimitKey);
    }

    public RemoteCall<BigInteger> getTxGasLimit() {
        final Function function = new Function(FUNC_GETTXGASLIMIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setBlockGasLimit(BigInteger value) {
        final Function function = new Function(FUNC_SETBLOCKGASLIMIT,
                Arrays.<Type>asList(new Uint64(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setBlockGasLimitObservable(EthFilter filter) {
        return eventObservable(filter, BlockGasLimitKey);
    }

    public List<EventResponse> getSetBlockGasLimitEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, BlockGasLimitKey);
    }

    public RemoteCall<BigInteger> getBlockGasLimit() {
        final Function function = new Function(FUNC_GETBLOCKGASLIMIT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setCheckContractDeployPermission(BigInteger value) {
        final Function function = new Function(FUNC_SETCHECKCONTRACTDEPLOYPERMISSION,
                Arrays.<Type>asList(new Uint32(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setCheckContractDeployPermissionObservable(EthFilter filter) {
        return eventObservable(filter, IsCheckContractDeployPermissionKey);
    }

    public List<EventResponse> getSetCheckContractDeployPermissionEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, IsCheckContractDeployPermissionKey);
    }

    public RemoteCall<BigInteger> getCheckContractDeployPermission() {
        final Function function = new Function(FUNC_GETCHECKCONTRACTDEPLOYPERMISSION,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setIsApproveDeployedContract(BigInteger value) {
        final Function function = new Function(FUNC_SETISAPPROVEDEPLOYEEDCONTRACT,
                Arrays.<Type>asList(new Uint32(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setIsApproveDeployedContractObservable(EthFilter filter) {
        return eventObservable(filter, IsApproveDeployedContractKey);
    }

    public List<EventResponse> getSetIsApproveDeployedContractEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, IsApproveDeployedContractKey);
    }

    public RemoteCall<Boolean> getIsApproveDeployedContract() {
        final Function function = new Function(FUNC_GETISAPPROVEDEPLOYEEDCONTRACT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> setIsTxUseGas(BigInteger value) {
        final Function function = new Function(FUNC_SETISTXUSEGAS,
                Arrays.<Type>asList(new Uint32(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setIsTxUseGasObservable(EthFilter filter) {
        return eventObservable(filter, IsTxUseGasKey);
    }

    public List<EventResponse> getSetIsTxUseGasEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, IsTxUseGasKey);
    }

    public RemoteCall<Boolean> getIsTxUseGas() {
        final Function function = new Function(FUNC_GETISTXUSEGAS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }


    public RemoteCall<TransactionReceipt> setVRFParams(String vrfParams) {
        final Function function = new Function(FUNC_SETVRFPARAMS,
                Arrays.<Type>asList(new Utf8String(vrfParams)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setVRFParamsObservable(EthFilter filter) {
        return eventObservable(filter, VrfParamsKey);
    }

    public List<EventResponse> getSetVRFParamsEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, VrfParamsKey);
    }

    public RemoteCall<String> getVRFParams() {
        final Function function = new Function(FUNC_GETVRFPARAMS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
