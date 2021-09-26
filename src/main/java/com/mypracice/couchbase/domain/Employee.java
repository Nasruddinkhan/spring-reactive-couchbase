package com.mypracice.couchbase.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class Employee implements Serializable {
    private static final long serialVersionUID = 42L;
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;

}
