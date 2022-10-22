package service;

import model.lifters.Powerlifter;

public interface PowerlifterService {
    Powerlifter addPowerlifter(final Powerlifter powerlifter);

    Powerlifter findPowerlifterById(final Long id);

    void deletePowerlifter(final Powerlifter powerlifter);

    double getTotal(Powerlifter powerlifter);
}
