package com.matheuscordeiro.bffageofempiresapi.clients.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;

@FeignClient
@Validated
public interface AgeOfEmpiresApi {
}
