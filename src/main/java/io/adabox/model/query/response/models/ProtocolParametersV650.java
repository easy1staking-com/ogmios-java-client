package io.adabox.model.query.response.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtocolParametersV650 {

    @Getter
    @Setter
    public static class Amount {
        private Ada ada;
    }

    @Getter
    @Setter
    public static class Ada {
        private Long lovelace;
    }

    @Getter
    @Setter
    public static class Bytes {
        private Long bytes;
    }

    @Getter
    @Setter
    public static class Version {
        private Integer major;
        private Integer minor;
    }

    @Getter
    @Setter
    public static class ScriptExecutionPrices {
        private String memory;
        private String cpu;
    }

    @Getter
    @Setter
    public static class MaxExecutionUnits {
        private Long memory;
        private Long  cpu;
    }

    @Getter
    @Setter
    public static class PlutusCostModels {

        @JsonProperty("plutus:v1")
        private List<Integer> plutusV1;

        @JsonProperty("plutus:v2")
        private List<Integer> plutusV2;

        @JsonProperty("plutus:v3")
        private List<Integer> plutusV3;

    }

    private Integer minFeeCoefficient;

    private Amount minFeeConstant;

    private Bytes maxBlockBodySize;

    private Bytes maxBlockHeaderSize;

    private Bytes maxTransactionSize;

    private Amount stakeCredentialDeposit;

    private Amount stakePoolDeposit;

    private Integer stakePoolRetirementEpochBound;

    private Integer desiredNumberOfStakePools;

    private String stakePoolPledgeInfluence;

    private String monetaryExpansion;

    private String treasuryExpansion;

    private Amount minStakePoolCost;

    private Amount minUtxoDepositConstant;

    private Integer minUtxoDepositCoefficient;

    private PlutusCostModels plutusCostModels;

    private ScriptExecutionPrices scriptExecutionPrices;

    private MaxExecutionUnits maxExecutionUnitsPerTransaction;

    private MaxExecutionUnits maxExecutionUnitsPerBlock;

    private Bytes maxValueSize;

    private Integer collateralPercentage;

    private Integer maxCollateralInputs;

    private Version version;

}
