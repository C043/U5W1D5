package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Utente;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.repositories.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public void saveUtente(Utente utente) {
        if (utenteRepository.existsByEmailOrUsername(utente.getEmail(), utente.getUsername())) throw new ValidationException("Esiste già un utente con questa email o " +
                "con questo username");
        if (utente.getNomeCompleto().length() < 2) throw new ValidationException("Nome troppo corto!");
        utenteRepository.save(utente);
        log.info("Utente {} salvato con successo!", utente.getNomeCompleto());
    }

    public Utente findUtenteById(Long utenteId) {
        return utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public void findUtenteByIdAndDelete(Long utenteId) {
        utenteRepository.delete(this.findUtenteById(utenteId));
        log.info("Utente con id: {} eliminato con successo!", utenteId);
    }

    public void findUtenteByIdAndUpdate(Long utenteId, Utente updatedUtente) {
        Utente found = this.findUtenteById(utenteId);
        if (!Objects.equals(found.getEmail(), updatedUtente.getEmail()) && utenteRepository.existsByEmail(updatedUtente.getEmail())) throw new ValidationException(
                "Email già esistente.");
        else found.setEmail(updatedUtente.getEmail());
        if (!Objects.equals(found.getUsername(), updatedUtente.getUsername()) && utenteRepository.existsByUsername(updatedUtente.getUsername()))
            throw new ValidationException(
                    "Username già esistente.");
        else found.setUsername(updatedUtente.getUsername());
        found.setNomeCompleto(updatedUtente.getNomeCompleto());
        utenteRepository.save(found);
        log.info("Utente aggiornato con successo!");
    }
}
