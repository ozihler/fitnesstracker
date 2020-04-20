package com.zihler.fitness_tracker.adapters.presentation.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/admin/instantiationTime")
public class AdminInformationResource {

    private final long instantiationTime;

    @Autowired
    public AdminInformationResource() {
        this.instantiationTime = System.currentTimeMillis();
    }

    @GetMapping
    public long instantiationTime() {
        return this.instantiationTime;
    }
}
