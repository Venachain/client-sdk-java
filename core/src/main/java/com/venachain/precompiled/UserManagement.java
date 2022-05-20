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

public class UserManagement extends VenachainContract {

    public static final String CONTRACT_ADDRESS = "0x1000000000000000000000000000000000000001";

    public static final String FUNC_SETSUPERADMIN = "setSuperAdmin";
    public static final String FUNC_TRANSFERSUPERADMINBYADDRESS = "transferSuperAdminByAddress";

    public static final String FUNC_ADDCHAINADMINBYADDRESS = "addChainAdminByAddress";
    public static final String FUNC_ADDCHAINADMINBYNAME = "addChainAdminByName";
    public static final String FUNC_DELCHAINADMINBYADDRESS = "delChainAdminByAddress";
    public static final String FUNC_DELCHAINADMINBYNAME = "delChainAdminByName";

    public static final String FUNC_ADDGROUPADMINBYADDRESS = "addGroupAdminByAddress";
    public static final String FUNC_DELGROUPADMINBYADDRESS = "delGroupAdminByAddress";
    public static final String FUNC_ADDGROUPADMINBYNAME = "addGroupAdminByName";
    public static final String FUNC_DELGROUPADMINBYNAME = "delGroupAdminByName";

    public static final String FUNC_ADDNODEADMINBYADDRESS = "addNodeAdminByAddress";
    public static final String FUNC_DELNODEADMINBYADDRESS = "delNodeAdminByAddress";
    public static final String FUNC_ADDNODEADMINBYNAME = "addNodeAdminByName";
    public static final String FUNC_DELNODEADMINBYNAME = "delNodeAdminByName";

    public static final String FUNC_ADDCONTRACTADMINBYADDRESS = "addContractAdminByAddress";
    public static final String FUNC_DELCONTRACTADMINBYADDRESS = "delContractAdminByAddress";
    public static final String FUNC_ADDCONTRACTADMINBYNAME = "addContractAdminByName";
    public static final String FUNC_DELCONTRACTADMINBYNAME = "delContractAdminByName";

    public static final String FUNC_ADDCONTRACTDEPLOYERBYADDRESS = "addContractDeployerByAddress";
    public static final String FUNC_DELCONTRACTDEPLOYERBYADDRESS = "delContractDeployerByAddress";
    public static final String FUNC_ADDCONTRACTDEPLOYERBYNAME = "addContractDeployerByName";
    public static final String FUNC_DELCONTRACTDEPLOYERBYNAME = "delContractDeployerByName";

    public static final String FUNC_GETROLESBYADDRESS = "getRolesByAddress";
    public static final String FUNC_GETROLESBYNAME = "getRolesByName";
    public static final String FUNC_GETADDRLISTOFROLE = "getAddrListOfRole";

    public static final String FUNC_ADDUSER = "addUser";
    public static final String FUNC_UPDATEUSERDESCINFO = "updateUserDescInfo";

    public static final String FUNC_GETALLUSERS = "getAllUsers";
    public static final String FUNC_GETUSERBYADDRESS = "getUserByAddress";
    public static final String FUNC_GETUSERBYNAME = "getUserByName";

