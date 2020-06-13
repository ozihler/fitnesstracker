package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import com.zihler.fitness_tracker.domain.values.CreationDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AdminInformationResource {

    private final long instantiationTime;

    @Autowired
    public AdminInformationResource() {
        this.instantiationTime = System.currentTimeMillis();
    }

    @GetMapping("/api/admin/instantiationTime")
    public LocalDateTime instantiationTime() {
        return CreationDateTime.of(this.instantiationTime)
                .toLocalDateTime();
    }

}
