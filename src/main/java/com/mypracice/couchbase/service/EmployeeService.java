package com.mypracice.couchbase.service;

import com.mypracice.couchbase.dto.EmployeeDto;
import reactor.core.publisher.Flux;

public interface EmployeeService {
   Flux<EmployeeDto> findByEmpByParameters(StringBuilder builder);
}
