package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Prenotazione;
import fragnito.U5W1D5.exceptions.NotFoundException;
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
        if (prenotazioneRepository.countPrenotazioni(prenotazione.getGiorno()) == prenotazione.getPostazione().getMaxOccupanti()) throw new ValidationException(
                "Postazione al completo per quel giorno!");
        prenotazioneRepository.save(prenotazione);
        log.info("Prenotazione per il giorno {} per l'utente {} alla postazione nell'edificio {} avvenuta con successo!", prenotazione.getGiorno(), prenotazione.getUtente(), prenotazione.getPostazione().getEdificio().getNome());
    }

    public Prenotazione findPrenotazioneById(Long prenotazioneId) {
        return prenotazioneRepository.findById(prenotazioneId).orElseThrow(() -> new NotFoundException(prenotazioneId));
    }

    public void findPrenotazioneByIdAndDelete(Long prenotazioneId) {
        prenotazioneRepository.delete(this.findPrenotazioneById(prenotazioneId));
        log.info("Prenotazione con id: {} è stata eliminata con successo!", prenotazioneId);
    }
}
