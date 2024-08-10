package io.adabox.model.query.request.base;

import io.adabox.model.base.Request;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class QueryRequest extends Request {

    protected QueryRequest(long msgId) {
        super(msgId);
    }

    @Override
    public String getMethod() {
        return "";
    }

}
