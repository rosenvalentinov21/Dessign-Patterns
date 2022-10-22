package service.impl;

import exception.NonExistingEntityException;
import model.lifters.Powerlifter;
import org.springframework.stereotype.Service;
import repository.PowerlifterRepository;
import service.PowerlifterService;

@Service
public class PowerlifterServiceImpl implements PowerlifterService {
    private final PowerlifterRepository powerlifterRepository;
    private final String NO_POWERLIFTER_FOUND = "No powerlifter with the given id was found";

    public PowerlifterServiceImpl(final PowerlifterRepository powerlifterRepository) {
        this.powerlifterRepository = powerlifterRepository;
    }

    public Powerlifter addPowerlifter(final Powerlifter powerlifter) {
        return powerlifterRepository.findById(powerlifter.getId())
                .orElse(powerlifterRepository.save(powerlifter));
    }

    public Powerlifter findPowerlifterById(final Long id) {
        return powerlifterRepository.findById(id)
                .orElseThrow(() -> new NonExistingEntityException(NO_POWERLIFTER_FOUND));
    }

    public void deletePowerlifter(final Powerlifter powerlifter) {
        powerlifterRepository.delete(powerlifter);
    }


    public double getTotal(Powerlifter powerlifter) {
        return powerlifter.getPowerliftingExercises().getSquatPR()
                + powerlifter.getPowerliftingExercises().getBenchPR()
                + powerlifter.getPowerliftingExercises().getDeadliftPR();
    }

}
