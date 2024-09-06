package fragnito.U5W1D5.repositories;

import fragnito.U5W1D5.entities.Postazione;
import fragnito.U5W1D5.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Long> {
    @Query("SELECT p FROM Postazione p WHERE p.tipoPostazione = :tipoPostazione AND p.edificio.citta = :citta")
    List<Postazione> filterByTipoPostazioneAndCitta(TipoPostazione tipoPostazione, String citta);
}
