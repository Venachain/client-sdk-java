package com.venachain.protocol.core;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import com.venachain.protocol.core.methods.response.AbiDefinition;
import com.venachain.protocol.core.methods.response.EthBlock;
import com.venachain.protocol.core.methods.response.EthCompileSolidity;
import com.venachain.protocol.core.methods.response.EthLog;
import com.venachain.protocol.core.methods.response.EthSyncing;
import com.venachain.protocol.core.methods.response.Log;
import com.venachain.protocol.core.methods.response.ShhMessages;
import com.venachain.protocol.core.methods.response.Transaction;
import com.venachain.protocol.core.methods.response.TransactionReceipt;

public class EqualsVerifierResponseTest {

    @Test
    public void testBlock() {
        EqualsVerifier.forClass(EthBlock.Block.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransaction() {
        EqualsVerifier.forClass(Transaction.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransactionReceipt() {
        EqualsVerifier.forClass(TransactionReceipt.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testLog() {
        EqualsVerifier.forClass(Log.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSshMessage() {
        EqualsVerifier.forClass(ShhMessages.SshMessage.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSolidityInfo() {
        EqualsVerifier.forClass(EthCompileSolidity.SolidityInfo.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testSyncing() {
        EqualsVerifier.forClass(EthSyncing.Syncing.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testAbiDefinition() {
        EqualsVerifier.forClass(AbiDefinition.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testAbiDefinitionNamedType() {
        EqualsVerifier.forClass(AbiDefinition.NamedType.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testHash() {
        EqualsVerifier.forClass(EthLog.Hash.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testCode() {
        EqualsVerifier.forClass(EthCompileSolidity.Code.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testTransactionHash() {
        EqualsVerifier.forClass(EthBlock.TransactionHash.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testCompiledSolidityCode() {
        EqualsVerifier.forClass(EthCompileSolidity.Code.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testDocumentation() {
        EqualsVerifier.forClass(EthCompileSolidity.Documentation.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @Test
    public void testError() {
        EqualsVerifier.forClass(Response.Error.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}
