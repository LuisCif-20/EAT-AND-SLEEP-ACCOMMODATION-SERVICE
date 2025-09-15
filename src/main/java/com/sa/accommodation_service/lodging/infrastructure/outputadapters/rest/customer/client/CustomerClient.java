package com.sa.accommodation_service.lodging.infrastructure.outputadapters.rest.customer.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path = "/api/v1.0/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    public void getCustomerById(@PathVariable UUID id);
    
}
