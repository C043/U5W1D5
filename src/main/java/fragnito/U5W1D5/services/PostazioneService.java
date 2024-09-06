package fragnito.U5W1D5.services;

import fragnito.U5W1D5.entities.Postazione;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.repositories.PostazioneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
