package com.venachain.protocol.admin.methods.response;

import java.util.List;

import com.venachain.protocol.core.Response;

/**
 * personal_listAccounts.
 */
public class PersonalListAccounts extends Response<List<String>> {
    public List<String> getAccountIds() {
        return getResult();
    }
}
