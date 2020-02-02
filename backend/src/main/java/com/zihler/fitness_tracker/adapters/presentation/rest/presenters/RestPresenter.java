package com.zihler.fitness_tracker.adapters.presentation.rest.presenters;

import org.springframework.http.ResponseEntity;

public interface RestPresenter<T> {

    ResponseEntity<T> getResponse();

}
