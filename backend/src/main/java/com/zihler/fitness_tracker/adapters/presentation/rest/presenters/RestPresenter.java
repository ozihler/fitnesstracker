package com.zihler.fitness_tracker.adapters.presentation.rest.presenters;

import org.springframework.http.ResponseEntity;

public abstract class RestPresenter<T> {
    protected ResponseEntity<T> response;

    public ResponseEntity<T> getResponse() {
        return response;
    }

}
