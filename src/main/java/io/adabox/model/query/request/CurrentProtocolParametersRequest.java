package io.adabox.model.query.request;

import io.adabox.model.query.request.base.QueryRequest;
import io.adabox.model.query.request.base.QueryType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrentProtocolParametersRequest extends QueryRequest {

    @Override
    public String getMethod() {
        return "queryLedgerState/protocolParameters";
    }
}
