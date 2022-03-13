package com.timmmy.cutomer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .email(customerRegistrationRequest.email())
                .firstname(customerRegistrationRequest.firstname())
                .lastname(customerRegistrationRequest.lastname())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse response = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(response.isFraudster()) {
            restTemplate.postForEntity(
                    "http://localhost:8082/api/v1/notify",
                    new CustomerNotifyRequest(
                            "user is fraudster",
                            customer.getFirstname(),
                            customer.getLastname(),
                            customer.getEmail()
                    ),
                    Void.class
            );
            throw new IllegalStateException("fraudster");
        }
        restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notify",
                new CustomerNotifyRequest(
                        "user success registration",
                        customer.getFirstname(),
                        customer.getLastname(),
                        customer.getEmail()
                ),
                Void.class
        );

    }

}
