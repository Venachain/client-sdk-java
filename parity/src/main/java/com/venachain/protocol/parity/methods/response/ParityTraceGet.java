package com.venachain.protocol.parity.methods.response;

import com.venachain.protocol.core.Response;

/**
 * trace_get.
 */
public class ParityTraceGet extends Response<Trace> {
    public Trace getTrace() {
        return getResult();
    }
}
