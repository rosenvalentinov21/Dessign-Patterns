package XDirectionProject.service;

import XDirectionProject.dto.LifterDTO;
import XDirectionProject.model.lifters.Lifter;

public interface LifterService {
    Lifter addLifter(final Lifter lifter);

    Lifter findLifterByEmail(final String email);

    void deleteLifter(final String lifter);

    Lifter updateLifter(final String email, LifterDTO lifterDTO);
}
