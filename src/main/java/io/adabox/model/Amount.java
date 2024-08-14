package io.adabox.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Amount {

    private String unit;
    private BigInteger quantity;
}
