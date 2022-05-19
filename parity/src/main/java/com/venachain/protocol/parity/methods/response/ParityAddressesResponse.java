package com.venachain.protocol.parity.methods.response;

import java.util.ArrayList;

import com.venachain.protocol.core.Response;

/**
 * parity_listAccounts
 * parity_getGetDappAddresses
 * parity_getGetNewDappsAddresses
 * parity_importGethAccounts
 * parity_listGethAccounts.
 */
public class ParityAddressesResponse extends Response<ArrayList<String>> {
    public ArrayList<String> getAddresses() {
        return getResult();
    }
}
