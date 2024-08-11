package io.adabox.model.query.response.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class UtxoByAddressResponse {

    @Getter
    @Setter
    @ToString
    public static class Transaction {

        private String id;
    }

    @Getter
    @Setter
    @ToString
    public static class Script {

        private String language;

        private String cbor;

    }

    private Transaction transaction;

    private Integer index;

    private String address;

    private Map<String, Map<String, Long>> value;

    private String datum;

    private String datumHash;

    private Script script;

}
