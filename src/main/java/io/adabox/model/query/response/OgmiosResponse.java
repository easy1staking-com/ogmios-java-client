package io.adabox.model.query.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.adabox.model.Amount;
import io.adabox.model.query.response.models.Point;
import io.adabox.model.query.response.models.ProtocolParametersV650;
import io.adabox.model.query.response.models.Utxo;
import io.adabox.model.query.response.models.UtxoByAddressResponse;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "method", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OgmiosResponse.BlockHeight.class, name = "queryNetwork/blockHeight"),
        @JsonSubTypes.Type(value = OgmiosResponse.ChainTip.class, name = "queryLedgerState/tip"),
        @JsonSubTypes.Type(value = OgmiosResponse.CurrentEpoch.class, name = "queryLedgerState/epoch"),
        @JsonSubTypes.Type(value = OgmiosResponse.CurrentProtocolParameters.class, name = "queryLedgerState/protocolParameters"),
        @JsonSubTypes.Type(value = OgmiosResponse.EraStart.class, name = "queryLedgerState/eraStart"),
        @JsonSubTypes.Type(value = OgmiosResponse.UtxoByAddress.class, name = "queryLedgerState/utxo"),
})
@Slf4j
public abstract class OgmiosResponse<T> {

    public String jsonrpc;

    public String method;

    public String id;

    public abstract T getResult();

    @Setter
    public static class BlockHeight extends OgmiosResponse<Long> {

        public Long result;

        @Override
        public Long getResult() {
            return result;
        }
    }

    public static class ChainTip extends OgmiosResponse<Point> {

        public Point result;

        @Override
        public Point getResult() {
            return result;
        }
    }

    public static class CurrentEpoch extends OgmiosResponse<Long> {

        public Long result;

        @Override
        public Long getResult() {
            return result;
        }

    }

    public static class CurrentProtocolParameters extends OgmiosResponse<ProtocolParametersV650> {

        public ProtocolParametersV650 result;

        @Override
        public ProtocolParametersV650 getResult() {
            return result;
        }

    }

    public static class EraStart extends OgmiosResponse<io.adabox.model.query.response.models.EraStart> {

        public io.adabox.model.query.response.models.EraStart result;

        @Override
        public io.adabox.model.query.response.models.EraStart getResult() {
            return result;
        }
    }

    @ToString
    public static class UtxoByAddress extends OgmiosResponse<List<Utxo>> {

        public List<UtxoByAddressResponse> result;

        @Override
        public List<Utxo> getResult() {
            return result.stream()
                    .map(utxoByAddressResponse -> {
                        List<Amount> amounts = utxoByAddressResponse.getValue().entrySet().stream().map(entry -> {
                                    String policy = entry.getKey(); //ada or policyId
                                    if (policy.equals("ada")) {
                                        return new Amount("lovelace", BigInteger.valueOf(entry.getValue().entrySet().stream().findFirst().get().getValue()));
                                    } else {
                                        Map.Entry<String, Long> asset = entry.getValue().entrySet().stream().findFirst().get();
                                        String unit = policy + asset.getKey();
                                        return new Amount(unit, BigInteger.valueOf(asset.getValue()));
                                    }
                                })
                                .collect(Collectors.toList());
                        return new Utxo(utxoByAddressResponse.getTransaction().getId(), utxoByAddressResponse.getIndex().longValue(), amounts, utxoByAddressResponse.getDatum());
                    })
                    .collect(Collectors.toList());
        }
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