    protected UserManagement(String contractBinary, String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddress, web3j, credentials, contractGasProvider);
    }

    public static UserManagement load(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new UserManagement("", CONTRACT_ADDRESS, web3j, credentials, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> setSuperAdmin() {
        final Function function = new Function(FUNC_SETSUPERADMIN,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> setSuperAdminObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_SETSUPERADMIN);
    }

    public List<EventResponse> getSetSuperAdminEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_SETSUPERADMIN);
    }

    public RemoteCall<TransactionReceipt> transferSuperAdminByAddress(String address) {
        final Function function = new Function(FUNC_TRANSFERSUPERADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> transferSuperAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_TRANSFERSUPERADMINBYADDRESS);
    }

    public List<EventResponse> getTransferSuperAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_TRANSFERSUPERADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addChainAdminByAddress(String address) {
        final Function function = new Function(FUNC_ADDCHAINADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addChainAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCHAINADMINBYADDRESS);
    }

    public List<EventResponse> getAddChainAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCHAINADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addChainAdminByName(String name) {
        final Function function = new Function(FUNC_ADDCHAINADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addChainAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCHAINADMINBYNAME);
    }

    public List<EventResponse> getAddChainAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCHAINADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> delChainAdminByAddress(String address) {
        final Function function = new Function(FUNC_DELCHAINADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delChainAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCHAINADMINBYADDRESS);
    }

    public List<EventResponse> getDelChainAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCHAINADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> delChainAdminByName(String name) {
        final Function function = new Function(FUNC_DELCHAINADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delChainAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCHAINADMINBYNAME);
    }

    public List<EventResponse> getDelChainAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCHAINADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> addGroupAdminByAddress(String address) {
        final Function function = new Function(FUNC_ADDGROUPADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addGroupAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDGROUPADMINBYADDRESS);
    }

    public List<EventResponse> getAddGroupAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDGROUPADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> delGroupAdminByAddress(String address) {
        final Function function = new Function(FUNC_DELGROUPADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delGroupAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELGROUPADMINBYADDRESS);
    }

    public List<EventResponse> getDelGroupAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELGROUPADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addGroupAdminByName(String name) {
        final Function function = new Function(FUNC_ADDGROUPADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addGroupAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDGROUPADMINBYNAME);
    }

    public List<EventResponse> getAddGroupAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDGROUPADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> delGroupAdminByName(String name) {
        final Function function = new Function(FUNC_DELGROUPADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delGroupAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELGROUPADMINBYNAME);
    }

    public List<EventResponse> getDelGroupAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELGROUPADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> addNodeAdminByAddress(String address) {
        final Function function = new Function(FUNC_ADDNODEADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addNodeAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDNODEADMINBYADDRESS);
    }

    public List<EventResponse> getAddNodeAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDNODEADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> delNodeAdminByAddress(String address) {
        final Function function = new Function(FUNC_DELNODEADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delNodeAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELNODEADMINBYADDRESS);
    }

    public List<EventResponse> getDelNodeAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELNODEADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addNodeAdminByName(String name) {
        final Function function = new Function(FUNC_ADDNODEADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addNodeAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDNODEADMINBYNAME);
    }

    public List<EventResponse> getAddNodeAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDNODEADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> delNodeAdminByName(String name) {
        final Function function = new Function(FUNC_DELNODEADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delNodeAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELNODEADMINBYNAME);
    }

    public List<EventResponse> getDelNodeAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELNODEADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> addContractAdminByAddress(String address) {
        final Function function = new Function(FUNC_ADDCONTRACTADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addContractAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCONTRACTADMINBYADDRESS);
    }

    public List<EventResponse> getAddContractAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCONTRACTADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> delContractAdminByAddress(String address) {
        final Function function = new Function(FUNC_DELCONTRACTADMINBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delContractAdminByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCONTRACTADMINBYADDRESS);
    }

    public List<EventResponse> getDelContractAdminByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCONTRACTADMINBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addContractAdminByName(String name) {
        final Function function = new Function(FUNC_ADDCONTRACTADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addContractAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCONTRACTADMINBYNAME);
    }

    public List<EventResponse> addContractAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCONTRACTADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> delContractAdminByName(String name) {
        final Function function = new Function(FUNC_DELCONTRACTADMINBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delContractAdminByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCONTRACTADMINBYNAME);
    }

    public List<EventResponse> getDelContractAdminByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCONTRACTADMINBYNAME);
    }

    public RemoteCall<TransactionReceipt> addContractDeployerByAddress(String address) {
        final Function function = new Function(FUNC_ADDCONTRACTDEPLOYERBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addContractDeployerByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCONTRACTDEPLOYERBYADDRESS);
    }

    public List<EventResponse> getAddContractDeployerByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCONTRACTDEPLOYERBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> delContractDeployerByAddress(String address) {
        final Function function = new Function(FUNC_DELCONTRACTDEPLOYERBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delContractDeployerByAddressObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCONTRACTDEPLOYERBYADDRESS);
    }

    public List<EventResponse> getDelContractDeployerByAddressEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCONTRACTDEPLOYERBYADDRESS);
    }

    public RemoteCall<TransactionReceipt> addContractDeployerByName(String name) {
        final Function function = new Function(FUNC_ADDCONTRACTDEPLOYERBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addContractDeployerByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDCONTRACTDEPLOYERBYNAME);
    }

    public List<EventResponse> getAddContractDeployerByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDCONTRACTDEPLOYERBYNAME);
    }

    public RemoteCall<TransactionReceipt> delContractDeployerByName(String name) {
        final Function function = new Function(FUNC_DELCONTRACTDEPLOYERBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> delContractDeployerByNameObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_DELCONTRACTDEPLOYERBYNAME);
    }

    public List<EventResponse> getDelContractDeployerByNameEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_DELCONTRACTDEPLOYERBYNAME);
    }

    public RemoteCall<String> getRolesByAddress(String address) {
        final Function function = new Function(FUNC_GETROLESBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getRolesByName(String name) {
        final Function function = new Function(FUNC_GETROLESBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getAddrListOfRole(String role) {
        final Function function = new Function(FUNC_GETADDRLISTOFROLE,
                Arrays.<Type>asList(new Utf8String(role)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addUser(String user) {
        final Function function = new Function(FUNC_ADDUSER,
                Arrays.<Type>asList(new Utf8String(user)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> addUserObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_ADDUSER);
    }

    public List<EventResponse> getAddUserEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_ADDUSER);
    }

    public RemoteCall<TransactionReceipt> updateUserDescInfo(String userAddress, String userDescInfo) {
        final Function function = new Function(FUNC_UPDATEUSERDESCINFO,
                Arrays.<Type>asList(new Utf8String(userAddress),
                        new Utf8String(userDescInfo)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public Observable<EventResponse> updateUserDescInfoObservable(EthFilter filter) {
        return eventObservable(filter, FUNC_UPDATEUSERDESCINFO);
    }

    public List<EventResponse> getUpdateUserDescInfoEvents(TransactionReceipt transactionReceipt) {
        return eventResponses(transactionReceipt, FUNC_UPDATEUSERDESCINFO);
    }

    public RemoteCall<String> getAllUsers() {
        final Function function = new Function(FUNC_GETALLUSERS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getUserByAddress(String address) {
        final Function function = new Function(FUNC_GETUSERBYADDRESS,
                Arrays.<Type>asList(new Utf8String(address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getUserByName(String name) {
        final Function function = new Function(FUNC_GETUSERBYNAME,
                Arrays.<Type>asList(new Utf8String(name)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }
}
