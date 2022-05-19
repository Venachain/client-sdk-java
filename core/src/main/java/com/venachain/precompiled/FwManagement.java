package com.venachain.precompiled;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.crypto.Credentials;
import com.venachain.protocol.Web3j;
import com.venachain.protocol.core.EventResponse;
import com.venachain.protocol.core.RemoteCall;
import com.venachain.protocol.core.methods.request.EthFilter;
import com.venachain.protocol.core.methods.response.TransactionReceipt;
import com.venachain.tx.VenachainContract;
import com.venachain.tx.gas.ContractGasProvider;
import rx.Observable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FwManagement extends VenachainContract {

    public static final String CONTRACT_ADDRESS = "0x1000000000000000000000000000000000000005";

    public static final String FUNC_SYSFWOPEN = "__sys_FwOpen";
    public static final String FUNC_SYSFWCLOSE = "__sys_FwClose";
    public static final String FUNC_SYSFWADD = "__sys_FwAdd";
    public static final String FUNC_SYSFWCLEAR = "__sys_FwClear";
    public static final String FUNC_SYSFWDEL = "__sys_FwDel";
    public static final String FUNC_SYSFWSET = "__sys_FwSet";
    public static final String FUNC_SYSFWSTATUS = "__sys_FwStatus";
    public static final String FUNC_SYSFWIMPORT = "__sys_FwImport";
    public static final String FUNC_SYSFWEXPORT = "__sys_FwExport";
    public static final String NotifyEventKey = "Notify";

    protected FwManagement(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static FwManagement load(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new FwManagement("", CONTRACT_ADDRESS, web3j, credentials, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> sysFwOpen(String contractAddress) {
        final Function function = new Function(FUNC_SYSFWOPEN,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwOpenObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWOPEN, NotifyEventKey});
    }

    public List<EventResponse> getSysFwOpenEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWOPEN, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> sysFwClose(String contractAddress) {
        final Function function = new Function(FUNC_SYSFWCLOSE,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwCloseObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWCLOSE, NotifyEventKey});
    }

    public List<EventResponse> getSysFwCloseEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWCLOSE, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> sysFwAdd(String contractName, String rule, String funcNames) {
        final Function function = new Function(FUNC_SYSFWADD,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(rule),
                        new Utf8String(funcNames)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwAddObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWADD, NotifyEventKey});
    }

    public List<EventResponse> getSysFwAddEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWADD, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> sysFwDel(String contractName, String rule, String funcNames) {
        final Function function = new Function(FUNC_SYSFWDEL,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(rule),
                        new Utf8String(funcNames)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwDelObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWDEL, NotifyEventKey});
    }

    public List<EventResponse> getSysFwDelEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWDEL, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> sysFwSet(String contractName, String rule, String funcNames) {
        final Function function = new Function(FUNC_SYSFWSET,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(rule),
                        new Utf8String(funcNames)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwSetObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWSET, NotifyEventKey});
    }

    public List<EventResponse> getSysFwSetEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWSET, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> sysFwClear(String contractName, String rule) {
        final Function function = new Function(FUNC_SYSFWCLEAR,
                Arrays.<Type>asList(new Utf8String(contractName),
                        new Utf8String(rule)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwClearObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWCLEAR, NotifyEventKey});
    }

    public List<EventResponse> getSysFwClearEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWCLEAR, NotifyEventKey});
    }

    public RemoteCall<String> sysFwStatus(String contractAddress) {
        final Function function = new Function(FUNC_SYSFWSTATUS,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> sysFwImport(String contractAddress, String config) {
        final Function function = new Function(FUNC_SYSFWIMPORT,
                Arrays.<Type>asList(new Utf8String(contractAddress),
                        new Utf8String(config)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> sysFwImportObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_SYSFWIMPORT, NotifyEventKey});
    }

    public List<EventResponse> getSysFwImportEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_SYSFWIMPORT, NotifyEventKey});
    }

    public RemoteCall<String> sysFwExport(String contractAddress) {
        final Function function = new Function(FUNC_SYSFWEXPORT,
                Arrays.<Type>asList(new Utf8String(contractAddress)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
