package fragnito.U5W1D5;

import com.github.javafaker.Faker;
import fragnito.U5W1D5.entities.Postazione;
import fragnito.U5W1D5.enums.TipoPostazione;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.services.EdificioService;
import fragnito.U5W1D5.services.PostazioneService;
import fragnito.U5W1D5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EdificioService edificioService;

    @Autowired
    private PostazioneService postazioneService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        try {
            postazioneService.savePostazione(new Postazione("Grande spazio aperto per qualsiasi tipo di progetto", TipoPostazione.OPENSPACE,
                    edificioService.findEdificioById(1L)));
            postazioneService.savePostazione(new Postazione("Piccolo ufficio", TipoPostazione.PRIVATO, edificioService.findEdificioById(3L)));
            postazioneService.savePostazione(new Postazione("Grande sala riunioni molto elegante", TipoPostazione.SALA_RIUNIONI, edificioService.findEdificioById(2L)));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }


    }
}
