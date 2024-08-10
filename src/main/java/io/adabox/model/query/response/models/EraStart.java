package io.adabox.model.query.response.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EraStart {

    @Getter
    @Setter
    public static class Time {
        private Long seconds;
    }

    private Time time;

    private Long slot;

    private Integer epoch;


}
