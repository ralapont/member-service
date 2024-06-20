package com.example.members.feign.client;

import com.example.members.feign.dtos.State;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(name="state-service", url="http://localhost:8081")
public interface StateServiceProxy {

    @GetMapping(value = "/api/states/{codigo}")
    State getState(@PathVariable("codigo") String codigo);

    @GetMapping(value = "/api/states")
    List<State> getState();

}
