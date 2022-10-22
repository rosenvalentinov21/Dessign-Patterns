package repository;


import model.lifters.Powerlifter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerlifterRepository extends JpaRepository<Powerlifter, Long> {

}
