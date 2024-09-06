package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Postazione;
import fragnito.U5W1D5.enums.TipoPostazione;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.repositories.PostazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public void savePostazione(Postazione postazione) {
        postazioneRepository.save(postazione);
        log.info("Postazione salvata con successo!");
    }

    public Postazione findPostazioneById(Long postazioneId) {
        return postazioneRepository.findById(postazioneId).orElseThrow(() -> new NotFoundException(postazioneId));
    }

    public List<Postazione> filterByTipoPostazioneAndCitta(TipoPostazione tipoPostazione, String citta) {
        List<Postazione> found = postazioneRepository.filterByTipoPostazioneAndCitta(tipoPostazione, citta);
        if (found.isEmpty()) log.info("Nessun elemento trovato.");
        return found;
    }

    public void findPostazioneByIdAndDelete(Long postazioneId) {
        postazioneRepository.delete(this.findPostazioneById(postazioneId));
        log.info("Postazione con id: {} è stata eliminata con successo!", postazioneId);
    }

    public void findPostazioneByIdAndUpdate(Long postazioneId, Postazione updatedPostazione) {
        Postazione found = this.findPostazioneById(postazioneId);
        found.setDescrizione(updatedPostazione.getDescrizione());
        found.setTipoPostazione(updatedPostazione.getTipoPostazione());
        found.setEdificio(updatedPostazione.getEdificio());
        found.setMaxOccupanti(updatedPostazione.getMaxOccupanti());
        postazioneRepository.save(found);
        log.info("Postazione con id: {} è stata aggiornata con successo!", postazioneId);
    }
}
