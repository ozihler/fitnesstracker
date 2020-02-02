package com.zihler.fitness_tracker.application.outbound_ports.presenters;

import com.zihler.fitness_tracker.application.outbound_ports.documents.SetDocument;

public interface SetPresenter {
    void present(SetDocument set);
}
