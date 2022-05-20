package com.venachain.venachain.contracts;

import com.venachain.abi.EventEncoder;
import com.venachain.abi.TypeReference;
import com.venachain.abi.datatypes.Event;
import com.venachain.abi.datatypes.Function;
import com.venachain.abi.datatypes.Type;
import com.venachain.abi.datatypes.Utf8String;
import com.venachain.abi.datatypes.generated.Int32;
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
public class NFT extends VenachainContract {
    private static final String ABI = "[{\"name\":\"name\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"symbol\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"balanceOf\",\"inputs\":[{\"name\":\"_owner\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"ownerOf\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"mortgage\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"},{\"name\":\"_to\",\"type\":\"string\"},{\"name\":\"_desc\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"redeem\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"totalSupply\",\"inputs\":[],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"changeNFTData\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"},{\"name\":\"updateJson\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"getMortgageNFTByHolder\",\"inputs\":[{\"name\":\"_address\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"showTokenByIndex\",\"inputs\":[{\"name\":\"pagesize\",\"type\":\"int32\"},{\"name\":\"pagenum\",\"type\":\"int32\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"showRecordById\",\"inputs\":[{\"name\":\"tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"getNFTById\",\"inputs\":[{\"name\":\"tokenId\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"showAllMintersByIndex\",\"inputs\":[{\"name\":\"pagesize\",\"type\":\"int32\"},{\"name\":\"pagenum\",\"type\":\"int32\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"getNFTByMinter\",\"inputs\":[{\"name\":\"_address\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"showTokenOwnerByIndex\",\"inputs\":[{\"name\":\"pagesize\",\"type\":\"int32\"},{\"name\":\"pagenum\",\"type\":\"int32\"}],\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"constant\":\"true\",\"type\":\"function\"},{\"name\":\"mint\",\"inputs\":[{\"name\":\"metadata\",\"type\":\"string\"},{\"name\":\"_to\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"modifyMinter\",\"inputs\":[{\"name\":\"_operation\",\"type\":\"string\"},{\"name\":\"_address\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"transfer\",\"inputs\":[{\"name\":\"_tokenId\",\"type\":\"string\"},{\"name\":\"_price\",\"type\":\"uint64\"},{\"name\":\"_to\",\"type\":\"string\"},{\"name\":\"tx_description\",\"type\":\"string\"}],\"outputs\":[{\"name\":\"\",\"type\":\"int32\"}],\"constant\":\"false\",\"type\":\"function\"},{\"name\":\"transfer\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"string\"}],\"type\":\"event\"},{\"name\":\"mortgage\",\"inputs\":[{\"type\":\"string\"}],\"type\":\"event\"},{\"name\":\"mint\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"string\"}],\"type\":\"event\"},{\"name\":\"redeem\",\"inputs\":[{\"type\":\"string\"}],\"type\":\"event\"},{\"name\":\"modify\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"string\"}],\"type\":\"event\"},{\"name\":\"changeNFT\",\"inputs\":[{\"type\":\"string\"},{\"type\":\"string\"}],\"type\":\"event\"}]";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_OWNEROF = "ownerOf";

    public static final String FUNC_MORTGAGE = "mortgage";

    public static final String FUNC_REDEEM = "redeem";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_CHANGENFTDATA = "changeNFTData";

    public static final String FUNC_GETMORTGAGENFTBYHOLDER = "getMortgageNFTByHolder";

    public static final String FUNC_SHOWTOKENBYINDEX = "showTokenByIndex";

    public static final String FUNC_SHOWRECORDBYID = "showRecordById";

    public static final String FUNC_GETNFTBYID = "getNFTById";

    public static final String FUNC_SHOWALLMINTERSBYINDEX = "showAllMintersByIndex";

    public static final String FUNC_GETNFTBYMINTER = "getNFTByMinter";

    public static final String FUNC_SHOWTOKENOWNERBYINDEX = "showTokenOwnerByIndex";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_MODIFYMINTER = "modifyMinter";

    public static final String FUNC_TRANSFER = "transfer";

    public static final Event TRANSFER_EVENT = new Event("transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event MORTGAGE_EVENT = new Event("mortgage", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event MINT_EVENT = new Event("mint", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event REDEEM_EVENT = new Event("redeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event MODIFY_EVENT = new Event("modify", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event CHANGENFT_EVENT = new Event("changeNFT", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager) {
        super(contractBinary, contractAddressOrName, web3j, transactionManager);
    }

    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials) {
        super(contractBinary, contractAddressOrName, web3j, credentials);
    }

    protected NFT(String contractAddressOrName, Web3j web3j, TransactionManager transactionManager) {
        super(contractAddressOrName, web3j, transactionManager);
    }

    protected NFT(String contractAddressOrName, Web3j web3j, Credentials credentials) {
        super(contractAddressOrName, web3j, credentials);
    }

    @Deprecated
    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractBinary, contractAddressOrName, web3j, credentials, gasPrice, gasLimit);
    }

    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(contractBinary, contractAddressOrName, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NFT(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> name(String _tokenId) {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String nameData(String _tokenId) {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function nameFunction(String _tokenId) {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> symbol(String _tokenId) {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String symbolData(String _tokenId) {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function symbolFunction(String _tokenId) {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> balanceOf(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Utf8String(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String balanceOfData(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Utf8String(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function balanceOfFunction(String _owner) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Utf8String(_owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> ownerOf(String _tokenId) {
        final Function function = new Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String ownerOfData(String _tokenId) {
        final Function function = new Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function ownerOfFunction(String _tokenId) {
        final Function function = new Function(FUNC_OWNEROF, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> mortgage(String _tokenId, String _to, String _desc) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(_to),
                new Utf8String(_desc)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mortgageWithNonce(String _tokenId, String _to, String _desc, BigInteger nonce) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(_to),
                new Utf8String(_desc)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String mortgageData(String _tokenId, String _to, String _desc) {
        final Function function = new Function(
                FUNC_MORTGAGE, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(_to),
                new Utf8String(_desc)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger mortgageGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String _tokenId, String _to, String _desc) throws IOException {
        String ethEstimateGasData = mortgageData(_tokenId, _to, _desc);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> redeem(String _tokenId) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> redeemWithNonce(String _tokenId, BigInteger nonce) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String redeemData(String _tokenId) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(new Utf8String(_tokenId)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger redeemGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String _tokenId) throws IOException {
        String ethEstimateGasData = redeemData(_tokenId);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static String totalSupplyData() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int32>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function totalSupplyFunction() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int32>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> changeNFTData(String _tokenId, String updateJson) {
        final Function function = new Function(
                FUNC_CHANGENFTDATA, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(updateJson)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeNFTDataWithNonce(String _tokenId, String updateJson, BigInteger nonce) {
        final Function function = new Function(
                FUNC_CHANGENFTDATA, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(updateJson)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String changeNFTDataData(String _tokenId, String updateJson) {
        final Function function = new Function(
                FUNC_CHANGENFTDATA, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new Utf8String(updateJson)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger changeNFTDataGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String _tokenId, String updateJson) throws IOException {
        String ethEstimateGasData = changeNFTDataData(_tokenId, updateJson);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<String> getMortgageNFTByHolder(String _address) {
        final Function function = new Function(FUNC_GETMORTGAGENFTBYHOLDER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getMortgageNFTByHolderData(String _address) {
        final Function function = new Function(FUNC_GETMORTGAGENFTBYHOLDER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getMortgageNFTByHolderFunction(String _address) {
        final Function function = new Function(FUNC_GETMORTGAGENFTBYHOLDER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> showTokenByIndex(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String showTokenByIndexData(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function showTokenByIndexFunction(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> showRecordById(String tokenId) {
        final Function function = new Function(FUNC_SHOWRECORDBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String showRecordByIdData(String tokenId) {
        final Function function = new Function(FUNC_SHOWRECORDBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function showRecordByIdFunction(String tokenId) {
        final Function function = new Function(FUNC_SHOWRECORDBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getNFTById(String tokenId) {
        final Function function = new Function(FUNC_GETNFTBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getNFTByIdData(String tokenId) {
        final Function function = new Function(FUNC_GETNFTBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getNFTByIdFunction(String tokenId) {
        final Function function = new Function(FUNC_GETNFTBYID, 
                Arrays.<Type>asList(new Utf8String(tokenId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> showAllMintersByIndex(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWALLMINTERSBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String showAllMintersByIndexData(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWALLMINTERSBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function showAllMintersByIndexFunction(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWALLMINTERSBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> getNFTByMinter(String _address) {
        final Function function = new Function(FUNC_GETNFTBYMINTER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String getNFTByMinterData(String _address) {
        final Function function = new Function(FUNC_GETNFTBYMINTER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function getNFTByMinterFunction(String _address) {
        final Function function = new Function(FUNC_GETNFTBYMINTER, 
                Arrays.<Type>asList(new Utf8String(_address)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<String> showTokenOwnerByIndex(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENOWNERBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public static String showTokenOwnerByIndexData(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENOWNERBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return VenachainUtil.invokeEncode(function);
    }

    public static Function showTokenOwnerByIndexFunction(BigInteger pagesize, BigInteger pagenum) {
        final Function function = new Function(FUNC_SHOWTOKENOWNERBYINDEX, 
                Arrays.<Type>asList(new Int32(pagesize),
                new Int32(pagenum)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return function;
    }

    public RemoteCall<TransactionReceipt> mint(String metadata, String _to) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Utf8String(metadata),
                new Utf8String(_to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mintWithNonce(String metadata, String _to, BigInteger nonce) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Utf8String(metadata),
                new Utf8String(_to)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String mintData(String metadata, String _to) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new Utf8String(metadata),
                new Utf8String(_to)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger mintGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String metadata, String _to) throws IOException {
        String ethEstimateGasData = mintData(metadata, _to);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> modifyMinter(String _operation, String _address) {
        final Function function = new Function(
                FUNC_MODIFYMINTER, 
                Arrays.<Type>asList(new Utf8String(_operation),
                new Utf8String(_address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> modifyMinterWithNonce(String _operation, String _address, BigInteger nonce) {
        final Function function = new Function(
                FUNC_MODIFYMINTER, 
                Arrays.<Type>asList(new Utf8String(_operation),
                new Utf8String(_address)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String modifyMinterData(String _operation, String _address) {
        final Function function = new Function(
                FUNC_MODIFYMINTER, 
                Arrays.<Type>asList(new Utf8String(_operation),
                new Utf8String(_address)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger modifyMinterGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String _operation, String _address) throws IOException {
        String ethEstimateGasData = modifyMinterData(_operation, _address);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public RemoteCall<TransactionReceipt> transfer(String _tokenId, BigInteger _price, String _to, String tx_description) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new com.venachain.abi.datatypes.generated.Uint64(_price),
                new Utf8String(_to),
                new Utf8String(tx_description)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferWithNonce(String _tokenId, BigInteger _price, String _to, String tx_description, BigInteger nonce) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new com.venachain.abi.datatypes.generated.Uint64(_price),
                new Utf8String(_to),
                new Utf8String(tx_description)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransactionWithNonce(function, nonce);
    }

    public static String transferData(String _tokenId, BigInteger _price, String _to, String tx_description) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Utf8String(_tokenId),
                new com.venachain.abi.datatypes.generated.Uint64(_price),
                new Utf8String(_to),
                new Utf8String(tx_description)),
                Collections.<TypeReference<?>>emptyList());
        return VenachainUtil.invokeEncode(function);
    }

    public static BigInteger transferGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String _tokenId, BigInteger _price, String _to, String tx_description) throws IOException {
        String ethEstimateGasData = transferData(_tokenId, _price, _to, tx_description);
        return VenachainUtil.estimateGasLimit(web3j,estimateGasFrom,estimateGasTo,ethEstimateGasData);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public List<MortgageEventResponse> getMortgageEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MORTGAGE_EVENT, transactionReceipt);
        ArrayList<MortgageEventResponse> responses = new ArrayList<MortgageEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MortgageEventResponse typedResponse = new MortgageEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<MortgageEventResponse> mortgageEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, MortgageEventResponse>() {
            @Override
            public MortgageEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MORTGAGE_EVENT, log);
                MortgageEventResponse typedResponse = new MortgageEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<MortgageEventResponse> mortgageEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MORTGAGE_EVENT));
        return mortgageEventObservable(filter);
    }

    public List<MintEventResponse> getMintEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MINT_EVENT, transactionReceipt);
        ArrayList<MintEventResponse> responses = new ArrayList<MintEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            MintEventResponse typedResponse = new MintEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<MintEventResponse> mintEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, MintEventResponse>() {
            @Override
            public MintEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MINT_EVENT, log);
                MintEventResponse typedResponse = new MintEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<MintEventResponse> mintEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINT_EVENT));
        return mintEventObservable(filter);
    }

    public List<RedeemEventResponse> getRedeemEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(REDEEM_EVENT, transactionReceipt);
        ArrayList<RedeemEventResponse> responses = new ArrayList<RedeemEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RedeemEventResponse typedResponse = new RedeemEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RedeemEventResponse> redeemEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RedeemEventResponse>() {
            @Override
            public RedeemEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(REDEEM_EVENT, log);
                RedeemEventResponse typedResponse = new RedeemEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RedeemEventResponse> redeemEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEM_EVENT));
        return redeemEventObservable(filter);
    }

    public List<ModifyEventResponse> getModifyEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(MODIFY_EVENT, transactionReceipt);
        ArrayList<ModifyEventResponse> responses = new ArrayList<ModifyEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ModifyEventResponse typedResponse = new ModifyEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ModifyEventResponse> modifyEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ModifyEventResponse>() {
            @Override
            public ModifyEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(MODIFY_EVENT, log);
                ModifyEventResponse typedResponse = new ModifyEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ModifyEventResponse> modifyEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MODIFY_EVENT));
        return modifyEventObservable(filter);
    }

    public List<ChangeNFTEventResponse> getChangeNFTEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGENFT_EVENT, transactionReceipt);
        ArrayList<ChangeNFTEventResponse> responses = new ArrayList<ChangeNFTEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ChangeNFTEventResponse typedResponse = new ChangeNFTEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChangeNFTEventResponse> changeNFTEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChangeNFTEventResponse>() {
            @Override
            public ChangeNFTEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGENFT_EVENT, log);
                ChangeNFTEventResponse typedResponse = new ChangeNFTEventResponse();
                typedResponse.log = log;
                typedResponse.param1 = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.param2 = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ChangeNFTEventResponse> changeNFTEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGENFT_EVENT));
        return changeNFTEventObservable(filter);
    }

    public static RemoteCall<NFT> deploy(Web3j web3j, Credentials credentials, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFT.class, web3j, credentials, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<NFT> deploy(Web3j web3j, Credentials credentials, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFT.class, web3j, credentials, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    public static RemoteCall<NFT> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NFT.class, web3j, transactionManager, contractGasProvider, contractBinary, ABI, "");
    }

    @Deprecated
    public static RemoteCall<NFT> deploy(Web3j web3j, TransactionManager transactionManager, String contractBinary, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NFT.class, web3j, transactionManager, gasPrice, gasLimit, contractBinary, ABI, "");
    }

    @Deprecated
    public static NFT load(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT(contractBinary, contractAddressOrName, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NFT load(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NFT(contractBinary, contractAddressOrName, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NFT load(String contractBinary, String contractAddressOrName, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NFT(contractBinary, contractAddressOrName, web3j, credentials, contractGasProvider);
    }

    public static NFT load(String contractBinary, String contractAddressOrName, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NFT(contractBinary, contractAddressOrName, web3j, transactionManager, contractGasProvider);
    }

    public static String getDeployData(String contractBinary) {
        return VenachainUtil.deployEncode(contractBinary, ABI);
    }

    public static BigInteger getDeployGasLimit(Web3j web3j, String estimateGasFrom, String estimateGasTo, String contractBinary) throws IOException {
        return VenachainUtil.estimateGasLimit(web3j, estimateGasFrom, estimateGasTo, getDeployData(contractBinary));
    }

    public static class TransferEventResponse {
        public Log log;

        public String param1;

        public String param2;
    }

    public static class MortgageEventResponse {
        public Log log;

        public String param1;
    }

    public static class MintEventResponse {
        public Log log;

        public String param1;

        public String param2;
    }

    public static class RedeemEventResponse {
        public Log log;

        public String param1;
    }

    public static class ModifyEventResponse {
        public Log log;

        public String param1;

        public String param2;
    }

    public static class ChangeNFTEventResponse {
        public Log log;

        public String param1;

        public String param2;
    }
}
