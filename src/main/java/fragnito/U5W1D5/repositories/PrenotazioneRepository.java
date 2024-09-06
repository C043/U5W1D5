package fragnito.U5W1D5.repositories;

import fragnito.U5W1D5.entities.Prenotazione;
import fragnito.U5W1D5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByGiornoAndUtente(LocalDate localDate, Utente utente);
}
