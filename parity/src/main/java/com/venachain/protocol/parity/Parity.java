package com.venachain.protocol.parity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.venachain.protocol.Web3jService;
import com.venachain.protocol.parity.methods.request.Derivation;
import com.venachain.crypto.WalletFile;
import com.venachain.protocol.admin.Admin;
import com.venachain.protocol.admin.methods.response.BooleanResponse;
import com.venachain.protocol.admin.methods.response.NewAccountIdentifier;
import com.venachain.protocol.admin.methods.response.PersonalSign;
import com.venachain.protocol.core.DefaultBlockParameter;
import com.venachain.protocol.core.Request;
import com.venachain.protocol.parity.methods.response.ParityAddressesResponse;
import com.venachain.protocol.parity.methods.response.ParityAllAccountsInfo;
import com.venachain.protocol.parity.methods.response.ParityDefaultAddressResponse;
import com.venachain.protocol.parity.methods.response.ParityDeriveAddress;
import com.venachain.protocol.parity.methods.response.ParityExportAccount;
import com.venachain.protocol.parity.methods.response.ParityListRecentDapps;

/**
 * JSON-RPC Request object building factory for Parity.
 */
public interface Parity extends Admin, Trace {
    static Parity build(Web3jService web3jService) {
        return new JsonRpc2_0Parity(web3jService);
    }

    Request<?, ParityAllAccountsInfo> parityAllAccountsInfo();
    
    Request<?, BooleanResponse> parityChangePassword(
            String accountId, String oldPassword, String newPassword);
    
    Request<?, ParityDeriveAddress> parityDeriveAddressHash(
            String accountId, String password, Derivation hashType, boolean toSave);
    
    Request<?, ParityDeriveAddress> parityDeriveAddressIndex(
            String accountId, String password, List<Derivation> indicesType, boolean toSave);
    
    Request<?, ParityExportAccount> parityExportAccount(String accountId, String password);
    
    Request<?, ParityAddressesResponse> parityGetDappAddresses(String dAppId);
    
    Request<?, ParityDefaultAddressResponse> parityGetDappDefaultAddress(String dAppId);
    
    Request<?, ParityAddressesResponse> parityGetNewDappsAddresses();
    
    Request<?, ParityDefaultAddressResponse> parityGetNewDappsDefaultAddress();
    
    Request<?, ParityAddressesResponse> parityImportGethAccounts(ArrayList<String> gethAddresses);
    
    Request<?, BooleanResponse> parityKillAccount(String accountId, String password);

    Request<?, ParityAddressesResponse> parityListAccounts(
            BigInteger quantity, String accountId, DefaultBlockParameter blockParameter);

    Request<?, ParityAddressesResponse> parityListGethAccounts();
    
    Request<?, ParityListRecentDapps> parityListRecentDapps();
    
    Request<?, NewAccountIdentifier> parityNewAccountFromPhrase(String phrase, String password);
    
    Request<?, NewAccountIdentifier> parityNewAccountFromSecret(String secret, String password);
    
    Request<?, NewAccountIdentifier> parityNewAccountFromWallet(
            WalletFile walletFile, String password);
    
    Request<?, BooleanResponse> parityRemoveAddress(String accountId);
    
    Request<?, BooleanResponse> paritySetAccountMeta(
            String accountId, Map<String, Object> metadata);
    
    Request<?, BooleanResponse> paritySetAccountName(String address, String name);
    
    Request<?, BooleanResponse> paritySetDappAddresses(
            String dAppId, ArrayList<String> availableAccountIds);
    
    Request<?, BooleanResponse> paritySetDappDefaultAddress(String dAppId, String defaultAddress);
    
    Request<?, BooleanResponse> paritySetNewDappsAddresses(ArrayList<String> availableAccountIds);
    
    Request<?, BooleanResponse> paritySetNewDappsDefaultAddress(String defaultAddress);
    
    Request<?, BooleanResponse> parityTestPassword(String accountId, String password);
    
    Request<?, PersonalSign> paritySignMessage(
            String accountId, String password, String hexMessage);
}
