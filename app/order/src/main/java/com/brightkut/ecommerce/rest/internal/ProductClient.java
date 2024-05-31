package com.brightkut.ecommerce.rest.internal;

import com.brightkut.ecommerce.exception.BusinessException;
import com.brightkut.ecommerce.order.dto.PurchaseRequest;
import com.brightkut.ecommerce.rest.internal.model.PurchaseResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requests, headers);

        ResponseEntity<List<PurchaseResponse>> responseEntity = restTemplate.exchange(
                productUrl+"/purchases",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<PurchaseResponse>>() {}
        );

        if(responseEntity.getStatusCode().isError()) {
            throw new BusinessException("An error occurred while processing the product purchases: "+responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }
}
