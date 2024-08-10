package io.adabox.model.query.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.adabox.model.query.response.models.Point;
import lombok.extern.slf4j.Slf4j;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "method", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OgmiosResponse.BlockHeight.class, name = "queryNetwork/blockHeight"),
        @JsonSubTypes.Type(value = OgmiosResponse.ChainTip.class, name = "queryLedgerState/tip"),
        @JsonSubTypes.Type(value = OgmiosResponse.CurrentEpoch.class, name = "queryLedgerState/epoch"),
        @JsonSubTypes.Type(value = OgmiosResponse.CurrentProtocolParameters.class, name = "queryLedgerState/protocolParameters"),
})
@Slf4j
public class OgmiosResponse {

    public String jsonrpc;

    public String method;

    public String id;

    public static class BlockHeight extends OgmiosResponse {

        public Long result;

    }

    public static class ChainTip extends OgmiosResponse {

        public Point result;

    }

    public static class CurrentEpoch extends OgmiosResponse {

        public Long result;

    }

    public static class CurrentProtocolParameters extends OgmiosResponse {

        public Long result;

    }

    public static OgmiosResponse deserialize(String messageJson) {
        try {
            log.info("messageJson: {}", messageJson);
            return new ObjectMapper().readerFor(OgmiosResponse.class).readValue(messageJson);
        } catch (JsonProcessingException e) {
            log.warn("Cannot deserialize message.", e);
        }
        return null;
    }

}
