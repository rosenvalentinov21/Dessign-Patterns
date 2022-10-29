package XDirectionProject.service;

import XDirectionProject.model.lifters.Lifter;

public interface LifterService {
    Lifter addLifter(final Lifter lifter);

    Lifter findLifterByEmail(final String email);

    void deleteLifter(final Lifter lifter);
}
