package io.adabox.model.query.response.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Summary of the confirmed parts of the ledger.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EraSummary {

    private Bound start;
    private Bound end;
    private EraParameters parameters;

}
