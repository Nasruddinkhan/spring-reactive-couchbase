package com.mypracice.couchbase.constant;

public class QueryConstant {
    private QueryConstant(){}
    public static final String GET_ALL_EMPLOYEE =  """
                SELECT META(`employee`).id AS _ID, META(`employee`).cas AS _CAS, `employee`.* FROM `employee` order by 1  
                """;
}
