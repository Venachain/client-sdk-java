package com.venachain.protocol.geth;

import java.util.Arrays;
import java.util.Collections;

import com.venachain.protocol.Web3jService;
import com.venachain.protocol.geth.response.PersonalEcRecover;
import com.venachain.protocol.geth.response.PersonalImportRawKey;
import rx.Observable;

import com.venachain.protocol.admin.JsonRpc2_0Admin;
import com.venachain.protocol.admin.methods.response.BooleanResponse;
import com.venachain.protocol.admin.methods.response.PersonalSign;
import com.venachain.protocol.core.Request;
import com.venachain.protocol.core.methods.response.EthSubscribe;
import com.venachain.protocol.core.methods.response.MinerStartResponse;
import com.venachain.protocol.websocket.events.PendingTransactionNotification;
import com.venachain.protocol.websocket.events.SyncingNotfication;

/**
 * JSON-RPC 2.0 factory implementation for Geth.
 */
public class JsonRpc2_0Geth extends JsonRpc2_0Admin implements Geth {

    public JsonRpc2_0Geth(Web3jService web3jService) {
        super(web3jService);
    }
    
    @Override
    public Request<?, PersonalImportRawKey> personalImportRawKey(
            String keydata, String password) {
        return new Request<>(
                "personal_importRawKey",
                Arrays.asList(keydata, password),
                web3jService,
                PersonalImportRawKey.class);
    }

    @Override
    public Request<?, BooleanResponse> personalLockAccount(String accountId) {
        return new Request<>(
                "personal_lockAccount",
                Arrays.asList(accountId),
                web3jService,
                BooleanResponse.class);
    }

    @Override
    public Request<?, PersonalSign> personalSign(
            String message, String accountId, String password) {
        return new Request<>(
                "personal_sign",
                Arrays.asList(message,accountId,password),
                web3jService,
                PersonalSign.class);
    }

    @Override
    public Request<?, PersonalEcRecover> personalEcRecover(
            String hexMessage, String signedMessage) {
        return new Request<>(
                "personal_ecRecover",
                Arrays.asList(hexMessage,signedMessage),
                web3jService,
                PersonalEcRecover.class);
    }

    @Override
    public Request<?, MinerStartResponse> minerStart(int threadCount) {
        return new Request<>(
                "miner_start",
                Arrays.asList(threadCount),
                web3jService,
                MinerStartResponse.class);
    }

    @Override
    public Request<?, BooleanResponse> minerStop() {
        return new Request<>(
                "miner_stop",
                Collections.<String>emptyList(),
                web3jService,
                BooleanResponse.class);
    }

    public Observable<PendingTransactionNotification> newPendingTransactionsNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        "eth_subscribe",
                        Arrays.asList("newPendingTransactions"),
                        web3jService,
                        EthSubscribe.class),
                "eth_unsubscribe",
                PendingTransactionNotification.class
        );
    }

    @Override
    public Observable<SyncingNotfication> syncingStatusNotifications() {
        return web3jService.subscribe(
                new Request<>(
                        "eth_subscribe",
                        Arrays.asList("syncing"),
                        web3jService,
                        EthSubscribe.class),
                "eth_unsubscribe",
                SyncingNotfication.class
        );
    }
}
