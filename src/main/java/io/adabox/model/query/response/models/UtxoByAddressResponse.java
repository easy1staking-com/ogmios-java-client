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

    private Transaction transaction;

    private Integer index;

    private String address;

    private Map<String, Map<String, Long>> value;

    private String datum;
}
