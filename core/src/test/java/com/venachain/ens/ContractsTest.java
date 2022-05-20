package com.venachain.ens;

import org.junit.Test;

import com.venachain.tx.ChainId;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.venachain.ens.Contracts.MAINNET;
import static com.venachain.ens.Contracts.RINKEBY;
import static com.venachain.ens.Contracts.ROPSTEN;
import static com.venachain.ens.Contracts.resolveRegistryContract;

public class ContractsTest {

    @Test
    public void testResolveRegistryContract() {
        assertThat(resolveRegistryContract(ChainId.MAINNET + ""), is(MAINNET));
        assertThat(resolveRegistryContract(ChainId.ROPSTEN + ""), is(ROPSTEN));
        assertThat(resolveRegistryContract(ChainId.RINKEBY + ""), is(RINKEBY));
    }

    @Test(expected = EnsResolutionException.class)
    public void testResolveRegistryContractInvalid() {
        resolveRegistryContract(ChainId.NONE + "");
    }
}
