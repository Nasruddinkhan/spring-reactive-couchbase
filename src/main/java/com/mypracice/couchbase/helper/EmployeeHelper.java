package com.mypracice.couchbase.helper;

import com.mypracice.couchbase.constant.QueryConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@Slf4j
public class EmployeeHelper {
    public StringBuilder getQueryBuilder(MultiValueMap<String, String> parameters) {
        StringBuilder builder = new StringBuilder(QueryConstant.GET_ALL_EMPLOYEE);
        builder.append(" ").append("offset").append(" ").append(parameters.getFirst("offset"))
                .append(" ").append("limit").append(" ").append(parameters.getFirst("limit"));
        parameters.forEach((k, v) -> log.info(k + " = " + v));
        log.info(builder.toString());
        return builder;
    }
}
