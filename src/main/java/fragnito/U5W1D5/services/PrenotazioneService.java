package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Prenotazione;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.repositories.PrenotazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public void savePrenotazione(Prenotazione prenotazione) {
        if (prenotazioneRepository.existsByGiornoAndUtente(prenotazione.getGiorno(), prenotazione.getUtente())) throw new ValidationException("Esiste già una " +
                "prenotazione questo giorno per questo utente");
        prenotazioneRepository.save(prenotazione);
        log.info("Prenotazione per il giorno {} per l'utente {} alla postazione nell'edificio {} avvenuta con successo!", prenotazione.getGiorno(), prenotazione.getUtente(), prenotazione.getPostazione().getEdificio().getNome());
    }
}
