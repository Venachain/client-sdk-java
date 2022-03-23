package com.venachain.ens;

import com.venachain.tx.ChainId;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContractsTest {

    @Test
    public void testResolveRegistryContract() {
        assertThat(Contracts.resolveRegistryContract(ChainId.MAINNET + ""), is(Contracts.MAINNET));
        assertThat(Contracts.resolveRegistryContract(ChainId.ROPSTEN + ""), is(Contracts.ROPSTEN));
        assertThat(Contracts.resolveRegistryContract(ChainId.RINKEBY + ""), is(Contracts.RINKEBY));
    }

    @Test(expected = EnsResolutionException.class)
    public void testResolveRegistryContractInvalid() {
        Contracts.resolveRegistryContract(ChainId.NONE + "");
    }
}
