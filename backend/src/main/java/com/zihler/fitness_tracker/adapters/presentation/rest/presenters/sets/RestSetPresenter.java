package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.SetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import org.springframework.http.ResponseEntity;

public class RestSetPresenter extends RestPresenter<SetViewModel> implements SetPresenter {

    @Override
    public void present(SetDocument set) {
        this.response = ResponseEntity.ok(new SetViewModel(
                set.getGid().toLong(),
                set.getWeight().value(),
                set.getWeight().unitOfMeasurement().shortname(),
                set.getRepetitions().number(),
                set.getWaitingTime().value(),
                set.getWaitingTime().unitOfTime().shortname()
        ));
    }
}
