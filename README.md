# Domain Events (Reactive Non-Blocking)

## Overview

This project provides an example of a non-blocking reactive web application.  

## Testing

Non-blocking reactive spring web can be unit tested without bringing in the entire spring context by specifying the portions of the context needed for testing.  The sample code below illustrates this.

```java
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
```

## References

- [Spring – @WebFluxTest with WebTestClient](https://howtodoinjava.com/spring-webflux/webfluxtest-with-webtestclient/)