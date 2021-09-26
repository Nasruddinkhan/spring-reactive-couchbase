package com.mypracice.couchbase.routeendpoints;

import com.mypracice.couchbase.dto.EmployeeDto;
import com.mypracice.couchbase.handler.EmployeeHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@AllArgsConstructor
@Slf4j
public class EmployeeRouterConfig {

    @RouterOperations(value = {
            @RouterOperation(path = "/employee",
                    beanClass = EmployeeHandler.class,
                    beanMethod = "getEmployeeDetails",
                    method = RequestMethod.GET,

                    operation = @Operation(
                            summary = "Trigger an exception",
                            parameters = {@Parameter(in = ParameterIn.QUERY, name = "offset", required = true),
                                    @Parameter(in = ParameterIn.QUERY, name = "limit", required = true)},
                            responses = @ApiResponse(
                                    responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(
                                            array = @ArraySchema(arraySchema = @Schema(implementation = EmployeeDto.class))
                                    ))

                    )
            )}
    )
    @Bean
    public RouterFunction<ServerResponse> routes(EmployeeHandler employeeHandler){
       log.info("EmployeeRouterConfig.serverResponseRouterFunction");
        return RouterFunctions.route(RequestPredicates.GET("/employee")
                        .and(accept(MediaType.APPLICATION_JSON)),
                employeeHandler::getEmployeeDetails);
    }
}
