package io.adabox.model.query.response.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.adabox.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Utxo {

    private String txId;
    private Long index;
    private List<Amount> amountList;
    private String datum;
    private String datumHash;

    public static Utxo deserialize(JsonNode arrayNode) {
        Utxo utxo = null;
        Iterator<JsonNode> iterator = arrayNode.iterator();
        String txId = null;
        Long index = null;
        List<Amount> amountList = null;
        String datum = null;
        String datumHash = null;
        while (iterator.hasNext()) {
            JsonNode jsonObject = iterator.next();
            if (jsonObject.has("txId")) {
                txId = jsonObject.get("txId").asText();
            }
            if (jsonObject.has("index")) {
                index = jsonObject.get("index").asLong();
            }
            if (jsonObject.has("value")) {
                amountList = deserializeAmountList(jsonObject.get("value"));
            }
            if (jsonObject.has("datum")) {
                datum = jsonObject.get("datum").asText();
            }
            if (jsonObject.has("datumHash")) {
                datumHash = jsonObject.get("datumHash").asText();
            }
        }
        return new Utxo(txId, index, amountList, datum, datumHash);
    }

    private static List<Amount> deserializeAmountList(JsonNode jsonNode) {
        List<Amount> amountList = new ArrayList<>();
        amountList.add(new Amount("lovelace", jsonNode.get("coins").bigIntegerValue()));
        JsonNode assets = jsonNode.get("assets");
        Iterator<String> iterator = assets.fieldNames();
        while (iterator.hasNext()) {
            String unit = iterator.next();
            amountList.add(new Amount(unit, assets.get(unit).bigIntegerValue()));
        }
        return amountList;
    }
}
