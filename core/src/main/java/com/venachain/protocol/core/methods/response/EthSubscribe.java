package com.venachain.protocol.core.methods.response;

import com.venachain.protocol.core.Response;

public class EthSubscribe extends Response<String> {
    public String getSubscriptionId() {
        return getResult();
    }
}
