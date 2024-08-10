package io.adabox.model.base;

public enum MethodType {

    QUERY("query"),
    REQUEST_NEXT("requestNext"),
    SUBMIT_TX("submitTx"),
    EVALUATE_TX("evaluateTx");

    private final String value;

    MethodType(String value) {
        this.value = value;
    }

    public static MethodType convert(String type) {
        for (MethodType methodType : values()) {
            if (methodType.getValue().equals(type)) {
                return methodType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
