package fragnito.U5W1D5.repositories;

import fragnito.U5W1D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
    boolean existsByEmailOrUsername(String email, String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
