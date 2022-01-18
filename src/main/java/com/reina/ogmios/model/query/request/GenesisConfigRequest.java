package com.reina.ogmios.model.query.request;

import com.reina.ogmios.model.query.QueryType;
import com.reina.ogmios.model.query.request.base.QueryRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GenesisConfigRequest extends QueryRequest {

    private static final QueryType QUERY_TYPE = QueryType.GENESIS_CONFIG;

    public GenesisConfigRequest(long msgId) {
        super(msgId);
    }

    public String getQueryArgs() {
        return "\""+QUERY_TYPE.getValue()+"\"";
    }

    @Override
    public String getMirror() {
        return "\"object\":\""+QUERY_TYPE.getValue()+"\",\"msg_id\":"+getMsgId();
    }
}
