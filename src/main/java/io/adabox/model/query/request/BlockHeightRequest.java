package io.adabox.model.query.request;

import io.adabox.model.query.request.base.QueryRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BlockHeightRequest extends QueryRequest {

    @Override
    public String getMethod() {
        return "queryNetwork/blockHeight";
    }

}
