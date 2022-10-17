package repository;


import model.lifters.Powerlifter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerlifterRepository extends CrudRepository<Powerlifter, Long> {

}
