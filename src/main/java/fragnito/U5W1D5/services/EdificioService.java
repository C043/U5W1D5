package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Edificio;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.repositories.EdificioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public void saveEdificio(Edificio edificio) {
        if (edificioRepository.existsByIndirizzoAndNome(edificio.getIndirizzo(), edificio.getNome())) throw new ValidationException("Edificio già esistente.");
        edificioRepository.save(edificio);
        log.info("Edificio {} salvato con successo!", edificio.getNome());
    }

    public Edificio findEdificioById(Long edificioId) {
        return edificioRepository.findById(edificioId).orElseThrow(() -> new NotFoundException(edificioId));
    }

    public void findEdificioByIdAndDelete(Long edificioId) {
        edificioRepository.delete(this.findEdificioById(edificioId));
        log.info("Edificio con id: {} è stato eliminato con successo!", edificioId);
    }

    public void findEdificioByIdAndUpdate(Long edificioId, Edificio updatedEdificio) {
        Edificio found = this.findEdificioById(edificioId);
        found.setCitta(updatedEdificio.getCitta());
        found.setNome(updatedEdificio.getNome());
        found.setIndirizzo(updatedEdificio.getIndirizzo());
        edificioRepository.save(found);
        log.info("Edificio con id: {} aggiornato con successo!", edificioId);
    }
}
