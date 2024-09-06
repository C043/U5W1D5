package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Utente;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public void saveUtente(Utente utente) {
        if (utenteRepository.existsByEmail(utente.getEmail())) throw new ValidationException("Esiste già un utente con questa email");
        if (utente.getNomeCompleto().length() < 2) throw new ValidationException("Nome troppo corto!");
        utenteRepository.save(utente);
        log.info("Utente {} salvato con successo!", utente.getNomeCompleto());
    }

    public Utente findUtenteById(Long utenteId) {
        return utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }
}
