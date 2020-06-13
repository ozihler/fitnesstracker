package com.zihler.fitness_tracker.adapters.presentation.rest.presenters;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.exceptions.ResponseUndefinedException;
import org.springframework.http.ResponseEntity;

public abstract class RestPresenter<T> {
    protected ResponseEntity<T> response;

    public ResponseEntity<T> getResponse() {
        if (response == null) {
            throw new ResponseUndefinedException("The response was not yet defined. Call 'present' on the respective presenter");
        }

        return response;
    }

}
