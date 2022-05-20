package com.venachain.protocol.parity.methods.response;

import com.venachain.crypto.WalletFile;
import com.venachain.protocol.core.Response;

/**
 * parity_ExportAccount.
 */
public class ParityExportAccount extends Response<WalletFile> {
    public WalletFile getWallet() {
        return getResult();
    }
}
