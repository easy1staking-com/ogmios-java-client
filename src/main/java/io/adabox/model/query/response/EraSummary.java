package io.adabox.model.query.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EraSummary {

    @Getter
    @Setter
    @ToString
    public static class EraTime {

        private Time time;

        private Long slot;

        private Integer epoch;

    }

    @Getter
    @Setter
    @ToString
    public static class Time {

        private Long seconds;

    }

    @Getter
    @Setter
    @ToString
    public static class Parameters {

        private Long epochLength;

        private Milliseconds slotLength;

        private Long safeZone;

    }

    @Getter
    @Setter
    @ToString
    public static class Milliseconds {

        private Long milliseconds;

    }

    private EraTime start;

    private EraTime end;

    private Parameters parameters;

}
