package io.adabox.model.query.request;

import io.adabox.model.query.request.base.QueryRequest;
import io.adabox.model.query.request.base.QueryType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RewardsProvenanceRequest extends QueryRequest {

    private static final QueryType QUERY_TYPE = QueryType.REWARDS_PROVENANCE;

    public String getQueryArgs() {
        return "\""+QUERY_TYPE.getValue()+"\"";
    }

    public String getMirror() {
        return "\"object\":\""+QUERY_TYPE.getValue()+"\",\"msg_id\":"+getMsgId();
    }
}
