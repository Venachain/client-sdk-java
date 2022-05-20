package com.venachain.precompiled;

import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.abi.datatypes.generated.Int32;
import com.venachain.abi.datatypes.generated.Uint32;
import com.venachain.crypto.Credentials;
import com.venachain.protocol.Web3j;
import com.venachain.protocol.core.EventResponse;
import com.venachain.protocol.core.RemoteCall;
import com.venachain.protocol.core.methods.request.EthFilter;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.tx.VenachainContract;
import com.venachain.tx.gas.ContractGasProvider;
import rx.Observable;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CnsManagement extends VenachainContract {

    public static final String CONTRACT_ADDRESS = "0x0000000000000000000000000000000000000011";

    public static final String FUNC_CNSREGISTER = "cnsRegister";
    public static final String FUNC_CNSREDIRECT = "cnsRedirect";
    public static final String FUNC_GETCONTRACTADDRESS = "getContractAddress";
    public static final String FUNC_IFREGISTEREDBYNAME = "ifRegisteredByName";
    public static final String FUNC_IFREGISTEREDBYADDRESS = "ifRegisteredByAddress";
    public static final String FUNC_GETREGISTEREDCONTRACTS = "getRegisteredContracts";
    public static final String FUNC_GETREGISTEREDCONTRACTSBYNAME = "getRegisteredContractsByName";
    public static final String FUNC_GETREGISTEREDCONTRACTSBYADDRESS = "getRegisteredContractsByAddress";
    public static final String FUNC_GETREGISTEREDCONTRACTSBYORIGIN = "getRegisteredContractsByOrigin";
    public static final String FUNC_IMPORTOLDCNSMANAGERDATA = "importOldCnsManagerData";
    public static final String CNSNotifyEventKey = "[CNS] Notify";
    public static final String NotifyEventKey = "Notify";

    protected CnsManagement(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CnsManagement load(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CnsManagement("", CONTRACT_ADDRESS, web3j, credentials, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> cnsRegister(String contractName, String contractVersion, String contractAddress) {
        final Function function = new Function(FUNC_CNSREGISTER,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(contractVersion),
                        new Utf8String(contractAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> cnsRegisterObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_CNSREGISTER, NotifyEventKey, CNSNotifyEventKey});
    }

    public List<EventResponse> getCnsRegisterEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_CNSREGISTER, NotifyEventKey, CNSNotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> cnsRedirect(String contractName, String contractVersion) {
        final Function function = new Function(FUNC_CNSREDIRECT,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(contractVersion)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> cnsRedirectObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_CNSREDIRECT, NotifyEventKey, CNSNotifyEventKey});
    }

    public List<EventResponse> getCnsRedirectEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_CNSREDIRECT, NotifyEventKey, CNSNotifyEventKey});
    }

    public RemoteCall<String> getContractAddress(String contractName, String contractVersion) {
        final Function function = new Function(FUNC_GETCONTRACTADDRESS,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(contractVersion)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> ifRegisteredByName(String contractName) {
        final Function function = new Function(FUNC_IFREGISTEREDBYNAME,
                Arrays.<Type>asList(new Utf8String(contractName)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ifRegisteredByAddress(String contractAddress) {
        final Function function = new Function(FUNC_IFREGISTEREDBYADDRESS,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getRegisteredContracts(int startIndex, int endIndex) {
        final Function function = new Function(FUNC_GETREGISTEREDCONTRACTS,
                Arrays.<Type>asList(new Int32(startIndex),
                        new Int32(endIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getRegisteredContractsByAddress(String contractAddress) {
        final Function function = new Function(FUNC_GETREGISTEREDCONTRACTSBYADDRESS,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getRegisteredContractsByName(String contractName) {
        final Function function = new Function(FUNC_GETREGISTEREDCONTRACTSBYNAME,
                Arrays.<Type>asList(new Utf8String(contractName)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getRegisteredContractsByOrigin(String origin) {
        final Function function = new Function(FUNC_GETREGISTEREDCONTRACTSBYORIGIN,
                Arrays.<Type>asList(new Utf8String(origin)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> importOldCnsManagerData(String oldCnsManagerData) {
        final Function function = new Function(FUNC_IMPORTOLDCNSMANAGERDATA,
                Arrays.<Type>asList(new Utf8String(oldCnsManagerData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> importOldCnsManagerDataObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_IMPORTOLDCNSMANAGERDATA, NotifyEventKey, CNSNotifyEventKey});
    }

    public List<EventResponse> getImportOldCnsManagerDataEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_IMPORTOLDCNSMANAGERDATA, NotifyEventKey, CNSNotifyEventKey});
    }
}
