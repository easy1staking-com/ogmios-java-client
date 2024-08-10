package io.adabox.model.query.request;

import io.adabox.model.query.request.base.QueryRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UtxoByAddressRequest extends QueryRequest {

    @Getter
    @Setter
    public static class Addresses {

        private List<String> addresses;

    }

    private final String address;

    @Override
    public String getMethod() {
        return "queryLedgerState/utxo";
    }

    public UtxoByAddressRequest(String address) {
        this.address = address;
    }

    @Override
    protected Object getParams() {
        Addresses addresses = new Addresses();
        addresses.setAddresses(List.of(address));
        return addresses;
    }
}
