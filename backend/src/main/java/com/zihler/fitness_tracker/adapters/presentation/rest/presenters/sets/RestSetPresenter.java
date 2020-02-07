package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets;

import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.RestPresenter;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.sets.outputs.FullSetOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.FullSetViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;
import com.zihler.fitness_tracker.application.outbound_ports.presenters.SetPresenter;
import org.springframework.http.ResponseEntity;

public class RestSetPresenter extends RestPresenter<FullSetViewModel> implements SetPresenter {

    @Override
    public void present(SetDocument set) {
        var output = new FullSetOutput(set);

        response = ResponseEntity.ok(output.toViewModel());
    }

}
