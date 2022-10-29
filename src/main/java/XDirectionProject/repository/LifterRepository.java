package XDirectionProject.repository;


import XDirectionProject.model.lifters.Lifter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LifterRepository extends JpaRepository<Lifter, Long> {

    Optional<Lifter> findLifterByEmail(String email);
}
