package org.web3j.precompiled;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint32;
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

public class NodeManagement extends VenachainContract {

    public static final String CONTRACT_ADDRESS = "0x1000000000000000000000000000000000000002";

    public static final String FUNC_ADD = "add";
    public static final String FUNC_UPDATE = "update";
    public static final String FUNC_GETALLNODES = "getAllNodes";
    public static final String FUNC_VALIDJOINNODE = "validJoinNode";
    public static final String FUNC_GETNODES = "getNodes";
    public static final String FUNC_GETDELETEDENODENODES = "getDeletedEnodeNodes";
    public static final String FUNC_GETNORMALENODENODES = "getNormalEnodeNodes";
    public static final String FUNC_NODESNUM = "nodesNum";
    public static final String FUNC_IMPORTOLDNODESDATA = "importOldNodesData";
    public static final String NotifyEventKey = "Notify";

    protected NodeManagement(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NodeManagement load(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NodeManagement("", CONTRACT_ADDRESS, web3j, credentials, contractGasProvider);
    }
    public RemoteCall<TransactionReceipt> add(String nodeInfo) {
        final Function function = new Function(FUNC_ADD,
                Arrays.<Type>asList(new Utf8String(nodeInfo)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_ADD, NotifyEventKey});
    }

    public List<EventResponse> getAddEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_ADD, NotifyEventKey});
    }

    public RemoteCall<TransactionReceipt> update(String nodeName, String nodeInfo) {
        final Function function = new Function(FUNC_UPDATE,
                Arrays.<Type>asList(new Utf8String(nodeName),
                        new Utf8String(nodeInfo)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> updateObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_UPDATE, NotifyEventKey});
    }

    public List<EventResponse> getUpdateEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_UPDATE, NotifyEventKey});
    }

    public RemoteCall<Boolean> validJoinNode(String nodePublicKey) {
        final Function function = new Function(FUNC_VALIDJOINNODE,
                Arrays.<Type>asList(new Utf8String(nodePublicKey)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<String> getNodes(String nodeInfo) {
        final Function function = new Function(FUNC_GETNODES,
                Arrays.<Type>asList(new Utf8String(nodeInfo)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getDeletedEnodeNodes() {
        final Function function = new Function(FUNC_GETDELETEDENODENODES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getNormalEnodeNodes() {
        final Function function = new Function(FUNC_GETNORMALENODENODES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> nodesNum(String nodeInfo) {
        final Function function = new Function(FUNC_NODESNUM,
                Arrays.<Type>asList(new Utf8String(nodeInfo)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> importOldNodesData(String nodeInfo) {
        final Function function = new Function(FUNC_IMPORTOLDNODESDATA,
                Arrays.<Type>asList(new Utf8String(nodeInfo)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> importOldNodesDataObservable(EthFilter filter) {
        return eventObservable(filter, new String[]{FUNC_IMPORTOLDNODESDATA, NotifyEventKey});
    }

    public List<EventResponse> getImportOldNodesDataEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, new String[]{FUNC_IMPORTOLDNODESDATA, NotifyEventKey});
    }

    public RemoteCall<String> getAllNodes() {
        final Function function = new Function(FUNC_GETALLNODES,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
