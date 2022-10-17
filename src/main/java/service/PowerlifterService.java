package service;

import exception.NonExistingEntityException;
import model.lifters.Powerlifter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PowerlifterRepository;

@Service
public class PowerlifterService {

    private final PowerlifterRepository powerlifterRepository;

    @Autowired
    public PowerlifterService(final PowerlifterRepository powerlifterRepository) {
        this.powerlifterRepository = powerlifterRepository;
    }

    Powerlifter savePowerlifter(final Powerlifter powerlifter) {
        return powerlifterRepository.save(powerlifter);
    }

    Powerlifter findPowerlifterById(final Long id) {
        return powerlifterRepository.findById(id).orElseThrow(() -> new NonExistingEntityException("No powerlifter with the given id was found"));
    }

    Powerlifter updatePowerlifter(final Powerlifter powerlifter) {
        return powerlifterRepository.save(powerlifter);
    }

    void deletePowerlifter(final Powerlifter powerlifter) {
        powerlifterRepository.delete(powerlifter);
    }
}
