package com.venachain.protocol.core.methods.response;

import com.venachain.protocol.core.Response;

/**
 * shh_version.
 */
public class ShhVersion extends Response<String> {

    public String getVersion() {
        return getResult();
    }
}
