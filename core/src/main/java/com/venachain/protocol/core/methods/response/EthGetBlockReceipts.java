package com.venachain.protocol.core.methods.response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;

import com.venachain.protocol.ObjectMapperFactory;
import com.venachain.protocol.core.Response;

/**
 * eth_getBlockReceipts.
 */
public class EthGetBlockReceipts extends Response<List<TransactionReceipt>> {

    public Optional<List<TransactionReceipt>> getAllReceipts() {
        return Optional.ofNullable(getResult());
    }

    public static class ResponseDeserialiser extends JsonDeserializer<List<TransactionReceipt>> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public List<TransactionReceipt> deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext) throws IOException {

            List<TransactionReceipt> receipts = new ArrayList<>();
            JsonToken nextToken = jsonParser.nextToken();

            if (nextToken == JsonToken.START_OBJECT || nextToken == JsonToken.START_ARRAY) {
                Iterator<TransactionReceipt> transactionIterator =
                        objectReader.readValues(jsonParser, TransactionReceipt.class);
                while (transactionIterator.hasNext()) {
                    receipts.add(transactionIterator.next());
                }
            }

            return receipts;
        }
    }
}
