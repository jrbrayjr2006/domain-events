package com.jrbrayjr.spring.cloud.ddd.domainevents.controller;

import com.jrbrayjr.spring.cloud.ddd.domainevents.model.PatientDTO;
import com.jrbrayjr.spring.cloud.ddd.domainevents.service.PatientManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@WebFluxTest( controllers = {ClinicController.class})
@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {ClinicController.class, PatientManagementService.class} )
class ClinicControllerTest {

    ClinicController controller;

    PatientManagementService mockPatientManagementService;

    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        mockPatientManagementService = mock(PatientManagementService.class);
        controller = new ClinicController(mockPatientManagementService);
        webTestClient = WebTestClient.bindToController(controller).build();
    }

    @Test
    @DisplayName("Given a patient, when registration is submitted, then patient data is saved")
    void whenPatientSubmitted_thenRegisterPatient() {
        // Given
        PatientDTO patient = PatientDTO.builder().firstName("John").build();
        String endpoint = "/register";

        // When
        webTestClient.post()
                .uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(patient))
                .exchange()
                .expectStatus().isOk();

        // Then
        verify(mockPatientManagementService).register(any(PatientDTO.class));
    }
}