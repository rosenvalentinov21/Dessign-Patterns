package XDirectionProject.service.impl;

import XDirectionProject.dto.LifterDTO;
import XDirectionProject.exception.NonExistingEntityException;
import XDirectionProject.model.lifters.Lifter;
import XDirectionProject.repository.LifterRepository;
import XDirectionProject.service.LifterService;
import XDirectionProject.utillity.Constants;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LifterServiceImpl implements LifterService {

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
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
    }

    @Override
    public void deleteLifter(String email) {
        Lifter lifterToBeDeleted = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
        lifterRepository.delete(lifterToBeDeleted);
    }

    @Override
    public Lifter updateLifter(String email, LifterDTO lifterDTO) {
        Lifter lifterToBeUpdated = lifterRepository.findLifterByEmail(email)
                .orElseThrow(() -> new NonExistingEntityException(Constants.NON_EXISTING_ENTITY));
        Lifter updatedLifter = updateLifterDetails(lifterToBeUpdated, lifterDTO);

        return lifterRepository.save(updatedLifter);

    }

    private Lifter updateLifterDetails(Lifter lifter, LifterDTO updateDetails) {
        if (Objects.nonNull(updateDetails.getFirstName()))
            lifter.setFirstName(updateDetails.getFirstName());
        if (Objects.nonNull(updateDetails.getLastName()))
            lifter.setLastName(updateDetails.getLastName());
        if (Objects.nonNull(updateDetails.getEmail())) {
            if (!lifterRepository.existsLifterByEmail(updateDetails.getEmail()))
                lifter.setEmail(updateDetails.getEmail());
        }
        if (updateDetails.getAge() != 0)
            lifter.setAge(updateDetails.getAge());
        if (updateDetails.getHeight() != 0)
            lifter.setHeight(updateDetails.getHeight());
        if (updateDetails.getWeight() != 0)
            lifter.setWeight(updateDetails.getWeight());

        return lifter;
    }
}
