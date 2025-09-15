package com.sa.accommodation_service.lodging.infrastructure.outputadapters.rest.customer;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.sa.accommodation_service.common.infrastructure.annotations.OutputAdapter;
import com.sa.accommodation_service.lodging.application.outputports.rest.ExistsCustomerById;
import com.sa.accommodation_service.lodging.infrastructure.outputadapters.rest.customer.client.CustomerClient;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@OutputAdapter
public class CustomerApi implements ExistsCustomerById {

    private final CustomerClient customerClient;

    @Override
    public boolean existsById(UUID id) {
        try {
            customerClient.getCustomerById(id);
            return true;
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                return false;
            }
            throw e;
        }
    }

}
