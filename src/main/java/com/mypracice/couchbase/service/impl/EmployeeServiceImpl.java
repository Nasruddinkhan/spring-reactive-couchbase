package com.mypracice.couchbase.service.impl;

import com.mypracice.couchbase.dto.EmployeeDto;
import com.mypracice.couchbase.repository.N1QLEmployeeRepository;
import com.mypracice.couchbase.service.EmployeeService;
import com.mypracice.couchbase.util.MapperUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final N1QLEmployeeRepository n1QLEmployeeRepository;
    private final Sinks.Many<EmployeeDto> sink;

    @Override
    public Flux<EmployeeDto> findByEmpByParameters(StringBuilder builder) {
        return n1QLEmployeeRepository.findAssetByParameters(builder).map(m -> MapperUtil.map(m, EmployeeDto.class)).log()
                .doOnNext(this.sink::tryEmitNext);
    }
}
