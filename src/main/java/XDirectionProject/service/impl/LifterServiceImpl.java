package XDirectionProject.service.impl;

import XDirectionProject.exception.NonExistingEntityException;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.repository.LifterRepository;
import XDirectionProject.service.LifterService;
import org.springframework.stereotype.Service;

@Service
public class LifterServiceImpl implements LifterService {

    private final String NON_EXISTING_ENTITY = "The entity does not exist";

    private LifterRepository lifterRepository;

    public LifterServiceImpl(LifterRepository lifterRepository) {
        this.lifterRepository = lifterRepository;
    }

    @Override
    public Lifter addLifter(Lifter lifter) {
        return lifterRepository.save(lifter);
    }

    @Override
    public Lifter findLifterByEmail(String email) {
        return lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(NON_EXISTING_ENTITY));
    }

    @Override
    public void deleteLifter(Lifter lifter) {
        lifterRepository.delete(lifter);
    }
}
