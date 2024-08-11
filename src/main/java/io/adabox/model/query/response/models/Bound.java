package io.adabox.model.query.response.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * An era bound which captures the time, slot and epoch at which the era start. The time is relative to the start time
 * of the network.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Bound {

    private Long time;

    private Long slot;

    private Integer epoch;


}