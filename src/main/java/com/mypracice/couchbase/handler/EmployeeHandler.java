package com.mypracice.couchbase.handler;

import com.mypracice.couchbase.dto.EmployeeDto;
import com.mypracice.couchbase.helper.EmployeeHelper;
import com.mypracice.couchbase.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EmployeeHandler {
    private EmployeeService employeeService;
    private EmployeeHelper employeeHelper;

    public Mono<ServerResponse> getEmployeeDetails(ServerRequest serverRequest) {
        MultiValueMap<String, String> name = serverRequest.queryParams();
        StringBuilder builder = employeeHelper.getQueryBuilder(name);
        return ServerResponse.ok().body(employeeService.findByEmpByParameters(builder), EmployeeDto.class);
    }


}
