package XDirectionProject.repository;


import XDirectionProject.model.lifters.Lifter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LifterRepository extends JpaRepository<Lifter, Long> {

    @Query(value = "SELECT * FROM lifter as l WHERE l.email=:email;", nativeQuery = true)
    Optional<Lifter> findLifterByEmail(String email);
}
