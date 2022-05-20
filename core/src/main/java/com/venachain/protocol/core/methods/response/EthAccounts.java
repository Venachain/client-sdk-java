package com.venachain.protocol.core.methods.response;

import java.util.List;

import com.venachain.protocol.core.Response;

/**
 * eth_accounts.
 */
public class EthAccounts extends Response<List<String>> {
    public List<String> getAccounts() {
        return getResult();
    }
}
