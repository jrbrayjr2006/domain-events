package com.jrbrayjr.spring.cloud.ddd.domainevents.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Builder
@Data
@JsonComponent
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private String firstName;
}
