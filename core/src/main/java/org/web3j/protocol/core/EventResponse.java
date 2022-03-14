package org.web3j.protocol.core;

import org.web3j.protocol.core.methods.response.Log;

import java.math.BigInteger;

public class EventResponse {
    private Log log;
    private BigInteger code;
    private String msg;

    public EventResponse() {
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
