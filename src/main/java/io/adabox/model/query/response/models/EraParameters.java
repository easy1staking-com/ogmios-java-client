package io.adabox.model.query.response.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Parameters that can vary across hard forks.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EraParameters {

    /**
     * An epoch number or length.
     */
    private Long  epochLength;

    /**
     * A slot length, in seconds. Starting from v5.5.4, this can be a floating number. Before v5.5.4, the floating value
     * would be rounded to the nearest second.
     */
    private Long slotLength;

    /**
     * Number of slots from the tip of the ledger in which it is guaranteed that no hard fork can take place.
     * This should be (at least) the number of slots in which we are guaranteed to have k blocks.
     */
    private Long safeZone;

    public static EraParameters deserialize(JsonNode jsonNode) {
        return null;
    }
}
