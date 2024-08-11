package io.adabox.model.query.request;

import io.adabox.model.query.request.base.QueryRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EraSummariesRequest extends QueryRequest {
    @Override
    public String getMethod() {
        return "queryLedgerState/eraSummaries";
    }

}
