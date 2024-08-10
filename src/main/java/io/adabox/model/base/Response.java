package io.adabox.model.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.adabox.model.query.response.OgmiosResponse;
import io.adabox.util.JsonHelper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class Response extends Message {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public static OgmiosResponse deserialize(String messageJson) {
        try {
            log.info("messageJson: {}", messageJson);

            OgmiosResponse response = new ObjectMapper().readerFor(OgmiosResponse.class).readValue(messageJson);
            log.info("response: {}", response);

            if (response instanceof OgmiosResponse.BlockHeight) {
                Long result = ((OgmiosResponse.BlockHeight)response).result;
                log.info("result: {}", result);
            }


        } catch (JsonProcessingException e) {
            log.warn("Cannot deserialize message. Message does not contain \"reflection\" parameter", e);
        }
        return null;
    }

    public Response(long msgId) {
        super(msgId);
    }
}
