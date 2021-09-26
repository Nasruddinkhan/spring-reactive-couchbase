package com.mypracice.couchbase.repository;

import com.couchbase.client.java.query.N1qlQuery;
import com.mypracice.couchbase.domain.Employee;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.data.repository.util.ReactiveWrapperConverters;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@N1qlPrimaryIndexed
public interface N1QLEmployeeRepository extends ReactiveCouchbaseRepository<Employee, String> {
   /* default Flux<Employee> findAssetByParameters(StringBuilder builder) {
       var str = """
                SELECT META(`employee`).id AS _ID, META(`employee`).cas AS _CAS, `employee`.* FROM `employee` order by 1
                """;
        N1qlQuery query = N1qlQuery.simple(str.concat(builder.toString()));
        return ReactiveWrapperConverters
                .toWrapper(getCouchbaseOperations().findByN1QL(query, Employee.class), Flux.class);

    }*/

    default Flux<Employee> findAssetByParameters(StringBuilder builder) {
        N1qlQuery query = N1qlQuery.simple(builder.toString());
        return ReactiveWrapperConverters
                .toWrapper(getCouchbaseOperations().findByN1QL(query, Employee.class), Flux.class);

    }

}
