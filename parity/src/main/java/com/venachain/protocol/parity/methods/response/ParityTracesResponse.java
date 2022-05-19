package com.venachain.protocol.parity.methods.response;

import java.util.List;

import com.venachain.protocol.core.Response;

/**
 * trace_block
 * trace_filter
 * trace_transaction.
 */
public class ParityTracesResponse extends Response<List<Trace>> {
    public List<Trace> getTraces() {
        return getResult();
    }
}
