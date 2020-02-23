package com.jrbrayjr.spring.cloud.ddd.domainevents.controller;

import com.jrbrayjr.spring.cloud.ddd.domainevents.model.PatientDTO;
import com.jrbrayjr.spring.cloud.ddd.domainevents.service.PatientManagementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
public class ClinicController {


    PatientManagementService patientManagementService;

    public ClinicController(PatientManagementService patientManagementService) {
        this.patientManagementService = patientManagementService;
    }

    @PostMapping( value = "/register" )
    public Mono<ServerResponse> registerPatient(PatientDTO patient) {
        Mono<ServerResponse> response = ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromValue("hello"));
        patientManagementService.register(patient);
        return response;
    }
}
