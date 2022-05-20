package com.venachain.protocol.parity;

import java.math.BigInteger;
import java.util.List;

import com.venachain.protocol.core.DefaultBlockParameter;
import com.venachain.protocol.core.Request;
import com.venachain.protocol.core.methods.request.Transaction;
import com.venachain.protocol.parity.methods.request.TraceFilter;
import com.venachain.protocol.parity.methods.response.ParityFullTraceResponse;
import com.venachain.protocol.parity.methods.response.ParityTraceGet;
import com.venachain.protocol.parity.methods.response.ParityTracesResponse;

/**
 * * JSON-RPC Parity traces API request object building factory.
 */
public interface Trace {
    Request<?, ParityFullTraceResponse> traceCall(
            Transaction transaction,
            List<String> traceTypes,
            DefaultBlockParameter blockParameter);

    Request<?, ParityFullTraceResponse> traceRawTransaction(String data, List<String> traceTypes);

    Request<?, ParityFullTraceResponse> traceReplayTransaction(
            String hash, List<String> traceTypes);

    Request<?, ParityTracesResponse> traceBlock(DefaultBlockParameter blockParameter);

    Request<?, ParityTracesResponse> traceFilter(TraceFilter traceFilter);

    Request<?, ParityTraceGet> traceGet(String hash, List<BigInteger> indices);

    Request<?, ParityTracesResponse> traceTransaction(String hash);
}
