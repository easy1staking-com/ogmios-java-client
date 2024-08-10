package io.adabox.model.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Slf4j
public abstract class Request extends Message {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class OgmiosRequest {

        private String jsonrpc;

        private String method;

        private String id;

    }

    protected Request(long msgId) {
        super(msgId);
    }

    @Override
    public String toString() {
        OgmiosRequest request = OgmiosRequest.builder()
                .jsonrpc("2.0")
                .id(String.valueOf(getMsgId()))
                .method(getMethod()).build();
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            log.info("jsonRequest: {}", jsonRequest);
            return jsonRequest;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getMethod();

}
