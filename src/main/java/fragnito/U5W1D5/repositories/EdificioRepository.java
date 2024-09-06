package fragnito.U5W1D5.repositories;

import fragnito.U5W1D5.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    boolean existsByIndirizzoAndNome(String indirizzo, String nome);
}
