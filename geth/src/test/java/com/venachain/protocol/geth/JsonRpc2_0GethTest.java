package com.venachain.protocol.geth;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.venachain.protocol.ObjectMapperFactory;
import org.junit.Before;
import org.junit.Test;

import com.venachain.protocol.websocket.WebSocketClient;
import com.venachain.protocol.websocket.WebSocketListener;
import com.venachain.protocol.websocket.WebSocketService;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JsonRpc2_0GethTest {

    private WebSocketClient webSocketClient = mock(WebSocketClient.class);

    private WebSocketService webSocketService = new WebSocketService(
            webSocketClient, true
    );

    private Geth geth = Geth.build(webSocketService);

    private final ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

    private WebSocketListener listener;

    @Before
    public void before() throws Exception {
        when(webSocketClient.connectBlocking()).thenReturn(true);

        doAnswer(invocation -> {
            listener = invocation.getArgumentAt(0, WebSocketListener.class);
            return null;
        }).when(webSocketClient).setListener(any());

        doAnswer(invocation -> {
            String message = invocation.getArgumentAt(0, String.class);
            int requestId = getRequestId(message);

            sendSubscriptionConfirmation(requestId);
            return null;
        }).when(webSocketClient).send(anyString());

        webSocketService.connect();
    }


    @Test
    public void testPendingTransactionsNotifications() {
        geth.newPendingTransactionsNotifications();

        verify(webSocketClient).send(matches(
                "\\{\"jsonrpc\":\"2.0\",\"method\":\"eth_subscribe\",\"params\":"
                        + "\\[\"newPendingTransactions\"],\"id\":[0-9]{1,}}"));
    }

    @Test
    public void testSyncingStatusNotifications() {
        geth.syncingStatusNotifications();

        verify(webSocketClient).send(matches(
                "\\{\"jsonrpc\":\"2.0\",\"method\":\"eth_subscribe\","
                        + "\"params\":\\[\"syncing\"],\"id\":[0-9]{1,}}"));
    }

    private int getRequestId(String message) throws IOException {
        JsonNode messageJson = objectMapper.readTree(message);
        return messageJson.get("id").asInt();
    }

    private void sendSubscriptionConfirmation(int requestId) throws IOException {
        listener.onMessage(
                String.format(
                        "{"
                                + "\"jsonrpc\":\"2.0\","
                                + "\"id\":%d,"
                                + "\"result\":\"0xcd0c3e8af590364c09d0fa6a1210faf5\""
                                + "}",
                        requestId));
    }
}