package com.venachain.protocol.core.methods.response;

import com.venachain.protocol.core.Response;

import java.util.List;

/**
 * User: dongqile
 * Date: 2018/11/12
 * Time: 14:19
 */
public class EthPendingTransactions extends Response<List<Transaction>> {
    public List<Transaction> getTransactions() {
        return getResult();
    }
}